package cs413.brewduino_server.model;

import jssc.*;

/**
 * The Brewduino class handles the communication between the Arduino and the
 * Spring application. The Brewduino class opens communication with the Arduino
 * and is capable of sending and receiving messages to/from the Arduino.
 *
 * This class is based heavily on http://playground.arduino.cc/Interfacing/Java,
 * which provided pretty much all the steps required in order to make this work.
 *
 * @author Aidan O'Grady
 */
public class Brewduino implements SerialPortEventListener{

    private SerialPort motorPort;
    private SerialPort dispensePort;

    public Brewduino() {
        String mPort = "/dev/ttyACM0";
        String dPort = "/dev/ttyACM1";
        motorPort = new SerialPort(mPort);
        dispensePort = new SerialPort(dPort);
        init();
    }

    private void init() {
        try {
            System.out.println("Attempting port " + motorPort.getPortName());
            System.out.println("Port opened: " + motorPort.openPort());
            System.out.println("Params setted: " + motorPort.setParams(9600, 8, 1, 0));
            System.out.println("Attempting port " + dispensePort.getPortName());
            System.out.println("Port opened: " + dispensePort.openPort());
            System.out.println("Params setted: " + dispensePort.setParams(9600, 8, 1, 0));
            motorPort.addEventListener(this);
            dispensePort.addEventListener(this);
            Thread.sleep(1500);
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeString(String msg, SerialPort port) {
        try {
            port.writeBytes(msg.getBytes());
            System.out.println("Outgoing: " + msg + " to " + port.getPortName());
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void brew(Request request) {
        int coffeeSleep = 10000;
        new Thread(() -> {
            try {
                dispensePort.writeInt(1);
                Thread.sleep(coffeeSleep);
                moveMotor(); // Now over sugar
                for(int i = 0; i < request.getSugar(); i++) {
                    dispense();
                }
                moveMotor(); // Now on semi-skimmed milk
                switch(request.getMilk()) {
                    case "no milk":
                        moveMotor(); // Now on skimmed milk
                        moveMotor(); // Now on first syrup
                        break;
                    case "semi-skimmed":
                        dispense();
                        moveMotor(); // Now on skimmed milk
                        moveMotor(); // Now on first syrup
                        break;
                    case "skimmed":
                        moveMotor(); // Now on skimmed milk
                        dispense();
                        moveMotor(); // Now on first syrup
                        break;
                }

                if(request.isVanillaSyrup()) {
                    dispense();
                }
                moveMotor(); // Now on caramel syrup
                if(request.isCaramelSyrup()) {
                    dispense();
                }
                moveMotor();

            } catch (SerialPortException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void moveMotor() throws SerialPortException, InterruptedException {
        int motorSleep = 5000;
        motorPort.writeInt(1);
        Thread.sleep(motorSleep);
    }

    private void dispense() throws SerialPortException, InterruptedException {
        int dispenseSleep = 2500;
        dispensePort.writeInt(1);
        Thread.sleep(dispenseSleep);
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

    }
}


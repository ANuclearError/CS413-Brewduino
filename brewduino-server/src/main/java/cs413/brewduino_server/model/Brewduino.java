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

    private boolean running = false;

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

    public void brew(Request request) {
        if(!running) {
            new Thread(() -> {
                try {
                    running = true;
                    System.out.println("Dispensing coffee");
                    dispenseCoffee();
                    System.out.println("Moving to sugar");
                    moveMotor();
                    moveMotor();

                    for (int i = 0; i < request.getSugar(); i++) {
                        System.out.println("Dispensing sugar");
                        dispenseShot();
                    }
                    System.out.println("Moving to milk");
                    moveMotor(); // Now on semi-skimmed milk

                    if (request.isMilk()) {
                        System.out.println("Dispensing milk");
                        dispenseShot();
                    }
                    System.out.println("Moving to first syrup");
                    moveMotor();

                    if (request.isVanillaSyrup()) {
                        System.out.println("Dispensing first syrup");
                        dispenseShot();
                    }
                    System.out.println("Moving to second syrup");
                    moveMotor();

                    if (request.isVanillaSyrup()) {
                        System.out.println("Dispensing second syrup");
                        dispenseShot();
                    }
                    System.out.println("Moving back to beginning");
                    moveMotor();

                    running = false;
                } catch (SerialPortException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        } else {
            System.out.println("Busy");
        }
    }

    private synchronized void moveMotor() throws SerialPortException, InterruptedException {
        int motorSleep = 10000;
        motorPort.writeString("1\n");
        Thread.sleep(motorSleep);
    }

    private synchronized void dispenseCoffee() throws SerialPortException, InterruptedException {
        int dispenseSleep = 360000;
        dispensePort.writeString("1\n");
        Thread.sleep(dispenseSleep);
    }

    private synchronized void dispenseShot() throws SerialPortException, InterruptedException {
        int dispenseSleep = 25000;
        dispensePort.writeString("2\n");
        Thread.sleep(dispenseSleep);
    }


    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.isRXCHAR()) {//If data is available
            try {
                String dString = dispensePort.readString();
                String mString = motorPort.readString();
                if(dString != null)
                    System.out.println("Dispense port: " + dString);
                if(mString != null)
                    System.out.println("Motor port: " + mString);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    }
}


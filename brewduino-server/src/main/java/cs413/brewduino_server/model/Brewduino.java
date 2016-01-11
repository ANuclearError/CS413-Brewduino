package cs413.brewduino_server.model;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.io.UnsupportedEncodingException;

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

    private SerialPort serialPort;

    public Brewduino() {
        String port = "COM3";
        serialPort = new SerialPort(port);
        System.out.println("Attempting port " + port);
        try {
            System.out.println("Port opened: " + serialPort.openPort());
            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
            serialPort.addEventListener(this);
            Thread.sleep(1500);
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeString(String msg) {
        try {
            serialPort.writeBytes(msg.getBytes());
            System.out.println("Outgoing: " + msg);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.isRXCHAR()) {//If data is available
            try {
                System.out.println("Incoming: " + serialPort.readString());
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    }
}


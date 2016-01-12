#include <Wire.h>
#include <Adafruit_MotorShield.h>
#include "utility/Adafruit_PWMServoDriver.h"

#define LOC_OFF 500

// Create the motor shield object with the default I2C address
Adafruit_MotorShield AFMS = Adafruit_MotorShield(); 
// Or, create it with a different I2C address (say for stacking)
// Adafruit_MotorShield AFMS = Adafruit_MotorShield(0x61); 

// Connect a stepper motor with 200 steps per revolution (1.8 degree)
// to motor port #2 (M3 and M4)
Adafruit_StepperMotor *myMotor = AFMS.getStepper(200, 2);

#include <Servo.h> 

int lastState = 0;
int pos = 0;
String inputString = ""; // a string to hold incoming data
boolean stringComplete = false;
#define strokeMax (100)

void setup() {
    Serial.begin(9600);           // set up Serial library at 9600 bps
    Serial.println("REDY!");

    AFMS.begin();  // create with the default frequency 1.6KHz
    inputString.reserve(200); // Hold 200 bytes for input string

    myMotor->setSpeed(10);  // 10 rpm  
}

void loop() {
    if(inputString == "1" && stringComplete){
        moveMotor();
        inputString = "";
        stringComplete = false;
    }
}

void moveMotor(){
    if(pos < 5){
        Serial.println("MOVE");
        myMotor->step(LOC_OFF, BACKWARD, SINGLE); 
        pos++;
    } else {
        Serial.println("ZERO");
        myMotor->step((LOC_OFF * 5), FORWARD, SINGLE); 
        pos = 0;
    }
    
}
void serialEvent() {
    while (Serial.available()) {
        if(inputString.length() > 50){
            inputString = ""; // If size getting to large reset
        }
        // get the new byte:
        char inChar = (char)Serial.read();
        // add it to the inputString:
        if (inChar == '\n') {
            stringComplete = true;
        } else {
            inputString += inChar;
        }
    }
}

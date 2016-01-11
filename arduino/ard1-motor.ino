/* 
This is a test sketch for the Adafruit assembled Motor Shield for Arduino v2
It won't work with v1.x motor shields! Only for the v2's with built in PWM
control

For use with the Adafruit Motor Shield v2 
---->  http://www.adafruit.com/products/1438
*/


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
Servo shotServo;
#define PIN_SERVO (6)
#define strokeMax (100)

void setup() {
    Serial.begin(9600);           // set up Serial library at 9600 bps
    Serial.println("Motors!");

    AFMS.begin();  // create with the default frequency 1.6KHz
    //AFMS.begin(1000);  // OR with a different frequency, say 1KHz
    pinMode(2, INPUT);   // digital sensor is on digital pin 2
    inputString.reserve(200); // Hold 200 bytes for input string

    myMotor->setSpeed(10);  // 10 rpm  
    shotServo.attach(PIN_SERVO);
}

void loop() {
    Serial.println("Stepper test!");
    if(inputString == "1" && stringComplete){
        moveMotor();
    }
    inputString = "";
    stringComplete = false;
}

void moveMotor(){
    if(pos < 5){
        Serial.println("Moving!");
        myMotor->step(LOC_OFF, BACKWARD, SINGLE); 
        pos++;
    } else {
        Serial.println("Resetting!");
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

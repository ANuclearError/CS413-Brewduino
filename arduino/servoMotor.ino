#include <Stepper.h>
#include <Servo.h>
#include <SoftwareSerial.h>
#include <AFMotor.h>

AF_Stepper Stepper2(200, 2);   // A 200-step-per-revolution motor
int position = 0;
boolean chkString = false;
String inputString = "";         // a string to hold incoming data
boolean stringComplete = false;
int vars[8] = {};

void setup()
{
  // start serial port at 9600 bps and wait for port to open:

  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }


  inputString.reserve(200); // Hold 200 bytes for input string

  pinMode(2, INPUT);   // digital sensor is on digital pin 2
  establishContact();  // send a byte to establish contact until receiver responds
}

void loop()
{
  // if we get a valid byte, read analog ins:
  while (stringComplete) {
    Serial.println(inputString.length());
    if(inputString.length() ==  8){
      parseInput(inputString);
      for(int i = 0; i < 8; i++){
        Stepper2.step(100, BACKWARD, SINGLE);
        Serial.println(vars[i]);
      }
      Serial.println(inputString);
    }
    inputString = "";
    chkString = false;
    stringComplete = false;
  }

}

void establishContact() {
  while (Serial.available() <= 0) {
    Serial.println("Waiting...");   // send an initial string
    delay(300);
  }
}

void parseInput(String s) {
  int sum = 0;
  for(int i = 0; i < (s.length() -1); i++){
    if(isDigit(s.charAt(i))){
      vars[i] = (s.charAt(i) - '0');
      sum += vars[i];
    }
  }
  int chkInt = s.charAt(s.length()) - '0';
  if(sum = chkInt){
    chkString = true;
  }
}

void serialEvent() {
  while (Serial.available()) {
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

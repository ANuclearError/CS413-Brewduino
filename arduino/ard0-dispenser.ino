#include <Servo.h> 

#define PIN_SERVO (9)
#define PIN_RELAY (8)

Servo shotServo;

String inputString = "";         // a string to hold incoming data
boolean stringComplete = false;  // whether the string is complete

void setup() {
  // initialize serial:
  Serial.begin(9600);
  shotServo.attach(PIN_SERVO);
  pinMode(PIN_RELAY, OUTPUT);
  // reserve 200 bytes for the inputString:
  inputString.reserve(200);
  SetStrokePerc(40);
  delay(5000);
  Serial.println("DREDT!");
}

void loop() {
  // print the string when a newline arrives:
  if (stringComplete) {
    if(inputString == "1"){
        Serial.println("Dispensing Coffee!");
        dispenseCoffee();
    } else if (inputString == "2"){
        Serial.println("Dispensing Shot!");
        dispenseShot();
    }
    inputString = "";
    stringComplete = false;
  }
}


void dispenseShot(){
    SetStrokePerc(64);  
    delay(6000);
    SetStrokePerc(40);
    delay(5000);
}

void dispenseCoffee(){
    digitalWrite(PIN_RELAY, HIGH);
    delay(300000);
    digitalWrite(PIN_RELAY, LOW);
    delay(5000);
}


void SetStrokePerc(float strokePercentage)
{
    if ( strokePercentage >= 1.0 && strokePercentage <= 99.0 )
    {
        int usec = 1000 + strokePercentage * ( 2000 - 1000 ) / 100.0 ;
        shotServo.writeMicroseconds( usec );
    }
}
void serialEvent() {
  while (Serial.available()) {
    // get the new byte:
    char inChar = (char)Serial.read();
    // add it to the inputString:
    if (inChar == '\n') {
      stringComplete = true;
    }
    if(!stringComplete) {
      inputString += inChar;
    }
  }
}

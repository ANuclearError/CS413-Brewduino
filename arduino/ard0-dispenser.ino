#include <Servo.h> 
  
Servo shotServo;
#define PIN_SERVO (8)
#define PIN_RELAY (9)
#define strokeMax (99)

boolean stringComplete = false;
String inputString = ""; // a string to hold incoming data

void SetStrokePerc(float strokePercentage)
{
    if ( strokePercentage >= 1.0 && strokePercentage <= 99.0 )
    {
        int usec = 1000 + strokePercentage * ( 2000 - 1000 ) / 100.0 ;
        shotServo.writeMicroseconds( usec );
    }
}
void SetStrokeMM(int strokeReq)
{
    SetStrokePerc( ((float)strokeReq) / strokeMax );
}

 
void setup() 
{ 
    pinMode(PIN_RELAY, OUTPUT);

    inputString.reserve(200); // Hold 200 bytes for input string

    shotServo.attach(PIN_SERVO);
} 
  
 
void loop() 
{
    if(inputString == "1" && stringComplete){
        dispenseCoffee();
        inputString = "";
        stringComplete = false;  
    } else if (inputString == "2" && stringComplete){
        dispenseShot();
        inputString = "";
        stringComplete = false;  
    } 
}

void dispenseShot(){
    SetStrokePerc(63);
    delay(5000);
    SetStrokePerc(40);
    delay(5000);
}

void dispenseCoffee(){
    digitalWrite(PIN_RELAY, HIGH);
    delay(300000);
    digitalWrite(PIN_RELAY, LOW);
    delay(5000);
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

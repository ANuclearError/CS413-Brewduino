#include <Servo.h> 
  
Servo shotServo;
#define PIN_SERVO (8)
#define strokeMax (100)

// Move the actuator to the specified point
void SetStrokePerc(float strokePercentage)
{
  if ( strokePercentage >= 1.0 && strokePercentage <= 99.0 )
  {
    int usec = 1000 + strokePercentage * ( 2000 - 1000 ) / 100.0 ;
    myServo.writeMicroseconds( usec );
  }
}

// Untested
// Move the actuator to a certain height (in mm)
void SetStrokeMM(int strokeReq,int strokeMax)
{
  SetStrokePerc( ((float)strokeReq) / strokeMax );
}

 
void setup() 
{ 
  myServo.attach(PIN_SERVO);
} 
  
 
void loop() 
{ 

// Test move to 60% (Current shot trigger value)
  SetStrokePerc(60);
  delay(3000);
  
// Reset to lowest height
  SetStrokePerc(1);
  delay(3000);
  
// Move to top of stroke 
  SetStrokePerc(99);
  delay(3000);
  
// Reset to lowest height
  SetStrokePerc(1);
  delay(3000);
  
// increment between highest and lowest in increments of 10%
  int d = 10;
  int delayMS = 1500;
  int i = 0;
  for ( i = 1; i < 99; i += d )
  {
    SetStrokePerc(i);
    delay(delayMS);
  }
  for ( i = 99; i > 1;  i -= d )
  {
    SetStrokePerc(i);
    delay(delayMS);
  }
}

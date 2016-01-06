#include <Servo.h> 
  
Servo shotServo;
#define PIN_SERVO (8)
#define strokeMax (100)
 
void SetStrokePerc(float strokePercentage)
{
  if ( strokePercentage >= 1.0 && strokePercentage <= 99.0 )
  {
    int usec = 1000 + strokePercentage * ( 2000 - 1000 ) / 100.0 ;
    shotServo.writeMicroseconds( usec );
  }
}
void SetStrokeMM(int strokeReq,int strokeMax)
{
  SetStrokePerc( ((float)strokeReq) / strokeMax );
}

 
void setup() 
{ 
  shotServo.attach(PIN_SERVO);
} 
  
 
void loop() 
{ 

  SetStrokePerc(60);
  delay(3000);
  SetStrokePerc(1);
  delay(3000);
  SetStrokePerc(99);
  delay(3000);
  
  
  int d = 10;
  int delayMS = 1000;
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

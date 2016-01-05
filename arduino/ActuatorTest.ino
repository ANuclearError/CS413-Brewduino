// the setup function runs once when you press reset or power the board

int actDelay = 1000;
void setup() {
  pinMode(6, OUTPUT);
}

void loop() {
  
  analogWrite(6, 255);
  delayMicroseconds(actDelay);
  analogWrite(6, 255);
  delayMicroseconds(actDelay);
  analogWrite(6, 0);
  delay(2000);
  analogWrite(6, 255);
  delayMicroseconds(actDelay);
  analogWrite(6, 153);
  delayMicroseconds(actDelay);
  analogWrite(6, 0);
  delay(2000);
  analogWrite(6, 255);
  delayMicroseconds(actDelay);
  analogWrite(6, 0);
  delayMicroseconds(actDelay);
  analogWrite(6, 0);
  delay(2000);

}

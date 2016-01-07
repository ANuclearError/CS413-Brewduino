void setup()
{
  pinMode(5,OUTPUT);
}

void loop()
{
  digitalWrite(5, HIGH);
  delay(5000);
  digitalWrite(5, LOW);
  delay(5000);
}

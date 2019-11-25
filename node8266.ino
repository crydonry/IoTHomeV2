#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

#define WIFI_SSID       "iPhonememe"
#define WIFI_PASSWORD   "1234567888"
#define FIREBASE_HOST "iothome-49684.firebaseio.com"
#define FIREBASE_AUTH "XlQi9McPbl9UPY7NTOwJ7ibW2RKZBBVLSPUAD7YS"



const int bluePin = D6;
const int redPin = D7; 
const int greenPin = D8;   


void setup() {
  Serial.begin(115200);


    // configure LED PWM resolution/range and set pins to LOW
  analogWrite(bluePin, 0);
  analogWrite(redPin, 0);
  analogWrite(greenPin, 0);
  
  // Connect to Wi-Fi network with SSID and password
  Serial.println(WiFi.localIP());
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(1500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  Serial.println(WiFi.SSID());
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  
}

void loop() {
Blue();
Red();
Green();
delay(3000);
}
void Red(){
int data = Firebase.getInt("/IoT/LED/red"); 
Serial.println("red = "+ String(data));
analogWrite(redPin, data); 
}
void Blue(){
int data = Firebase.getInt("/IoT/LED/blue");
Serial.println("blue = "+ String(data));
analogWrite(bluePin, data);

}

void Green(){
int data = Firebase.getInt("/IoT/LED/green"); 
Serial.println("green = "+ String(data));
analogWrite(greenPin, data);
}







  

  

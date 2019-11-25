#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

#define WIFI_SSID       "iPhonememe"
#define WIFI_PASSWORD   "1234567888"
#define FIREBASE_HOST "iothome-49684.firebaseio.com"
#define FIREBASE_AUTH "XlQi9McPbl9UPY7NTOwJ7ibW2RKZBBVLSPUAD7YS"

const int Relay1 = D0; // กำหนดขาใช้งาน
const int Relay2 = D1;
const int Relay3 = D2;
const int Relay4 = D3;

void setup() {
  Serial.begin(115200);

  pinMode(Relay1, OUTPUT); 
  pinMode(Relay2, OUTPUT); 
  pinMode(Relay3, OUTPUT); 
  pinMode(Relay4, OUTPUT); 
  
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
  

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  
}

void loop() {
Power();
LowPower();
MidPower();
HighPower();
delay(2000);
}
void Power(){
       int data = Firebase.getInt("/IoT/Fan/power");
       Serial.println("Power = "+ String(data));
       digitalWrite(Relay1, data);

}
void LowPower(){
       int data = Firebase.getInt("/IoT/Fan/lowpower"); 
       Serial.println("LowPower = "+ String(data));
       digitalWrite(Relay2, data);
}

void MidPower(){
 
       int data = Firebase.getInt("/IoT/Fan/midpower");
       Serial.println("MidPower = "+ String(data));
       digitalWrite(Relay3, data);
 
}
void HighPower(){
       int data = Firebase.getInt("/IoT/Fan/highpower");
       Serial.println("HighPower = "+ String(data));
       digitalWrite(Relay4, data);
}







  

  

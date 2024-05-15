# Házi feladat dokumentáció

Feladat azonosítója és címe: Terápiás mobil applikáció, chatbot.

Beadó neve: Liska Tamás és Végh Gerzson

Github repo: https://github.com/Tomi1026/prompt_engineering

## Feladat leírása:
A feladat célja egy egyszerű terápiás chat alkalmazás fejlesztése volt, amely képes kommunikálni a ChatGPT API-val, és megjeleníti a kapott üzeneteket a felhasználói felületen. Liska Tamás feladata az volt, hogy elkészítse az API kommunikációt és az internetes műveleteket, míg Végh Gerzson a felhasználói felület kialakításáért és megjelenítéséért volt felelős.

## Megoldás körülményei:
A csapat két tagja a feladatok felosztása alapján párhuzamosan dolgozott a projekt megvalósításán. Liska Tamás elkészítette az API kommunikációt Retrofit segítségével, implementálta a hálózati műveleteket és a válaszok feldolgozását. Végh Gerzson létrehozta a felhasználói felületet Compose segítségével, kialakította az üzenetek megjelenítését és a felhasználói interakciókat.

## Megoldás:
A chat alkalmazás két fő részből áll: az API kommunikáció és a felhasználói felület. Az API kommunikáció felelős a külső szerverrel való kommunikációért, az üzenetek küldéséért és fogadásáért. A felhasználói felület felelős az üzenetek megjelenítéséért és a felhasználó interakcióinak kezeléséért.

kotlin

    // Példa az API kommunikációra Kotlin nyelven
    suspend fun sendMessageToServer(message: String) {
        // Küldjük el az üzenetet a szervernek
    }

    // Példa a felhasználói felületre Compose keretrendszerrel
    @Composable
    fun ChatScreen() {
        // Felhasználói felület megvalósítása
    }

## Érdekes tanulságok:
A projekt során számos érdekes tanulságot szereztünk. Például megtanultuk, hogyan kell hatékonyan kommunikálni a ChatGPT API-val, hogyan lehet felhasználni a Retrofit és a Compose keretrendszereket, és hogyan lehet ezeket integrálni egy valós idejű alkalmazásba. Emellett megértettük a párhuzamos munkavégzés fontosságát és a csapatmunka előnyeit.

## Liska Tamás által használt promptok

https://chat.openai.com/share/97fa8d91-2425-4d42-b920-8611eca37aa4

## Végh Gerzson által használt promptok



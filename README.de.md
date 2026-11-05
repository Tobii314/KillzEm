# KillzEm

Language: [English](README.md) | **Deutsch**

---

# KillzEm
Mein erstes Projekt aus dem Jahr 2024.  
Ein Clicker-Arcade-Spiel mit Elementen zur Verbesserung und Progression, entwickelt in Java mit dem 2D-Framework **libGDX**. Das Projekt enthält ein Gegnersystem, unterschiedliche Waffenmechaniken, Einschränkungen und Eigenschaften des Helden, ein System zur Verbesserung von Werten sowie eine benutzerdefinierte Speicherung in Textdateien.

##  Projektziel
Dieses Projekt entstand als universitäre Studienarbeit (Pet-Project), um praktische Kenntnisse in Java und Hilfstechnologien (wie dem libGDX-Framework, Gradle und GitHub) zu zeigen. Zudem ging es um die Interaktion mit dem Dateisystem und die manuelle Verwaltung von Spielzuständen sowie Grafiken.

##  Technologie-Stack
* **Programmiersprache:** Java
* **Spiele-Engine / Framework:** libGDX (Scene2D, SpriteBatch, Stage, Viewport)
* **Grafik und Benutzeroberfläche:** `Texture`, `BitmapFont`, libGDX UI (`ProgressBar`, `Skin`, `ImageButton`, `Table`)
* **Audio:** libGDX Audio (`Music`, `Sound`)
* **Datenverwaltung:** Java I/O (`BufferedReader`, `FileHandle`), reguläre Ausdrücke (`java.util.regex`)

##  Architektur und Design-Muster
Während der Entwicklung wurden folgende Muster und architektonische Ansätze angewendet:
1. **Builder-Muster (Builder):**
    * Implementiert in der Klasse `Enemy.java` (`EnemyBuilder`). Ermöglicht die flexible Erstellung von Gegnern mit optionalen Parametern (Schild, Regeneration, Gift-/Frostresistenz).
2. **Einfache Fabrik (Simple Factory / Factory Method):**
    * Implementiert in `AddEnemys.java`. Kapselt die Erstellung von `Enemy`-Objekten für verschiedene Stufen (Tier 1 bis 5).
3. **Zustandsbasierte Bildschirmnavigation (State-based Screens):**
    * Die Spielbildschirme (`MainMenuScreen`, `GameScreen`, `SettingsScreen`, `GameParametersScreen`) implementieren das `Screen`-Interface und trennen die Spielzustände mit ihren Lebenszyklen (`show`, `render`, `pause`, `dispose`).
4. **Globale Konfiguration:**
    * Die Klassen `ConfigurationFields` und `ParametersFields` dienen als zentraler Zugriffspunkt für globale Audio-Einstellungen, Geld und Spielerwerte.

##  Analyse der Datenspeicherung (Serialisierung / Deserialisierung)
Obwohl kein standardmäßiges `java.io.Serializable` verwendet wurde, gibt es eine **selbstgeschriebene, manuelle Text-Serialisierung und -Deserialisierung**:
* **Lesen (Deserialisierung):** In `KillzEm.java` werden Daten mit einem `BufferedReader` aus `data/mob.txt`, `data/config.txt` und `data/parameter.txt` eingelesen. Zahlenwerte werden mithilfe von regulären Ausdrücken (`Pattern` / `Matcher`) aus den Zeilen extrahiert.
* **Schreiben (Serialisierung):** Die Methode `dispose()` speichert aktuelle Werte, die Lautstärke und den Status besiegter Gegner über `FileHandle.writeString()` ab.

##  Erkannte Schwachstellen im Code
Nach einer Code-Überprüfung zwei Jahre später wurden einige verbesserungswürdige und riskante Stellen im Code festgestellt:
1. **Monolithischer `GameScreen`:**
    * Diese Klasse übernimmt zu viele Aufgaben gleichzeitig: Sie steuert die Game-Loop, mehrere `Stage`-Objekte, Schadensberechnungen, Debuff-Timer, die Liste der Kugeln und die UI-Anzeigen.
2. **Fehlende Fehlerbehandlung (Exception Handling):**
    * In den Spielbildschirmen und beim Parsen fehlen `try-catch`-Blöcke fast komplett.
    * Beim Laden von Konfigurationen in `KillzEm.java` wird nur ein einfaches `e.printStackTrace()` oder `RuntimeException` verwendet. Es gibt keine Datenvalidierung vor `Float.parseFloat()` oder `Integer.parseInt()`, was bei beschädigten Dateien zu Abstürzen (`NumberFormatException`, `NullPointerException`) führen kann.
3. **Feste Indizes in der Benutzeroberfläche (Magic Indices):**
    * Das Abrufen von UI-Elementen über Indizes wie `listOfParameters.get(4)` hängt von der Reihenfolge ab. Bei Änderungen am Layout führt dies schnell zu Fehlern (`IndexOutOfBoundsException`).
4. **Ressourcenverwaltung und Garbage Collector:**
    * Häufige Aufrufe von `new Random()` innerhalb der `render()`-Schleife belasten den Garbage Collector unnötig.
5. **Fehlende automatisierte Tests:**
    * In `GameScreen.java` sind Schadensberechnungen, Schilde und kritische Treffer direkt mit der Benutzeroberfläche verknüpft, was automatische Tests erschwert.

##  Zukunftsaussichten und Verbesserungen
Mit dem heutigen Wissen könnte das Projekt durch folgende Schritte verbessert werden:
### 1. Sauberer Code (Clean Architecture) und Fehlerbehandlung
* Die Spiellogik in reine POJO-Services (`CombatService`, `EnemyManager`) auslagern und komplett von libGDX trennen.
* Sicheres Parsen mit Standardwerten (Fallback values) bei beschädigten Dateien sowie eine saubere Fehlerprotokollierung anstelle von `e.printStackTrace()` einführen.
* Feste UI-Indizes durch benannte Variablen ersetzen.
### 2. Backend-Integration (Spring Boot + SQL / JPA)
* Eine REST-API mit **Spring Boot** entwickeln, um Spielstände und globale Statistiken zu speichern.
* **Spring Data JPA** und eine Datenbank (MySQL oder PostgreSQL) nutzen, um Speicherstände, Parameter und Gegnerkonfigurationen anstelle von lokalen Textdateien zu verwalten.
* Einen globalen Fehlerbehandler (`@ControllerAdvice` / `@ExceptionHandler`) einrichten, um dem Client saubere HTTP-Statuscodes und verständliche Fehlermeldungen zurückzugeben.
* Ergebnisse vom libGDX-Client über HTTP-Anfragen (`Gdx.net`) an das Backend senden.
### 3. Automatisierte Tests (Java Testing)
* Die Spiellogik mit Unit-Tests (**JUnit 5**, **Mockito**) absichern.
* Negativ-Tests schreiben, um das Verhalten bei ungültigen oder beschädigten Daten zu prüfen.
* Integrationstests für REST-Controller und Repositories mit `@DataJpaTest` und Testcontainers schreiben.
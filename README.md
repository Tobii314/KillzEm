# KillzEm

Language: **English** | [Deutsch](README.de.md)

---

# KillzEm
My first pet project, created in 2024.  
A clicker-arcade game with progression and upgrade elements, developed in Java using the 2D framework **libGDX**. The project features an enemy progression system (tiers with health shields, regeneration, and resistances), various weapon mechanics (freeze, poison), hero limitations and features, a stat upgrade system, and custom persistent saving.

##  Project Goal
This project was created as a university coursework pet project to demonstrate skills in working with Java and auxiliary technologies (such as the libGDX framework, Gradle, and GitHub), interacting with the file system, and manually managing game states and graphic resources.

##  Tech Stack
* **Programming Language:** Java
* **Game Engine / Framework:** libGDX (Scene2D, SpriteBatch, Stage, Viewport)
* **Graphics & UI:** `Texture`, `BitmapFont`, libGDX UI (`ProgressBar`, `Skin`, `ImageButton`, `Table`)
* **Audio:** libGDX Audio (`Music`, `Sound`)
* **Data Management:** Java I/O (`BufferedReader`, `FileHandle`), Regular Expressions (`java.util.regex`)

##  Architecture Analysis & Design Patterns
The following patterns and architectural approaches were used during development:
1. **Builder Pattern:**
    * Implemented in `Enemy.java` (`EnemyBuilder`). Allows flexible construction of enemy objects with optional parameters (shield, regeneration, poison/freeze resistance).
2. **Simple Factory / Factory Method:**
    * Implemented in `AddEnemys.java`. Encapsulates the creation of `Enemy` objects for various tiers (1st to 5th).
3. **State-based Screen Navigation:**
    * Game screens (`MainMenuScreen`, `GameScreen`, `SettingsScreen`, `GameParametersScreen`) implement the `Screen` interface, organizing isolated states with their own lifecycles (`show`, `render`, `pause`, `dispose`).
4. **Global Configuration:**
    * The `ConfigurationFields` and `ParametersFields` classes serve as a single point of access for global audio settings, money, and player parameters.

##  Data Persistence Analysis (Serialization/Deserialization)
Although standard `java.io.Serializable` was not used, the project implements a **custom manual text-based serialization and deserialization**:
* **Reading (Deserialization):** In `KillzEm.java`, data is read from `data/mob.txt`, `data/config.txt`, and `data/parameter.txt` using `BufferedReader`. Numeric values are extracted from strings using regular expressions (`Pattern` / `Matcher`).
* **Writing (Serialization):** The `dispose()` method saves current stats, volume, and killed mobs state via `FileHandle.writeString()` with number formatting.

##  Identified Code Weaknesses
Reviewing the code 2 years later, several weak and vulnerable areas were identified:
1. **Monolithic `GameScreen`:**
    * Although many specific methods were created, this class is heavily overloaded with responsibilities: it simultaneously manages the game loop, multiple `Stages`, damage calculations, debuff timers, the bullet list, and UI overlays.
2. **Lack of Robust Exception Handling:**
    * Game screens and parsing logic completely lack `try-catch` blocks.
    * Reading configurations in `KillzEm.java` relies only on a basic `e.printStackTrace()` or wrapping in a `RuntimeException`. Data validation before calling `Float.parseFloat()` or `Integer.parseInt()` is missing, making the app vulnerable to crashes (`NumberFormatException`, `NullPointerException`) if text files are corrupted.
3. **Hardcoded UI Magic Indices:**
    * Retrieving UI elements by indices like `listOfParameters.get(4)` relies strictly on their addition order. This makes the UI fragile and prone to `IndexOutOfBoundsException` when layouts change.
4. **Resource Management & Garbage Collector Pressure:**
    * Frequent calls to `new Random()` inside the `render()` game loop create unnecessary garbage collection pressure.
5. **Lack of Automated Testing:**
    * In `GameScreen.java`, damage mathematics, shield logic, and critical hit calculations are tightly coupled with the UI/presentation layer, making automated verification difficult.

##  Roadmap & Potential Improvements
With current knowledge, the following steps could improve the project:
### 1. Clean Architecture, Error Handling, & General Refactoring
* Extract combat math, regeneration, and damage calculations into clean POJO services (`CombatService`, `EnemyManager`), fully isolating them from the libGDX framework.
* Implement safe data parsing with fallback values (defaults) for file corruption cases and a centralized logging system with proper exception handling instead of `e.printStackTrace()`.
* Remove UI magic indices by using clear named fields.
### 2. Backend Integration (Spring Boot + SQL / JPA)
* Develop a REST API using **Spring Boot** for saving progress and global statistics.
* Set up **Spring Data JPA** and a database (via MySQL or PostgreSQL) to manage save slots, parameters, and enemy configurations instead of local `txt` files.
* Implement a global backend error handler (`@ControllerAdvice` / `@ExceptionHandler`) to return clean HTTP statuses and understandable error messages to the client.
* Send results from the libGDX client via HTTP requests (`Gdx.net`).
### 3. Automated Testing (Java Testing)
* Cover combat mathematics and business logic with unit tests (**JUnit 5**, **Mockito**).
* Write negative tests to verify application behavior when invalid or corrupted data is passed.
* Write integration tests for backend REST controllers and repositories using `@DataJpaTest` and Testcontainers.
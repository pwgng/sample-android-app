# Sample Android App

A basic Android Hello World application built with Kotlin.

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/sampleandroidapp/
│   │   │   └── MainActivity.kt       # Main entry point
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml # Hello World layout
│   │   │   └── values/
│   │   │       ├── strings.xml
│   │   │       ├── colors.xml
│   │   │       └── themes.xml
│   │   └── AndroidManifest.xml
│   ├── test/                         # Unit tests
│   └── androidTest/                  # Instrumented tests
└── build.gradle.kts
```

## Tech Stack

- **Language:** Kotlin
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 34 (Android 14)
- **Build System:** Gradle with Kotlin DSL
- **UI:** ConstraintLayout + Material Components

## Getting Started

1. Clone the repo
2. Open in Android Studio
3. Run on emulator or device

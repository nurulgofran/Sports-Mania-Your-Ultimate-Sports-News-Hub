# Sports Mania

Sports Mania is an Android sports news application built as a bachelor final-year project. This repository contains the Java Android client together with a cleanup pass focused on repository hygiene, safer local configuration, and a more professional package structure.

## Features

- Browse the latest sports news and category feeds
- Search articles and open detailed news pages
- Save favorite news locally
- Toggle dark mode and data-saving preferences
- Load remote app settings and text labels from the backend API

## Local Setup

1. Clone the repository.
2. Copy the local config template:

```sh
cp app-config.properties.example app-config.properties
```

3. Update `app-config.properties` if you want to point the app at a different backend or use your own ad / notification service IDs.
4. Build from the terminal:

```sh
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
./gradlew assembleDebug
```

5. Or open the project in Android Studio and sync Gradle.

## Project Structure

- `app/src/main/java/com/nurul/sportmania/ui`: activities, fragments, adapters, and UI behaviors
- `app/src/main/java/com/nurul/sportmania/data`: local storage, preferences, remote API access, and models
- `app/src/main/java/com/nurul/sportmania/util`: shared utility helpers
- `app/src/main/java/com/nurul/sportmania/ads`: ad-loading helper
- `Docs/`: reports and presentation material from the original project

## Configuration

The project reads environment-specific values from `app-config.properties`, Gradle properties, or environment variables.

- `SPORTSMANIA_BASE_URL`
- `SPORTSMANIA_API_PATH_PREFIX`
- `SPORTSMANIA_ADMOB_APP_ID`
- `SPORTSMANIA_ADMOB_BANNER_ID`
- `SPORTSMANIA_ADMOB_INTERSTITIAL_ID`
- `SPORTSMANIA_ONESIGNAL_APP_ID`
- `SPORTSMANIA_ONESIGNAL_GOOGLE_PROJECT_NUMBER`

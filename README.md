# GeoStack
An easy software stack for location-aware mobile apps

## Prerequisites
You need the following setup on your machine.

### DB
- PostgreSQL 9.4
- PostGIS

### Server
- Java 8
- Maven 3

### Client
- Android Studio
- Android SDK

## Configuration
Follow instructions [here](https://developers.google.com/cloud-messaging/android/client) to generate API keys for GCM and a `google-services.json` configuration file.
Follow instructions [here](https://developers.google.com/maps/documentation/android-api/) to generate API keys for Google Maps.
Put your credentials in the following configuration files.

### DB
- Run the `geo-db/geo.sql` script in a PostGIS database.

### Server
- Put the database connection information in `geo-backend/src/main/resources/application.properties`.
- Put the GCM server key in `geo-backend/src/main/resources/application.properties`.

### Client
- Move your `google-services.json` configuration file to `geo-android/GeoApp/app/`.
- Put the Google Maps API key in `geo-android\GeoApp\app\src\main\res\values\google_maps_api.xml`.
- Put the remote server connection information in `geo-android\GeoApp\app\src\main\res\values\remote_server.xml`.


## Execution
All three tiers are easy to run.

### DB
- Run the PostgreSQL server equipped with the PostGIS extension as any PostgreSQL instance.

### Server
- Run `geo-backend/src/main/java/com/zenika/back/Application.java` as a Spring Boot application.

### Client
- Run `/geo-android/GeoApp/app/src/main/java/com/zenika/geoapp/MainActivity.java` as an Android application.

# Star Battle

Project includes both client and server code.

## Client

### Java Client

To start the Java client: `./gradlew run` or use Tasks -> Application -> Run in the IntelliJ Gradle window

Code located in: `src/main/java/starb/client`

### HTML/CSS/Javascript client.  

Start server (see below) first, then open browser and go to:

http://localhost:3390/client/index.html

HTML/JS/CSS files located under: `static/client`

Port number is configured in: `src/main/resources/application.properties`

## Server

To start the server:  `./gradlew bootRun` or use Tasks -> application -> bootRun in the
IntelliJ Gradle window.

## Tests

Tests are located in `src/test/java`.  To execute tests using IntelliJ,
use the green arrow.  To execute using Gradle run:

`./gradlew test`
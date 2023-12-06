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

## User Documentation: How to Start and Play Star Battles

* Create Docker Container: While in the terminal of the main project folder type:  “docker compose up -d”. 
* Perform BootRun: Click the Gradle icon in IntelliJ and click starb-copernicus-2023f then Tasks, then application then bootRun.
* BootRun: populates the Database with Initial Data and keeps it running. 
* Client Server: You can use BootRun to run the DataBase or you can stop BootRun and start using StarbClient to run the server when the server has seed Data from BootRun.
* Perform Run:  In application click Run to run game. If the Server is going you will be able to start playing.

### Play Game: After Set Up
* Try to solve the Puzzle: 

* Side Panel: There are 3 buttons in the Side Panel of the Game Scene: Menu, View Solution, and Auto Mark type. Menu takes you to the Menu Scene, View Solution takes you to a scene with the Solution to the Puzzle and a link back to Menu, and Auto Mark Type changes between Auto Mark and Manual mark.
* Playing with Auto Mark: If you click a space once it will be a star and will fill all illegal placements and forbid you from clicking on an illegal space.
* Playing with Manual: If you click a space once it will be a star, twice a dot and three times the space becomes empty.

* Menu: In the menu scene there will be three buttons on the scene. The ‘Puzzle’ button takes you to the page where you can go back to previous puzzles that the user solved. The ‘Play’ button takes you to the game scene where you will start off from the latest puzzle that the user needs to solve to go to the next level. The ‘Profile’ button takes you to the Profile scene where the information about the user is displayed, information like user id, title, how many puzzle solves etc. it also has a menu button to take you back to the menu scene.

* Puzzles button Scene:  This scene has buttons of the levels of puzzle you have completed, and has the button of the puzzle you are currently solving. Clicking any of these buttons will take you to the puzzle but it wouldn’t affect the user level. There is also a Menu button to take you back to the menu scene.




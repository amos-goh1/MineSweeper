# Java MineSweeper

A console-based **MineSweeper** game implemented in **Java** 

## Requirements

- Java 17 or higher
- Maven 3.6+

## Project Structure
```
game/
├── pom.xml
├── src/
│ ├── main/java/com/projects/game
│ │ └── MineSweeper.java # Main entry point
│ └── test/java/com/projects/game
│ └── MinesweeperTest.java # Unit tests (JUnit 5)
```

# How to run the game
1. Open a terminal (command prompt) and navigate to the project root directory 
2. Compile and run the game

```
mvn compile
mvn exec:java
```

# How to run the tests
1. Open a terminal (command prompt) and navigate to the project root directory 
2. Type mvn test
```
mvn test
```

# How to play
1. The game will prompt for grid size. Allowable size is 2 to 9.
2. The game will prompt for number of mines. Maximum amount of mines are 35% of the total squares.
3. Reveal squares by Row-Column coordinates (e.g. B3)
4. Objective is to reveal squares and avoid mines. Game ends when:
- You hit a mine (lose)
- You uncover all non-mine squares (win)

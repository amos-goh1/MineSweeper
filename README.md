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

## How to run the game
1. Open a terminal (command prompt) and navigate to the project root directory 
2. Compile and run the game

```
mvn compile
mvn exec:java
```

## How to run the tests
1. Open a terminal (command prompt) and navigate to the project root directory 
2. Type mvn test
```
mvn test
```

## How to play
1. The game will prompt for grid size. Allowable size is 2 to 9.
2. The game will prompt for number of mines. Maximum amount of mines are 35% of the total squares.
3. Reveal squares by Row-Column coordinates (e.g. B3)
4. Objective is to reveal squares and avoid mines. Game ends when:
- You hit a mine (lose)
- You uncover all non-mine squares (win)

### Success example
```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
4
Enter the number of mines to place on the grid (maximum is 5, 35% of the total squares): 
2

Here is your minefield:
  1 2 3 4 
A _ _ _ _ 
B _ _ _ _ 
C _ _ _ _ 
D _ _ _ _ 

Select a square to reveal (e.g. A1): a1
This square contains 0 adjacent mine(s).

Here is your updated minefield:
  1 2 3 4 
A 0 0 0 0 
B 0 0 1 1 
C 0 1 2 _ 
D 0 1 _ _ 

Select a square to reveal (e.g. A1): d4

Here is your updated minefield:
  1 2 3 4 
A 0 0 0 0 
B 0 0 1 1 
C 0 1 2 * 
D 0 1 * 2 
Congratulations, you have won the game!

Press any key to play again... (type 'end' to stop) 
```

### Failure example
```
Welcome to Minesweeper!

Enter the size of the grid (e.g. 4 for a 4x4 grid): 
4
Enter the number of mines to place on the grid (maximum is 5, 35% of the total squares): 
3

Here is your minefield:
  1 2 3 4 
A _ _ _ _ 
B _ _ _ _ 
C _ _ _ _ 
D _ _ _ _ 

Select a square to reveal (e.g. A1): a1
This square contains 1 adjacent mine(s).

Here is your updated minefield:
  1 2 3 4 
A 1 _ _ _ 
B _ _ _ _ 
C _ _ _ _ 
D _ _ _ _ 

Select a square to reveal (e.g. A1): a2
This square contains 1 adjacent mine(s).

Here is your updated minefield:
  1 2 3 4 
A 1 1 _ _ 
B _ _ _ _ 
C _ _ _ _ 
D _ _ _ _ 

Select a square to reveal (e.g. A1): b1
Oh no, you detonated a mine! Game over.

Press any key to play again... (type 'end' to stop) 
```
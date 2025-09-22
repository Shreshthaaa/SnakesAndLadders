# Snake and Ladders Game

## Overview

This is a console-based Snake and Ladders game implemented in Java, following SOLID principles and using design patterns where appropriate. The game supports:

* Dynamic board size (n x n)
* Multiple dice
* Human and bot players
* Auto-generated snakes and ladders with cycle prevention
* Interactive turn-based gameplay with real-time board visualization
* Special rules like extra turns on 6, 3 consecutive 6s penalty, kick-outs, and exact winning cell rule

## How to Run

1. Clone the repository.
2. Compile all `.java` files in the `snakesandladders` package.
3. Run the `Main` class.
4. Input board size, dice count, number of players, and number of human players.

```bash
javac snakesandladders/*.java
java snakesandladders.Main
```

## Game Rules

* Players start at position 1.
* Roll dice and move forward by the sum.
* Landing on a snake head → slide down to tail.
* Landing on a ladder bottom → climb up to top.
* Rolling a 6 → extra turn.
* Rolling 3 consecutive 6s → movement canceled and sent back to start.
* Landing on occupied space → existing player gets kicked back to start.
* Must land exactly on the final cell to win.

## Classes and Design

### Class Overview

* `Main`: Entry point, handles setup and starts the game.
* `Board`: Manages board size, snakes, ladders, and prints the board.
* `DiceSet`: Represents multiple dice and handles dice rolling.
* `Player`: Abstract class for players, with `HumanPlayer` and `BotPlayer` subclasses.
* `PlayerFactory`: Factory pattern to create human or bot players.
* `BotStrategy`: Interface for bot behavior, implemented by `RandomBotStrategy`.
* `GameEngine`: Core game loop, handles turn-taking, winning logic.
* `RuleHandler`: Encapsulates game rules like extra turn, 3 consecutive sixes, kick-outs, snakes and ladders.
* `Entity`: Abstract class for `Snake` and `Ladder`.

### UML Diagram

```
+----------------+        uses        +----------------+
|     Main       |------------------->|    GameEngine  |
+----------------+                    +----------------+
| +main(args)    |                    | -board: Board  |
+----------------+                    | -dice: DiceSet |
                                       | -players: List |
                                       | -ruleHandler   |
                                       +----------------+
                                               |
                                               v
                                      +-----------------+
                                      |   RuleHandler   |
                                      +-----------------+
                                      | -board          |
                                      | -players        |
                                      +-----------------+
                                      | +applyRules()   |
                                      +-----------------+
                                               ^
                                               |
           +----------------+       +----------------+
           |     Board      |       |     DiceSet    |
           +----------------+       +----------------+
           | -size           |       | -count         |
           | -snakes         |       | -sides         |
           | -ladders        |       | -rand          |
           +----------------+       +----------------+
           | +generateRandomEntities() | +roll()      |
           | +getNextPosition()       |             |
           | +printBoardWithPlayers() |             |
           +----------------+                       |
                                                    v
                                       +-----------------+
                                       |    Player       |
                                       +-----------------+
                                       | -name           |
                                       | -position       |
                                       | -consecutiveSixes|
                                       +-----------------+
                                       | +waitForTurn()  |
                                       +-----------------+
                                       /\
                                      /  \
                       +-----------------+   +-----------------+
                       | HumanPlayer     |   | BotPlayer       |
                       +-----------------+   +-----------------+
                       | +waitForTurn()  |   | +waitForTurn()  |
                       +-----------------+   +-----------------+
                                               | strategy: BotStrategy
                                               +-----------------+

+------------------+
| PlayerFactory     |
+------------------+
| +createHuman()    |
| +createBot()      |
+------------------+

+------------------+
| Entity           |
+------------------+
| -from            |
| -to              |
+------------------+
| +getDestination() |
| +describe()      |
+------------------+
       /\
      /  \
+----------+  +-----------+
| Snake    |  | Ladder    |
+----------+  +-----------+
| +describe()| | +describe()|
+-----------+ +------------+

+------------------+
| BotStrategy       |
+------------------+
| +takeTurn()       |
+------------------+
       ^
       |
+------------------+
| RandomBotStrategy |
+------------------+
| +takeTurn()       |
+------------------+
```

This UML shows the major classes, their relationships, and which classes use others. `RuleHandler` centralizes all rules so that `GameEngine` only handles the game flow. Bots implement strategies, humans just wait for input. Board manages entities and visualizes player positions.

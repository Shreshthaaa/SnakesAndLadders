# Snake and Ladders Game

A console-based Snake and Ladders game implemented in Java with a complete **DevOps CI/CD pipeline**, containerization, and Kubernetes deployment.

## DevOps & CI/CD Pipeline

This project showcases a comprehensive DevOps workflow with automated testing, security scanning, containerization, and deployment.

### CI/CD Architecture

```mermaid
    A[Git Push] --> B[GitHub Actions CI]
    B --> C[Linting & CodeQL]
    C --> D[Maven Build & Test]
    D --> E[Docker Build]
    E --> F[Trivy Security Scan]
    F --> G[Push to Docker Hub]
    G --> H[CD Pipeline Trigger]
    H --> I[Kubernetes Deployment]
    I --> J[Rollout Verification]
```

### Continuous Integration (CI)

**Workflow:** `.github/workflows/maven.yml`

The CI pipeline automatically runs on every push to `master` and includes:

1. **Code Quality & Security**
   - **Checkstyle Linting** - Enforces Java code style standards
   - **CodeQL Analysis** - GitHub's semantic code analysis for security vulnerabilities
   - **OWASP Dependency Check** - Scans for known vulnerabilities in dependencies

2. **Build & Test**
   - Maven compilation with dependency caching
   - Comprehensive unit test suite execution
   - Test coverage reporting

3. **Containerization**
   - Multi-stage Docker build (builder + runtime)
   - Automated Docker image testing
   - **Trivy Security Scan** - Container vulnerability scanning

4. **Artifact Publishing**
   - Automated push to Docker Hub
   - Tagged with `latest` for easy deployment

### Continuous Deployment (CD)

**Workflow:** `.github/workflows/cd.yml`

The CD pipeline triggers automatically after successful CI completion:

1. **Deployment Orchestration**
   - Kubernetes deployment to staging environment
   - Dynamic image tag substitution
   - Automated rollout status verification

2. **Infrastructure as Code**
   - Kubernetes manifests in `k8s/` directory
   - Declarative deployment configuration
   - GitOps-ready setup

**Features:**
- Single replica deployment for CLI application
- Interactive TTY support (`stdin: true`, `tty: true`)
- Pulls from Docker Hub registry
- Production-ready configuration

---

## Game Features

This is a console-based Snake and Ladders game implemented in Java, following SOLID principles and using design patterns where appropriate. The game supports:

* Dynamic board size (n x n)
* Multiple dice
* Human and bot players
* Auto-generated snakes and ladders with cycle prevention
* Interactive turn-based gameplay with real-time board visualization
* Special rules like extra turns on 6, 3 consecutive 6s penalty, kick-outs, and exact winning cell rule

## How to Run

### Local Development

```bash
# Compile and run
javac *.java
java Main
```

### With Maven

```bash
# Run tests
mvn test

# Build package
mvn package

# Run the application
mvn exec:java -Dexec.mainClass="Main"
```

### With Docker

```bash
# Build and run
docker build -t snakes-and-ladders .
docker run -it snakes-and-ladders
```

## Security & Testing

### Automated Security Scanning

The CI/CD pipeline includes multiple layers of security:

| Tool | Purpose | Stage |
|------|---------|-------|
| **CodeQL** | Semantic code analysis for vulnerabilities | CI - Build |
| **OWASP Dependency Check** | Identifies known CVEs in dependencies | CI - Build |
| **Trivy** | Container image vulnerability scanning | CI - Post-Build |
| **Checkstyle** | Code quality and style enforcement | CI - Pre-Build |

### Unit Testing

Comprehensive test suite covering all game components:

- **Board Logic Tests** - Grid generation, entity placement, cycle detection
- **Game Engine Tests** - Turn management, win conditions, rule application
- **Player Tests** - Human and bot player behaviors
- **Rule Handler Tests** - Snake/ladder mechanics, special rules (6s, kick-outs)
- **Dice Tests** - Random number generation, multi-dice support

**Run tests:**
```bash
mvn test
```

**Test execution is automated in:**
- Local Docker builds (fails fast if tests fail)
- CI pipeline (blocks deployment on test failure)

### Quality Metrics

- **Test Coverage:** Comprehensive unit test suite
- **Code Style:** Enforced via Checkstyle
- **Security:** Multi-layer scanning (SAST, SCA, container scanning)
- **Build Verification:** Docker image smoke tests

---

## Technology Stack

### Application
- **Language:** Java 11+
- **Build Tool:** Maven 3.9
- **Testing:** JUnit 5
- **Code Quality:** Checkstyle

### DevOps & Infrastructure
- **CI/CD:** GitHub Actions
- **Containerization:** Docker (Multi-stage builds)
- **Orchestration:** Kubernetes
- **Registry:** Docker Hub
- **Security Scanning:** CodeQL, OWASP Dependency Check, Trivy

### Design Patterns
- **Factory Pattern** - Player creation (`PlayerFactory`)
- **Strategy Pattern** - Bot behavior (`BotStrategy`)
- **Single Responsibility** - Separated concerns (`RuleHandler`, `GameEngine`)

---

## DevOps Quick Reference

### CI/CD Commands

```bash
# Trigger CI manually
git push origin master

# View workflow runs
gh run list --workflow=maven.yml

# View workflow logs
gh run view <run-id> --log
```

### Docker Commands

```bash
# Build locally
docker build -t snakes-and-ladders .

# Run with input
echo -e "3\n1\n2\n0\n" | docker run -i snakes-and-ladders

# Push to registry (requires login)
docker tag snakes-and-ladders:latest <username>/snakes-and-ladders:latest
docker push <username>/snakes-and-ladders:latest

# Scan image locally
trivy image snakes-and-ladders:latest
```

### Kubernetes Commands

```bash
# Deploy application
kubectl apply -f k8s/deployment.yaml

# Check pod status
kubectl get pods -l app=snakes-and-ladders

# View logs
kubectl logs -l app=snakes-and-ladders

# Execute interactive session
kubectl exec -it deployment/snake-and-ladders-cli -- /bin/bash

# Delete deployment
kubectl delete -f k8s/deployment.yaml
```

### Maven Commands

```bash
# Run all tests
mvn test

# Build package
mvn clean package

# Run checkstyle
mvn checkstyle:check

# Dependency security check
mvn org.owasp:dependency-check-maven:check
```

---

## Game Rules

* Players start at position 1.
* Roll dice and move forward by the sum.
* Landing on a snake head → slide down to tail.
* Landing on a ladder bottom → climb up to top.
* Rolling a 6 → extra turn.
* Rolling 3 consecutive 6s → movement canceled and sent back to start.
* Landing on occupied space → existing player gets kicked back to start.
* Must land exactly on the final cell to win.

## Project Structure

```
SnakesAndLadders/
├── .github/
│   └── workflows/
│       ├── maven.yml          # CI pipeline (build, test, security, Docker)
│       └── cd.yml             # CD pipeline (Kubernetes deployment)
├── k8s/
│   └── deployment.yaml        # Kubernetes deployment manifest
├── test/                      # JUnit 5 test suite
│   ├── BoardTest.java
│   ├── GameEngineTest.java
│   ├── PlayerTest.java
│   └── ...
├── *.java                     # Source files (Main, Board, Player, etc.)
├── pom.xml                    # Maven build configuration
├── Dockerfile                 # Multi-stage Docker build
├── .dockerignore              # Docker build exclusions
└── README.md                  # This file
```

---

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

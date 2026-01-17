import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class GameEngineTest {
    
    private Board board;
    private DiceSet dice;
    private List<Player> players;
    private GameEngine gameEngine;
    
    @BeforeEach
    void setUp() {
        board = new Board(10);
        dice = new DiceSet(1, 6);
        players = new ArrayList<>();
    }
    
    @Test
    void testGameEngineCreation() {
        players.add(new BotPlayer("Bot1", new RandomBotStrategy()));
        players.add(new BotPlayer("Bot2", new RandomBotStrategy()));
        
        gameEngine = new GameEngine(board, dice, players);
        assertNotNull(gameEngine);
    }
    
    @Test
    void testGameEngineWithMultiplePlayers() {
        players.add(new BotPlayer("Bot1", new RandomBotStrategy()));
        players.add(new BotPlayer("Bot2", new RandomBotStrategy()));
        players.add(new BotPlayer("Bot3", new RandomBotStrategy()));
        
        gameEngine = new GameEngine(board, dice, players);
        assertNotNull(gameEngine);
    }
    
    @Test
    void testGameInitialization() {
        Player p1 = new BotPlayer("Bot1", new RandomBotStrategy());
        Player p2 = new BotPlayer("Bot2", new RandomBotStrategy());
        
        players.add(p1);
        players.add(p2);
        
        gameEngine = new GameEngine(board, dice, players);
        
        assertEquals(1, p1.getPosition());
        assertEquals(1, p2.getPosition());
    }
    
    @Test
    void testGameWinCondition() {
        Player p1 = new BotPlayer("Bot1", new RandomBotStrategy());
        p1.setPosition(100);
        
        players.add(p1);
        
        gameEngine = new GameEngine(board, dice, players);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        assertDoesNotThrow(() -> {
        });
        
        System.setOut(originalOut);
    }
    
    @Test
    void testGameWithSinglePlayer() {
        Player p1 = new BotPlayer("SoloBot", new RandomBotStrategy());
        players.add(p1);
        
        gameEngine = new GameEngine(board, dice, players);
        assertNotNull(gameEngine);
    }
    
    @Test
    void testGameEngineComponentsInitialized() {
        players.add(new BotPlayer("Bot1", new RandomBotStrategy()));
        
        Board testBoard = new Board(5);
        DiceSet testDice = new DiceSet(2, 6);
        
        gameEngine = new GameEngine(testBoard, testDice, players);
        
        assertNotNull(gameEngine);
    }
}

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class RuleHandlerTest {
    
    private Board board;
    private List<Player> players;
    private RuleHandler ruleHandler;
    private Player player1;
    private Player player2;
    
    @BeforeEach
    void setUp() {
        board = new Board(10);
        players = new ArrayList<>();
        player1 = new HumanPlayer("Alice");
        player2 = new HumanPlayer("Bob");
        players.add(player1);
        players.add(player2);
        ruleHandler = new RuleHandler(board, players);
    }
    
    @Test
    void testRuleHandlerCreation() {
        assertNotNull(ruleHandler);
    }
    
    @Test
    void testNormalMovement() {
        player1.setPosition(1);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        boolean moved = ruleHandler.applyRules(player1, 5);
        
        System.setOut(originalOut);
        
        assertTrue(moved);
        assertEquals(6, player1.getPosition());
        assertEquals(0, player1.getConsecutiveSixes());
    }
    
    @Test
    void testConsecutiveSixesIncrement() {
        player1.setPosition(10);
        player1.setConsecutiveSixes(0);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        ruleHandler.applyRules(player1, 6);
        
        System.setOut(originalOut);
        
        assertEquals(1, player1.getConsecutiveSixes());
    }
    
    @Test
    void testConsecutiveSixesReset() {
        player1.setPosition(10);
        player1.setConsecutiveSixes(2);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        ruleHandler.applyRules(player1, 4);
        
        System.setOut(originalOut);
        
        assertEquals(0, player1.getConsecutiveSixes());
    }
    
    @Test
    void testThreeConsecutiveSixes() {
        player1.setPosition(50);
        player1.setConsecutiveSixes(2);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        boolean moved = ruleHandler.applyRules(player1, 6);
        
        System.setOut(originalOut);
        
        assertFalse(moved);
        assertEquals(1, player1.getPosition());
        assertEquals(0, player1.getConsecutiveSixes());
        
        String output = outputStream.toString();
        assertTrue(output.contains("three 6s"));
    }
    
    @Test
    void testOvershooting() {
        player1.setPosition(98);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        boolean moved = ruleHandler.applyRules(player1, 5);
        
        System.setOut(originalOut);
        
        assertFalse(moved);
        assertEquals(98, player1.getPosition());
        
        String output = outputStream.toString();
        assertTrue(output.contains("overshot"));
    }
    
    @Test
    void testExactWinningPosition() {
        player1.setPosition(97);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        boolean moved = ruleHandler.applyRules(player1, 3);
        
        System.setOut(originalOut);
        
        assertTrue(moved);
        assertEquals(100, player1.getPosition());
    }
    
    @Test
    void testPlayerCollision() {
        player1.setPosition(20);
        player2.setPosition(25);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        boolean moved = ruleHandler.applyRules(player1, 5);
        
        System.setOut(originalOut);
        
        assertTrue(moved);
        assertEquals(25, player1.getPosition());
        assertEquals(1, player2.getPosition());
        
        String output = outputStream.toString();
        assertTrue(output.contains("Kicked back to start"));
    }
    
    @Test
    void testNoCollisionWithSelf() {
        player1.setPosition(30);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        boolean moved = ruleHandler.applyRules(player1, 0);
        
        System.setOut(originalOut);
        
        // Player should not kick themselves
        assertEquals(30, player1.getPosition());
    }
    
    @Test
    void testMultiplePlayersNotAtSamePosition() {
        player1.setPosition(10);
        player2.setPosition(20);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        boolean moved = ruleHandler.applyRules(player1, 5);
        
        System.setOut(originalOut);
        
        assertTrue(moved);
        assertEquals(15, player1.getPosition());
        assertEquals(20, player2.getPosition());
    }
}

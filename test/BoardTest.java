import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class BoardTest {
    
    private Board board;
    
    @BeforeEach
    void setUp() {
        board = new Board(10);
    }
    
    @Test
    void testBoardCreation() {
        assertEquals(10, board.getSize());
    }
    
    @Test
    void testBoardSizeVariations() {
        Board smallBoard = new Board(5);
        assertEquals(5, smallBoard.getSize());
        
        Board largeBoard = new Board(20);
        assertEquals(20, largeBoard.getSize());
    }
    
    @Test
    void testGetNextPositionWithoutEntities() {
        assertEquals(5, board.getNextPosition(5));
        assertEquals(10, board.getNextPosition(10));
        assertEquals(50, board.getNextPosition(50));
    }
    
    @Test
    void testGenerateRandomEntities() {
        assertDoesNotThrow(() -> board.generateRandomEntities());
    }
    
    @Test
    void testGetNextPositionWithSnake() {
        Board testBoard = new Board(10);
        testBoard.generateRandomEntities();
        
        for (int i = 1; i <= 100; i++) {
            int nextPos = testBoard.getNextPosition(i);
            assertTrue(nextPos >= 1 && nextPos <= 100, 
                "Next position should be valid: " + nextPos);
        }
    }
    
    @Test
    void testPrintBoardEntities() {
        board.generateRandomEntities();
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        board.printBoardEntities();
        
        System.setOut(originalOut);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Snakes"));
        assertTrue(output.contains("Ladders"));
    }
    
    @Test
    void testPrintBoardWithPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer("Alice"));
        players.add(new BotPlayer("Bot", new RandomBotStrategy()));
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        board.printBoardWithPlayers(players);
        
        System.setOut(originalOut);
        
        String output = outputStream.toString();
        assertFalse(output.isEmpty());
    }
    
    @Test
    void testPrintBoardWithPlayersAtDifferentPositions() {
        List<Player> players = new ArrayList<>();
        Player p1 = new HumanPlayer("Alice");
        Player p2 = new HumanPlayer("Bob");
        
        p1.setPosition(25);
        p2.setPosition(50);
        
        players.add(p1);
        players.add(p2);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        board.printBoardWithPlayers(players);
        
        System.setOut(originalOut);
        
        String output = outputStream.toString();
        assertTrue(output.contains("[A]") || output.contains("A"));
        assertTrue(output.contains("[B]") || output.contains("B"));
    }
    
    @Test
    void testBoardBoundaries() {
        Board board = new Board(10);
        
        assertEquals(1, board.getNextPosition(1));
        
        assertEquals(100, board.getNextPosition(100));
    }
}

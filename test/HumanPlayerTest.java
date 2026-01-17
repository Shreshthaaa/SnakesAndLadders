import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {
    
    private HumanPlayer player;
    
    @BeforeEach
    void setUp() {
        player = new HumanPlayer("Alice");
    }
    
    @Test
    void testPlayerCreation() {
        assertEquals("Alice", player.getName());
        assertEquals(1, player.getPosition());
        assertEquals(0, player.getConsecutiveSixes());
    }
    
    @Test
    void testIsBot() {
        assertFalse(player.isBot());
    }
    
    @Test
    void testSetPosition() {
        player.setPosition(10);
        assertEquals(10, player.getPosition());
        
        player.setPosition(50);
        assertEquals(50, player.getPosition());
    }
    
    @Test
    void testConsecutiveSixes() {
        assertEquals(0, player.getConsecutiveSixes());
        
        player.setConsecutiveSixes(1);
        assertEquals(1, player.getConsecutiveSixes());
        
        player.setConsecutiveSixes(3);
        assertEquals(3, player.getConsecutiveSixes());
        
        player.setConsecutiveSixes(0);
        assertEquals(0, player.getConsecutiveSixes());
    }
    
    @Test
    void testMultiplePlayers() {
        HumanPlayer player1 = new HumanPlayer("Bob");
        HumanPlayer player2 = new HumanPlayer("Charlie");
        
        assertEquals("Bob", player1.getName());
        assertEquals("Charlie", player2.getName());
        assertNotEquals(player1.getName(), player2.getName());
    }
}

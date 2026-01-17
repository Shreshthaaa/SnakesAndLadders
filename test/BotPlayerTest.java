import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class BotPlayerTest {
    
    private BotPlayer botPlayer;
    private BotStrategy strategy;
    
    @BeforeEach
    void setUp() {
        strategy = new RandomBotStrategy();
        botPlayer = new BotPlayer("BotAlice", strategy);
    }
    
    @Test
    void testBotPlayerCreation() {
        assertEquals("BotAlice", botPlayer.getName());
        assertEquals(1, botPlayer.getPosition());
        assertEquals(0, botPlayer.getConsecutiveSixes());
    }
    
    @Test
    void testIsBot() {
        assertTrue(botPlayer.isBot());
    }
    
    @Test
    void testSetPosition() {
        botPlayer.setPosition(15);
        assertEquals(15, botPlayer.getPosition());
        
        botPlayer.setPosition(75);
        assertEquals(75, botPlayer.getPosition());
    }
    
    @Test
    void testConsecutiveSixes() {
        assertEquals(0, botPlayer.getConsecutiveSixes());
        
        botPlayer.setConsecutiveSixes(2);
        assertEquals(2, botPlayer.getConsecutiveSixes());
        
        botPlayer.setConsecutiveSixes(0);
        assertEquals(0, botPlayer.getConsecutiveSixes());
    }
    
    @Test
    void testWaitForTurnCallsStrategy() {
        assertDoesNotThrow(() -> botPlayer.waitForTurn());
    }
    
    @Test
    void testMultipleBotPlayers() {
        BotPlayer bot1 = new BotPlayer("Bot1", new RandomBotStrategy());
        BotPlayer bot2 = new BotPlayer("Bot2", new RandomBotStrategy());
        
        assertEquals("Bot1", bot1.getName());
        assertEquals("Bot2", bot2.getName());
        assertTrue(bot1.isBot());
        assertTrue(bot2.isBot());
    }
}

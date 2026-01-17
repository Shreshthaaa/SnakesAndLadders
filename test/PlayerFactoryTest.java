import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerFactoryTest {
    
    @Test
    void testCreateHuman() {
        Player player = PlayerFactory.createHuman("Alice");
        
        assertNotNull(player);
        assertTrue(player instanceof HumanPlayer);
        assertEquals("Alice", player.getName());
        assertFalse(player.isBot());
        assertEquals(1, player.getPosition());
    }
    
    @Test
    void testCreateBot() {
        BotStrategy strategy = new RandomBotStrategy();
        Player player = PlayerFactory.createBot("BotBob", strategy);
        
        assertNotNull(player);
        assertTrue(player instanceof BotPlayer);
        assertEquals("BotBob", player.getName());
        assertTrue(player.isBot());
        assertEquals(1, player.getPosition());
    }
    
    @Test
    void testCreateMultiplePlayers() {
        Player human1 = PlayerFactory.createHuman("Player1");
        Player human2 = PlayerFactory.createHuman("Player2");
        Player bot1 = PlayerFactory.createBot("Bot1", new RandomBotStrategy());
        
        assertFalse(human1.isBot());
        assertFalse(human2.isBot());
        assertTrue(bot1.isBot());
        
        assertEquals("Player1", human1.getName());
        assertEquals("Player2", human2.getName());
        assertEquals("Bot1", bot1.getName());
    }
}

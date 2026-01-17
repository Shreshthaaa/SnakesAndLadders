import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class RandomBotStrategyTest {
    
    @Test
    void testTakeTurnExecutesWithoutError() {
        RandomBotStrategy strategy = new RandomBotStrategy();
        assertDoesNotThrow(() -> strategy.takeTurn("TestBot"));
    }
    
    @Test
    void testTakeTurnPrintsMessage() {
        RandomBotStrategy strategy = new RandomBotStrategy();
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        strategy.takeTurn("TestBot");
        
        System.setOut(originalOut);
        
        String output = outputStream.toString();
        assertTrue(output.contains("TestBot"));
        assertTrue(output.contains("rolling dice"));
    }
    
    @Test
    void testTakeTurnWithDifferentNames() {
        RandomBotStrategy strategy = new RandomBotStrategy();
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        strategy.takeTurn("Bot1");
        strategy.takeTurn("Bot2");
        
        System.setOut(originalOut);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Bot1"));
        assertTrue(output.contains("Bot2"));
    }
    
    @Test
    void testTakeTurnDelay() {
        RandomBotStrategy strategy = new RandomBotStrategy();
        
        long startTime = System.currentTimeMillis();
        strategy.takeTurn("TestBot");
        long endTime = System.currentTimeMillis();
        
        // Should take at least 500ms due to Thread.sleep
        long duration = endTime - startTime;
        assertTrue(duration >= 400, "Expected delay of at least 400ms, but was " + duration + "ms");
    }
}

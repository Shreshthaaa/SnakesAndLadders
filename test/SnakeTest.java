import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
    
    @Test
    void testSnakeCreation() {
        Snake snake = new Snake(25, 5);
        assertEquals(25, snake.getSource());
        assertEquals(5, snake.getDestination());
    }
    
    @Test
    void testSnakeDescribe() {
        Snake snake = new Snake(30, 10);
        String description = snake.describe();
        assertTrue(description.contains("Snake"));
        assertTrue(description.contains("30"));
        assertTrue(description.contains("10"));
        assertEquals("Snake: head=30 tail=10", description);
    }
    
    @Test
    void testSnakeWithDifferentPositions() {
        Snake snake1 = new Snake(99, 1);
        assertEquals(99, snake1.getSource());
        assertEquals(1, snake1.getDestination());
        
        Snake snake2 = new Snake(50, 25);
        assertEquals(50, snake2.getSource());
        assertEquals(25, snake2.getDestination());
    }
}

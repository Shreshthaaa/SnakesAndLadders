import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LadderTest {
    
    @Test
    void testLadderCreation() {
        Ladder ladder = new Ladder(5, 25);
        assertEquals(5, ladder.getSource());
        assertEquals(25, ladder.getDestination());
    }
    
    @Test
    void testLadderDescribe() {
        Ladder ladder = new Ladder(10, 30);
        String description = ladder.describe();
        assertTrue(description.contains("Ladder"));
        assertTrue(description.contains("10"));
        assertTrue(description.contains("30"));
        assertEquals("Ladder: bottom=10 top=30", description);
    }
    
    @Test
    void testLadderWithDifferentPositions() {
        Ladder ladder1 = new Ladder(1, 99);
        assertEquals(1, ladder1.getSource());
        assertEquals(99, ladder1.getDestination());
        
        Ladder ladder2 = new Ladder(25, 50);
        assertEquals(25, ladder2.getSource());
        assertEquals(50, ladder2.getDestination());
    }
}

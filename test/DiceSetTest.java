import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;

class DiceSetTest {
    
    @Test
    void testDiceSetCreation() {
        DiceSet dice = new DiceSet(1, 6);
        assertNotNull(dice);
    }
    
    @Test
    void testSingleDiceRollRange() {
        DiceSet dice = new DiceSet(1, 6);
        
        for (int i = 0; i < 100; i++) {
            int roll = dice.roll();
            assertTrue(roll >= 1 && roll <= 6, 
                "Roll should be between 1 and 6, but was " + roll);
        }
    }
    
    @Test
    void testMultipleDiceRollRange() {
        DiceSet dice = new DiceSet(2, 6);
        
        for (int i = 0; i < 100; i++) {
            int roll = dice.roll();
            assertTrue(roll >= 2 && roll <= 12, 
                "Roll should be between 2 and 12, but was " + roll);
        }
    }
    
    @Test
    void testDifferentDiceSides() {
        DiceSet dice4 = new DiceSet(1, 4);
        DiceSet dice8 = new DiceSet(1, 8);
        DiceSet dice20 = new DiceSet(1, 20);
        
        for (int i = 0; i < 50; i++) {
            int roll4 = dice4.roll();
            assertTrue(roll4 >= 1 && roll4 <= 4);
            
            int roll8 = dice8.roll();
            assertTrue(roll8 >= 1 && roll8 <= 8);
            
            int roll20 = dice20.roll();
            assertTrue(roll20 >= 1 && roll20 <= 20);
        }
    }
    
    @RepeatedTest(10)
    void testRollProducesDifferentResults() {
        DiceSet dice = new DiceSet(1, 6);
        
        int[] rolls = new int[20];
        for (int i = 0; i < 20; i++) {
            rolls[i] = dice.roll();
        }
        
        boolean hasDifferentValues = false;
        for (int i = 1; i < rolls.length; i++) {
            if (rolls[i] != rolls[0]) {
                hasDifferentValues = true;
                break;
            }
        }
        assertTrue(hasDifferentValues, "Expected some variation in dice rolls");
    }
    
    @Test
    void testThreeDiceRoll() {
        DiceSet dice = new DiceSet(3, 6);
        
        for (int i = 0; i < 50; i++) {
            int roll = dice.roll();
            assertTrue(roll >= 3 && roll <= 18, 
                "Roll should be between 3 and 18, but was " + roll);
        }
    }
}

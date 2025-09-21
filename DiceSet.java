import java.util.*;

class DiceSet {
    private int count;
    private int sides;
    private Random rand = new Random();

    public DiceSet(int count, int sides) {
        this.count = count;
        this.sides = sides;
    }

    public int roll() {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += rand.nextInt(sides) + 1;
        }
        return sum;
    }
}
abstract class Player {
    protected String name;
    protected int position;
    protected int consecutiveSixes;

    public Player(String name) {
        this.name = name;
        this.position = 1;
        this.consecutiveSixes = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }

    public int getConsecutiveSixes() {
        return consecutiveSixes;
    }

    public void setConsecutiveSixes(int val) {
        this.consecutiveSixes = val;
    }

    public abstract boolean isBot();

    public abstract void waitForTurn();
}
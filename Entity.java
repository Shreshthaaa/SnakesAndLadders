abstract class Entity {
    protected final int from;
    protected final int to;

    protected Entity(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getDestination() {
        return to;
    }

    public int getSource() {
        return from;
    }

    public abstract String describe();
}
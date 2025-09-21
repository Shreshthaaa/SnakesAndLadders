class Ladder extends Entity {
    public Ladder(int bottom, int top) {
        super(bottom, top);
    }

    @Override
    public String describe() {
        return "Ladder: bottom=" + from + " top=" + to;
    }
}
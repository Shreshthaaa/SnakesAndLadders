class Snake extends Entity {
    public Snake(int head, int tail) {
        super(head, tail);
    }

    @Override
    public String describe() {
        return "Snake: head=" + from + " tail=" + to;
    }
}
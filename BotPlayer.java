class BotPlayer extends Player {
    private BotStrategy strategy;

    public BotPlayer(String name, BotStrategy strategy) {
        super(name);
        this.strategy = strategy;
    }

    @Override
    public boolean isBot() {
        return true;
    }

    @Override
    public void waitForTurn() {
        strategy.takeTurn(name);
    }
}
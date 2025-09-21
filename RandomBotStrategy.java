class RandomBotStrategy implements BotStrategy {
    public void takeTurn(String name) {
        System.out.println(name + " is rolling dice...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }
}
import java.util.Scanner;

class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public boolean isBot() {
        return false;
    }

    @Override
    public void waitForTurn() {
        System.out.print(name + "'s turn. Press Enter to roll dice...");
        new Scanner(System.in).nextLine();
    }
}
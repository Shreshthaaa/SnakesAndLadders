import java.util.*;

class GameEngine {
    private Board board;
    private DiceSet dice;
    private List<Player> players;
    private RuleHandler ruleHandler;
    private boolean gameOver;

    public GameEngine(Board board, DiceSet dice, List<Player> players) {
        this.board = board;
        this.dice = dice;
        this.players = players;
        this.ruleHandler = new RuleHandler(board, players);
        this.gameOver = false;
    }

    public void play() {
        int currentIdx = 0;

        while (!gameOver) {
            Player p = players.get(currentIdx);
            p.waitForTurn();

            int roll = dice.roll();
            System.out.println(p.getName() + " rolled: " + roll);

            boolean moved = ruleHandler.applyRules(p, roll);

            if (moved) {
                System.out.println(p.getName() + " is now at position " + p.getPosition());
                board.printBoardWithPlayers(players);
            }

            if (p.getPosition() == board.getSize() * board.getSize()) {
                System.out.println(p.getName() + " wins!");
                gameOver = true;
                break;
            }

            if (roll == 6 && moved) {
                System.out.println(p.getName() + " gets another turn!");
            } else {
                currentIdx = (currentIdx + 1) % players.size();
            }
        }
    }
}
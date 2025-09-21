import java.util.*;;

class RuleHandler {
    private Board board;
    private List<Player> players;

    public RuleHandler(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
    }

    public boolean applyRules(Player p, int roll) {
        if (roll == 6) {
            p.setConsecutiveSixes(p.getConsecutiveSixes() + 1);
        } else {
            p.setConsecutiveSixes(0);
        }

        if (p.getConsecutiveSixes() == 3) {
            System.out.println(p.getName() + " rolled three 6s in a row! No movement, back to start.");
            p.setPosition(1);
            p.setConsecutiveSixes(0);
            return false;
        }

        int nextPos = p.getPosition() + roll;
        if (nextPos > board.getSize() * board.getSize()) {
            System.out.println(p.getName() + " overshot the final cell. Stay at " + p.getPosition());
            return false;
        }

        p.setPosition(board.getNextPosition(nextPos));

        for (Player other : players) {
            if (other != p && other.getPosition() == p.getPosition()) {
                System.out.println(p.getName() + " landed on " + other.getName() + ". Kicked back to start!");
                other.setPosition(1);
            }
        }
        return true;
    }
}
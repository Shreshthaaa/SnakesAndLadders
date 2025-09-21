import java.util.*;

class Board {
    private int size;
    private Map<Integer, Integer> snakes = new HashMap<>();
    private Map<Integer, Integer> ladders = new HashMap<>();

    public Board(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void generateRandomEntities() {
        Random rand = new Random();
        int entities = size - 3;

        for (int i = 0; i < entities; i++) {
            int start = rand.nextInt(size * size - 1) + 2;
            int end = rand.nextInt(start - 1) + 1;
            if (!snakes.containsKey(start) && !ladders.containsKey(start)) {
                snakes.put(start, end);
            }
        }
        for (int i = 0; i < entities; i++) {
            int start = rand.nextInt(size * size - 1) + 1;
            int end = rand.nextInt(size * size - start) + start + 1;
            if (!ladders.containsKey(start) && !snakes.containsKey(start)) {
                ladders.put(start, end);
            }
        }
    }

    public int getNextPosition(int pos) {
        if (snakes.containsKey(pos)) {
            System.out.println("Oops! Snake bite at " + pos + ", go down to " + snakes.get(pos));
            return snakes.get(pos);
        }
        if (ladders.containsKey(pos)) {
            System.out.println("Yay! Ladder at " + pos + ", climb up to " + ladders.get(pos));
            return ladders.get(pos);
        }
        return pos;
    }

    public void printBoardEntities() {
        System.out.println("Snakes: " + snakes);
        System.out.println("Ladders: " + ladders);
    }

    public void printBoardWithPlayers(List<Player> players) {
        int n = size;
        for (int row = n - 1; row >= 0; row--) {
            for (int col = 0; col < n; col++) {
                int cell = row * n + (row % 2 == 0 ? col + 1 : (n - col));
                StringBuilder cellStr = new StringBuilder(String.format("%3d", cell));
                for (Player p : players) {
                    if (p.getPosition() == cell) {
                        cellStr.append("[").append(p.getName().charAt(0)).append("]");
                    }
                }
                System.out.print(cellStr + " ");
            }
            System.out.println();
        }
    }
}
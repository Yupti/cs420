import java.util.Random;

public class Board implements Comparable<Board> {

	private Random rand;
    private int[] board;
    private int fitness;
    private int weight;

    public Board(int[] b) {
    	rand = new Random();
        board = b;
        fitness = findNonAttackingQueens();
        weight = 0;
    }

    // Overridden compareTo method that compares the fitness value
    @Override
    public int compareTo(Board b) {
        if (this.getFitness() < b.getFitness())
            return -1;
        else if (this.getFitness() > b.getFitness())
            return 1;
        else
            return 0;
    }

    public int[] getBoard() {
        return board;
    }

    public int getFitness() {
        return fitness;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int w) {
        weight = w;
    }

    public void decrementWeight(int d) {
        weight -= d;
    }

    // find number of queens that cannot attack/be attacked
    public int findNonAttackingQueens() {
        int nonAttacking = 0;
        int num;
        for (int i = 0; i < board.length; i++) {
            for (int j = i + 1; j < board.length; j++) {
                num = j - i;
                int n1 = board[j] - num;
                int n2 = board[j] + num;
                if (board[i] != board[j] && n1 != board[i] && n2 != board[i]) {
                    nonAttacking++;
                }
            }
        }
        return nonAttacking;
    }

    // Moves a random queen to a random spot
    public void moveQueenRandomly() {
        board[rand.nextInt( board.length )] = rand.nextInt( board.length );
        fitness = findNonAttackingQueens();
    }

    public void printBoard() {
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (j == board[i])
					System.out.print("Q ");
				else
					System.out.print(". ");
			}
			System.out.println("");
		}
		
		System.out.println("");
	}

}




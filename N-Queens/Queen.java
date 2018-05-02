package n_queens;
import java.util.ArrayList;
import java.util.Random;


public class Queen {

	private int[] board = new int[8];
	
	public Queen() {
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		Random r = new Random();
		int randIndex, randCol;
		
		for (int i = 0; i < 8; i++) 
			al.add(i);
		
		for (int i = 0; i < 8; i++) { // generating board
			randIndex = r.nextInt(al.size());
			randCol = al.remove(randIndex);
			board[i] = randCol;
		}
	}
	
	public Queen(int[] board) {
		this.board = board;
	}
	
	public int[] getBoard() {
		return board;
	}
	
	public void solveBoard() {
		double temperature = 100;
		Queen temp;
		
		
	}
	
	public int[] getNextMove(int[] temp) {
		int len = temp.length;
		
		while (true) {
			int col = (int)(Math.random() * len);
			int row = (int)(Math.random() * len);
			int tmpRow = temp[col];
		}
		return temp; // for now
	}
	public void printBoard(int[] b) {
		
		for (int i = 0; i < b.length; i++) 
			System.out.print(b[i] + " ");
		System.out.println("\n");
		
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if (j == b[i])
					System.out.print("1 ");
				else
					System.out.print("0 ");
			}
			System.out.println("");
		}
		
		System.out.println("");
	}
	
	public void collisions(int[] b) {
		
		int collisions = 0;
		for (int i = 0; i < b.length - 1; i++) {
			for (int j = i + 1; j < b.length; j++) {
				if (b[i] == b[j] || (j-i) == Math.abs(b[i] - b[j]))
					collisions++;
			}
		}
		
		System.out.println("Collisions: " + collisions);
	}
}

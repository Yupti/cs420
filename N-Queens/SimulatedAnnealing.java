package n.queens;
import java.util.ArrayList;
import java.util.Random;


public class SimulatedAnnealing {

	private int[] board = new int[21];
	
	public SimulatedAnnealing() {
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		Random r = new Random();
		int randIndex, randCol;
		
		for (int i = 0; i < 21; i++) 
			al.add(i);
		
		for (int i = 0; i < 21; i++) { // generating board with randomized queen set up
			randIndex = r.nextInt(al.size());
			randCol = al.remove(randIndex);
			board[i] = randCol;
		}
	}
	
	public boolean solveBoard() {
		
		double temperature = 350;
		int[] temp = {0};
		
		int testCost = collisions(board);
		
		for (int i = 0; i < 750 && testCost > 0; i++) {
			temp = getNextMove(board, testCost, temperature);
			testCost = collisions(board);
			temperature = Math.max(temperature * 0.1, 0.01);
		}
		
		if (testCost == 0) {
			//printBoard(temp);
			System.out.println("Board is solved.");
			return true;
		}
		else {
			System.out.println("Solution not found.");
			return false;
		}
		
	}
	
	// this needs testing
	public int[] getNextMove(int[] temp, int target, double temperature) {
		
		int len = temp.length;
		
		while (true) {
			int col = (int)(Math.random() * len);
			int row = (int)(Math.random() * len);
			int tmpRow = temp[col];
			temp[col] = row;
			
			int cost = collisions(temp);
			if (cost < target)
				return temp;
			
			int dE = target - cost;
			double acceptable = Math.min(1, Math.exp(dE / temperature));
			
			if (Math.random() < acceptable)
				return temp;
			
			temp[col] = tmpRow;
		}
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
	
	public int collisions(int[] b) {
		
		int collisions = 0;
		for (int i = 0; i < b.length - 1; i++) {
			for (int j = i + 1; j < b.length; j++) {
				if (b[i] == b[j] || (j-i) == Math.abs(b[i] - b[j]))
					collisions++;
			}
		}
		
		return collisions;
	}
}

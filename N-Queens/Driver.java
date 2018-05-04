package test.pkg;

import java.util.ArrayList;
import java.util.Random;

public class Driver {

	public static void main(String[] args) {
		
		GeneticAlg g = new GeneticAlg(22, 21); // no go
		
//		ArrayList<Board> test = new ArrayList<Board>();
//		test = generatePopulation(22, 21);
//		Test t = new Test(test);
//		t.solve();
		
	}
	
	public static ArrayList<Board> generatePopulation(int n, int s) { // n for # of states in population, s for size of board
		
		ArrayList<Board> population = new ArrayList<Board>(); // to store the population of different states
		Random r = new Random();
		
		int rand;
		
		for (int i = 0; i < n; i++) {
			int[] temp = new int[s];
			for (int j = 0; j < s; j++) {
				rand = r.nextInt(10);
				temp[j] = rand;
			}
			
			population.add(new Board(temp));
			
		}
		
		return population; // return arraylist with a number (n) of randomized states for new population
	}
	
/*public void printBoard() {
		
		for (int i = 0; i < board.length; i++) 
			System.out.print(board[i] + " ");
		System.out.println("\n");
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (j == board[i])
					System.out.print("1 ");
				else
					System.out.print("0 ");
			}
			System.out.println("");
		}
		
		System.out.println("");
	}*/
}

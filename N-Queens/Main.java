package n_queens;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		/*int counter1 = 0;
		for (int i = 0; i < 100; i++) {
			SimulatedAnnealing s = new SimulatedAnnealing();
			if(s.solveBoard())
				counter1++;
		}
		
		System.out.println("Number of successes out of 100: " + counter1);*/
		// above is given for 750 allowed iterations for 21 x 21 board

		SimulatedAnnealing s = new SimulatedAnnealing();
		s.solveBoard();
		ArrayList<Board> test = new ArrayList<Board>();
		test = generatePopulation(50, 21); // population, size of array
		GeneticAlg t = new GeneticAlg(test);
		t.solve();
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
	
}

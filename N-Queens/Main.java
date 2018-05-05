import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
	
		run();
	
		// analysisTests(); // function used for analysis, takes a long time to run
	}
	
	public static void run() {
		
		int input, input2 = 0, size;
		long start, end;
		boolean running = true;
		
		while (running) {
			
			printMainMenu();
			input = keyboard.nextInt();
			
			switch (input) {
				case 1:
					// goes to simulated annealing
					SimulatedAnnealing s;
					boardSizeMenu();
					input2 = keyboard.nextInt();
					switch (input2) {
						case 1: // for user input size 
							System.out.print("Enter size of board: ");
							size = keyboard.nextInt();
							s = new SimulatedAnnealing(size);
							start = System.currentTimeMillis();
							s.solveBoard();
							end = System.currentTimeMillis();
							System.out.println("Time taken to solve: " + (end - start) + " ms\n");
							break;
							
						default: // for size 21
							s = new SimulatedAnnealing(21);
							start = System.currentTimeMillis();
							s.solveBoard();
							end = System.currentTimeMillis();
							System.out.println("Time taken to solve: " + (end - start) + " ms\n");
							break;
					}
					break;
				case 2:
					// goes to genetic algorithm
					GeneticAlg g;
					ArrayList<Board> b = new ArrayList<Board>();
					boardSizeMenu();
					input2 = keyboard.nextInt();
					switch (input2) {
						case 1: // for user input size 
							System.out.print("Enter size of board: ");
							size = keyboard.nextInt();
							b = generatePopulation(50, size);
							g = new GeneticAlg(b);
							start = System.currentTimeMillis();
							g.solve();
							end = System.currentTimeMillis();
							System.out.println("Time taken to solve: " + (end - start) + " ms\n");
							break;
							
						default: // for size 21
							b = generatePopulation(50, 21);
							g = new GeneticAlg(b);
							start = System.currentTimeMillis();
							g.solve();
							end = System.currentTimeMillis();
							System.out.println("Time taken to solve: " + (end - start) + " ms\n");
							break;
					}
					break;
				case 3:
					System.out.println("Ending program...");
					keyboard.close();
					running = false;
					break;
					
				default:
					System.out.println("Incorrect choice made, please try again.");
					break;
			}
		}
	}
	
	public static void analysisTests() {
		
		DecimalFormat d = new DecimalFormat("#.00");
		int counter = 0;
		long start, end;
		double time = 0.0;
		boolean success;
		
		System.out.println("Running both algorithms for 150 iterations.\n");
		System.out.println("Analysis test for Simulated Annealing\n");
		
		for (int i = 0; i < 150; i++) {
			SimulatedAnnealing test1 = new SimulatedAnnealing(21);
			start = System.currentTimeMillis();
			success = test1.solveBoard();
			end = System.currentTimeMillis();
			time += (end - start);
			if (success)
				counter++;
		}
		
		System.out.println("Number of successes: " + counter);
		System.out.println("Success Rate: " + d.format(((double) counter / 150 * 100)) + "%");
		System.out.println("Average time: " + (time / 150) + " ms\n");
		
		System.out.println("Analysis test for Genetic Algorithm\n");
		
		counter = 0;
		for (int j = 0; j < 150; j++) {
			ArrayList<Board> b = new ArrayList<Board>();
			b = generatePopulation(50, 21); // population, size of array
			GeneticAlg test2 = new GeneticAlg(b);
			start = System.currentTimeMillis();
			success = test2.solve();
			end = System.currentTimeMillis();
			time += (end - start);
			if (success)
				counter++;
		}
		
		System.out.println("Number of successes: " + counter);
		System.out.println("Success Rate: " + d.format(((double) counter / 150 * 100)) + "%");
		System.out.println("Average time: " + (time / 150) + " ms");
	}
	
	public static void printMainMenu() {
		
		System.out.println("Enter a command for N-Queens Problem:\n(1) Simulated Annealing\n(2) Genetic Algorithm\n(3) Exit");
		System.out.print("Choice: ");
	}
	
	public static void boardSizeMenu() {
		
		System.out.println("Enter a command:\n(1) Enter board size manually\n(2) Test case for n = 21\n(NOTE: Entering other values will default to n = 21)");
		System.out.print("Choice: ");
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

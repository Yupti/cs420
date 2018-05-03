package n_queens;
import java.util.ArrayList;
import java.util.Random;

// fitness = collisions
public class GeneticAlgorithm {

	private ArrayList<int[]> successors = new ArrayList<int[]>();
	public GeneticAlgorithm() {
			
	}
	
	public boolean solve(ArrayList<int[]> p) {
		
		int limit = 100000; // temporary number of generations to do
		int[] temp1 = {0}; // to hold arrays w/ smallest amount of collisions
		int[] temp2 = {0};
		int[] chosen;
		int count1 = Integer.MAX_VALUE, count2 = count1, cValue; // same as above comment
		boolean solution = false;
		
		// SOMETHING WRONG WITH THIS
		int maxFit = maxFitness(10);
		for (int i = 0; i < limit && !solution; i++) {
			
			// finds best two arrays w/ minimal collisions
			for (int j = 0; j < p.size() && !solution; j++) {
				
				cValue = fitness(p.get(j));
				
				if (cValue == maxFit) { // board found
					System.out.println("Solution found.");
					chosen = p.get(j);
					//printBoard(chosen);
					//System.out.println("\nThere are " + fitness(chosen) + " collisions.");
					solution = true;
					return true; // test
					
				} else if (cValue < count1) {
					temp1 = p.get(j);
					count1 = cValue;
				} else if (cValue < count2) {
					temp2 = p.get(j);
					count2 = cValue;
				}
			}
			
			chosen = crossOver(temp1, temp2);
			
			// NOT WORKING
			for (int k = 0; k < 24; k++) {
				int[] temp = chosen.clone();
				successors.add(mutate(temp)); // given best child, creates mutations that will occupy new population
			}
			
//			p = generatePopulation(99);
			p.clear(); // clear old generation
			p.add(chosen); // add original unmutated successor
			p.addAll(successors); // adding new mutated successors to population to be evaluated again
		}
	
		if (!solution) 
			System.out.println("Failure.");
		return false; // test
	}
	
	// need better way to do this
	public int[] crossOver(int[] parent1, int[] parent2) {
		
		Random r = new Random();
		int[] child = new int[parent1.length];
		/*boolean collision = false, collision2 = false;
		
		for (int i = 0; i < parent1.length; i++) {
			for (int j = i + 1; j < parent1.length; j++) {
				if (parent1[i] == parent1[j] || (j-i) == Math.abs(parent1[i] - parent1[j]))
					collision = true;
				if (parent2[i] == parent2[j] || (j-i) == Math.abs(parent2[i] - parent2[j]))
					collision2 = true;
			}
			
			if (!collision && !collision2) { // neither cause collision, either can go to child
				int val = r.nextInt(2);
				switch (val) {
					case 0:
						child[i] = parent1[i];
						break;
					case 1: 
						child[i] = parent2[i];
						break;
				}
			}
			else if (!collision)  // collision does not occur
				child[i] = parent1[i];
			else if (!collision2)  // collision2 does not occur
				child[i] = parent2[i];
			
			collision = false;  // reset
			collision2 = false;
			
		}*/
		
		int crossOverPoint = r.nextInt(parent1.length);
		
		for (int i = 0; i < child.length; i++) {
			if (i < crossOverPoint)
				child[i] = parent1[i];
			else
				child[i] = parent2[i];
		}
		return child;
	}
	
	public int[] mutate(int[] b) {
		
		Random r = new Random();
		b[r.nextInt(b.length)] = r.nextInt(b.length);
		return b;
	}
	
	public int fitness(int[] b) {
		
		int nonAttacking = 0;
		int num;
		for (int i = 0; i < b.length - 1; i++) {
			for (int j = i + 1; j < b.length; j++) {
				num = j - i;
				int n1 = b[j] - num;
				int n2 = b[j] + num;
				if (b[i] != b[j] && n1 != b[i] && n2 != b[i])
					nonAttacking++;
			}
		}
		
		return nonAttacking;
	}
	
	// not using this, this is here for reference
	/*public int collisions(int[] b) {
		
		int collisions = 0;
		for (int i = 0; i < b.length - 1; i++) {
			for (int j = i + 1; j < b.length; j++) {
				if (b[i] == b[j] || (j-i) == Math.abs(b[i] - b[j]))
					collisions++;
			}
		}
		
		return collisions;
	}*/
	
	public int maxFitness(int n) {
		return n * (n-1) / 2;
	}
	
	// MAY NEED TO BE COMPLETELY RANDOMIZED
	public ArrayList<int[]> generatePopulation(int n) { // for number of states to make for a population
		
		ArrayList<int[]> population = new ArrayList<int[]>(); // to store the population of different states
		
		// this one for simulated annealing style rows/cols

		
		// below for completely random boards, no tracks on rows/cols, may have issues with collisions method
		Random r = new Random();
		
		int rand;
		
		for (int i = 0; i < n; i++) {
			int[] temp = new int[10];
			
			for (int j = 0; j < 10; j++) {
				rand = r.nextInt(10);
				temp[j] = rand;
			}
			
			population.add(temp);
			
		}
		
		return population; // return arraylist with a number (n) of randomized states for new population
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
	
}

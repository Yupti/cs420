package n_queens;
import java.util.ArrayList;
import java.util.Random;

// fitness = collisions
public class GeneticAlgorithm {

	public GeneticAlgorithm() {
		
		ArrayList<int[]> population;
		
		population = generatePopulation(100); // population size set at 100, may change
		solve(population);
		
	}
	
	public void solve(ArrayList<int[]> p) {
		
		int limit = 50000; // temporary number of generations to do
		int[] temp1; // to hold arrays w/ smallest amount of collisions
		int[] temp2;
		int count1 = Integer.MAX_VALUE, count2 = count1, cValue; // same as above comment
		boolean solution = false;
		
		for (int i = 0; i < limit && !solution; i++) {
			
			// finds best two arrays w/ minimal collisions
			for (int j = 0; j < p.size() && !solution; j++) {
				
				cValue = collisions(p.get(j));
				
				if (cValue == 0) { // board found
					System.out.println("Solution found.");
					solution = true;
					
				} else if (cValue < count1) {
					temp1 = p.get(j);
					count1 = cValue;
				} else if (cValue < count2) {
					temp2 = p.get(j);
					count2 = cValue;
				}
			}
		}
	
		if (!solution) 
			System.out.println("Failure.");
	}
	
	public void crossOver() {
		
	}
	
	public void mutate(int[] b) {
		
	}
	
	// MAY NEED TO BE COMPLETELY RANDOMIZED
	public ArrayList<int[]> generatePopulation(int n) { // for number of states to make for a population
		
		ArrayList<int[]> population = new ArrayList<int[]>(); // to store the population of different states
		
		// this one for simulated annealing style rows/cols
		/*for (int i = 0; i < n; i++) {
			ArrayList<Integer> al = new ArrayList<Integer>();
			int[] temp = new int[21];
			Random r = new Random();
			int randIndex, randCol;
			
			for (int j = 0; j < 21; j++) 
				al.add(j);
			
			for (int k = 0; k < 21; k++) { // generating board with randomized queen set up
				randIndex = r.nextInt(al.size());
				randCol = al.remove(randIndex);
				temp[k] = randCol;
			}
			
			population.add(temp);
		}*/
		
		// below for completely random boards, no tracks on rows/cols, may have issues with collisions method
		Random r = new Random();
		
		int rand;
		
		for (int i = 0; i < n; i++) {
			int[] temp = new int[21];
			
			for (int j = 0; j < 21; j++) {
				rand = r.nextInt(21);
				temp[j] = rand;
			}
			
			population.add(temp);
			
		}
		
		return population; // return arraylist with a number (n) of randomized states for new population
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

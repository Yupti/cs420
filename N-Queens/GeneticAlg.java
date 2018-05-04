package test.pkg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlg {
	
	private ArrayList<Board> pop = new ArrayList<Board>();
	private ArrayList<Board> successors = new ArrayList<Board>();
	private Random rand;
	private int size;
	private int curFitness;
	private int maxFitness;
	private int generations;
	private int populationLimit;
	
	public GeneticAlg(int p, int s) {
		size = s; // for testing for now, will accept size and create tables based on this
		populationLimit = p;
		curFitness = 0;
		maxFitness = maxFitnessValue(size);
		rand = new Random();
		pop = generatePopulation(populationLimit, size); // fills population w/ initial states
		
		solve();
	}
	
	public boolean solve() {
		int[] parent1;
		int[] parent2;
		int fitVal;
		
		// need to calc weights IN PROGRESS
//		for (int i = 0; i < pop.size(); i++) {
//			pop.get(i).setFitness(fitness(pop.get(i).getBoard()));
//		}
		
		while (true) {
			findFitness();
			
			fitVal = pop.get(pop.size() - 1).getFitness();
            
			System.out.println("Max Fit Value is: " + maxFitness);
			System.out.println("Fit value is: " + fitVal);
            if (fitVal == (int)(maxFitness)) {
            	System.out.println("Finished, took " + generations + " generations to complete.");
                return true;
            }
            
            parent1 = bestParent();
            parent2 = bestParent();
            
            successors.add(new Board(crossOver(parent1, parent2)));
            
            for ( int i = 0; i <= pop.size() / 4 ; i++) 
                pop.remove(0);
             
            breed(parent1, parent2); // NEED TO MAKE BREED FUNCTION
            Collections.sort(successors);
            mutate();
             
            pop.clear();
            pop.addAll(successors);
            successors.clear();
             
            generations++;
		}
	}
	
	public int[] crossOver(int[] parent1, int[] parent2) {
		
		int[] child = new int[parent1.length];
		
		int crossOverPoint = rand.nextInt(parent1.length);
		
		for (int i = 0; i < child.length; i++) {
			if (i < crossOverPoint)
				child[i] = parent1[i];
			else
				child[i] = parent2[i];
		}
		return child;
	}
	
	public void reproduce(int[] parent1, int[] parent2) {
		
		int[] child;
		int[] clone;
        while( successors.size() < populationLimit ) {
        	
            int index = rand.nextInt( pop.size() );
            clone = pop.get(index).getBoard().clone();
            if( rand.nextBoolean() )
                child = crossOver( parent1, clone );
            else
                child = crossOver( parent2, clone );

            successors.add( new Board(child) );
        }
    }
	
	public void breed(int[] p1, int[] p2) {
        int index;
        int[] child;
        int[] temp;
         
        while (successors.size() < populationLimit) {
            index = rand.nextInt(pop.size());
            temp = pop.get(index).getBoard().clone();
             
            if ( rand.nextBoolean()) 
                child = crossOver(p1, temp);
            else 
                child = crossOver(p2, temp);
             
            successors.add(new Board(child));
        }
    }
	
	public void mutate() {
		for (int i = 0; i < successors.size() / 4; i++) 
            successors.get(i).moveQueenRandomly();
	}
	
	public void findFitness() {
        curFitness = 0;
        Collections.sort(pop);
         
        for (int i = 0; i < pop.size(); i++) {
            pop.get(i).setWeight(curFitness);
            curFitness += pop.get(i).getFitness();
        }
        
        System.out.println("At findFitness(), value: " + curFitness);
         
    }
	
	public int[] bestParent() {
        int count = 0;
        int decrement;
        System.out.println("Current Fitness: " + curFitness);
        int num = rand.nextInt(curFitness);
         
        while ( count < pop.size() - 1 && num > pop.get(count).getWeight())
            count++;
         
        int[] parents = pop.get(count).getBoard();
        decrement = pop.get(count).getFitness();
        curFitness -= decrement;
        pop.remove(count);
         
         
        for (int i = count + 1; i < pop.size(); i++)
            pop.get(i).decrementWeight(decrement);
         
        return parents;
    }
	
	// might only be used for 1 iteration
	public ArrayList<Board> generatePopulation(int n, int s) { // n for # of states in population, s for size of board
		
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
	
	public int maxFitnessValue(int n) {
		return n * (n-1) / 2;
	}
	
	// figures out fitness of board
	
}

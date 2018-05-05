import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlg {
     
    private ArrayList<Board> pop;
    private int popSize;
    private int curFitness;
    private int genCount;
    private int idealFit;
    private Random rand;
     
     
    public GeneticAlg(ArrayList<Board> p) {
        pop = p;
        popSize = p.size();
        curFitness = 0;
        idealFit = p.get(0).getBoard().length;
        genCount = 0;
        rand = new Random();
    }
     
    public boolean solve() {
        ArrayList<Board> successors = new ArrayList<>();
        int[] parent1;
        int[] parent2;
        int fit;
         
        while (true) {
            fitLevels();
             
            fit = pop.get(popSize - 1).getFitness();
             
            if (fit == (int) (idealFit * ((idealFit - 1) / 2.0))) {
            	pop.get(popSize - 1).printBoard();
            	System.out.println(genCount + " generations made.");
            	System.out.println("");
                return true;
            }
             
            parent1 = bestParent(); 
            parent2 = bestParent();
            successors.add(new Board(crossBreed(parent1, parent2)));
             
            for ( int i = 0; i <= pop.size() / 4 ; i++) 
                pop.remove(0);
             
            nextGen(successors, parent1, parent2);
            Collections.sort(successors);
            mutate(successors);
             
            pop.clear();
            pop.addAll(successors);
            successors.clear();
             
            genCount++;
        }
    }

    public void fitLevels() {
        curFitness = 0;
        Collections.sort(pop);
         
        for (int i = 0; i < pop.size(); i++) {
            pop.get(i).setWeight(curFitness);
            curFitness += pop.get(i).getFitness();
        }
         
    }

    public int[] bestParent() {
        int count = 0;
        int decrement;
        int num = rand.nextInt(curFitness);
         
        while (count < pop.size() - 1 && num > pop.get(count).getWeight())
            count++;
         
        int[] parent = pop.get(count).getBoard();
        decrement = pop.get(count).getFitness();
        curFitness -= decrement;
        pop.remove(count);
         
         
        for (int i = count + 1; i < pop.size(); i++)
            pop.get(i).decrementWeight(decrement);
         
        return parent;
    }

    // cross-breeds a new child from parents at a random point in board
    public int[] crossBreed(int[] b1, int[] b2) {
        int crossPoint = rand.nextInt(b1.length);
        int[] child = new int[b1.length];
         
        for (int i = 0; i < child.length; i++) {
            if ( i < crossPoint ) {
                child[i] = b1[i];
            } else {
                child[i] = b2[i];
            }
        }
        return child;
         
    }

    // creates next generation
    public void nextGen(ArrayList<Board> succ, int[] parent1, int[] parent2) {
        int index;
        int[] child;
        int[] temp;
         
        while (succ.size() < popSize) {
            index = rand.nextInt(pop.size());
            temp = pop.get(index).getBoard().clone();
             
            if ( rand.nextBoolean()) {
                child = crossBreed(parent1, temp);
            } else {
                child = crossBreed(parent2, temp);
            }
             
            succ.add(new Board(child));
        }
    }
    
    // causes mutations for successor boards w/ lower fitness scores
    public void mutate(ArrayList<Board> succ) {
         
        for (int i = 0; i < succ.size() / 4; i++)
            succ.get(i).moveQueenRandomly();
    }
}

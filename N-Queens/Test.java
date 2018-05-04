package test.pkg;

import java.util.*;

public class Test {
     
    private ArrayList<Board> pop;
    private int popSize;
    private int curFitness;
    private int genCount;
    private int idealFit;
    private Random rand;
     
     
    public Test(ArrayList<Board> popul) {
        pop = popul;
        popSize = popul.size();
        curFitness = 0;
        idealFit = popul.get(0).getBoard().length;
        genCount = 0;
        rand = new Random();
    }
     
    public boolean solve() {
        ArrayList<Board> successors = new ArrayList<>();
        int[] p1;
        int[] p2;
        int fit;
         
         
        while (true) {
            findFit();
             
            fit = pop.get(popSize - 1).getFitness();
             
            if (fit == (int) (idealFit * ((idealFit - 1) / 2.0))) {
            	System.out.println("Done");
            	pop.get(popSize - 1).printBoard();
                return true;
            }
             
            p1 = findParent(); 
            p2 = findParent();
            successors.add(new Board(crossBreed(p1, p2)));
             
            for ( int i = 0; i <= pop.size() / 4 ; i++) {
                pop.remove(0);
            }
             
            breed(successors, p1, p2);
            Collections.sort(successors);
            mutate(successors);
             
            pop.clear();
            pop.addAll(successors);
            successors.clear();
             
            genCount++;
             
             
        }
    }
     
    public void findFit() {
        curFitness = 0;
        Collections.sort(pop);
         
        for (int i = 0; i < pop.size(); i++) {
            pop.get(i).setWeight(curFitness);
            curFitness += pop.get(i).getFitness();
        }
         
    }
     
    public int[] findParent() {
        int count = 0;
        int decrement;
        int num = rand.nextInt(curFitness);
         
        while ( count < pop.size() - 1 && num > pop.get(count).getWeight()) {
            count++;
        }
         
        int[] parents = pop.get(count).getBoard();
        decrement = pop.get(count).getFitness();
        curFitness -= decrement;
        pop.remove(count);
         
         
        for (int i = count + 1; i < pop.size(); i++) {
            pop.get(i).decrementWeight(decrement);
        }
         
        return parents;
    }
     
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
     
    public void breed(ArrayList<Board> succ, int[] p1, int[] p2) {
        int index;
        int[] child;
        int[] temp;
         
        while (succ.size() < popSize) {
            index = rand.nextInt(pop.size());
            temp = pop.get(index).getBoard().clone();
             
            if ( rand.nextBoolean()) {
                child = crossBreed(p1, temp);
            } else {
                child = crossBreed(p2, temp);
            }
             
            succ.add(new Board(child));
        }
    }
     
    public void mutate (ArrayList<Board> succ) {
         
        for (int i = 0; i < succ.size() / 4; i++) {
            succ.get(i).moveQueenRandomly();
        }
    }
     
     
     
    public int getGenCount() {
        return genCount;
    }
     
 
}

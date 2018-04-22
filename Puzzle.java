import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Puzzle {

	HashSet<State> visited = new HashSet<State>();
	HashSet<State> unvisited = new HashSet<State>();
	
	public void aStar(State obj, int heuristic) {
		
		boolean solved = false;
		boolean solvable = true;
				
		switch (heuristic) { // Calculates heuristic and 'f' values for first iteration
			case 1: 
				obj.calcH1();
				obj.calcF(1);

				break;
			case 2: 
				obj.calcH2();
				obj.calcF(2);

				break;
		}
		
		do {	
			if (checkState(obj)) {
				solved = true;
			}
			else {
				// iterator's for visited and unvisited states
				Iterator<State> i = visited.iterator();
				Iterator<State> i2 = unvisited.iterator();
				State temp = obj;
				State temp2;
				
				// checks visited states, if any
				if (!visited.isEmpty()) {
					while (i.hasNext()) { 
						temp2 = i.next();
						if (temp2.getF() < obj.getF()) 
							obj = temp2;
					}
					visited.remove(obj); 
					visited.add(temp);
				}

				// checks unvisited states, if any
				if (!unvisited.isEmpty()) {
					temp2 = i2.next();
					while (i2.hasNext()) {
						if (temp2.getF() < obj.getF())
							obj = temp2;
					}
					unvisited.remove(obj);
				}

				State bestChild = obj.findBestChild(heuristic); // locates best Child towards goal state
				ArrayList<State> al = obj.getLesserChildren();
				
				for (int k = 0; k < al.size(); k++) // adds all other children to unvisited set
					unvisited.add(al.get(k));
				
				if (bestChild.getF() < obj.getF()) // locates most optimal state
					obj = bestChild;
			}
			if (obj.getG() > 31) {
				System.out.println("This puzzle is unsolvable.");
				solvable = false;
			}
			
		} while(solved == false && solvable == true);
		
		obj.printPuzzle();
		
	}
	
	// checks for final goal state, [0,1,2,3,4,5,6,7,8]
	public boolean checkState(State obj) {
		
		int[] temp = new int[9];
		temp = obj.getTiles();
		for (int i = 0; i < temp.length; i++) {
			if (i != temp[i]) 
				return false;
		}
		
		return true;
	}
	
}

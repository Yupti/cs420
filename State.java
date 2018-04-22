import java.util.ArrayList;

public class State {

	private int[] tiles = new int[9];
	private int f = 0, g = 0, h1 = 0, h2 = 0;
	private State parent = null, greaterChild;
	private ArrayList<State> al = new ArrayList<State>();
		
	public State(int[] tiles) {
		this.tiles = tiles;
	}
	public State(int[] tiles, State parent) {
		this.tiles = tiles;
		this.parent = parent;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public void setLesserChildren(ArrayList<State> al) {
		this.al = al;
	}
	public int getF() {
		return f;
	}
	
	public int getG() {
		return g;
	}
	
	public State getParent() {
		return parent;
	}
	
	public State getGreaterChild() {
		return greaterChild;
	}
	
	public ArrayList<State> getLesserChildren() {
		return al;
	}
	
	public int getH1() {
		return h1;
	}
	
	public int getH2() {
		return h2;
	}
	
	public int[] getTiles() {
		return tiles;
	}
	
	public void calcH1() {
		
		for (int i = 0; i < tiles.length; i++) {
			
			if (tiles[i] != i && tiles[i] != 0)
				h1++;
		}
	}
	
	public void calcH2() { 

		for (int i = 0; i < tiles.length; i++) { 
			if (tiles[i] != i && tiles[i] != 0)
				h2 += (Math.abs(tiles[i] / 3 - i / 3) + Math.abs(tiles[i] % 3 - i % 3));
		}
	}
	
	// calculates value based on heuristic
	public void calcF(int num) {
		if (num == 1)
			f = g + h1;
		else
			f = g + h2;
	}
	
	public State findBestChild(int heuristic) {
		
		ArrayList<State> al = new ArrayList<State>();
		State tempState = new State(tiles);
		int intTemp;
		int[] tempArray = tiles.clone();
		
		// obtains all possible children that are mismatched
		for (int i = 0; i < 9; i++) {
			switch (i) {
				case 0:
					if (tiles[i] != 0) {
						tempArray = swap(0, 1, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(0, 3, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
					}
					break;
				
				case 1:
					if (tiles[i] != 1) {
						tempArray = swap(1, 0, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(1, 2, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(1, 4, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
					}
					break;
					
				case 2:
					if (tiles[i] != 2) {
						tempArray = swap(2, 1, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(2, 5, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
					}
					break;
				
				case 3:
					if (tiles[i] != 3) {
						tempArray = swap(3, 0, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(3, 4, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(3, 6, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
					}
					break;
					
				case 4:
					if (tiles[i] != 4) {
						tempArray = swap(4, 1, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(4, 3, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(4, 5, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(4, 7, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
					}
					break;
					
				case 5:
					if (tiles[i] != 5) {
						tempArray = swap(5, 2, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(5, 4, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(5, 8, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
					}
					break;
					
				case 6:
					if (tiles[i] != 6) {
						tempArray = swap(6, 3, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(6, 7, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
					}
					break;
					
				case 7:
					if (tiles[i] != 7) {
						tempArray = swap(7, 4, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(7, 6, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(7, 8, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
					}
					break;
					
				case 8:
					if (tiles[i] != 8) {
						tempArray = swap(8, 5, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
						
						tempArray = tiles.clone();
						tempArray = swap(8, 7, tempArray);
						tempState = new State(tempArray);
						tempState = calculations(heuristic, tempState);
						al.add(tempState);
					}
					break;
			}
			tempArray = tiles.clone();
		}
		
		for (int j = 0; j < al.size(); j++)
			al.get(j).setG((al.get(j).getG() + 1));
		
		intTemp = this.getF();
		for (int k = 0; k < al.size(); k++) {
			if (al.get(k).getF() < intTemp) {
				intTemp = al.get(k).getF();
				tempState = al.get(k);
			}
		}
		
		return tempState;
	}
	
	// makes swaps/inversions for tiles
	public static int[] swap(int x, int y, int[] tA) {
		int tempInt = tA[x];
		tA[x] = tA[y];
		tA[y] = tempInt;
		return tA;
	}
	
	// calculates heuristic and 'f' values for all children
	public State calculations(int h, State s) {
		if (h == 1) {
			s.calcH1();
			s.calcF(1);
		}
		else {
			s.calcH2();
			s.calcF(2);
		}
		return s;
	}
	
	public void printPuzzle() {
		
		for (int i = 0; i < tiles.length; i++)
			System.out.print(tiles[i] + " ");
		System.out.println("");
	}
}

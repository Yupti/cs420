package cs420;

public class State {

	private int[] tiles = new int[9];
	private State greaterChild, lesserChild; // might be unnecessary
	private int f, g = 0, h1 = 0, h2 = 0;
	
	public State(int[] tiles) {
		this.tiles = tiles;
	}
	
	public int getF() {
		return f;
	}
	
	public int getG() {
		return g;
	}
	
	public int getH1() {
		return h1;
	}
	
	public int getH2() {
		return h2;
	}
	
	public void calcG() {
		
	}
	
	public void calcH1() {
		
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] == 0) {
				// find a better way to go about this, skipping 0
			}
			else if (tiles[i] != i)
				h1++;
		}
	}
	
	public void calcH2() { // ACCOUNT FOR TILE 0, SHOULD NOT BE COUNTED
		
		int temp, counter, counter2;
		for (int i = 0; i < tiles.length; i++) { // cycle through all tiles
			counter = 0;
			counter2 = 0;
			temp = tiles[i];
			if (temp == 0) {
				// strange skip
			}
			else { // verify that this is correct
				for (int j = 0; j < tiles.length; j++) { // locates location, adding h1 score depending on manhattan distance
					if (temp == j) // breaks out of for loop when locates target location
						break;
					else {
						counter++;
						if (counter > 2)
							counter = 0;
							counter2++;
					}
				}
		}
			h2 += (counter + counter2);
		}
	}
}

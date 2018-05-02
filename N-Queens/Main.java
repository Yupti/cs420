package n_queens;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		Random r = new Random();
		int[] board = new int[8];
		int randIndex, randCol;
		
		for (int i = 0; i < 8; i++) 
			al.add(i);
		
		for (int i = 0; i < 8; i++) { // generating board
			randIndex = r.nextInt(al.size());
			randCol = al.remove(randIndex);
			board[i] = randCol;
		}
		
		Queen q = new Queen();
		int[] test;
		test = q.getBoard();
		/*int[] felix = {3, 5, 0, 4, 1, 7, 2, 6};
		Queen f = new Queen(felix);
		f.printBoard();
		f.collisions();*/
	}
	
}

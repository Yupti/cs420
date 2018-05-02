import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	static Scanner keyboard = new Scanner(System.in);
	public static void main(String[] args) {
		
		int choice, choice2;
		int[] puzzle = new int[9];
		boolean running = true;
		
		Puzzle p = new Puzzle();
		State start;
		
		while (running) {
			menu();
			choice = keyboard.nextInt();
			
			switch (choice) {
				case 1:
					puzzle = puzzleCreation(1);
					start = new State(puzzle);
					menu2();
					choice2 = keyboard.nextInt();
					if (choice2 != 1 && choice2 != 2) 
						System.out.println("Incorrect choice made, please try again.");
					else
						p.aStar(start, choice2);
					break;
				case 2:
					puzzle = puzzleCreation(2);
					start = new State(puzzle);
					menu2();
					choice2 = keyboard.nextInt();
					if (choice2 != 1 && choice2 != 2) 
						System.out.println("Incorrect choice made, please try again.");
					else
						p.aStar(start, choice2);
					break;
				case 3:
					running = false;
					System.out.println("Thank you for running the program!");
					break;
				default:
					System.out.println("Incorrect choice made, please try again.");
					break;
			}
			System.out.println("");
		}
		
		keyboard.close();

	}
	
	public static void menu() {
		System.out.println("Please enter a choice:\n1.) User-input values\n2.) Randomly generated values\n3.) Exit program");
	}
	
	public static void menu2() {
		System.out.println("Please enter which heuristic to test:\n1.) Heuristic 1\n2.) Heuristic 2");
	}
	
	public static int[] puzzleCreation(int input) {
		
		ArrayList<Integer> aList = new ArrayList<Integer>();
		int[] temp = new int[9];
		int[] placeholder = {0,1,2,3,4,5,6,7,8};
		switch (input) {
			case 1: // user input
				for (int i = 0; i < temp.length; i++) {
					System.out.println("Enter value for tile " + i);
					temp[i] = keyboard.nextInt();
				}
				break;
				
			case 2: // randomly generated values
				for (int i = 0; i < placeholder.length; i++)
					aList.add(placeholder[i]);
				Collections.shuffle(aList);  
				for (int j = 0; j < temp.length; j++)
					temp[j] = aList.get(j);
				break;
				
			default:
				System.out.println("Incorrect choice used, please try again.");
				break;
		}
		
		return temp;
	}
}

package cs420;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.PriorityQueue;
import java.util.Scanner;

public class Puzzle {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		// for taking input of entered string and checking if contents are valid
		/*int[] test = {2,4,1,7,3};
		Arrays.sort(test);
		for (int i = 0; i < test.length; i++)
			System.out.println(test[i]);*/
		
		int[] test = {0,1,2,3,4,5,9,7,8};
		/*if (isValid(test))
			System.out.println("Success");
		else
			System.out.println("Failure");*/
		/*for (int i = 0; i < test.length; i++)
			System.out.println(test[i]);*/
		
		keyboard.close();
		//puzzleCreation(2);
		
		// test

	}
	
	// if user's input is not good, need to have condition to check contents (i.e., sum is 0
	public static int[] puzzleCreation(int input) {
		
		Scanner keyboard = new Scanner(System.in);
		ArrayList<Integer> aList = new ArrayList<Integer>();
		int[] temp = new int[9];
		int[] placeholder = {0,1,2,3,4,5,6,7,8};
		switch (input) {
			case 1: // user input
				for (int i = 0; i < temp.length; i++) {
					System.out.println("Enter value for tile " + i);
					temp[i] = keyboard.nextInt();
				}
				
				/*for (int j = 0; j < temp.length; j++)
					aList.add(temp[j]);*/
				break;
			case 2: // random
				for (int i = 0; i < placeholder.length; i++)
					aList.add(placeholder[i]);
				Collections.shuffle(aList);
				for (int j = 0; j < temp.length; j++)
					temp[j] = aList.get(j);
				/*//test
				for (int j = 0; j < list.size(); j++)
					System.out.println(list.get(j));*/
				break;
			default:
				System.out.println("Incorrect choice used, please try again.");
				break;
		}
		
		keyboard.close();
		return temp;
	}
	
	public static boolean isValid(int[] arr) {
		
		int[] test = new int[9];
		for (int i = 0; i < arr.length; i++) {
			test[i] = arr[i];
		}
		Arrays.sort(test);
		for (int i = 0; i < test.length; i++) {
			if (test[i] != i)
				return false;
		}
		return true;
	}
}

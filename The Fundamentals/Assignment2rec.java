package lab1;

import java.util.Scanner;

public class Assignment2rec {

	public static void recursive(char[] a, int index) {

		if (index < 0) { // first index = 0
			return;
		} else {
			System.out.print(a[index]); // print char array backwards
			recursive(a, index - 1); // next character
		}
	}

	public static void main(String[] args)  {

		Scanner s = new Scanner(System.in);
		System.out.println("Enter characters: ");

		char[] a = s.nextLine().toCharArray(); // put all characters from stdin into a char array
		System.out.print("Your input reversed is: ");
		recursive(a, (a.length-1)); // send character at last to recursive

		s.close();
	}
}

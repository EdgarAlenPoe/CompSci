import java.util.List;
import java.io.InputStream;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Shuffle {

	public static void main(String[] args) {
		Shuffle thing = new Shuffle();
		System.out.println(thing.shuffleInput(System.in));
	}

	public String shuffleInput(InputStream input) {
		Scanner scan = new Scanner(input);
		StringBuilder output = new StringBuilder();
		
		while (scan.hasNext()) {
			output.append(shuffleWord(scan.next()) + " ");
		}
		return output.toString();
	}
	
	public String shuffleInput(String input) {
		Scanner scan = new Scanner(input);
		StringBuilder output = new StringBuilder();
		
		while (scan.hasNext()) {
			output.append(shuffleWord(scan.next()) + " ");
		}
		return output.toString();
	}
	
	public String shuffleWord(String s) {
		
		Random rand = new Random();
		List<Character> set = new LinkedList<Character>();
		
		for (int x = 1; x < (s.length() - 1);x++) {
//			System.out.println(s.charAt(x));
			set.add(s.charAt(x));
		}
		
		StringBuilder newString = new StringBuilder(s);
		for (int x = 1; x < (s.length() - 1); x++) {
//			System.out.println(newString);
//			System.out.println(x);
			String next = String.valueOf((char) set.remove(rand.nextInt(set.size())));
//			System.out.println(next);
			newString.replace(x, x+1, next);
		}
		return newString.toString();
	}

}

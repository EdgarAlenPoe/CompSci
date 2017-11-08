import java.util.List;
import java.io.InputStream;

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
		scan.useDelimiter(" ");
		StringBuilder output = new StringBuilder();
		String next;
		while (scan.hasNext()) {
			next = scan.next();
			output.append(shuffleWord(next) + " ");
		}
		scan.close();
		return output.toString();
	}
	
	public String shuffleInput(String input) {
		Scanner scan = new Scanner(input);
		StringBuilder output = new StringBuilder();
		
		while (scan.hasNext()) {
			output.append(shuffleWord(scan.next()) + " ");
		}
		scan.close();
		return output.toString();
	}
	
	public String shuffleWord(String s) {
		
		Random rand = new Random();
		List<Character> set = new LinkedList<Character>();
		List<Integer> literals = new LinkedList<Integer>();
		if (s.length() < 2) {
			return s;
		}
		else if (s.substring(0, 1).matches("[- .,';:!?\\\n]")) {
			return s.substring(0,1) + shuffleWord(s.substring(1));
		}
		else if (s.substring(s.length()-1).matches("[- .,';:!?\\\n]")) {
			return shuffleWord(s.substring(0,s.length()-1)) + s.substring(s.length()-1);
		}
		else {
			for (int x = 1; x < (s.length() - 1);x++) {
				if (!String.valueOf(s.charAt(x)).matches("[- .,';:!?\\\n]"))set.add(s.charAt(x));
				else literals.add(x);
			}
			
			StringBuilder newString = new StringBuilder(s);
			for (int x = 1; x < (s.length() - 1); x++) {
				if (!literals.contains(x)) {
					String next = String.valueOf((char) set.remove(rand.nextInt(set.size())));
					newString.replace(x, x+1, next);
				}
			}
			return newString.toString();
		}
	}

}

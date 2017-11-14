import java.util.List;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Shuffle { //TODO fix linebreak handling. words are being scrambled across linebreaks with changing length.

	public static void main(String[] args) throws HeadlessException, IOException {
		Shuffle thing = new Shuffle();
//		System.out.println(thing.shuffleWord("laws\nof"));
//		Pattern pattern = Pattern.compile(".*?([- .,';:!?\\n\\r]).*", Pattern.DOTALL);
//		System.err.println(pattern.matcher("avntioia,\n" + 
//				"\n" + 
//				"  \n" + 
//				"there").matches());
		JOptionPane.showMessageDialog(null, "This program will take a text file input, shuffle all words, then output into a new file in the same directory.");
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		
		File outFile = new File(file.getParent() + File.separator + JOptionPane.showInputDialog("Enter name for output file."));
		
		if (!outFile.createNewFile()) {
			JOptionPane.showConfirmDialog(null, "File already exists. Overwrite it?");
		}
		
		PrintStream output = new PrintStream(outFile);
		
		
		
		try {
			output.println(thing.shuffleInput(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		finally {
			output.close();
		}
		
	}
	
	private Pattern pattern = Pattern.compile(".*?([- .,';:!?\\n\\r]).*", Pattern.DOTALL);

	public String shuffleInput(InputStream input) {
		Scanner scan = new Scanner(input);
		scan.useDelimiter(" ");
		StringBuilder output = new StringBuilder();
		String next;
		int wordNum = 0;
		while (scan.hasNext()) {
			next = scan.next();
//			System.err.println(wordNum);
			wordNum++;
			String nextOut = shuffleWord(next);
			output.append(nextOut + " ");
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
		Matcher match = pattern.matcher(s);
		
		if (s.length() < 3) {
//			System.err.println("debug, skipping");
			return s;
		}
		else if (match.matches()) {
			
			
//			System.err.println("debug, splitting");
			int splitPoint = match.start(1);
//			System.err.println("splitPoint: " + match.group(1));
//			System.err.println("getting first part");
			String firstPart = shuffleWord(s.substring(0, splitPoint));
//			System.err.println("getting last part");
//			System.err.println(s.substring(splitPoint + 1));
			String lastPart = shuffleWord(s.substring(splitPoint + 1));
			return  firstPart + match.group(1) + lastPart;
		}
		else {
//			System.err.println("debug, scrabbling");
			
			for (int x = 1; x < (s.length() - 1);x++) { //[- .,';:!?\\n\\r]
				if (!String.valueOf(s.charAt(x)).matches("[- .,';:!?\\n\\r]")) {
//					System.err.println("Inserting scramble");
					set.add(s.charAt(x));
				}
				else  {
					System.err.println("Inserting literal: " + s.charAt(x));
					literals.add(x);
				}
			}
			
			StringBuilder newString = new StringBuilder(s);
			for (int x = 1; x < (s.length() - 1); x++) {
				if (!literals.contains(x)) {
					String next = String.valueOf((char) set.remove(rand.nextInt(set.size())));
					newString.replace(x, x+1, next);
				}
			}
			if (newString.equals(s)) {
				return shuffleWord(s);
			}
			else {
				return newString.toString();
			}
		}
	}

}

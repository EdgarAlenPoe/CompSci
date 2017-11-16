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

/**
 * Simple program that will take an input file, then shuffle the words in the ways described here: <a href = "https://en.wikipedia.org/wiki/Typoglycemia">https://en.wikipedia.org/wiki/Typoglycemia</a>
 * @author Edgar Schafer
 *
 */
public class Shuffle { 

	public static void main(String[] args) throws HeadlessException, IOException {
		
		JOptionPane.showMessageDialog(null, "This program will take a text file input, shuffle all words, then output into a new file in the same directory.");
		
		/*
		 * Prompts user for a target file.
		 */
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		
		/*
		 * Prompts user for a name for output file.
		 */
		File outFile = new File(file.getParent() + File.separator + JOptionPane.showInputDialog("Enter name for output file."));
		
		/* 
		 * If the file already exists, prompt user about overwriting it.
		 */
		boolean run = true;
		
		if (!outFile.createNewFile() && 0 != JOptionPane.showConfirmDialog(null, "File already exists. Overwrite it?")) {
			run = false;
		}
		
		/*
		 * If everything is good, shuffle away, else, tell the user you exited.
		 */
		if (run) {
			PrintStream output = new PrintStream(outFile);
			Shuffle thing = new Shuffle();
			
			/*
			 * Handles any potential problems with writing to the output, and closes the file.
			 */
			try {
				output.println(thing.shuffleInput(new FileInputStream(file))); // Outputs and data at once, could be optimized
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				output.close();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Program aborted");
		}
		
		
	}
	
	private Pattern pattern = Pattern.compile(".*?([- .,';:!?\\n\\r\\t]).*", Pattern.DOTALL);

	/**
	 * <p>Takes a text input and shuffles all characters within words not counting first and last characters. (Ex: "Hello" may become "Hlleo" or "Hlelo". 
	 * First and last letters always preserved.) Three letter words are not shuffled, as there is no new form for them to take.</p><p>Also preserves punctuation in output. (Ex: "Welcome! How are you today?" may become "Wlmcoee! How are you tdaoy?")</p>
	 * @param input
	 * @return
	 */
	public String shuffleInput(InputStream input) {
		Scanner scan = new Scanner(input);
		scan.useDelimiter(" ");
		StringBuilder output = new StringBuilder();
		String next;
		while (scan.hasNext()) {
			next = scan.next();
			String nextOut = shuffleWord(next);
			output.append(nextOut + " ");
		}
		scan.close();
		return output.toString();
	}
	
	/**
	 * Same as shuffleInput, but handles string inputs.
	 * @param input String to be shuffled
	 * @return shuffled string
	 */
	public String shuffleInput(String input) {
		Scanner scan = new Scanner(input);
		StringBuilder output = new StringBuilder();
		
		while (scan.hasNext()) {
			output.append(shuffleWord(scan.next()) + " ");
		}
		scan.close();
		return output.toString();
	}
	
	/**
	 * Takes a string and shuffles letters within it. Recursively splits on punctuation to avoid awkward shuffles across periods and newlines.
	 * @param s String to be shuffled
	 * @return shuffle output
	 */
	public String shuffleWord(String s) {
		
		
		Matcher match = pattern.matcher(s);
		
		/*
		 *  Optimization and filter. If really short words enter they will break system, and three letter words can't be scrammbled.
		 */
		if (s.length() < 3) { 
			return s;
		}
		/* 
		 * Checks if punctuation is embedded with in word, then recursively breaks down problem to shuffle the words around punctuation
		 * to keep formatting the same
		 */
		else if (match.matches()) {  
			int splitPoint = match.start(1);
			String firstPart = shuffleWord(s.substring(0, splitPoint));
			String lastPart = shuffleWord(s.substring(splitPoint + 1));
			return  firstPart + match.group(1) + lastPart;
		}
		/*
		 * Main interesting bit. Does actual shuffling of words
		 */
		else {
			//TODO potential for optimizing four letter words ('four' could only become 'fuor', which could have a simpler algorithm)
			
			// Resources for scrambling
			Random rand = new Random();
			List<Character> set = new LinkedList<Character>();
			List<Integer> literals = new LinkedList<Integer>();
			
			// inserts characters between first and last letters into a list
			for (int x = 1; x < (s.length() - 1);x++) { 
				set.add(s.charAt(x));
			}
			
			StringBuilder newString = new StringBuilder(s);
			
			// Randomly choose (and remove from list) a letter in the list for each spot in word.
			for (int x = 1; x < (s.length() - 1); x++) {
				if (!literals.contains(x)) {
					String next = String.valueOf((char) set.remove(rand.nextInt(set.size())));
					newString.replace(x, x+1, next);
				}
			}
			// Quality check, ensure shuffled word isn't same as old word
			String output = newString.toString();
			
			if (s.equals(output)) {
				return shuffleWord(s);
			}
			else {
				return output;
			}
		}
	}

}

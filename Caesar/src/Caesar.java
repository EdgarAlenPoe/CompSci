import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Caesar {

	public static void main(String[] args) throws FileNotFoundException {
		
		String filename = JOptionPane.showInputDialog("Enter the file name:");
		
		Scanner file = new Scanner(new File("/home/student/" + filename));
		String aLine = file.nextLine();
		System.out.println(aLine);
		aLine = file.nextLine();
		System.out.println(aLine);
		aLine = file.nextLine();
		System.out.println(aLine);
		aLine = file.nextLine();
		System.out.println(aLine);
		
		Cipher cipher = new Cipher();
//		System.out.println(cipher.encode("DOG", 1));
//		System.out.println(cipher.encode("DOG", 2));
//		System.out.println(cipher.encode("DOG", 26));
//		System.out.println(cipher.encode("dog", 1));
//		System.out.println(cipher.encode("dog", 2));
//		System.out.println(cipher.encode("dog", 26));
//		cipher = new Cipher("abcdefg");
//		System.out.println(cipher.encode("DOG", 1));
//		System.out.println(cipher.encode("DOG", 2));
//		System.out.println(cipher.encode("DOG", 26));
//		System.out.println(cipher.encode("dog", 1));
//		System.out.println(cipher.encode("dog", 2));
//		System.out.println(cipher.encode("dog", 26));
		Object optionPicked;
		
		do {
			Object[] options = { "Encode","Decode", "Crack", "Exit" };
			optionPicked = JOptionPane.showInputDialog(null, "Pick an option", "Option menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			System.out.println(optionPicked);
			
			
			
			if (optionPicked.equals("Encode")) {
				String text = JOptionPane.showInputDialog("Enter the plain text:");
				System.out.println(text);
				String key = JOptionPane.showInputDialog("Enter the Cipher Key:");
				System.out.println(key);
				JOptionPane.showMessageDialog(null,cipher.encode(text, Integer.valueOf(key)));
			}
		} while (!optionPicked.equals("Exit"));
		
		
		
		
	}

}

class Cipher {
	
	String alphabet;
	
	public Cipher() {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	}
	
	public Cipher(String a) {
		alphabet = a;
	}
	
	public String encode(String in, int shift) {
		char string[] = in.toCharArray();
		// iterate over all items in array to change chars one at a time
		for (int x = 0; x < string.length; x++) {
			// algorithm works by first adding shift to char value (A = 0, shift = 2, 0 + 2 = 2 = C),
			// then values above and below range of alphabet are normalized into range, similar to how 0 = 360 = 720 with angle measure, last term + 1 = 0 
			if (alphabet.lastIndexOf(string[x]) != -1) {
				int temp = alphabet.indexOf(string[x]) + shift;
				temp = temp % (alphabet.length() - 1);
				string[x] = alphabet.charAt(temp);
			}
			
		}
		
		
//		// iterate over all items in array to change chars one at a time
//		for (int x = 0; x < string.length; x++) {
//			// handling capital vs lowercase. Case remains constant as shift happens. Other characters remain unchanged.
//			// Both cases use same algorithm on different ranges
//			if (string[x] >= 'A' && string[x] <= 'Z') {
//				// algorithm works by first adding shift to char value (A = 65, shift = 2, 65 + 2 = 67 = C),
//				// then values above and below range of A to Z are normalized into range, similar to how 0 = 360 with angles, Z + 1 = A
//				int temp = string[x] + shift; 
//				temp = ((temp - 'A') % ('Z' - 'A')) + ('A' - 1);
//				string[x] = (char) temp;
//			}
//			else if (string[x] >= 'a' && string[x] <= 'z') {
//				int temp = string[x] + shift;
//				temp = ((temp - 'a') % ('z' - 'a')) + ('a' - 1);
//				string[x] = (char) temp;
//			}
//		}
		return new String(string);
	}
}

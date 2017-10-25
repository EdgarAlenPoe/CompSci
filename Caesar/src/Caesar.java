
public class Caesar {

	public static void main(String[] args) {
		Cipher cipher = new Cipher();
		System.out.println(cipher.encode("DOG", 1));
		System.out.println(cipher.encode("DOG", 2));
		System.out.println(cipher.encode("DOG", 26));
		System.out.println(cipher.encode("dog", 1));
		System.out.println(cipher.encode("dog", 2));
		System.out.println(cipher.encode("dog", 26));
	}

}

class Cipher {
	private String alphabet;
	
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
			// handling capital vs lowercase. Case remains constant as shift happens. Other characters remain unchanged.
			// Both cases use same algorithm on different ranges
			if (string[x] >= 'A' && string[x] <= 'Z') {
				// algorithm works by first adding shift to char value (A = 65, shift = 2, 65 + 2 = 67 = C),
				// then values above and below range of A to Z are normalized into range, similar to how 0 = 360 with angles, Z + 1 = A
				int temp = string[x] + shift; 
				temp = ((temp - 'A') % ('Z' - 'A')) + ('A' - 1);
				string[x] = (char) temp;
			}
			else if (string[x] >= 'a' && string[x] <= 'z') {
				int temp = string[x] + shift;
				temp = ((temp - 'a') % ('z' - 'a')) + ('a' - 1);
				string[x] = (char) temp;
			}
		}
		return new String(string);
	}
}

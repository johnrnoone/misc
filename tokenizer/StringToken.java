package tokenizer;

import java.util.Scanner;
import java.util.StringTokenizer;

public class StringToken {
	public static void main (String args[]) {
		// Assume s contains a string of words

		System.out.println ("Enter the string of tokens...");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		in.close();

		String longestWord = "";
		StringTokenizer st = new StringTokenizer(s, " ,\t");
		while (st.hasMoreTokens()) {
			String w = st.nextToken();
			System.out.println ("nextToken returns " + w);
			if (w.length() > longestWord.length()) {
				longestWord = w;
			}
		}
		System.out.println ("longestWord = " + longestWord);

		String input[] = new String[4];
		input[0] = "m"; 
		input[1] = "o"; 
		input[2] = "o"; 
		input[3] = "d";
		String [] output = new String[4];
		output = reverseString (input);	
	}
	
	private static String[] reverseString (String[] in) {
		int strLen = in.length;
        String[] out = new String[4];
		int strlen = in.length; 
        for (int i=0; i <= strlen-1; i++) {
        	out[i] = in[strlen-i-1];
        }
        return out;
	}
}

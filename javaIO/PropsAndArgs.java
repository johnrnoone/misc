package javaIO;

import java.util.Properties;

public class PropsAndArgs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int x = 0;
		for (String s : args){
			System.out.print ("arg"+ (x++) +" : "+ s+", ");
		}
		System.out.printf("\n");

		Properties p = System.getProperties();
		if (p.containsKey("myProp")) {
			System.out.println ("myProp = " + p.getProperty("myProp"));
		} else {
			System.out.println ("myProp is not a property");
		}

	}

}

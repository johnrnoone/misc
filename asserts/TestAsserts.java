package asserts;
//
// This test relies on assertions being enabled by passing the -ea argument to the JVM
// Use 'run configurations' tab
//

public class TestAsserts {

	public static void main(String[] args) {

		String s = null;

		try {
			System.out.println ("recurseRev(abc) = " + recurseRev ("abc"));
		}catch (AssertionError e) {
			System.out.println("caught an AssertionError");e.printStackTrace();
		}catch (Exception e) {
			System.out.println("recurseRev caught an Exception"); e.printStackTrace();
		}

		try {
			System.out.println ("reverse(null) = " + reverse (s));
		}catch (AssertionError e) {
			System.out.println("caught an AssertionError");e.printStackTrace();
		}catch (Exception e) {
			System.out.println("caught an Exception");
		}

		try {
			System.out.println ("reverse(\"\") = " + reverse (""));
		}catch (AssertionError e) {
			System.out.println("caught an AssertionError");e.printStackTrace();
		}catch (Exception e) {
			System.out.println("caught anException");
		}

		try {
			System.out.println ("reverse(\"abc\") = " + reverse ("abc"));
		} catch (NullPointerException npe) {
			System.out.println("caught a NullPointerException");
		}

	}

	private static String reverse (String s) {
		String reverseStr = "";

		assert (s != null): "s is null";
		assert (s.length()>0): "s is 0";
		reverseStr = new String();
		for (int i = s.length()-1; i>=0; i--){
			reverseStr += s.charAt(i);
		}
		return reverseStr;

	}

	private static String recurseRev (String s) {	
		if (s.length() > 0) {
			String first = s.substring(0, 1);
			String rest = s.substring(1);
			return (recurseRev(rest) + first);
		} else {
			return s;
		}
	}

}

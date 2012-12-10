package variableArguments;

  class VariableArguments {
	
	public static void main (String[] args) {
		
		System.out.println ("This is VariableArguments.main()");
		for (String arg : args) {
			System.out.print ("arg = " + arg + " ");
		}
		System.out.println();
		printLine (args);
	}
	
	public static void printLine(String ... strings) {
		System.out.println ("This is printLine()");
		for (String string : strings) {
			System.out.println ("printLine() gets string = " + string);
		}
	}

}

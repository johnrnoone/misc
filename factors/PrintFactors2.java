package factors;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class PrintFactors2 {
//	static Logger logger = Logger.getLogger(PrintFactors.class);
	static Integer numberToFactor = 0;
	static final boolean OUTPUT_STRINGS = false;
	static boolean ENABLE_OPTIONS = false;
	public static ArrayList<String> factorSets = new  ArrayList<String>();
	public static ArrayList<ArrayList<Integer>> intFactorSets = new  ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) {
//		PropertyConfigurator.configure("log4j.properties");

//		logTest();
//		logger.info("PrintFactors is executing");

		try {
			testAsserts();
		}catch (AssertionError e) {
			System.out.println("caught an AssertionError");e.printStackTrace();
		}catch (Exception e) {
			System.out.println("caught an Exception");
		}
		
		Integer numberOfFactorSets = run(args);
		
	}
	
	static Integer run(String[] args) {
		
		try {
			numberToFactor = getArgument (args);
		} catch (IllegalArgumentException iae) {
			if (!ENABLE_OPTIONS) System.out.println ("Usage: 'java -cp . factors.PrintFactors number' ");
			System.exit(0);
		}

        long startTime = System.currentTimeMillis();
		String outStr = String.format("%d = ",numberToFactor);
	    computeFactors (numberToFactor, outStr);
        long endTime = System.currentTimeMillis();
	    
	    if (OUTPUT_STRINGS) {
		   printStrings();
	    } else {
		   printStrings2();
	    }
		
        printTimeDuration (startTime, endTime);        
		return intFactorSets.size();
	}
	
	static void computeFactors (Integer numberToFactor, String outStr) {
		intFactorSets.clear(); factorSets.clear();
		ArrayList<Integer> first = new ArrayList<Integer>();
		first.add(numberToFactor); first.add(1);
		intFactorSets.add(first);
		recursePrintFactors (numberToFactor, outStr);
	}

	static void printStrings() {
		System.out.println ("Integer " + numberToFactor + " contains " + intFactorSets.size() + " factor sets");
		Iterator<String> it = factorSets.iterator();
		while (it.hasNext()) {
			System.out.println (it.next());
		}
	}
	static void printStrings2() {
		System.out.println ("Integer " + numberToFactor + " contains " + intFactorSets.size() + " factor sets");
		Iterator<ArrayList<Integer>> it = intFactorSets.iterator();
		while (it.hasNext()) {
			System.out.println (it.next());
		}
	}

	static void recursePrintFactors (Integer numberToFactor, String oldOut) {
		String out = new String();

		ArrayList<Integer> divisors = getDivisors (numberToFactor);
		for (int i = 0; i<divisors.size(); i++){
			out = oldOut;

			Integer divisor = divisors.get(i);	

			if( isMultiple (divisor)) {		
				//	if this divisor isn't prime, add a factor set ending in this divisor and call recursePrintFactors 
				//  again to continuing factoring it
				String newStr = new String(); newStr = String.format("%s %d x %d", oldOut, divisor, numberToFactor/divisor );
				saveFactors (newStr);
				out = String.format("%s %d x", out, numberToFactor/divisor);
				recursePrintFactors (divisor, out);

			} else {
				// if this divisor is prime (not a multiple) add it to complete the factor set 
				String newStr = new String(); newStr = String.format("%s %d x %d", out,divisor, numberToFactor/divisor );
				saveFactors (newStr);
			}
		}
	}

	static void saveFactors (String str) {
		ArrayList<Integer>testVector = new ArrayList<Integer>();
		factorSets.add(str); 
		str = str.replaceAll("[=x]", "");
		Scanner scan = new Scanner (str);

		Integer field, fieldCount = 0;
		while (scan.hasNextInt()) {
			field = scan.nextInt();
			if (fieldCount++ > 0) testVector.add(field);
		}
		if (!isDuplicate (testVector, intFactorSets)) intFactorSets.add(testVector);
	}

	static boolean isDuplicate (ArrayList<Integer>candidate, ArrayList<ArrayList<Integer>>testVectors) {
		Collections.sort(candidate);
		for (int i = 0; i<testVectors.size(); i++) {
			ArrayList<Integer>testFactors = testVectors.get(i);
		    if (compareLists (candidate, testFactors)) return true;
		}
		return false;
	}

	static boolean compareLists (ArrayList<Integer>candidateA, ArrayList<Integer>candidateB){
		for (int j=0; j<candidateA.size(); j++){
			if (!candidateA.get(j).equals(candidateB.get(j))) return false;
		}
		return true;
	}

	static ArrayList<Integer> getDivisors (Integer candidate) {
		ArrayList<Integer>testVectors = new ArrayList<Integer>();
		Integer limit = candidate / 2; 
		if (limit <= 1) return testVectors;
		for (int i = 2; i<=limit; i++) {
			if ((candidate%i) == 0) testVectors.add(candidate/i);
		}

		return testVectors;
	}

	static Integer getArgument (String[] args) throws IllegalArgumentException {
		// determine if assertions are enabled
		RuntimeMXBean RuntimemxBean = ManagementFactory.getRuntimeMXBean();
		List<String> aList=RuntimemxBean.getInputArguments();
		String option = new String();

		for(int i=0;i<aList.size();i++) {
			option = aList.get(i);
		    if (option.equals("-ea")) ENABLE_OPTIONS = true;
		}
		
		// get the number to factor
		int nargs = args.length;
		if (nargs == 1) {
			try {
				return Integer.valueOf(args[0]);
			} catch (NumberFormatException nfe) {
				throw new IllegalArgumentException ("String to integer conversion error.");
			}
		} else {
			throw new IllegalArgumentException ("No integer input");
		}
	}

	static boolean isMultiple (Integer candidate) {
		Integer limit = candidate / 2; 
		if (limit <= 1) return false;
		for (int i = 2; i<=limit; i++) {
			if ((candidate%i) == 0) return true;
		}
		return false;
	}		
	
	static void printTimeDuration (long start, long end) {
        float seconds = (end - start) / 1000F;       
        System.out.println("Duration is " + Float.toString(seconds) + " seconds.");
	}
	
	static void testAsserts() throws AssertionError {
		factorSets = new  ArrayList<String>();
		String[] args32 = {"32"};
		assert (run(args32) == 7): "factorization error, 32";
		if (ENABLE_OPTIONS) System.out.println ("factor(32), OK" );
		
		String[] args12 = {"12"};
		assert (run(args12) == 4): "factorization error, 12";
		if (ENABLE_OPTIONS) System.out.println ("factor(12), OK");

		String[] args96 = {"96"};
		assert (run(args96) == 19): "factorization error, 96";
		if (ENABLE_OPTIONS) System.out.println ("factor(96), OK" );
		
		String[] args100 = {"100"};
		assert (run(args100) == 9): "factorization error, 100";
		if (ENABLE_OPTIONS) System.out.println ("factor(100), OK" );
		
		String[] args256 = {"256"};
		assert (run(args256) == 22): "factorization error, 256";
		if (ENABLE_OPTIONS) System.out.println ("factor(256), OK");

		String[] args10000 = {"10000"};
		assert (run(args10000) == 109): "factorization error, 10000";
		if (ENABLE_OPTIONS) System.out.println ("factor(10000), OK" );

		String[] args100000 = {"100000"};
		assert (run(args100000) == 339): "factorization error, 100000";
		if (ENABLE_OPTIONS) System.out.println ("factor(100000), OK" );
		
		String[] args1000000 = {"1000000"};
		assert (run(args1000000) == 1043): "factorization error, 1000000";
		if (ENABLE_OPTIONS) System.out.println ("factor(1000000), OK" );
	}
}


package factors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class PrintFactors {
//	static Logger logger = Logger.getLogger(PrintFactors.class);
	static Integer numberToFactor = 0;
	public static ArrayList<ArrayList<Integer>> factorSets = new  ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) {
//		PropertyConfigurator.configure("log4j.properties");

		//		logTest();
//		logger.info("PrintFactors is executing");
		try {
			numberToFactor = getArgument (args);
		} catch (IllegalArgumentException iae) {
			System.out.println ("Usage: 'java -cp . factors.PrintFactors number' ");
			System.exit(0);
		}

		ArrayList<Integer> primes = getPrimes (numberToFactor);
		printProduct (primes);

		ArrayList<ArrayList<Integer>> resultsArray = computePrimeMultiples (primes);
		printMultiples (resultsArray);

		System.out.println ("\ngetMultiples test:");
		primes = new ArrayList<Integer>(); primes.add(2); primes.add(3); primes.add(5); primes.add(5);printProduct (primes);
		ArrayList<Integer> multiples = getMultiples (primes);
		System.out.print ("After getMultiples multiples: "); printProducts(multiples);

		Integer numberToFactor = 36;
		primes = getPrimes (numberToFactor);
		printProduct (primes);
		ArrayList<Integer> divisors = getDivisors (numberToFactor);
		System.out.print ("After getDivisors divisors: "); printProducts (divisors);
		multiples = getMultiples (primes);
		System.out.print ("After getMultiples multiples: "); printProducts(multiples);
		ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>> ();
		divisors = new ArrayList<Integer> ();
		String outStr = new String(); outStr = outStr.format("%d = ", numberToFactor);
		recursePrintFactors (numberToFactor, divisors, outStr);
		System.out.println ("After recursePrintFactors results: "); printMultiples(factorSets);

	}

	static void recursePrintFactors (Integer numberToFactor, ArrayList<Integer> oldFactors, String out) {
		Scanner scan = new Scanner (System.in);

		ArrayList<Integer> factors = new ArrayList<Integer>(); factors.addAll(oldFactors);
		System.out.println ("recursePrintFactors: factors = "+ factors + ", working on numberToFactor = " + numberToFactor); 
		System.out.println ("recursePrintFactors: out = " + out);
		String oldOut = out;
		
		Integer divisor;	

		ArrayList<Integer> divisors = getDivisors (numberToFactor);
		for (int i = 0; i<divisors.size(); i++){
			ArrayList<Integer> newFactors = new ArrayList<Integer>();
			
			divisor = divisors.get(i);	
			System.out.println ("divisors = "+ divisors + ", working on " + divisor); 

			if( isMultiple (divisor)) {		
				System.out.println ("recursePrintFactors thinks "+divisor+" IS a multiple"); 
				newFactors.addAll(oldFactors); 
				newFactors.add(divisor); newFactors.add(numberToFactor / divisor);
				String newStr = new String(); newStr = newStr.format("%s %d x %d", oldOut, divisor,numberToFactor / divisor );
				System.out.println (newStr);
				factorSets.add(newFactors);
				System.out.print ("recursePrintFactors adds newFactors record :"); printProducts (newFactors);

				factors.add(numberToFactor / divisor); out = oldOut + (numberToFactor / divisor) + " x ";
				System.out.print ("recursePrintFactors adds factor to factors record: "); printProducts (factors);
				System.out.print ("recursePrintFactors calls itself with new divisor "+divisor+",  Continue?"); scan.nextLine(); System.out.println();
				recursePrintFactors ( divisor, factors, out);

			} else {

				factors.add(divisor); factors.add(numberToFactor/divisor); factorSets.add(factors);
				String newStr = new String(); newStr = newStr.format("%s %d x %d", out,divisor,numberToFactor / divisor );
				System.out.println (newStr);
				System.out.print ("recursePrintFactors thinks "+divisor+" IS NOT a multiple, insert factors record: ");printProducts (factors);
				System.out.print ("recursePrintFactors completes factor,  Continue?"); scan.nextLine(); System.out.println();
			}
		}
	}

	static Integer getArgument (String[] args) throws IllegalArgumentException {
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

	static ArrayList<Integer> getPrimes (Integer numberToFactor) {
		ArrayList<Integer> primes = new ArrayList<Integer>();

		int cursor = 2;
		int ntf = numberToFactor.intValue();
		while (!completeFactors (primes, numberToFactor)) {
			if (ntf == (ntf/cursor) * cursor) {
				primes.add(cursor); ntf = (ntf/cursor);
			} else {
				cursor++;
			}
		}
		Collections.sort(primes);
		return primes;
	}
	static void printFactors (String prefix, Integer numberToFactor) {
		ArrayList<Integer> factors = new ArrayList<Integer>();

		int cursor = 2;
		int ntf = numberToFactor.intValue();
		while (!completeFactors (factors, numberToFactor)) {
			printProduct (factors);
			if (ntf == (ntf/cursor) * cursor) {
				prefix = prefix + String.valueOf(cursor) + " x ";			
				System.out.println (prefix + ntf/cursor);
				factors.add(cursor); ntf = (ntf/cursor);
			} else {
				cursor++;
			}
		}
		Collections.sort(factors);
		printFactors (factors);
	}

	static boolean completeFactors (ArrayList<Integer>factors, Integer numberToFactor) {
		Integer product = new Integer(1);
		for (int i = 0; i<factors.size(); i++) {
			product = product * factors.get(i);
		}
		if (product == numberToFactor) return true;
		else return false;    	
	}

	static void printMultiples (ArrayList<ArrayList<Integer>> multiples) {
		System.out.println ("printMultiples ("+multiples.size()+") : ");
		for (int i = 0; i<multiples.size(); i++) {
			ArrayList<Integer> factors = multiples.get(i);
			printProduct (factors);
		}
	}

	static ArrayList<ArrayList<Integer>> computePrimeMultiples (ArrayList<Integer> primes) {
		ArrayList<ArrayList<Integer>> multiples = new ArrayList<ArrayList<Integer>>();

		int n = primes.size();
		for (int r = 1; r<n; r++) {
			ArrayList<ArrayList<Integer>> nCr = nChooseR (primes, r);
			multiples.addAll(nCr);
			//			formFactors (multiples, nCr);
		}
		ArrayList<ArrayList<Integer>> finalAnswer = qualityCheck (multiples);  //printMultiples (finalAnswer);
		return finalAnswer;
	}

	static void formFactors (ArrayList<ArrayList<Integer>> multiples, ArrayList<ArrayList<Integer>> nCr) {
		ArrayList<ArrayList<Integer>> testArray = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> testVector = new ArrayList<Integer>();
		multiples.addAll(nCr);

		for (int i = 0; i<nCr.size(); i++) {
			testVector = new ArrayList<Integer>();
			testVector = nCr.get(i); 
			Integer initialFactor = 1;
			for (int j = 0; j<testVector.size(); j++) {
				initialFactor = initialFactor * testVector.get(j);
				testVector.add(numberToFactor / getProduct(testVector));
				testArray.add(testVector);
			}
		}
	} 

	static ArrayList<Integer> getMultiples (ArrayList<Integer> primes) {
		ArrayList<Integer> combos = new ArrayList<Integer>();
		ArrayList<Integer> thisVector = new ArrayList<Integer>();
		//		Scanner scan = new Scanner (System.in);


		//		System.out.print ("getMultiples gets primes: "); printProduct(primes);

		for (int r = 1; r<=primes.size(); r++) {
			ArrayList<ArrayList<Integer>> nCr = nChooseR (primes, r);
			//			System.out.println ("getMultiples calls nChooseR with r="+r +", nCr.size = " + nCr.size()); printMultiples (nCr);

			for (int i = 0; i<nCr.size(); i++) {
				//				System.out.print ("getMultiples loops nCr with i,r="+i+","+r +" "); 
				thisVector = nCr.get(i);
				//				System.out.print ("getMultiples loops nCr with i,r="+i+","+r +" "); printProducts (thisVector);
				Integer combo = 1;
				for (int k = 0; k<r; k++) {
					combo = combo * thisVector.get(k);
					//					System.out.print ("nChooseR gets combo, k = "+combo+", "+k+", Continue?"); scan.nextLine(); System.out.println();
				}
				//				System.out.print ("nChooseR computes combo = "+combo+", Continue?"); scan.nextLine(); System.out.println();
				if (!isDuplicate (combo, combos)) combos.add(combo);
			}	
		}

		System.out.print ("getMultiples returns : ");
		for (int i = 0; i<combos.size(); i++) System.out.print (combos.get(i) + " ");
		System.out.println();

		return combos;
	}

	static ArrayList<ArrayList<Integer>> qualityCheck (ArrayList<ArrayList<Integer>> multiples) {
		ArrayList<ArrayList<Integer>> testArray = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> testVector = new ArrayList<Integer>();
		testVector.add(1); testVector.add(numberToFactor); testArray.add(testVector);

		for (int i = 0; i<multiples.size(); i++) {
			testVector = new ArrayList<Integer>();
			testVector = multiples.get(i); 
			testVector.add(numberToFactor / getProduct(testVector));
			if (!isDuplicate(testVector, testArray)) {
				testArray.add(testVector);
			}
		}
		return testArray;
	}

	static ArrayList<ArrayList<Integer>> nChooseR (ArrayList<Integer> primes, Integer r) {
		Integer n = primes.size();
		ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> permutedPrimes = permuteArrayList(primes);

		for (int i = 0; i<permutedPrimes.size(); i++) {
			ArrayList<Integer> candidate = new ArrayList<Integer>();
			for (int j = 0; j<r; j++) {
				candidate.add(permutedPrimes.get(i).get(j));
			}
			if (!isDuplicate (candidate, results)) results.add(candidate);
		}

		//		Scanner scan = new Scanner (System.in);
		//		printMultiples (results);
		//		System.out.print ("nChooseR returns results, r = "+r+", Continue?"); scan.nextLine(); System.out.println();

		return results;
	}

	static boolean isMultiple (Integer candidate) {
		Integer limit = candidate / 2; 
		if (limit <= 1) return false;
		for (int i = 2; i<=limit; i++) {
			if ((candidate%i) == 0) return true;
		}
		return false;
	}

	static Integer getGF (Integer candidate) {
		Integer limit = candidate / 2; 
		if (limit <= 1) return candidate;
		for (int i = 2; i<=limit; i++) {
			if ((candidate%i) == 0) return candidate/i;
		}
		return candidate;
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

	static boolean isDuplicate (Integer candidate, ArrayList<Integer>testVectors) {
		for (int i = 0; i<testVectors.size(); i++) if (candidate == testVectors.get(i)) return true;
		return false;
	}

	static boolean isDuplicate (ArrayList<Integer>candidates, ArrayList<ArrayList<Integer>>testVectors) {
		ArrayList<Integer> tc = new ArrayList<Integer>(); tc.addAll(candidates);
		Collections.sort(tc);
		for (int i = 0; i<testVectors.size(); i++) {
			ArrayList<Integer>testFactors = testVectors.get(i);
			boolean result = compareLists (tc, testFactors);
			if (result) return true;
		}
		return false;
	}

	static boolean compareLists (ArrayList<Integer>candidateA, ArrayList<Integer>candidateB){
		if (candidateA.size() != candidateB.size()) return false;
		Collections.sort(candidateB);
		for (int j=0; j<candidateA.size(); j++){
			if (candidateA.get(j) != candidateB.get(j)) return false;
		}
		return true;
	}

	static void printProducts (ArrayList<Integer>factors) {
		System.out.print ("printProducts: ");
		for (int i = 0; i<factors.size(); i++) {
			if (i==factors.size()-1) System.out.print (factors.get(i));
			else System.out.print (factors.get(i) + " ");
		}
		System.out.println ();
	}

	static void printProduct (ArrayList<Integer>factors) {
		Integer product = new Integer(1);
		System.out.print ("printProduct: ");
		for (int i = 0; i<factors.size(); i++) {
			product = product * factors.get(i);
			if (i==factors.size()-1) System.out.print (factors.get(i));
			else System.out.print (factors.get(i) + " x ");
		}
		System.out.println (" = " + product);
	}

	static Integer getProduct (ArrayList<Integer>factors) {
		Integer product = new Integer(1);
		//		System.out.print ("getProduct gets factors: ");
		for (int i = 0; i<factors.size(); i++) {
			product = product * factors.get(i);
			//			System.out.print (factors.get(i) + ", ");
		}
		//		System.out.println ("returns product = " + product);
		return product;
	}

	static void printFactors (ArrayList<Integer>factors) {
		System.out.print ("Factors: ");
		for (int i = 0; i<factors.size(); i++) {
			System.out.print (factors.get(i) + ", ");
		}
		System.out.println ();
	} 

/*	static void logTest(){
		logger.debug("Sample debug message");
		logger.info("Sample info message");
		logger.warn("Sample warn message");
		logger.error("Sample error message");
		logger.fatal("Sample fatal message");
	}*/

	static ArrayList<ArrayList<Integer>> permuteArrayList (ArrayList<Integer> factors) {
		//		System.out.print ("permuteArrayList gets factors: "); printFactors(factors);
		ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
		if (factors.size() <= 1) {
			results.add(factors);
		} else {
			ArrayList<Integer> newFactors = new ArrayList<Integer>();
			for (int i = 0; i<factors.size(); i++) {
				newFactors = new ArrayList<Integer>();
				Integer thisFactor = factors.get(i); newFactors.addAll(factors); newFactors.remove(i);
				//				System.out.print ("permuteArrayList gets newFactors: "); printFactors(newFactors);
				ArrayList<ArrayList<Integer>> tempList = permuteArrayList (newFactors);
				ArrayList<Integer> addRec = new ArrayList<Integer>();
				for (int j = 0; j<tempList.size(); j++){
					addRec = new ArrayList<Integer>();
					addRec.add(thisFactor); addRec.addAll(tempList.get(j));
					results.add(addRec);
				}
			}
		}
		return results;
	}
}
class IllegalArgumentException extends Exception {
	private static final long serialVersionUID = 1L;
	IllegalArgumentException() { super(); }
	IllegalArgumentException(String s) { super(s); }
}


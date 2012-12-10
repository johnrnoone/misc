package garbageCollection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GarbageCollection {

	public static void main (String[] args) {
		Runtime rt = Runtime.getRuntime();
		System.out.println ("Total JVM memory = " + rt.totalMemory());
		long fm1 = rt.freeMemory(); System.out.println ("Free  JVM memory = " + fm1);
		
		List<Date> dateArray = new ArrayList<Date>();
		Date d = null;
		for (int i=0; i < 1000000; i++) {
		  d = new Date(); 
//		  dateArray.add(d);
          d = null;
		}
		
		long fm2 = rt.freeMemory(); System.out.println ("Free  JVM memory = " + fm2 + ", diff (fm1-fm2) = " + (fm1-fm2));
		rt.gc();
		long fm3 = rt.freeMemory(); System.out.println ("After rt.gc(), free  JVM memory = " + fm3 + ", diff (fm3-fm2) = " + (fm3-fm2));


	}
}

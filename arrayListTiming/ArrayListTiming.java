package arrayListTiming;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ArrayListTiming {
	public static final int LIST_SIZE=10;
	public static final int RANGE=100000;


	public static void main(String[] args) {
		Date today1; Date today2; Long delta; String dateStr = new String();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss.SS");
 
	    List<Integer> lList = new LinkedList<Integer>();
		List<Integer> aList = new ArrayList<Integer>(); 
		
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
	    today1 = Calendar.getInstance().getTime();
	    System.out.println ("LinkedList.add() results:");
	    dateStr = formatter.format(today1); System.out.println("Local time: " + dateStr);	    
		for (int i = 0; i < LIST_SIZE; i++) {
			lList.add(rand.nextInt(RANGE));
		}
		System.out.println("       array = " + lList);
	    today2 = Calendar.getInstance().getTime();
	    delta = today2.getTime()-today1.getTime();
	    dateStr = formatter.format(today2); System.out.println("Local time: " + dateStr + ", delta = " + delta);
	    
	    today1 = Calendar.getInstance().getTime();
	    System.out.println ("ArrayList.add() results:");
	    dateStr = formatter.format(today1); System.out.println("Local time: " + dateStr);	    
		for (int i = 0; i < LIST_SIZE; i++) {
			aList.add(rand.nextInt(RANGE));
		}
//		System.out.println("       array = " + lList);
	    today2 = Calendar.getInstance().getTime();
	    delta = today2.getTime()-today1.getTime();
	    dateStr = formatter.format(today2); System.out.println("Local time: " + dateStr + ", delta = " + delta);
	    
//// GET TEST //////////////////////////////
	    today1 = Calendar.getInstance().getTime();
	    System.out.println ("LinkedList.get() results:");
	    dateStr = formatter.format(today1); System.out.println("Local time: " + dateStr);	    
		for (int i = 0; i < LIST_SIZE; i++) {
			lList.get(rand.nextInt(LIST_SIZE));
		}
//		System.out.println("       array = " + lList);
	    today2 = Calendar.getInstance().getTime();
	    delta = today2.getTime()-today1.getTime();
	    dateStr = formatter.format(today2); System.out.println("Local time: " + dateStr + ", delta = " + delta);
	    
	    today1 = Calendar.getInstance().getTime();
	    System.out.println ("ArrayList.get() results:");
	    dateStr = formatter.format(today1); System.out.println("Local time: " + dateStr);	    
		for (int i = 0; i < LIST_SIZE; i++) {
			aList.get(rand.nextInt(LIST_SIZE));
		}
//		System.out.println("       array = " + lList);
	    today2 = Calendar.getInstance().getTime();
	    delta = today2.getTime()-today1.getTime();
	    dateStr = formatter.format(today2); System.out.println("Local time: " + dateStr + ", delta = " + delta);
	    
//// Random Access TEST //////////////////////////////
	    today1 = Calendar.getInstance().getTime();
	    System.out.println ("LinkedList.contains() results:");
	    dateStr = formatter.format(today1); System.out.println("Local time: " + dateStr);	    
		for (int i = 0; i < LIST_SIZE; i++) {
			lList.contains(rand.nextInt(RANGE));
		}
//		System.out.println("       array = " + lList);
	    today2 = Calendar.getInstance().getTime();
	    delta = today2.getTime()-today1.getTime();
	    dateStr = formatter.format(today2); System.out.println("Local time: " + dateStr + ", delta = " + delta);
	    
	    today1 = Calendar.getInstance().getTime();
	    System.out.println ("ArrayList.contains() results:");
	    dateStr = formatter.format(today1); System.out.println("Local time: " + dateStr);	    
		for (int i = 0; i < LIST_SIZE; i++) {
			aList.contains(rand.nextInt(RANGE));
		}
//		System.out.println("       array = " + lList);
	    today2 = Calendar.getInstance().getTime();
	    delta = today2.getTime()-today1.getTime();
	    dateStr = formatter.format(today2); System.out.println("Local time: " + dateStr + ", delta = " + delta);
	}

}



package garbageCollection;

public class WrapperTest {

	public static void main (String[] args) {

		if (args[0] != null) {
			try {
				String s = args[0];
				double d   = Double.parseDouble(s); int i = Integer.parseInt(s);    
				System.out.println ("d="+d+", i="+i); 
				Double D   = Double.valueOf(s);     Integer I = Integer.valueOf(s); 
				System.out.println ("D="+D+", I="+I); System.out.println ("D.toString()="+D.toString()+", I.toString="+I.toString());
				Object o = D; System.out.println ("o.toString()="+o.toString());
				Double DD  = D.doubleValue();       double dd = I.doubleValue();    System.out.println ("DD="+DD+", dd="+dd);
				Integer II = D.intValue();          int ii = I.intValue();          System.out.println ("II="+II+", ii="+ii);
			} catch (NumberFormatException nfe) {
				System.out.println ("Got a NFE");
			}

			Integer i1 = 1000;
			Integer i2 = 1000;
			if (i1 == i2) System.out.println ("i1 == i2");
			if (i1 != i2) System.out.println ("i1 != i2");						
		}		
	}
}

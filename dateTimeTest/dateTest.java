package dateTimeTest;

//import java.text.DateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class dateTest {

	public static void main(String[] args) {
		
		Date d = new Date();
		System.out.println ("This is DateTest, the time is " + d.toString() + ", d.toString()");
		
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		Locale loc = new Locale("US");
		try {
			
		DateFormat df =  DateFormat.getDateInstance (DateFormat.SHORT, loc);
		System.out.println ("This is DateTest, the time is " + df.format(date) + ", df.format(date, SHORT)");
		
	    df =  DateFormat.getDateInstance (DateFormat.LONG, loc);
		System.out.println ("This is DateTest, the time is " + df.format(date) + ", df.format(date, LONG)");
		
		} catch (Exception e) {
			System.out.println ("Exception");
		}
		
	}
	
}

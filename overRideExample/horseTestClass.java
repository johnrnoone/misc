package overRideExample;

import static org.junit.Assert.*;

import java.io.File;
import java.util.GregorianCalendar;
import org.junit.Test;

public class horseTestClass {

	@Test
	public void HorseTest() {
		System.out.println("HorseTest test cases:");

		// Test 1
		Animal animal = new Animal();
		try {
			assertNotNull ("Check new Animal object", animal);
			animal.eat();
		} catch (Exception e) {
			System.out.println("Exception1 in HorseTest");
		}

		// Test 2
		Animal animal2 = (Animal) new Horse(); 
		try {
			assertNotNull ("Check new animal2 object", animal2);
			animal2.eat();
		} catch (Exception e) {
			System.out.println("Exception2 in HorseTest");
		}

		// Test 3
		Horse horse1   =          new Horse();  
		try {
			assertNotNull ("Check new horse1 object", animal);
			horse1.eat();
		} catch (Exception e) {
			System.out.println("Exception3 in HorseTest");
		}

		// Test 4
		Horse horse2   = (Horse)  new Animal(); 
		try {
			assertNotNull ("Check new horse2 object", animal);
			horse2.eat();
		} catch (Exception e) {
			System.out.println("Exception4 in HorseTest");
		}

	}
}


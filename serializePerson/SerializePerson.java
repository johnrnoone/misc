package serializePerson;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializePerson {
	public static void main(String args[]) {
		System.out.println ("This is SerializePerson");
		Person newGuy = new Person("Fred");
		
		try {
		FileOutputStream ofs = new FileOutputStream ("fred.txt");
		ObjectOutputStream oos = new ObjectOutputStream(ofs);
		oos.writeObject(newGuy);
		oos.close();
		
		ArrayList<Person> personArray = new ArrayList<Person>();
		personArray.add(newGuy);
		if (personArray.contains(newGuy)) {
			personArray.remove(newGuy);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

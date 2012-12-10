package serializePerson;

import java.io.Serializable;

public class Person implements Serializable {
   String name;

   // Person constructor
   public Person(String name)
   // Post: An instance of Person is initialized.
   {
      // Set the name of this Person object to the name passed into the constructor.
      this.name = name;
   }

   public String getName()
   // Post: The name of this person is returned.
   {
      return this.name;
   }
}

package overRideExample;

class Animal {
	public Animal() {};
	public void eat() {
		System.out.println ("The animal is eating");
	}
}

// Horse cannot extend Mammal because Mammal is final
//public class Horse extends Mammal {

	public class Horse extends Animal {
	
	public void eat() {
		System.out.println ("The horse is eating");
	}	
	

	public static void main (String[] args) {
		Animal animal1 =          new Animal(); animal1.eat();
		Animal animal2 = (Animal) new Horse();  animal2.eat();
		Horse horse1   =          new Horse();  horse1.eat();
//		Horse horse2   = (Horse)  new Animal(); horse2.eat();
		Mammal mammal1 = (Mammal) new Mammal();  mammal1.eat();
	}
}

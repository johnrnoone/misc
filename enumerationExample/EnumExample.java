package enumerationExample;



public class EnumExample {

	enum CoffeeSize {
		SMALL(10), MEDIUM(20), LARGE(30);
		CoffeeSize (int size) {ounces = size;}
		private int ounces;
		public int getSize () {return ounces;}
	}
	
	public static void main (String[] args) {

		CoffeeSize largeCoffee;
		largeCoffee = CoffeeSize.LARGE;
		System.out.println 
		("Coffee type: " + largeCoffee + ", size = " + largeCoffee.getSize());

		CoffeeSize mediumCoffee;
		mediumCoffee = CoffeeSize.MEDIUM;
		System.out.println 
		("Coffee type: " + mediumCoffee + ", size = " + mediumCoffee.getSize());

		//		CoffeeSize mediumCoffee;
		mediumCoffee = CoffeeSize.SMALL;
		System.out.println 
		("Coffee type: " + mediumCoffee + ", size = " + mediumCoffee.getSize());
		
		CoffeeSize smallCoffee = CoffeeSize.SMALL;


	}

}

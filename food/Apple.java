package food;

 class Apple extends Fruit{

	public Apple(){
		super();
		setTaste();
	}
	@Override
	public void setColor(String color) {
		this.color = color;		
	}	

	public void setTaste() {
		taste ="juicy";		
	}

	public static void main (String[] args) {
		Apple apple = new Apple();
		System.out.println ("This is Apple.main()");
		System.out.println ("This is Apple.main(), taste = " + apple.getTaste());
		apple.describeFruit();
		System.out.println ("Apple.main() sets color = green");
		apple.setColor("green");
		apple.describeFruit();
	}

}

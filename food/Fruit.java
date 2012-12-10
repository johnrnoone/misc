package food;

public abstract class Fruit {
	protected String color;
	protected String taste;
	public Fruit (){
		color = "yellow"; taste = "sweet";
		System.out.println ("Fruit constructor sets color = " + color);
		System.out.println ("Fruit constructor sets taste = " + taste);
	}
	public String getColor(){return this.color;};
	public void setColor (){this.color="red";};
	public void setColor (String color){this.color="red";};
	public String getTaste(){return this.taste;};
	public abstract void setTaste();
	public void describeFruit() {
		System.out.println ("The fruit color is " + color + ", taste is " + taste);
	}
	}


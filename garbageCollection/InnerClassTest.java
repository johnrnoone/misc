package garbageCollection;

public class InnerClassTest extends Hobbit {
	public static void main(String[] args) {
		Short myGold = 7;
		System.out.println(countGold(myGold, 6));
	}
}
class Hobbit {
	static int countGold(int x, int y) { return x + y; }
	// int countGold(int x, int y) { return x + y; } // fails to compile

}

package test;

public class SwitchTest {

	public static void main(String[] args) {
		test(2);
		test(33);
	}
	
	public static void test(int a) {
		switch(a) {
			case 1:
				System.out.println("a: " + a);
				break;
			case 2:
				System.out.println("a: " + a);
				break;
				default:
					System.out.println(0);
					break;
		}
	}
}

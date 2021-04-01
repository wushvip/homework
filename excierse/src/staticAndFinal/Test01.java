package staticAndFinal;

public class Test01 {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		MyClass myclass1 = new MyClass();
		MyClass myclass2 = new MyClass();
		System.out.println(myclass1.getI());
		System.out.println(myclass1.getJ());
		System.out.println(myclass2.getI());
		System.out.println(myclass2.getJ());

	}
}

class MyClass{
	private final double i = Math.random();
	private static double j = Math.random();
	public static double getJ() {
		return j;
	}
	public static void setJ(double j) {
		MyClass.j = j;
	}
	public double getI() {
		return i;
	}
	
	
}

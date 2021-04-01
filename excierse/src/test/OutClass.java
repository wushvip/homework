package test;

public class OutClass {
	private int a;
	
	private static String b;
	
	public class Inner{
		public void printMessage() {
			//内部类中可以访问静态成员，也可以访问非静态成员
			System.out.println(a);
			System.out.println(b);
		}
	}
	
	public static class Inne{
		public void printMessage() {
			//静态类中可以访问外部类的静态成员，但是不能访问非静态成员
//			System.out.println(a);
			System.out.println(b);
		}
	}
	public static void main(String[] args) {
		OutClass.Inne inne = new OutClass.Inne();
		inne.printMessage();
	}
}

package test;

public class OutClass {
	private int a;
	
	private static String b;
	
	public class Inner{
		public void printMessage() {
			//�ڲ����п��Է��ʾ�̬��Ա��Ҳ���Է��ʷǾ�̬��Ա
			System.out.println(a);
			System.out.println(b);
		}
	}
	
	public static class Inne{
		public void printMessage() {
			//��̬���п��Է����ⲿ��ľ�̬��Ա�����ǲ��ܷ��ʷǾ�̬��Ա
//			System.out.println(a);
			System.out.println(b);
		}
	}
	public static void main(String[] args) {
		OutClass.Inne inne = new OutClass.Inne();
		inne.printMessage();
	}
}

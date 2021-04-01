package string_test;

public abstract class Test02 {

	public static void main(String[] args) {
		String a = "hello2";
		String d = "hello";
		final String b = "hello";
		String c = b + 2;
		String e = d + 2;
		System.out.println( a == c);
		System.out.println(a == e);

		String[] strs = {"1","a","2"};
		Object[] objArrs = new Object[]{strs};
		for(Object obj:objArrs){
			System.out.println(obj);
		}

	}

}

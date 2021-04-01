package string_test;

public class Test01 {

	public static void main(String[] args) {
		String str = "123456 ,7890qw ";
		String mes = "404 Not Found";
		System.out.println(mes.contains("404"));
		System.out.println(String.format("http://10.154.9.75:8080/api/v1/clusters/BCH301TEST02/",6556));

		System.out.println(str.trim());
		System.out.println(str.substring(11, 12));
		System.out.println("   ".equals(""));

		float a = 5f;
		System.out.println(String.valueOf(a));
		String cpuQuota,ramQuota,netWorkQuota,storeQuota = "21233";
//		System.out.println(cpuQuota);
//		if(!Float.isNaN(a)){
//			System.out.println(String.valueOf(int(a)));
//		}
		int i = 9;
		System.out.println(i*a);
	}

}

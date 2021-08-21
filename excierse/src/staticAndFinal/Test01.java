package staticAndFinal;

public class Test01 {

	private final String storePath;
	private final String signalPath;

	public Test01(String storePath,String signalPath){
		this.storePath = storePath;
		this.signalPath = signalPath;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Test01 test01 = new Test01("aaa","bbb");
		
	}
	
}

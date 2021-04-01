package thread;

public class ThreadTest03 {
	
	private static int num = 0;
	
	private synchronized static void add() {
		num++;
		System.out.println(Thread.currentThread() + ":" + num);
	}

	public static void main(String[] args) {
//		Runnable r = new Runnable() {
//			@Override
//			public void run() {
//				add();
//			}
//		};
//		new Thread(r).start();
		
		//匿名内部类
//		for(int i=0;i<10;i++) {
//			Thread t = new Thread (new Runnable() {
//				@Override
//				public void run() {
//					for(int b=0;b<10;b++) {
//						add();
//					}
//					
//				}
//			});
//			t.start();
//			t.yield();
			
//		}
		
		//lambda表达式
		int i = 10;
		while(i>0) {
			new Thread(() -> {
				for(int j=0;j<1000;j++) {
					add();
				}
			}).start();
			i--;
		}
	}

}

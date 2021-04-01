package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
        Callable<String> call = new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(1000 * 10);//����ָ����ʱ�䣬�˴���ʾ�ò����ȽϺ�ʱ
                return "Other less important but longtime things.";
            }
        };
        Future<String> task = exec.submit(call);
        //��Ҫ������
        System.out.println("Let's do important things. start");
        Thread.sleep(1000 * 3);
        System.out.println("Let's do important things. end");
 
        //����Ҫ������
        while(! task.isDone()){
            System.out.println("still waiting....");
            Thread.sleep(1000 * 1);
        }
        System.out.println("get sth....");
        //����get()����ʱ��������߳�û��ִ����ϣ���˷�����������ֱ�����߳�������񣬷��ؽ��
        String obj = task.get();
        System.out.println(obj);
        //�ر��̳߳�
        exec.shutdown();

	}

}

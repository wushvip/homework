package jvm;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class Singleton {
//    private static volatile Singleton single_instance;
    private static Singleton single_instance;

    public static Singleton getInstance(){
        if(single_instance==null){
            synchronized(Singleton.class){
                if(single_instance==null){
                    try {
                        single_instance = Singleton.class.newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return single_instance;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    System.out.println(Singleton.getInstance());
                }
            }.start();
        }
    }
}

package jvm;

/**
 * 单分排多分派
 */
public class Dispatch {

    static class QQ{}

    static class _360{}

    public static class Father{
        public void hardChoice(QQ qq){
            System.out.println("this is fatcher choice qq!");
        }

        public void hardChoice(_360 param){
            System.out.println("this is fatcher choice 360!");
        }

    }

    public static class Son{
        public void hardChoice(QQ qq){
            System.out.println("this is son choice qq!");
        }

        public void hardChoice(_360 param){
            System.out.println("this is son choice 360!");
        }

    }


    public static void main(String[] args) {
        Father father = new Father();
        Son son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }

}

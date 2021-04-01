package com.data.structure.stack;

/**
 *jdk中关于stack的常用方法
 * push（） 入栈
 * peek()  取值栈顶
 * pop() 出栈
 *empty() 判断当前栈是否为空
 *
 *
 *；两种实现方式：数组和链表
 * 压栈、出栈、判断栈是否为空、判断是否满栈
 * @Author: wushuai
 * @Date: 2019/8/20 12:36
 * @Description:
 */
public class MyStack<T> {

    private T[] datas;

    private int topIndex;//指向栈顶元素的数组下标

    private int max;

//    private T top;
//
//    private T bottom;


    public MyStack(){}


    public void init(int size){
        datas = (T[]) new Object[size];
        max = size;
        topIndex = -1;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        if(datas==null || datas.length<=0){
            return true;
        }
        return false;
    }


    /**
     *
     */
    public String map(){
        StringBuilder res = new StringBuilder("[");
        for(int i=0;i<=topIndex;i++){
            res.append(datas[i]).append(",");
        }
        String result = res.toString();
        return result.substring(0,result.length()-1) + "]";
    }


    /** 入栈
     *
     * @param data
     */
    public void pushStack(T data){
        if(datas==null){
            throw new RuntimeException();
        }
        datas[++topIndex] = data;
    }

    /**
     * 出栈
     * @return
     */
    public T pop(){
        T topData = datas[topIndex];
        datas[topIndex] = null;
        topIndex--;
        return topData;
    }

    /**
     * 查看栈顶数据
     * @return
     */
    public T peek(){
        return datas[topIndex];
    }


    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        System.out.println(stack.isEmpty());
        stack.init(5);
        for(int i=0;i<5;i++){
            stack.pushStack(i);
        }
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.map());
    }

}

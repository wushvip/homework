package com.data.structure.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**顺序队列的实现,无论是线性队列还是环形队列，队列中永远有一个位置空着
 * @Author: wushuai
 * @Date: 2019/8/19 10:38
 * @Description:
 */
public class MyQueue<T> {

    private T[] arrs;//数组

    private int maxSize;

    private int size;

    private int front;//头指针

    private int rear;//末尾指针,指向最后一个元素的下一个位置

    /**
     * 队列初始化
     * @param maxSize
     */
    public MyQueue(int maxSize){
        this.arrs = (T[])new Object[maxSize];
        this.maxSize = maxSize;
        this.front = 0;
    }


    /**
     * 判断当前队列是空队列
     * @return
     */
    public boolean isNull(){
        if(this.front==this.rear){
            return true;
        }
        return false;
    }

    /**
     * 判断队列是否已满
     * @return
     */
    public boolean isFull(){
        if((this.rear + 1)%maxSize==front){
            return true;
        }
        return false;
    }




    public void add(T data){
        if(isFull()){
            resize();
            front = 0;
        }
        rear = (front + size)%maxSize;
        arrs[rear] = data;
        rear = (rear + 1)%maxSize;
        size++;
    }

    public T remove(){
        if(isNull()){
            return null;
        }
        T frontData = (T) arrs[front];
        arrs[front] = null;
        front = (front+1)%maxSize;
        size--;
        return frontData;
    }

    /**
     * 扩容
     */
    public void resize(){
        int oldLen = this.maxSize;
        this.maxSize = oldLen*2;
        T[] tmp = (T[]) new Object[maxSize];
        System.arraycopy(this.arrs,0,tmp,0,oldLen);
        this.arrs = tmp;
        this.front = 0;
        tmp = null;
    }


    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<String>(5);
        System.out.println(queue.isNull());
        queue.add("one");
        queue.add("two");
        queue.add("three");
        queue.add("four");
        System.out.println(queue.isFull());
        queue.add("five");
        System.out.println(queue.isFull());
        //数组的扩容采用原数组长度向右位移1位的方法
//        List<String> list = new ArrayList<>(4);


        System.out.println(queue.remove());
        System.out.println(queue.remove());



    }

}

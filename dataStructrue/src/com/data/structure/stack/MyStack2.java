package com.data.structure.stack;

import com.data.structure.list.Node;

/**
 * @Author: wushuai
 * @Date: 2019/8/20 20:14
 * @Description:
 */
public class MyStack2 {

    private Node top;

    private Node bottom;

    public void init(Node top,Node bottom){
        this.top = top;
        this.bottom = bottom;
    }

    public boolean isEmpty(){
        if(top==null && bottom==null){
            return true;
        }
        return false;
    }

    /**
     * 压栈
     * @param val
     */
    public void push(String val){
        if(val==null){
            return;
        }
        Node tmp = new Node(val);
        tmp.setNext(top);
        top = tmp;
    }

    /**
     * 出栈
     * @return
     */
    public String pop(){
        if(isEmpty()){
            return null;
        }
        String value = top.getValue();
        top = top.getNext();
        return value;
    }

    /**
     * 清空栈
     */
    public void clear(){
        top = null;
        bottom = null;
    }

}

package com.data.structure.list;

/**
 * @Author: wushuai
 * @Date: 2019/8/15 13:34
 * @Description:
 */
public class Node {

    private String value;

    private Node next;

    public Node(){}

    public Node(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

package com.data.structure.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wushuai
 * @Date: 2019/8/17 23:28
 * @Description:
 */
public class Shizhan {

    /**
     * 倒序排列
     * @param head
     * @return
     */
    public static Node revertLIst(Node head){
        List<Node> nodes = new ArrayList<>();
        Node pre = null;
        Node next = null;
        while (head !=null){
            next = head.getNext();
            head.setNext(pre);
            pre = head;
            head = next;
        }
        return pre;
    }


    /**
     * 获取链表中中间节点
     * @param head
     * @return
     */
    public static Node getmiddleNode(Node head){
        if(head==null){
            return head;
        }
        //define two crul
        Node fast = head;
        Node slow = head;
        while (fast.getNext() !=null && fast.getNext().getNext() !=null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }

        return slow;



    }

    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");
        Node node6 = new Node("6");
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node5.setNext(null);
//        Node head = revertLIst(node1);
//        LinkList.map(head);


        Node middle = getmiddleNode(node1);
        System.out.println(middle.getValue());
    }
}

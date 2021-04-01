package com.data.structure.list;

import java.util.List;

/**
 * 单向链表
 * @Author: wushuai
 * @Date: 2019/8/15 13:47
 * @Description:
 */
public class LinkList {

    /**
     * 插入头部
     * @param p
     * @param head
     */
    public static void insertHead(Node p, Node head){
        if(p==null){
            return;
        }
        p.setNext(head);
        head = p;
    }
    /**
     * 插入尾部
     * @param p
     * @param nodes
     */
    public static void insertTail(Node p, Node head){
        if(null==head){
            return;
        }
        Node temp = head.getNext();
        while (temp.getNext() !=null){
            temp = temp.getNext();
        }
        temp.setNext(p);
    }


    /**
     * 插入中间
     * @param newNode
     * @param pre
     * @param next
     */
    public static void insertMiddle(Node newNode, Node pre,Node next){
       Node temp = next;
       pre.setNext(newNode);
       newNode.setNext(temp);
    }

    /**
     * 循环遍历所有节点data
     * @param current
     */

    public static void map(Node current){
        //遍历单向链表
//        if(null==head){
//            return;
//        }
//        System.out.print(head.getValue() + "  ");
//        if (head.getNext() !=null){
//            //递归
//            LinkList.map(head.getNext());
//        }
        //第二种方法：迭代输出，直到next为null时！
//        while (current !=null){
//            System.out.print(current.getValue() + "  ");
//            current = current.getNext();
//        }


        do{
            Node next = current.getNext();
            System.out.print(current.getValue() + "  ");
            current = next;
        }while (current !=null);


    }

    public static void main(String[] args) {
        Node head = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        head.setNext(node2);
        node2.setNext(node3);
        node3.setNext(null);
//        LinkList.map(node1);

//        LinkList.map(node1);
        Node newNode = new Node("0");
        //插入头部
        insertHead(newNode,head);
        map(newNode);

        //插入中间
//        LinkList.insertMiddle(newNode,node2,node3);
//        LinkList.map(head);

    }

}



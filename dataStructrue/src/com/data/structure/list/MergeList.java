package com.data.structure.list;

/**
 * 合并两个链表为一个有序的链表
 * @Author: wushuai
 * @Date: 2019/8/18 15:55
 * @Description:
 */
public class MergeList {

    //递归的方式
    public static Node mergeNodeList(Node head1,Node head2){
        if(head1==null || head2==null){
            return head1==null?head2:head1;
        }
        Node head = null;
        Node next = null;
        if(Integer.parseInt(head1.getValue())>=Integer.parseInt(head2.getValue())){
            head = head2;
            next = mergeNodeList(head1, head2.getNext());
            head.setNext(next);
        }else {
            head = head1;
            next = mergeNodeList(head1.getNext(), head2);
            head.setNext(next);
        }
        return head;
    }



    //非递归的方式
    public static Node mergeListNode1(Node head1,Node head2){
        if(head1==null){
            return head2;
        }
        if(head2==null){
            return head1;
        }
        Node head = Integer.parseInt(head1.getValue())<=Integer.parseInt(head2.getValue())?
                head1:head2;
        //定义两个指针
        Node cur1 = head==head1?head1:head2;//取指针value较小的链表
        Node cur2 = head==head1?head2:head1;//取headvalue较大

        Node pre = null;//cur1的前一个节点
        Node next = null;//cur2的后一个节点
        while (cur1 !=null && cur2 !=null){
            if(Integer.parseInt(cur1.getValue())<=Integer.parseInt(cur2.getValue())){
                pre = cur1;
                cur1 = cur1.getNext();
            }else{
                next = cur2.getNext();
                pre.setNext(cur2);
                cur2.setNext(cur1);
                pre = cur2;
                cur2 = next;
            }
        }
        pre.setNext(cur1==null?cur2:cur1);
        return head;
    }



    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("8");
        Node node6 = new Node("5");
        Node node7 = new Node("0");
        node1.setNext(node3);
        node3.setNext(node5);
        node5.setNext(node7);
        node2.setNext(node4);
        node4.setNext(node6);
        Node head = mergeListNode1(node1, node2);
        LinkList.map(head);
    }


}

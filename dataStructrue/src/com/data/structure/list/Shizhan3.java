package com.data.structure.list;

/**
 * @Author: wushuai
 * @Date: 2019/8/19 8:54
 * @Description:
 */
public class Shizhan3 {

    /**
     * 获取中间节点
     * @param head
     * @return
     */
    public static Node getMiddleNodes(Node head){
        Node fast = head;
        Node slow = head;

        while(fast.getNext() !=null && fast.getNext().getNext() !=null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
//        Node nextHead = slow.getNext();
//        slow.setNext(null);
        return slow;
    }

    /**
     * 分而治之——递归排序
     * @param head
     * @return
     */
    public static Node sortList(Node head){
        if(head==null || head.getNext()==null){
            return head;
        }
        Node mid = getMiddleNodes(head);
        Node preHead = head;
        Node secHead =mid.getNext();
        mid.setNext(null);

        preHead = sortList(preHead);
        secHead = sortList(secHead);
        return mergeList(preHead,secHead);
    }


    /**
     * 归并
     * @param head1
     * @param head2
     * @return
     */
    public static Node mergeList(Node head1,Node head2){
        if(head1==null || head2==null){
            return head1==null?head2:head1;
        }
        Node head = null;
        Node next = null;
        if(head1 !=null && head2 !=null){
            if(Integer.parseInt(head1.getValue())>=Integer.parseInt(head2.getValue())){
                head = head2;
                next = mergeList(head1,head2.getNext());
                head.setNext(next);
            }else{
                head = head1;
                next = mergeList(head1.getNext(),head2);
                head.setNext(next);

            }
        }
        return head;
    }

    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node2 = new Node("9");
        Node node3 = new Node("4");
        Node node4 = new Node("6");
        Node node5 = new Node("10");
        Node node6 = new Node("12");
        Node node7 = new Node("7");
        Node node8 = new Node("8");
        Node node9 = new Node("5");
        Node node10 = new Node("2");
        Node node11 = new Node("3");
        Node node12 = new Node("11");
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(node9);
        node9.setNext(node10);
        node10.setNext(node11);
        node11.setNext(node12);
        node12.setNext(null);

//        Node[] headNodes = getHeadNodes(node1);
//        System.out.println("head1 : " + headNodes[0].getValue() + "head2 : " + headNodes[1].getValue());
        Node head = sortList(node1);
        LinkList.map(head);
    }



}

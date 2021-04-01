package com.data.structure.list;

/**
 * @Author: wushuai
 * @Date: 2019/8/18 15:50
 * @Description:
 */
public class Shizhan2 {

    //1  9  3  8  5  4  2  7
    //1  9  3  8  5  4  2  7
    //奇数位升序，偶数位降序，如何实现重排序

    public static Node[] splitTwoList(Node head){
        if(head==null){
            return null;
        }
        Node head1 = null;
        Node head2 = null;

        Node cur1 = null;
        Node cur2 = null;
        int count = 1;
        while (head !=null){
            if(count %2 ==1 ){
                if(head1==null){
                    head1 = head;
                    cur1 = head1;
                }else{
                    cur1.setNext(head);
                    cur1 = cur1.getNext();
                }
            }else{
                if(head2==null){
                    head2 = head;
                    cur2 = head2;
                }else {
                    cur2.setNext(head);
                    cur2 = cur2.getNext();
                }
            }
            head = head.getNext();
            count++;
        }
        cur1.setNext(null);
        cur2.setNext(null);
        System.out.println("split has finished!");
        return new Node[]{head1,head2};

    }


    public static Node revertList(Node head){
        Node pre = null;
        Node next = null;
        while (head !=null){
            next = head.getNext();
            head.setNext(pre);
            pre = head;
            head = next;
        }
        System.out.println("revert list has finished");
        return pre;

    }


    public static Node mergeTwoList(Node head1,Node head2){

        if(head1==null || head2==null){
            return head1==null?head2:head1;
        }
        int val1 = Integer.parseInt(head1.getValue());
        int val2 = Integer.parseInt(head2.getValue());
        Node head = val1<=val2? head1:head2;
        Node cur1 = head==head1?head1:head2;
        Node cur2 = head==head1?head2:head1;


        Node pre = null;
        Node next = null;

        while (cur1 !=null && cur2 !=null){
            if(Integer.parseInt(cur1.getValue()) <= Integer.parseInt(cur2.getValue())){
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
        System.out.println("merge has finished!");
        return head;
    }

    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node2 = new Node("9");
        Node node3 = new Node("3");
        Node node4 = new Node("6");
        Node node5 = new Node("5");
        Node node6 = new Node("4");
        Node node7 = new Node("7");
        Node node8 = new Node("2");
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(null);

        Node[] nodes = splitTwoList(node1);

        Node node = revertList(nodes[1]);
        LinkList.map(node);
        Node head = mergeTwoList(node1, node);
        LinkList.map(head);
    }
}

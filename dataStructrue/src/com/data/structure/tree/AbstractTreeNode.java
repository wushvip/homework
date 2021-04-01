package com.data.structure.tree;

/**
 * @Author: wushuai
 * @Date: 2019/8/22 9:54
 * @Description:
 */
public abstract class AbstractTreeNode {
    /**
     * 计算书中总节点个数
     */
    public static int getNodeNum(TreeNode root){
        if(root==null){
            return 0;
        }
        int total = 1;
        total += getNodeNum(root.leftNode);
        total += getNodeNum(root.rightNode);
        return total;
    }

    /**
     * 计算树的深度
     * @param root
     * @param <T>
     * @return
     */
    public static <T> int getDepth(TreeNode root){
        if(null==root){
            return 0;
        }
        int leftDepth = 1;
        int rightDepth = 1;
        leftDepth += getDepth(root.leftNode);
        rightDepth += getDepth(root.rightNode);
        return Math.max(leftDepth,rightDepth);
    }


    //遍历：前序遍历、中序遍历、后序遍历、层次遍历
    //前序遍历（根左右）
    public static <T> void preOrderTravel(TreeNode root){
        if(root==null){
            return;
        }
        visitNode(root);
        preOrderTravel(root.leftNode);
        preOrderTravel(root.rightNode);
    }
    //中序遍历（左根右）
    public static <T> void middleOrderTravel(TreeNode root){
        if(null==root){
            return;
        }
        middleOrderTravel(root.leftNode);
        visitNode(root);
        middleOrderTravel(root.rightNode);
    }

    //后序遍历（左右根）
    public static <T> void backOrderTravel(TreeNode root){
        if(null==root){
            return;
        }
        backOrderTravel(root.leftNode);
        backOrderTravel(root.rightNode);
        visitNode(root);
    }

    public static <T> void visitNode(TreeNode node){
        T value = (T) node.getValue();
        System.out.print(value + "  ");
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.addLeftNode(2);
        root.addRightNode(3);
        root.leftNode.addLeftNode(4);
        root.leftNode.addRightNode(5);
        root.rightNode.addLeftNode(6);
        root.rightNode.addRightNode(7);
        root.leftNode.leftNode.addLeftNode(8);
        root.leftNode.leftNode.addRightNode(9);
//        System.out.println(getNodeNum(root));
//        System.out.println(getDepth(root));

        System.out.println("前序遍历结果： ");
        preOrderTravel(root);
        System.out.println("\n中序遍历结果： ");
        middleOrderTravel(root);
        System.out.println("\n后序遍历结果： ");
        backOrderTravel(root);
    }




}

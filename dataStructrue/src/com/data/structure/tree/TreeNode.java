package com.data.structure.tree;

/**
 * 对于一般的二叉树的基本操作
 * @Author: wushuai
 * @Date: 2019/8/20 20:44
 * @Description:
 */
public class TreeNode<T> {

    private T value;

    public TreeNode leftNode;

    public TreeNode rightNode;

    public TreeNode(T value){
        this.value = value;
    }

    public void addLeftNode(T value){
        TreeNode<T> leftNode = new TreeNode<>(value);
        this.leftNode = leftNode;
    }
    public void addRightNode(T value){
        TreeNode<T> rightNode = new TreeNode<>(value);
        this.rightNode = rightNode;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

package com.rh.tree;

public class BinaryTree {

     TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public static void main(String[] args) {
        //创建一棵树
        BinaryTree binaryTree=new BinaryTree();

        //创建一个根节点
        TreeNode root=new TreeNode(1);

        //把根节点赋给树
        binaryTree.setRoot(root);

        //创建左子节点
        TreeNode leftNode=new TreeNode(2);
        //创建右子节点
        TreeNode rightNode=new TreeNode(3);

        root.setLeftNode(leftNode);
        root.setRightNode(rightNode);

        //为第二层的左节点创建两个子节点
        leftNode.setLeftNode(new TreeNode(4));
        leftNode.setRightNode(new TreeNode(5));

        //为第二层的右节点创建两个子节点
        rightNode.setLeftNode(new TreeNode(6));
        rightNode.setRightNode(new TreeNode(7));

//        binaryTree.frontShow();   //前序遍历
//        binaryTree.midShow();     //中序遍历
//        binaryTree.afterShow();   //后序遍历

//        TreeNode result=binaryTree.frontSearch(8);
//        System.out.println(result);

        binaryTree.delete(1);
        binaryTree.frontShow();
    }

    private void delete(int i) {
        if (root.value == i){
            root = null;
        }else {
            root.delete(i);
        }
    }

    private TreeNode frontSearch(int index) {
        if (root != null){
            return root.frontSearch(index);
        }
        return null;
    }

    private void afterShow() {
        if (root != null){
            root.afterShow();
        }
    }

    private void midShow() {
        if (root != null){
            root.midShow();
        }
    }

    private void frontShow() {
        if (root != null){
            root.frontShow();
        }
    }
}

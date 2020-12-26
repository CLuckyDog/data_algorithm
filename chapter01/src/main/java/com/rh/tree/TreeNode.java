package com.rh.tree;

public class TreeNode {

    int value;//节点的权

    TreeNode leftNode;//左子节点
    TreeNode rightNode;//右子节点

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    //前序遍历，根左右
    public void frontShow() {
        //先遍历当前节点的内容
        System.out.println(value);
        //左节点
        if (leftNode != null) {
            leftNode.frontShow();
        }

        //右节点
        if (rightNode != null) {
            rightNode.frontShow();
        }
    }

    //中序遍历  左根右
    public void midShow() {
        //左节点
        if (leftNode != null) {
            leftNode.midShow();
        }

        //遍历当前节点的内容
        System.out.println(value);

        //右节点
        if (rightNode != null) {
            rightNode.midShow();
        }
    }

    //后序遍历 左右根
    public void afterShow() {
        //左节点
        if (leftNode != null) {
            leftNode.afterShow();
        }

        //右节点
        if (rightNode != null) {
            rightNode.afterShow();
        }

        //遍历当前节点的内容
        System.out.println(value);
    }

    //前序查找
    public TreeNode frontSearch(int index) {
        TreeNode target = null;
        //先查找当前节点是否存在
        if (this.value == index) {
            return this;
        } else {
            if (leftNode != null) {
                target = leftNode.frontSearch(index);
                if (target != null) {
                    return target;
                }
            }
            if (rightNode != null) {
                target = rightNode.frontSearch(index);
            }
        }
        return target;
    }

    //删除一个子树
    public void delete(int i) {
        TreeNode parent = this;

        //判断左子节点
        if(parent.leftNode!=null&&parent.leftNode.value==i){
            parent.leftNode=null;
        }

        //判断右子节点
        if(parent.rightNode!=null&&parent.rightNode.value==i){
            parent.rightNode=null;
        }

        //递归删除左子节点
        parent=leftNode;
        if (parent!=null){
            parent.delete(i);
        }

        //递归删除右子节点
        parent=rightNode;
        if (parent!=null){
            parent.delete(i);
        }

    }
}
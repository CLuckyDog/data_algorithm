package com.binarytree;

public class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 向子树中添加节点
     * @param node
     */
    public void add(Node node) {
        if(node == null){
            return;
        }

        //判断传入的节点的值比当前子树的根节点的值是大还是小
        if (node.value < this.value){
            //如果左节点为空
            if (this.left == null){
                this.left = node;
            }else {
                //如果不为空，则递归添加
                this.left.add(node);
            }
        }else{
            //如果右节点为空
            if (this.right == null){
                this.right = node;
            }else {
                //如果不为空，则递归添加
                this.right.add(node);
            }
        }
    }

    public void midShow(Node node) {
        if (node == null){
            return;
        }
        midShow(node.left);
        System.out.println(node.value);
        midShow(node.right);

    }

    public Node search(int value) {
        if (this.value==value){
            return this;
        }else if(value < this.value){
            if (left == null){
                return null;
            }
            return left.search(value);
        }else{
            if (right == null){
                return null;
            }
            return right.search(value);
        }
    }

    public Node searchParent(int value) {
        if ((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }else {
            if (this.value > value && this.left!=null){
                return this.left.searchParent(value);
            }else if(this.value <= value && this.right!=null){
                return this.right.searchParent(value);
            }
            return null;
        }
    }
}

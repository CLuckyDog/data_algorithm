package com.binarytree;

public class TestBinaryTree {
    public static void main(String[] args) {
        int[] arr = new int[]{7,3,10,12,5,1,9};
        //创建一颗二叉树
        BinarySortTree bst=new BinarySortTree();
        //循环添加
        for (int i: arr){
            bst.add(new Node(i));
        }

        //中序遍历二叉排序
        bst.midShow();
//        System.out.println("-----");
//        //查找节点
//        Node node = bst.search(10);
//        Node node2 = bst.search(20);
//        System.out.println(node.value);
//        System.out.println(node2);
//        System.out.println("-----");
//        //测试查找父节点
////        Node parent = bst.searchParent(10);
////        System.out.println(parent.value);
//        bst.delete(5);
//        bst.midShow();
//        System.out.println("-----");
//        bst.delete(3);
//        bst.midShow();
        bst.delete(10);
        bst.midShow();
    }
}

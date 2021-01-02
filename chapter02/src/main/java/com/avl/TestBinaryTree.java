package com.avl;

public class TestBinaryTree {
    public static void main(String[] args) {
//        int[] arr = new int[]{1,2,3,4,5,6};
//        int[] arr = new int[]{2,1,4,3,5,6};
        int[] arr = new int[]{8,9,6,7,5,4};
//        int[] arr = new int[]{8,9,5,4,6,7};
        //创建一颗二叉树
        BinarySortTree bst=new BinarySortTree();
        //循环添加
        for (int i: arr){
            bst.add(new Node(i));
        }

        bst.midShow();
        System.out.println("---------");
        System.out.println(bst.root.height());
        System.out.println(bst.root.value);

    }
}

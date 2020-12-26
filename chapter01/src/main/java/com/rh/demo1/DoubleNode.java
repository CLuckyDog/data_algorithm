package com.rh.demo1;

/**
 * 循环双向链表
 */
public class DoubleNode {
    //当前节点的上一个节点
    DoubleNode pre=this;
    //当前节点的下一个节点
    DoubleNode next=this;
    //节点数据
    int data;

    public int getData() {
        return data;
    }

    public DoubleNode(int data) {
        this.data = data;
    }

    /**
     * 插入一个节点
     * @param node
     */
    public DoubleNode after(DoubleNode node){
        //取出当前节点的下一个节点,作为新链表的下下个节点
        DoubleNode nextNext=this.next;
        //围绕新节点，建立四种关联关系
        //把新节点设置成当前节点的下一个节点
        this.next=node;
        //把新节点的上一个节点设置成当前节点
        node.pre=this;
        //把新节点的下一个节点设置成当前节点的下下个节点
        node.next=nextNext;
        //把新节点设置成当前节点的下下个节点的上一个节点
        nextNext.pre=node;

        return node;
    }

    public static void main(String[] args) {
        DoubleNode n1=new DoubleNode(1);
        DoubleNode n2=new DoubleNode(2);
        DoubleNode n3=new DoubleNode(3);
        DoubleNode n4=new DoubleNode(4);
        DoubleNode n5=new DoubleNode(5);

        n1.after(n2).after(n3).after(n4).after(n5);

        System.out.println(n5.pre.getData());
        System.out.println(n5.getData());
        System.out.println(n5.next.getData());
    }
}

package com.rh.demo1;

/**
 * 单链表结构，一个是节点元素，一个是节点引用，指向下一个节点
 * 主要是节点的append方法
 */
public class LoopNode {
    int data;
    LoopNode next=this;

    //构造函数
    public LoopNode(int data) {
        this.data = data;
    }

    //获取当前节点实例的数据
    public int getData(){
        return this.data;
    }

    //获取当前节点的下一个节点
    public LoopNode getNext(){
        return this.next;
    }

    //单链表只能删除下一个节点,它没法知道自己的上一个节点是谁
    public LoopNode removeNext(){
        //取出当前节点的下一个节点的下一个节点
        LoopNode curNode=this;
        LoopNode nextNode=this.next;
        LoopNode nextNextNode=nextNode.next;
        this.next=nextNextNode;
        nextNode=null;
        return curNode;
    }

    //插入节点
    public LoopNode after(LoopNode node){
        LoopNode curNode=this;
        LoopNode nextNode=this.next;
        curNode.next=node;
        node.next=nextNode;
        return node;
    }


    public static void main(String[] args) {
        LoopNode n1=new LoopNode(1);
        LoopNode n2=new LoopNode(2);
        LoopNode n3=new LoopNode(3);
        LoopNode n4=new LoopNode(4);
        LoopNode n5=new LoopNode(5);

        n1.after(n2).after(n3).after(n4).after(n5);
        System.out.println(n1.getNext().getData());
        System.out.println(n4.getNext().getData());
        System.out.println(n5.getNext().getData());

        n1.removeNext().removeNext();
        System.out.println(n1.getNext().getData());

    }
}

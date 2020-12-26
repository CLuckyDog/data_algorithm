package com.rh.demo1;

/**
 * 单链表结构，一个是节点元素，一个是节点引用，指向下一个节点
 * 主要是节点的append方法
 */
public class Node {
    int data;
    Node next;

    //构造函数
    public Node(int data) {
        this.data = data;
    }

    //获取当前节点实例的数据
    public int getData(){
        return this.data;
    }

    //给整个链表最后面添加节点
    public Node append(Node next){
        Node currentNode=this;//把当前实例作为当前节点游标，依次往后遍历，在最后node进行添加
        while (true){
            Node nextNode=currentNode.next;
            //如果当前节点没有下一个节点，说明已经是最后一个节点
            if (nextNode == null){
                currentNode.next=next;
                break;
            }
            currentNode=nextNode;
        }
        return currentNode;
    }

    //获取当前节点的下一个节点
    public Node getNext(){
        return this.next;
    }

    //显示当前节点及其后的所有节点
    public void show(){
        Node currentNode=this;
        while (true){
            System.out.print(currentNode.data+"    ");
            currentNode=currentNode.next;
            if (currentNode == null){
                break;
            }
        }
    }

    //单链表只能删除下一个节点,它没法知道自己的上一个节点是谁
    public Node removeNext(){
        //取出当前节点的下一个节点的下一个节点
        Node curNode=this;
        Node nextNode=this.next;
        Node nextNextNode=nextNode.next;
        this.next=nextNextNode;
        nextNode=null;
        return curNode;
    }

    //插入节点
    public Node after(Node node){
        Node curNode=this;
        Node nextNode=this.next;
        curNode.next=node;
        node.next=nextNode;
        return node;
    }


    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
//        int data = node1.getData();
//        System.out.println(data);

//        node1.append(node2);
//        node2.append(node3);
//
//        System.out.println(node2.getNext().getData());

//        node1.append(node2);
//        node1.append(node3);
//        System.out.println(node2.getNext().getData());

        node1.append(node2).append(node3).append(node4).append(node5);
//        System.out.println(node2.getNext().getData());

//        node1.show();
//        node1.removeNext();
//        node1.show();
        node2.after(new Node(6)).after(new Node(7));
        node1.show();

    }
}

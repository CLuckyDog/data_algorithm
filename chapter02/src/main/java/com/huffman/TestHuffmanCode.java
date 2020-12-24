package com.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 潘忠健
 * \* Date: 2020/12/24
 * \* Time: 10:16
 * \* Description:
 * \
 */
public class TestHuffmanCode {
    public static void main(String[] args) {
        String s = "can you can a can as a can canner can a can.";
        byte[] bytes = s.getBytes();
        //进行赫夫曼编码
        byte[] b=huffmanZip(bytes);
    }

    /**
     * 进行赫夫曼编码压缩的方法
     * @param bytes
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //先统计每一个byte出现的次数，并放入一个集合中
        List<Node> nodes=getNodes(bytes);
        //创建一颗赫夫曼树
        Node tree= createHuffmanTree(nodes);
        //创建一个赫夫曼编码表
        //编码
        return new byte[0];
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size()>1){
            //排序
            Collections.sort(nodes);
            //取出两个权值最低的二叉树
            Node left = nodes.get(nodes.size() - 1);
            Node right = nodes.get(nodes.size() - 2);
            //创建一颗新的二叉树
            Node parent=new Node(null,left.weight+right.weight);
            //把之前取出来的两颗二叉树设置为新创建的二叉树的子树
            parent.left=left;
            parent.right=right;
            //把前面取出来的两颗二叉树删除
            nodes.remove(left);
            nodes.remove(right);
            //把新创建的的二叉树放入集合中
            nodes.add(parent);
        }
        //返回树的根节点
        return nodes.get(0);
    }

    /**
     * 把byte数组转为node集合
     * @param bytes
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes= new ArrayList<>();
        //存储每一个byte出现了多少次
        Map<Byte,Integer> counts= new HashMap<>();
        //统计每一个byte出现的次数
        for (byte b:bytes){
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }
        //把每一个键值对转换为一个node对象
        for (Map.Entry<Byte,Integer> entry:counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }
}
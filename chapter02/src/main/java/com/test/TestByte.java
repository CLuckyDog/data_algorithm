package com.test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 潘忠健
 * \* Date: 2020/12/24
 * \* Time: 9:14
 * \* Description:
 * \
 */
public class TestByte {
    public static void main(String[] args) {
        String s = "can you can a can as a can canner can a can.";
        byte[] bytes = s.getBytes();
        System.out.println(bytes[0]);
    }
}
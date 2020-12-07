package com.chinatel;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 潘忠健
 * \* Date: 2020/11/24
 * \* Time: 10:54
 * \* Description:
 * \
 */
public class GenericMemoryCell <AnyType> {
    private AnyType storedValue;
    public AnyType read(){
        return storedValue;
    }
    public void write(AnyType x){
        storedValue=x;
    }

    public static void main(String[] args) {
        GenericMemoryCell<String> cell=new GenericMemoryCell<>();
        Parent[] ps=new Parent[10];
        ps[0]= new Son();

        List<Parent> list=new ArrayList<>();
        list.add(new Son());
    }

}
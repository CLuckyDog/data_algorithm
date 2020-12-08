package com.chinatel;

import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 潘忠健
 * \* Date: 2020/12/8
 * \* Time: 9:52
 * \* Description:选择排序（有人理解成反向冒泡）
 *                        每一轮都从数组剩余元素中找出最小元素，依次放到左边，就实现了升序排序
 * \
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 3, 2, 8, 22, 98, 23, 43, 13, 58};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr){
        //第一个for遍历所有元素
        for (int i=0;i<arr.length;i++){
            //取出待确认的下标
            int minNum=arr[i];
            //遍历i后面的元素，并与元素i进行比对，选出最小的元素，并交换元素
            for (int j=i;j<arr.length;j++){
                //交换元素
                if(arr[j]<minNum){
                    minNum=arr[j];
                    arr[j]= arr[i];
                    arr[i]=minNum;
                }
            }
        }
    }
}
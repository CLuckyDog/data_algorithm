package com.chinatel;

import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 潘忠健
 * \* Date: 2020/12/7
 * \* Time: 9:26
 * \* Description:直接插入排序
 *                        思想：从数组第二个元素开始遍历比较，把前面的数组当做有序数组
 *                        把待处理元素，与前面的有序数组进行比对，找到对应位置，放置进去
 * \
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 3, 2, 8, 22, 98, 23, 43, 13, 58};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        //第一个for，进行所有元素的遍历，从第二个元素开始，就是下标为1的位置开始
        for (int i=1;i<arr.length;i++){
                //第二个循环，给待处理元素找位置，进行比对，所以，已经排好序的数组是，0 ~ i-1
                for (int j=i-1;j>=0;j--){
                    //如果arr[j]>arr[i]，则把num和j位置数字交换,把待比较数字往前移动
                    if (arr[j]>arr[i]){
                        int temp=arr[j];
                        arr[j]=arr[i];
                        arr[i]=temp;
                        i--;
                    }
                }
        }
    }
}
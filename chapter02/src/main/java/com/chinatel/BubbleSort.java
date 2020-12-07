package com.chinatel;

import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 潘忠健
 * \* Date: 2020/12/1
 * \* Time: 16:50
 * \* Description: 冒泡排序
 *                          就是把一组序列，从第一个元素开始，依次和后面的元素进行比较大小
 *                           如果大于后者，就交换位置，反之，不交换位置，继续往后比较
 * \
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1,88,23,58,134,2353,232,444};
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     *  时间复杂度：两个嵌套循环，所以是O(n^2)
     * @param arr 待排序的数组
     */
    public static void bubbleSort(int[] arr){

        //设置一个临时变量，用于比较交换位置
        int tmp=0;

        //第一个for循环是计算轮询次数，arr.length - 1 次，一个数不需要和自身比较
        for (int i =0;i<arr.length-1;i++){
            //第二次for循环是为了进行数的比较，并判断是否要交换位置
            //减去i的作用是，已经排好序的i个数字放到序列后面了，
            // 所以，不要在进行比较，及时比较不影响结果，但是，影响效率
            for(int j =0;j<arr.length-1-i;j++){

                if (arr[j]>arr[j+1]){
                    tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }

            }
        }
    }
}
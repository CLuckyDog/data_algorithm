package com.chinatel;

import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 潘忠健
 * \* Date: 2020/12/11
 * \* Time: 9:31
 * \* Description:  归并排序
 *                          是把两个有序数组，陆续把头一个元素进行比较，小的放入临时数组，最后返回
 *                          如果一个数组，是乱序的，我们需要把它递归分成两个有序数组，进行上述处理即可
 *                          所以，我们需要的参数是，数组，开始位置，中间位置，结束位置
 * \
 */
public class MergeSort {

    private static int counts=1;

    public static void main(String[] args) {
//        int[] arr = new int[]{1,3,5,7,9,2,4,6,8,10};
        int[] arr = new int[]{5, 2, 3, 2, 8, 22, 98, 23, 43, 13, 58};
//        int[] arr = new int[]{3,1};
//        merge(arr, 0, (arr.length+0)/2, arr.length);
        /*这里需要传入   arr.length-1  否则，会出现漏处理情况*/
        mergeSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    //递归处理任意乱序数组
    public static  void mergeSort(int[] arr,int low,int high){
        int middle=(high+low)/2;
        if (low<high){
            mergeSort(arr,low,middle);//左边
            mergeSort(arr,middle+1,high);//右边
            merge(arr,low,middle,high);//归并
        }
    }

    public static  void merge(int[] arr,int low,int middle,int high){
        System.out.println("第["+counts+"]次调用merge方法!");
        System.out.println("low:["+low+"],middle:["+middle+"],high:["+high+"]");
        System.out.println(Arrays.toString(arr));
        counts++;
        //创建一个临时数组，保存排序后的结果
        int[] temp = new int[high-low+1];/*易错点*/

        int leftIndex=low;//左边数组的游标
        int rightIndex=middle+1;//右边数组的游标

        int tempIndex=0;//temp数组的游标，标记存放到那个位置

        //进行循环比较
        while (leftIndex<=middle&&rightIndex<=high){

            //对两个数组值进行比较，小的放临时数组前面
            if (arr[leftIndex]<arr[rightIndex]){
                temp[tempIndex]=arr[leftIndex];
                leftIndex++;
            }else {
                temp[tempIndex]=arr[rightIndex];
                rightIndex++;
            }

            tempIndex++;
        }

        //处理剩余左边的数组数据
        while (leftIndex<=middle){
            temp[tempIndex]=arr[leftIndex];
            leftIndex++;
            tempIndex++;
        }
        //处理剩余右边的数组数据
        while (rightIndex<=high){
            temp[tempIndex]=arr[rightIndex];
            rightIndex++;
            tempIndex++;
        }
        System.out.println(Arrays.toString(temp));
        //把临时数组内容转移到原数组
        for (int i=0;i<temp.length;i++){
                arr[i+low]=temp[i];/*易错点*/
        }
    }

}
package com.chinatel;

import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 潘忠健
 * \* Date: 2020/12/1
 * \* Time: 17:14
 * \* Description: 快速排序
 * 1、选取数组第一个数作为基准数，进行比较，把大于它的数放前面，小于它的数放后面
 * 2、需要两个游标，地位游标和高位游标，进行交换数字使用
 * 3、当游标重合时，把基准数放到该位置，第一轮结束，并产生两个未排序的数组
 * 4、把两个未排序的数组分别传入该方法，进行递归处理，结束条件是开始位置大于等于结束位置
 * \
 */
public class QuickSort {
    public static int count = 1;

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 3, 2, 8, 22, 98, 23, 43, 13, 58};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort(int[] arr, int start, int end) {
        System.out.println("-------第" + count + "次调用quickSort------");
        count++;

        int low = start;//左边的下标
        int high = end;//右边的下标
        //递归结束条件，数组开始位置小于数组结束位置
        if (start < end) {
            int stard = arr[start];
            /*对每一轮数组的循环条件*/
            while (low < high) {
                //处理高位游标上的数字，如果它大于等于基准数，则进行high--
                //防止下标越界，先进行判断low<high
                while (low < high && arr[high] >= stard) {
                    high--;
                }
                //如果找到右边数字小于基准数的，进行高位和低位数字的交换
                arr[low] = arr[high];
                //处理低位游标上的数字，如果它小于基准数，则进行low++
                while (low < high && arr[low] < stard) {
                    low++;
                }
                //如果找到左边数字大于或等于基准数时，进行低位和高位数字的交换
                arr[high] = arr[low];
            }
            //一轮处理完之后，游标重合，把基准数放到该重合位置
            arr[low] = stard;

            //把处理后的两个数组，进行递归处理
            quickSort(arr, start, low);
            quickSort(arr, low + 1, end);
        }

    }
}
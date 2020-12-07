package com.chinatel;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 潘忠健
 * \* Date: 2020/11/30
 * \* Time: 17:40
 * \* Description:
 * \
 */
public class MaxSubSum {
    public static void main(String[] args) {
        int[] arr=new int[]{4,-3,5,-2,-1,2,6,-2};
        int maxSubSum = getMaxSubSum(arr);
        System.out.println(maxSubSum);
    }

    public static int getMaxSubSum(int[] a){
        int maxSum=0,thisSum=0;
        for (int j =0 ;j<a.length;j++){
            thisSum+=a[j];
            if (thisSum>maxSum){
                maxSum=thisSum;
            }else if (thisSum<0){
                thisSum=0;
            }
        }
        return maxSum;
    }
}
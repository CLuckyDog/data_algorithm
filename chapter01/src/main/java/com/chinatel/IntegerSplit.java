package com.chinatel;

import java.util.Scanner;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 潘忠健
 * \* Date: 2021/3/15
 * \* Time: 15:13
 * \* Description:
 *                      105元，发100个红包，每个红包不小于1元，不大于90元，最小单位是1元。
 *                      问：有多少种不同的红包组合，用程序实现。不考虑顺序。
 * \
 */
public class IntegerSplit {

    public static void main(String[] args) {
        //第一种方法
        // 1.将正整数n无序拆分成最大数为m的拆分方案个数，要求所有拆分方案不重复。
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int temp = integerhuafen(n, m);
            System.out.print(temp);
            scanner.close();
            break;
        }

        //第二种方法
        //2.将正整数n拆分成k份，每份不为空，不考虑顺序，求划分的种类。
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()){
//            int n = scanner.nextInt();
//            int k = scanner.nextInt();
//            int [][] arr = new int [n + 1][k + 1];
//            arr[0][0] = 1;
//
//            for (int i = 1; i <= n; i++){
//                for (int j = 1; j <= k; j++){
//                    if(i >= j){
//                        arr[i][j]=arr[i-j][j]+arr[i-1][j-1];
//                    }
//                }
//            }
//            System.out.println(arr[n][k]);
//        }
//        scanner.close();

    }

    public static int integerhuafen(int n, int m) {

        int dp[][] = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++ ){
                if(i == 1 || j == 1){
                    dp[i][j] = 1;
                }else if (i == j) {
                    dp[i][j] = 1 + dp[i][j - 1];
                }else if (i < j) {
                    dp[i][j] = dp[i][i];
                }else {
                    dp[i][j] = dp[i - j][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    public static int[][] integerhuafen2(int n, int m) {

        int[][] dp = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++ ){
                if(i == 1 || j == 1){
                    dp[i][j] = 1;
                }else if (i == j) {
                    dp[i][j] = 1 + dp[i][j - 1];
                }else if (i < j) {
                    dp[i][j] = dp[i][i];
                }else {
                    dp[i][j] = dp[i - j][j] + dp[i][j - 1];
                }
            }
        }
        return dp;
    }



}
最长公共子串
===========

# 一. 问题描述

> 最长公共子串(LCS),有三种情况: 1.公共子串的元素必须相邻  2.公共子串的元素可以不相邻  3.求多个字符串而不是两个字符串的最长公共子串

# 二. 公共子串的元素必须相邻

> 给定两个字符串A和B，同时给定两串的长度n和m，返回两个字符串的最长公共子串。例如："1AB2345CD",9,"12345EF",7  返回：4

> 解决方法是采用一个矩阵来记录两个字符串中所有位置的两个字符之间的匹配情况，若是匹配则为1，否则为0。然后求出对角线最长的1序列，其对应的位置就是最长匹配子串的位置。例如：A串作为x轴，B串作为y轴，矩阵对应位置表示两个字符之间的匹配情况，从矩阵可以找到，黄色部分是最长的匹配子串。如图1所示:
![image](https://github.com/ShaoQiBNU/LCS/blob/master/images/1.png)

> 但是在0和1矩阵中找最长的1对角线序列又要花去一定的时间。通过改进矩阵的生成方式和设置标记变量，可以省去这部分时间。如图2所示：
![image](https://github.com/ShaoQiBNU/LCS/blob/master/images/2.png)

> 算法的基本思想：当字符匹配的时候，并不是简单的给相应元素赋上1，而是赋上其左上角元素的值加1。这样用两个标记变量来标记矩阵中值最大的元素的位置，在矩阵生成的过程中来判断当前生成的元素的值是不是最大的，据此来改变标记变量的值，那么到矩阵完成的时候，最长匹配子串的位置和长度就已经出来了。代码如下：

```JAVA
import java.util.*;
public class LongestSubstring {
    public static int n;
    public static int m;
    public int findLongest(String A, int n, String B, int m) {
        if(n == 0 || m == 0){
            return 0;
        }

        int[][] matrix = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                matrix[i][j] = 0;
            }
        }
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(A.charAt(i) == B.charAt(j)){
                    if(i == 0 || j == 0){
                        matrix[i][j] = 1;
                    }else{
                        matrix[i][j] = matrix[i-1][j-1] + 1;
                    }
                    max = (max > matrix[i][j] ? max : matrix[i][j]);
                }
            }
        }
        return max;
    }
    public static void main(String[] args){
        LongestSubstring LongestSubstring=new LongestSubstring();
        n=9;
        m=7;
        String A="1AB2345CD";
        String B="12345EF";
        int res=LongestSubstring.findLongest(A,n,B,m);
        System.out.println(res);
    }
}

```


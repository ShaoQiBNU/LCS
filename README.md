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
# 三. 公共子串的元素可以不相邻

> 例如，输入： "1A2C3D4B56",10,"B1D23CA45B6A",12，返回： 6

> 采用动态规划算法，DP数组中的i和j分别表示在两串中，分别取到前i位和前j位时，两个前缀子串所递推得到的最长公共子序列。 将数组中的dp[i][0]和dp[0][i]分别初始化为0，表示无论i是多少，另一个串前缀取i位，与当前串的空串都不会有任何公共子序列。 然后开始递推，遍历i,j以对比两串中相等的字符，一旦遇到相等的字符，将从i-1,j-1的位置继承状态，并延续继承到的最长公共子序列的记录值，也就是+1操作，表示当两串的第i位字符和第j位字符相等时，将从不包括这两个字符的前缀子串里得到的最长公共子序列+1，得到当前位置的新的最长公共子序列。也就是求解当前i,j的状态，来自于i-1,j-1的状态+1获得。而当i，j位置的字符不等时，当前位不能延续最长公共子序列的值，只能从之前i-1,j 和i,j-1取最大值继承。这样递推，将得到整个串的最长公共子序列。状态转移方程： 
![image](https://github.com/ShaoQiBNU/LCS/blob/master/images/3.png)

> 代码如下：

```JAVA
import java.util.*;

public class LCS {
    public int findLCS(String A, int n, String B, int m) {

        int[][] dp=new int[n+1][m+1];

        // 边界赋值为0
        for(int i=0;i<=n;i++){
            dp[i][0]=0;
        }

        for(int i=0;i<=m;i++){
            dp[0][i]=0;
        }

        // 每个字符进行比较
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){

                // 字符相等，继承当前最长子串
                if(A.charAt(i)==B.charAt(j)){
                    dp[i+1][j+1]=dp[i][j]+1;
                }

                // 字符不同，继承最大的
                else{
                    dp[i+1][j+1]=dp[i+1][j] > dp[i][j+1] ? dp[i+1][j] : dp[i][j+1];
                }
            }
        }
        return dp[n][m];
    }
    public static void main(String[] args){
        LCS Robot=new LCS();
        n=10;
        m=12;
        String A="1A2C3D4B56";
        String B="B1D23CA45B6A";
        int res=Robot.countWays(A,n,B,m);
        System.out.println(res);
    }
}
```

# 四. 应用

> 对于一个字符串，如果不要求子串连续，那么一个字符串的最大回文子串的最大长度是多少？输出该字符串的最长回文子串的长度。（不要求输出最长回文串，并且子串不要求连续）  输入： adbca    输出：3  ada/aba/aca

> 要找的是最长的回文子序列（不一定连续），可以先对输入的字符串进行反转操作，再利用动态规划和原来的字符串求最长公共子序列。

```java
import java.util.*;
public class Main {
    static String s;
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        
        s = in.next();
        
        int n = s.length();
        
        if(n==1){
            
            System.out.println(1);
        }
        

        else{

            int[][] dp=new int[n+1][n+1];

            // 边界设置为1
            for(int i=0; i<=n; i++){
                
                dp[i][0]=0;
                dp[0][i]=0;
            }

            // 动态规划，寻找最长公共子串
            for(int i=0;i<n;i++){
                
                for(int j=0;j<n;j++){
                    
                    if(s.charAt(i)==s.charAt(n-j-1)){
                        
                        dp[i+1][j+1] = dp[i][j] + 1;
                    }
                    
                    else{
                        
                        dp[i+1][j+1] = dp[i+1][j] > dp[i][j+1] ? dp[i+1][j]: dp[i][j+1];
                    }
                
                }
            }
        
            System.out.println(dp[n][n]);
        
        }  
    }
}
```

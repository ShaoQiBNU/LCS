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
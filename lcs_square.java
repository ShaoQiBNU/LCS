import java.util.Scanner;
 
public class Main{

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        int max = Integer.MIN_VALUE;

        // 在各个位置将字符串分成两部分
        for(int i = 0; i < str.length(); i++){

            max = Math.max(max, lcs(str.substring(0, i), str.substring(i)));
        }

        System.out.println(2 * max);
    }
     

    public static int lcs(String str1, String str2) {

        // 设置dp数组
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        // 寻找两个子串的最长公共子序列
        for(int i = 0; i < str1.length(); i++) 

            for(int j = 0; j < str2.length(); j++) 

                dp[i + 1][j + 1] = str1.charAt(i) == str2.charAt(j) ? dp[i][j] + 1 : Math.max(dp[i + 1][j], dp[i][j + 1]);

        return dp[str1.length()][str2.length()];
    }
}
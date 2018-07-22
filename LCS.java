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
package n3304;

import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n3304.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            dp = new int[a.length()+1][b.length()+1];
            for (int i = 1; i <= a.length(); i++) {
                for (int j = 1; j <= b.length(); j++) {
                    if(a.charAt(i-1) == b.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
                    else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
            sb.append("#" + t + " " + dp[a.length()][b.length()]+"\n");
        }

        for(int i=0;i< dp.length;i++){
            for(int j=0;j<dp[i].length;j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        System.out.print(sb.toString());
        br.close();
    }

    static String backTracking(String a, String b, int i, int j) {
        if(i==0 || j==0) return "";
        else if (a.charAt(i) == b.charAt(j)) return backTracking(a, b, i-1, j-1) + a.charAt(i);

        if(dp[i][j-1] >= dp[i-1][j]) return backTracking(a, b, i, j-1);
        else return backTracking(a, b, i-1, j);
    }
}

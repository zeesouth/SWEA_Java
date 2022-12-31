package n8676;

import java.io.*;

public class Solution {
    final static int MAX = 1000000007;
    static int T;
    static int[] dp;
    static String s;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n8676.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            s = br.readLine();
            dp = new int[7];
            for(int i=0;i<s.length();i++) {
                char curr = s.charAt(i);
                if(curr == 'S') {
                    dp[0] = (dp[0]+1)%MAX;
                    dp[3] = (dp[3]+dp[2])%MAX;
                } else if (curr == 'A') {
                    dp[1] = (dp[0]+dp[1])%MAX;
                } else if (curr == 'M') {
                    dp[2] = (dp[1]+dp[2])%MAX;
                } else if (curr == 'U') {
                    dp[4] = (dp[3]+dp[4])%MAX;
                } else if (curr == 'N') {
                    dp[5] = (dp[4]+dp[5])%MAX;
                } else if (curr == 'G') {
                    dp[6] = (dp[5]+dp[6])%MAX;
                }
            }
            sb.append("#").append(t).append(" ").append(dp[6]).append("\n");
        }
        System.out.print(sb);
    }
}
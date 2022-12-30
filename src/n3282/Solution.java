package n3282;

import java.io.*;
import java.util.*;

public class Solution {
    static int T,N,K;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n3282.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N+1][2];
            for(int i=1;i<=N;i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            dp = new int[N+1][K+1];
            for(int i=1;i<=N;i++) {
                for(int j=1;j<=K;j++) {
                    if(arr[i][0] <= j)
                        dp[i][j] = Math.max(dp[i-1][j-arr[i][0]]+arr[i][1], dp[i-1][j]);
                    else
                        dp[i][j] = dp[i-1][j];
                }
            }
            sb.append("#"+t+" "+dp[N][K]+"\n");
        }
        System.out.print(sb.toString());
    }
}


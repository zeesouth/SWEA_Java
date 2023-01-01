package n12222;

import java.io.*;
public class Solution {
    static int T;
    static int[] s, dp;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n12222.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++)
        {
            char[] currStr = br.readLine().toCharArray();
            s = new int[currStr.length];
            for(int i=0;i<currStr.length;i++) s[i] = currStr[i]-'a';
            dp = new int[currStr.length+1];
            dp[1] = 1;
            dp[2] = s[1]==s[0] ? dp[1]:dp[1]+1; // 이전 문자열과 같다면 동결 -> 그렇지 않다면 +1 (다르면 나누는게 좋음)
            if(dp[1]==dp[2]) dp[3]=dp[2]+1; // 만약 이전 두글자가 붙여져 있는 글자라면 -> 현재에서 +1 (문자열 끊어야 함)
            else dp[3]=s[1]==s[2]?dp[2]:dp[2]+1;  // 이전 두 글자가 붙여져 있는 글자가 아니라면, 만약 현재 문자와 바로 직전 문자가 같은 문자라면 -> 만약 같다면 동결 / 그렇지 않다면 +1
            for(int i=4;i<s.length+1;i++){
                // 지난 연속된 3개의 문자열 중에 겹치는 것이 하나도 없었더라면 : 현재 문자와 이전 문자 비교 -> 같으면 동결, 그렇지 않으면 +1
                if(dp[i-2]+1 == dp[i-1] && dp[i-3]+1==dp[i-2]) dp[i] = s[i-1]==s[i-2] ? dp[i-1] : dp[i-1]+1;
                // 만약, 하나라도 겹치는게 있었다면 : 일단 경우의 수 추가
                else dp[i]=dp[i-1]+1;
            }
            sb.append("#").append(test_case).append(" ").append(dp[dp.length-1]).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}
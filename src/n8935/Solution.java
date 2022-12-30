package n8935;

import java.io.*;
import java.util.*;

public class Solution {

    static int T, N, M;
    static Integer[] A, B;
    static int[][][] dp = new int[3001][101][101];

    static int dfs(int n, int m, int skip) {

        if (n > N) return 0;
        if (dp[n][m][skip] != -1) return dp[n][m][skip];

        int result = 0;

        if (n < N) result = Math.max(result, dfs(n + 2, m, skip) + A[n]);
        if (n < N && m + skip < M) {
            result = Math.max(result, dfs(n + 1, m, skip + 1) + A[n]);
            result = Math.max(result, dfs(n + 1, m + 1, skip) + B[m]);
        }

        if (m + skip + 1 < M) result = Math.max(result, dfs(n, m + 1, skip + 1) + B[m]);
        if (n < N) result = Math.max(result, dfs(n + 1, m, skip));
        if (m + skip < M) result = Math.max(result, dfs(n, m, skip + 1));

        return dp[n][m][skip] = result;

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n8935.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            A = new Integer[N];
            for (int i = 0; i < N; i++) A[i] = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            B = new Integer[M];
            for (int i = 0; i < M; i++) B[i] = Integer.parseInt(br.readLine());

            for (int i = 0; i <= N; i++) for (int j = 0; j <= M; j++)
                Arrays.fill(dp[i][j], -1);

            Arrays.sort(B, Collections.reverseOrder());

            sb.append("#").append(t).append(" ").append(dfs(0, 0, 0)).append("\n");
        }
        System.out.print(sb);
    }
}
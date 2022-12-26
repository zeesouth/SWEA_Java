package n1868;

import java.io.*;
import java.util.*;

public class Solution {

    static int N, nearZeroCnt, broadCnt;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
    static int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n1868.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {
            // init
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            int totalCnt = N*N;
            int landmineCnt = nearZeroCnt = broadCnt = 0;

            for(int i=0;i<N;i++) {
                String currLine = br.readLine();
                for(int j=0;j<N;j++) {
                    if(currLine.charAt(j) == '*') {
                        map[i][j] = -1;
                        landmineCnt++;  // 지뢰개수
                    } else {
                        map[i][j] = -2;
                    }
                }
            }

            // 지뢰 개수 세주기
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(map[i][j] != -1) {
                        int count = 0;
                        for (int k = 0; k < 8; k++) {
                            if(isValid(i+dy[k], j+dx[k])) if(map[i+dy[k]][j+dx[k]] == -1) count++;
                        }
                        map[i][j] = count;
                    }
                }
            }

            // 0으로 되어있는 칸과 그 주변 칸들 count, 만약 주변칸이 0이면 큐에 넣어주기
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(!visited[i][j] && map[i][j] == 0) areaClick(i, j);
                }
            }
            System.out.println("#"+t+" "+(totalCnt-landmineCnt-nearZeroCnt+broadCnt));

        }
    }

    static void areaClick(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;
        nearZeroCnt++;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            for(int i=0;i<8;i++) {
                int currY = curr[0]+dy[i];
                int currX = curr[1]+dx[i];
                if(isValid(currY, currX)) {
                    if(!visited[currY][currX]){
                        if(map[currY][currX] == 0) q.add(new int[]{currY, currX});
                        visited[currY][currX] = true;
                        nearZeroCnt++;
                    }
                }
            }

        }
        broadCnt++;
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}

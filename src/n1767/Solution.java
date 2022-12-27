package n1767;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pair {
    int y, x;
    Pair(int y, int x) {this.y = y; this.x = x;}
}

public class Solution {

    static int N, coreCnt, min, max;
    static int[][] map;
    static int[][] visited; // 방문 횟수 체킹하기

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static Pair[] coreInfo;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n1767.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new int[N][N];
            coreInfo = new Pair[N * N];
            coreCnt = 0;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    // map에 모든 코어 정보를 저장하고, coreInfo에 가장자리를 제외한 코어 정보 저장하기
                    if ((map[i][j] = Integer.parseInt(st.nextToken())) == 1 && !isEdge(i, j)) coreInfo[coreCnt++] = new Pair(i, j);
            }

            dfs(0, 0, 0);

            System.out.println("#"+t+" "+min);
        }
        br.close();
    }

    static void dfs(int idx, int cnt, int len) {

        // 종료 조건 : 코어의 개수만큼 돌았을 때
        if(idx == coreCnt) {
            if(max < cnt) { // 연결할 수 있는 코어 개수가 더 많은 경우
                max = cnt;
                min = len;
            } else if (max == cnt) { // 코어 개수가 같은 경우
                if(min > len) min = len;
            }
            return;
        }

        int y = coreInfo[idx].y;
        int x = coreInfo[idx].x;

        for(int i=0;i<4;i++) {
            // 연결선 길이
            int count = 0;
            // 원래 x, y좌표
            int oy = y;
            int ox = x;
            // temp x, y
            int ty = y;
            int tx = x;

            while(true) {
                ty += dy[i];
                tx += dx[i];
                // 벽을 만나면 break
                if(!isValid(ty, tx)) break;
                // 중간에 코어나 전선을 만나는 경우
                if(map[ty][tx] == 1) {
                    count = 0; break;
                }
                count++;
            }

            // count 크기만큼 전선으로 표시
            for(int j=0;j<count;j++){
                oy+=dy[i];
                ox+=dx[i];
                map[oy][ox] = 1;
            }

            if(count == 0) dfs(idx+1, cnt, len);            // 전선을 연결할 수 없는 코어
            else {                                              // 전선을 연결할 수 있는 코어
                dfs(idx + 1, cnt + 1, len + count);

                // 위의 dfs 함수가 종료되면 전선을 없애고 초기화하기 (다른 방향의 전선을 체킹하기 위함)
                oy = y;
                ox = x;
                for(int j=0;j<count;j++){
                    oy+=dy[i];
                    ox+=dx[i];
                    map[oy][ox] = 0;
                }
            }


        }

    }

    static boolean isEdge(int y, int x) {
        return y == 0 || x == 0 || y == N-1 || x == N-1;
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}

package n1855;

import java.io.*;
import java.util.*;

class Node {
    int parent, depth;
    LinkedList<Integer> child;
    Node(int parent) {
        this.parent = parent;
        child = new LinkedList<>();
    }
}

public class Solution {
    static int N;
    static long answer;
    static Node[] nodes;
    static int[] firstVisitCnt, segMinTree;
    static ArrayList<Integer> visitRoute;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/n1855.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++) {
            N = Integer.parseInt(br.readLine());
            nodes = new Node[N+1];
            firstVisitCnt = new int[N+1];
            visitRoute = new ArrayList<>();

            nodes[1] = new Node(0);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=2;i<=N;i++) {
                int p = Integer.parseInt(st.nextToken());
                nodes[p].child.add(i);
                nodes[i] = new Node(p); // i의 부모는 p
            }
            dfs();
            int size = 1 << ((int) Math.ceil(Math.log(visitRoute.size())/Math.log(2))+1);
            segMinTree = new int[size];
            init(1, 0, visitRoute.size()-1);

            answer = 0;
            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            int beforeNode = 0;
            while(!q.isEmpty()) {
                int n = q.poll();
                if(beforeNode != 0) {
                    int lca = 0;
                    if(firstVisitCnt[beforeNode] < firstVisitCnt[n])
                        lca = getMin(1, 0, visitRoute.size()-1, firstVisitCnt[beforeNode], firstVisitCnt[n]);
                    else
                        lca = getMin(1, 0, visitRoute.size()-1, firstVisitCnt[n], firstVisitCnt[beforeNode]);
                    answer += (nodes[n].depth-nodes[lca].depth)+(nodes[beforeNode].depth-nodes[lca].depth);
                }
                beforeNode = n;
                for(int c : nodes[n].child) q.add(c);
            }
            System.out.println("#"+t+" "+answer);
        }
    }

    static void dfs() {
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[] {1, 0, 0}); // node, depth, parent

        while(!stack.isEmpty()) {
            int[] curr = stack.pop();
            nodes[curr[0]].depth = curr[1];
            if(nodes[curr[0]].parent!= 0) visitRoute.add(nodes[curr[0]].parent);
            if(firstVisitCnt[curr[0]] == 0) firstVisitCnt[curr[0]] = visitRoute.size();
            visitRoute.add(curr[0]);
            for(int child : nodes[curr[0]].child) {
                stack.push(new int[]{child, curr[1] + 1, curr[0]});
            }
        }
    }

    static int init(int node, int start, int end) {
        if(start == end)
            return segMinTree[node] = visitRoute.get(start);
        int mid = (start+end)/2;
        return segMinTree[node] = Math.min(init(node*2, start, mid), init(node*2+1, mid+1, end));
    }

    static int getMin(int node, int start, int end, int left, int right) {
        if(right < start || left > end)
            return Integer.MAX_VALUE;
        if(left <= start && end <= right)
            return segMinTree[node];
        int mid = (start+end)/2;
        return Math.min(getMin(node*2, start, mid, left, right), getMin(node*2+1, mid+1, end, left, right));
    }
}

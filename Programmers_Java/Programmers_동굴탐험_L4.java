package Programmers;

import java.util.*;

public class Programmers_동굴탐험_L4 {
    public static boolean solution(int n, int[][] path, int[][] order) {
        // 초기화 작업
        ArrayList<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int[] item : path) {
            edges[item[0]].add(item[1]);
            edges[item[1]].add(item[0]);
        }

        // 인덱스가 배열 값보다 선방문 해야한다.
        int[] key = new int[n];
        // 인덱스를 방문하려면 배열 값이 필요하다.
        int[] need= new int[n];
        boolean[] visited = new boolean[n];
        for (int[] item : order) {
            key[item[0]] = item[1];
            need[item[1]] = item[0];
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        if (need[0] != 0) return false;
        visited[0] = true;

        // 방문을 미뤄둔 edge들을 저장해둘 set
        Set<Integer> set = new HashSet<>();
        // BFS
        while (!q.isEmpty()) {
            int edge = q.poll();
            need[key[edge]] = 0;

            if (set.contains(key[edge])) {
                set.remove(key[edge]);
                q.add(key[edge]);
                visited[key[edge]] = true;
            }

            for (int item : edges[edge]) {
                if (visited[item]) continue;
                else if (need[item] != 0) set.add(item);
                else {
                    visited[item] = true;
                    q.add(item);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] path = {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
        int[][] order = {{8,5},{6,7},{4,1}};
        System.out.println(solution(9, path, order));
    }
}

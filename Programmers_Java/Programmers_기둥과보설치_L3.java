package Programmers;

import java.util.*;

public class Programmers_기둥과보설치_L3 {
    static class Point implements Comparable<Point> {
        // type이 0인 경우 기둥, 1인 경우 보
        int col, row, type;

        public Point(int col, int row, int type) {
            this.col = col;
            this.row = row;
            this.type = type;
        }

        // 졍렬을 위한 코드
        @Override
        public int compareTo(Point o) {
            if (this.col == o.col) {
                if (this.row == o.row) return this.type - o.type;
                return this.row - o.row;
            }
            return this.col - o.col;
        }

        // set의 contains를 사용하기 위한 코드
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return col == point.col &&
                    row == point.row &&
                    type == point.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(col, row, type);
        }
    }

    static Set<Point> set = new HashSet<>();

    public static int[][] solution(int n, int[][] build_frame) {
        for (int[] item : build_frame) {
            Point temp = new Point(item[0], item[1], item[2]);

            // 일단 시도해보고 set에 있는 부분 중 문제에 있는 조건대로 되지 않는다면
            // 행동을 취소한다.
            if (item[3] == 0) { // 삭제
                set.remove(temp);
                for (Point point : set) {
                    if (!check(point.col, point.row, point.type)) {
                        set.add(temp);
                        break;
                    }
                }
            } else { // 설치
                set.add(temp);
                for (Point point : set) {
                    if (!check(point.col, point.row, point.type)) {
                        set.remove(temp);
                        break;
                    }
                }
            }
        }

        List<Point> list = new ArrayList<>(set);
        Collections.sort(list);

        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            answer[i][0] = list.get(i).col;
            answer[i][1] = list.get(i).row;
            answer[i][2] = list.get(i).type;
        }
        return answer;
    }

    public static boolean check(int col, int row, int type) {
        // 기둥인 경우
        if (type == 0) {
            if (row == 0) return true;
            else if (set.contains(new Point(col, row - 1, 0))) return true;
            else if (set.contains(new Point(col, row, 1)) || set.contains(new Point(col - 1, row, 1)))
                return true;
        } else { // 보인 경우
            if (set.contains(new Point(col, row - 1, 0)) || set.contains(new Point(col + 1, row - 1, 0)))
                return true;
            else if (set.contains(new Point(col - 1, row, 1)) && set.contains(new Point(col + 1, row, 1)))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
        for (int[] item : solution(5, build_frame)) {
            System.out.println(Arrays.toString(item));
        }
    }
}


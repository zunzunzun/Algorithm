package Programmers;

public class Programmers_키패드누르기_L1 {
    static class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        void set(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        System.out.println(solution(numbers, "right"));
    }

    public static String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        Point left = new Point(3, 0);
        Point right = new Point(3, 2);

        int temp, row, col;
        for (int number : numbers) {
            if (number == 1 || number == 4 || number == 7) {
                sb.append("L");
                left.set((number - 1) / 3, 0);
            } else if (number == 3 || number == 6 || number == 9) {
                sb.append("R");
                right.set((number - 1) / 3, 2);
            } else {
                if (number == 0) {
                    row = 3;
                    col = 1;
                } else {
                    temp = number - 1;
                    row = temp / 3;
                    col = temp % 3;
                }

                int leftDistance = Math.abs(left.row - row) + Math.abs(left.col - col);
                int rightDistance = Math.abs(right.row - row) + Math.abs(right.col - col);

                if (leftDistance < rightDistance) {
                    sb.append("L");
                    left.set(row, col);
                } else if (rightDistance < leftDistance) {
                    sb.append("R");
                    right.set(row, col);
                } else if (hand.equals("left")) {
                    sb.append("L");
                    left.set(row, col);
                } else {
                    sb.append("R");
                    right.set(row, col);
                }
            }
        }

        return sb.toString();
    }
}

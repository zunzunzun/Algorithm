package Programmers;

public class Programmers_자물쇠와열쇠_L3 {
    public static boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;

        // lock 배열에 key의 길이의 -1 만큼 공간을 4방향에 추가한다.
        // key를 바깥부터 대조함으로써 lock을 열 수 있는지 완전 탐색한다.
        int[][] paddingLock = new int[n + 2 * (m - 1)][n + 2 * (m - 1)];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                paddingLock[i + m - 1][j + m - 1] = lock[i][j];
            }
        }

        // key는 4방향으로 돌릴수 있다.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n + m - 1; j++) {
                for (int k = 0; k < n + m - 1; k++) {

                    // paddingLock에 key 값을 더한다.
                    for (int l = 0; l < m; l++) {
                        for (int o = 0; o < m; o++) {
                            paddingLock[j + l][k + o] += key[l][o];
                        }
                    }

                    boolean flag = true;
                    loop:
                    for (int l = 0; l < n; l++) {
                        for (int o = 0; o < n; o++) {
                            // 1이 아니라면 key와 lock은 맞는게 아니다.
                            if (paddingLock[l + m - 1][o + m  -1] != 1) {
                                flag = false;
                                break loop;
                            }
                        }
                    }

                    // key와 lock이 맞으므로 true를 리턴
                    if (flag) return true;

                    // paddingLock을 재사용 하기 위해 앞에서 더한 값을 뺀다.
                    for (int l = 0; l < m; l++) {
                        for (int o = 0; o < m; o++) {
                            paddingLock[j + l][k + o] -= key[l][o];
                        }
                    }
                }
            }

            // key를 돌리는 부분.
            // 3번 돌린 상태에서 한번 더 돌리는 것은 결국 처음으로 돌아오기에 연산할 필요가 없다.
            if (i == 3) break;
            // key 돌리기 위해 임시로 저장해둘 배열산
            int[][] tempKey = new int[m][m];
            // 시계방향으로 돌린다.
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    tempKey[k][m - j - 1] = key[j][k];
                }
            }
            key = tempKey;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(solution(key, lock));
    }
}

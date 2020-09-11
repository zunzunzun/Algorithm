package Programmers;

import java.util.*;

// 완전 탐색으로 풀어야 하는 문제
// 원형으로 되어 있는 문제를 직선으로 만들고
// 친구의 수가 충분히 적으므로 모든 순열의 경우의 수를 구한다.
// 이를 직선으로 만든 weak에 대입한다.
public class Programmers_외벽점검_L3 {
    // 모든 친구들을 뽑는 경우의 수를 저장해 둘 list
    static LinkedList<int[]> list = new LinkedList<>();
    // 순열을 구하기 위해 필요한 임시 배열
    static int[] arr;
    // 순열을 구하기 위한 방문 boolean 배열
    static boolean[] isSelected;
    // 순열을 구하기 위해 전역변수가 필요
    static int[] distance;

    public static int solution(int n, int[] weak, int[] dist) {
        int length = weak.length;

        // 원형을 직선으로 만들기 위한 변수
        // 각 원소에 인덱스 값에 n을 더한 수를 붙이면 원형을 직선으로 만들 수 있다.
        int[] expand = new int[length * 2];
        for (int i = 0; i < length; i++) {
            expand[i] = weak[i];
        }
        for (int i = length; i < length * 2; i++) {
            expand[i] = weak[i % length] + n;
        }

        // 순열을 위한 코드
        distance = dist;
        // 순열은 1개에서 dist의 길이만큼 뽑는 모든 경우의 수를 구해야 한다.
        for (int i = 1; i <= dist.length; i++) {
            arr = new int[i];
            isSelected = new boolean[dist.length];
            permutation(0, i);
        }

        // 구한 것들을 통해 답을 구하는 과정
        // 모든 각 경우의 수를 비교한다.
        for (int[] item : list) {
            for (int i = 0; i < length; i++) {
                // weak를 몇 개 방문했는지 체크할 변수
                int count = 0;

                // 배열의 범위를 벗어나지 않도록 조건문을 잘 고려해야 한다.
                for (int j = 0; j < item.length && count < length; j++) {
                    // 첫 위험지역을 방문. 첫 방문이다 보니 count는 무조건 하나 올라간다.
                    // 또한 temp에 item의 원소 만큼 갈 수 있는 거리를 저장한다.
                    int temp = expand[i + count++] + item[j];
                    // temp 값이 weak 지역들보다 멀리 갈 수 있다면 count를 늘린다.
                    while (count < length && temp >= expand[i + count]) count++;
                }

                // 경우의 수는 친구의 수가 적은 것이 앞으로 정렬 되었기 때문에
                // 모든 취약 지점을 방문했다면 바로 리턴하면 된다.
                if (count == length) return item.length;
            }
        }

        // 만족하는 경우가 없으므로 -1 리턴
        return -1;
    }

    // 순서를 고려해야 하니 순열을 구해야 한다.
    // 순열을 구하는 부분. count는 뽑아야할 개수이다.
    static void permutation(int index, int count) {
        if (index == count) {
            int[] temp = arr.clone();
            list.add(temp);
        } else {
            for (int i = 0; i < distance.length; i++) {
                if (isSelected[i]) continue;

                arr[index] = distance[i];
                isSelected[i] = true;
                permutation(index + 1, count);
                isSelected[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        System.out.println(solution(12, weak, dist));
    }
}

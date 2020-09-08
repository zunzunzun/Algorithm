package Programmers;

import java.util.*;

public class Programmers_보석쇼핑_L3 {
    public static int[] solution(String[] gems) {
        int[] answer = {0, gems.length - 1};
        // 이름에 따른 빈도수를 담아둘 map
        Map<String, Integer> map = new HashMap<>();

        // 우선 총 보석의 종류를 구한다.
        for (String gem: gems) {
            if (!map.containsKey(gem)) {
                map.put(gem, 0);
            }
        }

        // 투포인터
        int l, r;
        int size = map.size();
        // 해당하는 구간의 보석의 종류를 담아둘 set
        Set<String> set = new HashSet<>();

        for (l = r = 0; r < gems.length; r++) {
            set.add(gems[r]);
            map.put(gems[r], map.get(gems[r]) + 1);

            // 해당하는 구간의 보석의 종류가 총 보석의 종류와 같다면 구간을 검사해야 한다.
            if (set.size() == size) {
                for (; l <= r; l++) {
                    map.put(gems[l], map.get(gems[l]) - 1);

                    // 0이라면 더이상 구간을 줄일 수 없으므로 총 구간과 비교하여 짧은지 비교한다.
                    if (map.get(gems[l]) == 0) {
                        set.remove(gems[l]);

                        if ((r - l) < (answer[1] - answer[0])) {
                            answer[0] = l; answer[1] = r;
                        }
                        l++;
                        break;
                    }
                }
            }
        }

        // 인덱스가 1번부터 시작하므로 1을 더한다.
        answer[0]++; answer[1]++;
        return answer;
    }


    public static void main(String[] args) {
//        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        for (int item : solution(gems)) {
            System.out.println(item);
        }
    }
}

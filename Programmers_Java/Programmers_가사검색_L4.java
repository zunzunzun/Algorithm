package Programmers;

public class Programmers_가사검색_L4 {
    static class Trie {
        // 하위 문자가 얼마나 있는지 체크할 변수
        int count;
        Trie[] childList;

        Trie() {
            this.childList = new Trie[26];
            this.count = 0;
        }

        void insert(char[] word) {
            Trie current = this;
            for (char ch : word) {
                current.count++;

                if (current.childList[ch - 'a'] != null) {
                    current = current.childList[ch - 'a'];
                } else {
                    current.childList[ch - 'a'] = new Trie();
                    current = current.childList[ch - 'a'];
                }
            }
        }

        int search(char[] query) {
            Trie current = this;
            for (char ch : query) {
                if (ch == '?') {
                    return current.count;
                }

                if (current.childList[ch - 'a'] != null) {
                    current = current.childList[ch - 'a'];
                } else {
                    return 0;
                }
            }
            return current.count;
        }
    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        Trie[] tries = new Trie[10001];
        Trie[] rtries = new Trie[10001];

        for (String word : words) {
            int length = word.length();
            if (tries[length] == null) {
                tries[length] = new Trie();
                rtries[length] = new Trie();
            }

            tries[length].insert(word.toCharArray());
            rtries[length].insert(new StringBuilder(word).reverse().toString().toCharArray());
        }

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int length = query.length();

            if (query.indexOf('?') == 0) {
                if (rtries[length] != null) {
                    query = new StringBuilder(query).reverse().toString();
                    answer[i] = rtries[length].search(query.toCharArray());
                }
            } else {
                if (tries[length] != null) answer[i] = tries[length].search(query.toCharArray());
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] result = solution(words, queries);
    }
}
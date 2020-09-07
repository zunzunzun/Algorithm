import Foundation

let input = readLine()!.split(separator: " ").map{ Int(String($0))! }
let n = input[0]
let k = input[1]

// 최대값을 100000이므로 100001으로 초기화
var dp = [Int](repeating: 100001, count: k + 1)
// 0원을 만드는 경우의 수는 0
dp[0] = 0

var arr = [Int](repeating: 0, count: n)
for i in 0..<n {
    arr[i] = Int(readLine()!)!
}

for item in arr {
    for j in item...k where item <= k {
        // DP를 활용
        dp[j] = min(dp[j], dp[j - item] + 1)
    }
}

print(dp[k] == 100001 ? -1 : dp[k])


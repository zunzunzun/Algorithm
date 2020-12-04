import Foundation

let input = readLine()!.split(separator: " ").map { Int(String($0))! }

let A = input[0]
let B = input[1]
let C = input[2]

func solution(_ a: Int, _ b: Int) -> Int {
    if b == 0 {
        return 1
    } else {
        let temp = solution(a, b / 2)

        if b % 2 == 0 {
            return temp % C * temp % C
        } else {
            return a % C * temp % C * temp % C
        }
    }
}

print(solution(A, B))

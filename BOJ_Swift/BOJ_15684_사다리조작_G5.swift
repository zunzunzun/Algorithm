import Foundation

let input = readLine()!.split(separator: " ").map{ Int(String($0))! }

let N = input[0], M = input[1], H = input[2]

var arr = [[Bool]](repeating: [Bool](repeating: false, count: N + 1), count: H + 1)

for _ in 0..<M {
    let temp = readLine()!.split(separator: " ").map { Int(String($0))! }
    arr[temp[0]][temp[1]] = true
}

var value = 0

for i in 0...3 {
    value = i
    dfs(index: 0, count: 0)
}

print(-1)

func dfs(index: Int, count: Int) {
    if value == count {
        if isAnswer() {
            print(value)
            exit(0)
        }
    } else {
        for i in index..<(H * (N - 1)) {
            let row = i / (N - 1) + 1
            let col = i % (N - 1) + 1
            
            if !arr[row][col], !arr[row][col - 1], !arr[row][col + 1] {
                arr[row][col] = true
                dfs(index: i + 1, count: count + 1)
                arr[row][col] = false
            }
        }
    }
}

func isAnswer() -> Bool {
    for i in 1...N {
        var pos = i
        
        for j in 1...H {
            if arr[j][pos] {
                pos += 1
            } else if arr[j][pos - 1] {
                pos -= 1
            }
        }
        
        if pos != i {
            return false
        }
    }
    
    return true
}

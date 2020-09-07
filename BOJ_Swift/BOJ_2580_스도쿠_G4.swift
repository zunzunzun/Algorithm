import Foundation

var sudoku = [[Int]](repeating: [Int](repeating: 0, count: 9), count: 9)

// 각각 행, 열, 사각형의 숫자가 있는지 확인할 배열
// rows[0][1]이 true면 첫 번째 행에는 1이라는 숫자가 있다는 것을 의미한다.
// 숫자를 담기위해 길이를 10으로 한다.
var rows = [[Bool]](repeating: [Bool](repeating: false, count: 10), count: 9)
var cols = [[Bool]](repeating: [Bool](repeating: false, count: 10), count: 9)
var squares = [[Bool]](repeating: [Bool](repeating: false, count: 10), count: 9)

for i in 0..<9 {
    let input = readLine()!.split(separator: " ").map { Int(String($0))! }
    for j in 0..<9 {
        sudoku[i][j] = input[j]
        
        if input[j] != 0 {
            rows[i][input[j]] = true
            cols[j][input[j]] = true
            
            // 가로, 세로 길이 3인 정사각형 정보를 담을 배열.
            // 아래와 같은 작업으로 index를 구할 수 있다.
            squares[(i / 3) * 3 + (j / 3)][input[j]] = true
        }
    }
}

dfs(0)

// 여러 숫자를 집어 넣어야 할 경우가 있을 수 있으니 DFS를 통한 백트래킹으로 구현
func dfs(_ count: Int) {
    if count == 81 {
        for row in sudoku {
            for col in row {
                print(col, terminator: " ")
            }
            print()
        }
        // 이미 답이 나왔으니 종료.
        exit(0)
    } else {
        let row = count / 9
        let col = count % 9
        
        if sudoku[row][col] == 0 {
            // 모든 숫자를 대입해 본다.
            for i in 1...9 {
                if !rows[row][i], !cols[col][i], !squares[(row / 3) * 3 + (col / 3)][i] {
                    rows[row][i] = true
                    cols[col][i] = true
                    squares[(row / 3) * 3 + (col / 3)][i] = true
                    sudoku[row][col] = i
                    dfs(count + 1)
                    // 해당 숫자가 답이 아니므로 다시 원래대로 복구한다.
                    rows[row][i] = false
                    cols[col][i] = false
                    squares[(row / 3) * 3 + (col / 3)][i] = false
                    sudoku[row][col] = 0
                }
            }
        } else { // 0이 아닌 경우는 바로 들어간다.
            dfs(count + 1)
        }
    }
}

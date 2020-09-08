//
//  SwiftAlgorithm
//
//  Created by zun on 09/02/2020.
//  Copyright © 2020 zun. All rights reserved.
//

// 순위와 실력을 저장할 구조체
struct Player {
  var index: Int
  var power: Int
}

let n = readLine()!.split(separator: " ").compactMap{ Int(String($0)) }[0]

// 정보를 저장할 배열
var arr = [Player](repeating: Player(index: 0, power: 0), count: 500001)
// merge sort를 구현하기 위해 사용될 임시 배열
var temp = [Player](repeating: Player(index: 0, power: 0), count: 500001)
// 답을 출력할 배열. index 별로 현재 달리고 있는 선수의 순위의 최선의 등수를 저장
var ans = [Int](repeating: 0, count: 500001)

func mergeSort(left: Int, right: Int) {
  if left == right {
    return
  } else {
    let mid = (left + right) / 2
    mergeSort(left: left, right: mid)
    mergeSort(left: mid + 1, right: right)
    
    // merge
    var lIndex = left // 병합시킬 왼쪽 배열의 인덱스
    var rIndex = mid + 1 // 병합시킬 오른쪽 배열의 인덱스
    
    for i in left...right {
      if lIndex > mid { // 왼쪽 배열을 다 병합 했을 경우
        temp[i] = arr[rIndex]
        rIndex += 1
      } else if rIndex > right { // 오른쪽 배열을 다 병합 했을 경우
        temp[i] = arr[lIndex]
        lIndex += 1
      } else if arr[lIndex].power > arr[rIndex].power {
        // 왼쪽 배열의 원소가 오른쪽 배열의 원소보다 값이 클 때
        // 즉 앞서 있는 사람의 실력이 더 좋을 땐 순위의 변동이 없다.
        temp[i] = arr[lIndex]
        lIndex += 1
      } else { // 뒤에 있는 사람이 실력이 좋을 때는 순위의 변동이 있으므로 갱신해줘야 한다.
        temp[i] = arr[rIndex]
        ans[arr[rIndex].index] -= (mid - lIndex + 1)
        rIndex += 1
      } // end of if
    } // end of for
    
    // 임시 배열에 저장해두었던 값 갱신
    for i in left...right {
      arr[i] = temp[i]
    }
  }// end of if
} // end of func

// 입력 부분
for i in 1...n {
  arr[i].index = i
  ans[i] = i
  arr[i].power = readLine()!.split(separator: " ").compactMap{ Int(String($0)) }[0]
}

mergeSort(left: 1, right: n)

for i in 1...n {
  print(ans[i])
}



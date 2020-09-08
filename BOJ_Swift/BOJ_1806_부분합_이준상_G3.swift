//
//  SwiftAlgorithm
//
//  Created by zun on 09/02/2020.
//  Copyright © 2020 zun. All rights reserved.
//

let input = readLine()!.split(separator: " ").compactMap{ Int(String($0)) }
let n = input[0]
let s = input[1]
let arr = readLine()!.split(separator: " ").compactMap{ Int(String($0)) }

var ans = Int.max
var endIndex = 0
var sum = 0

// 투포인터 알고리즘
for i in 0..<n {
  while endIndex < arr.count, sum < s {
    sum += arr[endIndex]
    endIndex += 1
  }
  
  if sum >= s {
    let count = endIndex - i
    if ans > count {
      ans = count
    }
  }
  
  sum -= arr[i]
}

if ans == Int.max {
  print(0)
} else {
  print(ans)
}

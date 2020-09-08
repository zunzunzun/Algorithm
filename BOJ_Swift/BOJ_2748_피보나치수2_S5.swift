//
//  BJ_2748.swift
//  SwiftAlgorithm
//
//  Created by zun on 09/02/2020.
//  Copyright © 2020 zun. All rights reserved.
//

let input = readLine()!.split(separator: " ").compactMap{ Int(String($0)) }
let n = input[0]

var arr = [Int](repeating: 0, count: 91)

arr[1] = 1

if n > 1 {
  for i in 2...n {
    arr[i] = arr[i - 1] + arr[i - 2]
  }
}

print(arr[n])



//
//  main.swift
//  SwiftAlgorithm
//
//  Created by zun on 03/02/2020.
//  Copyright © 2020 zun. All rights reserved.
//

import Foundation

let input1 = readLine()!.components(separatedBy: " ").compactMap{ Int($0) }
let n = input1[0]
let m = input1[1]
let input2 = readLine()!.components(separatedBy: " ").compactMap{ Int($0) }

var end = 0
var sum = 0
var count = 0

for item in input2 {
  while sum < m && end < input2.count {
    sum += input2[end]
    end += 1
  }
  
  if sum == m {
    count += 1
  }
  
  sum -= item
}

print(count)


//
//  main.swift
//  BJ2805
//
//  Created by zun on 03/02/2020.
//  Copyright © 2020 zun. All rights reserved.
//

let input = readLine()!.split(separator: " ").compactMap{ Int(String($0))}
let n = input[0]
let m = input[1]
let trees = readLine()!.split(separator: " ").compactMap{ Int(String($0)) }

var maxTree = 0

for tree in trees where tree > maxTree {
  maxTree = tree
}

func getTree(_ height: Int) -> Int {
  var sum = 0
  for tree in trees where tree > height {
    sum += tree - height
  }
  return sum
}

var start = 0
var mid = 0
var ans = 0
var temp = 0
var end = maxTree

while start < end {
  mid = (start + end) / 2
  temp = getTree(mid)
  
  if(temp < m) {
    end = mid
  } else {
    ans = mid
    start = mid + 1
  }
}

print(ans)

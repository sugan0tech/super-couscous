package main

import (
	"fmt"
)

func main() {
  arrays := []int{2, 3, 4, 5, 6}
  fmt.Println(transformNumbers(&arrays, double))
  fmt.Println(transformNumbers(&arrays, triple))
}

func transformNumbers(numbers *[]int, function func(int) int) []int{
  resultArray := []int{}
  for _, val := range *numbers {
    resultArray = append(resultArray, function(val))
  }
  return resultArray
}

func double(val int) int {
  return val*2
}

func triple(val int) int {
  return val*3
}

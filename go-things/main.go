package main

import (
	"fmt"
)

func main(){
  age := 56
  getAdultYears(&age)
  fmt.Println(age)
}
func getAdultYears(age *int) int{
  return *age - 18
}


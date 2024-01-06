package main

import (
	"fmt"
	"time"
  "example/hello/user"
)


func main() {
	age := 56
	getAdultYears(&age)
	fmt.Println(age)
	var appUser *user.User
  var err error
	appUser, err = user.NewUser("sugan", "kpms", 23, time.Now())
  if err != nil {
    fmt.Println(err)
    return
  }
  appUser.PrintUser()

}
func getAdultYears(age *int) int {
	return *age - 18
}

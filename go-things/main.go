package main

import (
	"errors"
	"fmt"
	"time"
)

type user struct {
	firstName  string
	secondName string
	age        int
	createdAt  time.Time
}

func newUser(firstName string, secondName string, age int, createdAt time.Time) (*user, error) {
	if firstName == "" || secondName == "" || age == 0 {
		return nil, errors.New("The fields are missing")
	}

	return &user{
		firstName:  firstName,
		secondName: secondName,
		age:        age,
		createdAt:  createdAt,
	}, nil
}

func (u user) printUser() {
	fmt.Println(u.firstName, u.secondName, u.age)
}

func main() {
	age := 56
	getAdultYears(&age)
	fmt.Println(age)
	var appUser *user
  var err error
	appUser, err = newUser("sugan", "", 23, time.Now())
  if err != nil {
    fmt.Println(err)
    return
  }

	appUser.printUser()
}
func getAdultYears(age *int) int {
	return *age - 18
}

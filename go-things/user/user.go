package user

import (
	"errors"
	"fmt"
	"time"
)

type User struct {
	firstName  string
	secondName string
	age        int
	createdAt  time.Time
}

func NewUser(firstName string, secondName string, age int, createdAt time.Time) (*User, error) {
	if firstName == "" || secondName == "" || age == 0 {
		return nil, errors.New("The fields are missing")
	}

	return &User{
		firstName:  firstName,
		secondName: secondName,
		age:        age,
		createdAt:  createdAt,
	}, nil
}

func (u User) PrintUser() {
	fmt.Println(u.firstName, u.secondName, u.age)
}

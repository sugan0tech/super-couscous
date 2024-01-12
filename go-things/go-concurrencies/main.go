package main

import (
	"fmt"
	"time"
)

func main(){
  dones := make([]chan bool, 4)

  dones[0] = make(chan bool)
  go greet("He:hi", dones[0])
  dones[1] = make(chan bool)
  go greet("She:hi", dones[1])
  dones[2] = make(chan bool)
  go slowGreet("He:how are you", dones[2])
  dones[3] = make(chan bool)
  go greet("She: I have a boyfriend", dones[3])

  for done := range dones {
    fmt.Print(done)
  }

}

func greet(str string, doneChan chan bool){
  fmt.Println(str)
  doneChan <- true
}

func slowGreet(str string, doneChan chan bool){
  time.Sleep(3 * time.Second)
  fmt.Println(str)
  doneChan <- true
}

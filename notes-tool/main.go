package main

import (
  "bufio"
  "fmt"
  "notes-app/types"
  "os"
  "strings"
)

func main() {
  fmt.Println("welcome to notes app")

  for{
    note , err:= getNotes()
    fmt.Println(err)
    if(err != nil){
      fmt.Print(err)
      return 
    }
    err = note.Save()
    if( err != nil ){
      fmt.Println(err)
      return 
    }
    note.Display()
  }


}

func getNotes() (types.Notes , error) {
  notes, err := types.New(getUserInput("Enter title: "),
    getUserInput("Enter Body: "),
    getUserInput("Enter author name: "))
  return notes, err
}

func getUserInput(prompt string) string {
  fmt.Print(prompt)
  reader := bufio.NewReader(os.Stdin)
  value, err := reader.ReadString('\n')
  if err != nil {
    return  ""
  }
  value = strings.TrimSuffix(value, "\n")
  value = strings.TrimSuffix(value, "\r")
  return value
}

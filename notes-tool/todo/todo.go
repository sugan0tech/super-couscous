package todo

import (
  "encoding/json"
  "errors"
  "fmt"
  "os"
)

var fileName = "../store.json"

type Todos struct {
  Text string `json:"text"`
}

func New(content string) (Todos, error){
  if content == "" {
    return Todos{}, errors.New("todo is missing")
  }
  return Todos{
    Text: content,
  }, nil
}

func (n Todos) Save() (error){
  fmt.Println(n)
  fileName := "todo.json"
  jsonData, err := json.Marshal(n)
  if err != nil {
    return err
  }
  os.WriteFile(fileName, jsonData, 0644)
  return nil
}

func (todo Todos) Display() {
  fmt.Println(todo)
}

func FetchTodos(title string) (n Todos, e error ){
  return 
}

package types

import (
  "encoding/json"
  "errors"
  "fmt"
  "os"
  "strings"
  "time"
)

var fileName = "../store.json"

type Notes struct {
  Title string `json:"title"`
  Body string `json:"body"`
  Author string `json:"author"`
  CreatedAt time.Time `json:"created_at"`
}

func New(title string, body string, author string) (Notes, error){
  if title == "" {
    return Notes{}, errors.New("title is missing")
  }else if body == "" {
    return Notes{}, errors.New("body is missing")
  }

  return Notes{
    Title: title,
    Body: body,
    Author: author,
    CreatedAt: time.Now(),
  }, nil
}

func (n Notes) Save() (error){
  fmt.Println(n)
  fileName := strings.ReplaceAll(n.Title, " ", "_")
  fileName = strings.ToLower(fileName) + ".json"
  jsonData, err := json.Marshal(n)
  if err != nil {
    return err
  }
  os.WriteFile(fileName, jsonData, 0644)
  return nil
}

func (n Notes) Display() {
  fmt.Println("The notes have the following content")
  fmt.Println(n)
}

func FetchNotes(title string) (n Notes, e error ){
  return 
}

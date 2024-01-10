package main

import (
	"bufio"
	"fmt"
	"notes-app/todo"
	"notes-app/types"
	"os"
	"strings"
)

type saver interface {
	Save() error
	Display()
}

func main() {
	fmt.Println("welcome to notes app")

	for {
		note, err := getNotes()
		if err != nil {
			fmt.Print(err)
			return
		}
		outputData(note)
		if err != nil {
			fmt.Print(err)
			return
		}

		todo, err := getTodos()
		if err != nil {
			fmt.Print(err)
			return
		}
		outputData(todo)
		if err != nil {
			fmt.Println(err)
			return
		}
	}
	sum := addGeneric(1, 3)
	fmt.Println(sum)
}

func addGeneric[T int | float64 | string](a, b T) T {
	return a + b
}

func outputData(data saver) error {
	data.Display()
	return saveData(data)
}

func saveData(data saver) error {
	return data.Save()
}

func getTodos() (todo.Todos, error) {
	todo, err := todo.New(getUserInput("Enter content: "))
	return todo, err
}

func getNotes() (types.Notes, error) {
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
		return ""
	}
	value = strings.TrimSuffix(value, "\n")
	value = strings.TrimSuffix(value, "\r")
	return value
}

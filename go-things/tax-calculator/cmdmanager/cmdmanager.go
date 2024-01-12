package cmdmanager

import "fmt"

type CmdManager struct {
}

func New() CmdManager {
	return CmdManager{}
}

func (cm CmdManager) ReadLines() ([]string, error) {
	fmt.Println("Please enter the prices")
	var prices []string
	for {
		var price string
		fmt.Println("Price: ")
		fmt.Scan(&price)
		if price == "0" {
			break
		}
		prices = append(prices, price)
	}
	return prices, nil
}

func (cm CmdManager) WriteResult(data any) error {
	_, err := fmt.Println(data)
	return err
}

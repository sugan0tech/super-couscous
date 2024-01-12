package conversion

import (
  "errors"
  "strconv"
)

func StringsToFloat(strings []string) ([]float64, error) {
  prices := make([]float64, len(strings))
  var err error

  for lineIndex, stringVal := range strings {
    prices[lineIndex], err = strconv.ParseFloat(stringVal, 64)
    if err != nil {
      return nil, errors.New("failed to convert string to float.")
    }
  }
  return prices, nil
}

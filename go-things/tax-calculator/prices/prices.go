package prices

import (
	"fmt"
	iomanager "tax-calculator/IOmanager"
	"tax-calculator/conversion"
)

type TaxIncludedPriceJob struct {
	IOManager         iomanager.IOmanager `json:"-"`
	TaxRate           float64             `json:"tax_rate"`
	InputPrices       []float64           `json:"input_prices"`
	TaxIncludedPrices map[string]string   `json:"tax_included_prices"`
}

func NewTaxIncludedPriceJob(io iomanager.IOmanager, taxRate float64) *TaxIncludedPriceJob {
	return &TaxIncludedPriceJob{
		IOManager:   io,
		InputPrices: []float64{10, 20, 30},
		TaxRate:     taxRate,
	}
}

func (job *TaxIncludedPriceJob) Process() error {
  err := job.LoadPrices()
  if err != nil {
    return err
  }
	result := make(map[string]string)
	for _, price := range job.InputPrices {
		result[fmt.Sprintf("%.2f", price)] = fmt.Sprintf("%.2f", price*(1+job.TaxRate))
	}
	job.TaxIncludedPrices = result

	return job.IOManager.WriteResult(job)
}

func (job *TaxIncludedPriceJob) LoadPrices() error {

	lines, err := job.IOManager.ReadLines()

	if err != nil {
		return err
	}

	// converter from str to float64
	prices, err := conversion.StringsToFloat(lines)
	if err != nil {
		return err
	}

	job.InputPrices = prices
  return nil
}

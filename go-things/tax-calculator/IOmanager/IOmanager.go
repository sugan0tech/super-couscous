package iomanager

type IOmanager interface {
	ReadLines() ([]string, error)
	WriteResult(data any) error
}

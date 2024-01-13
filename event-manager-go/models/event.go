package models

import (
	"event-manager-go/db"
	"time"
)

type Event struct {
	ID          int64       
	Name        string    `binding:"required"`
  Description string   `binding:"required"`
	Location    string    `binding:"required"`
	DateTime    time.Time `binding:"required"`
	UserId      int      
}


func (event Event) Save() error {
  query := `
  INSERT INTO events(name, description, location, dateTime, user_id) 
  VALUES(?, ?, ?, ?, ?)`
  stmt, err := db.DB.Prepare(query)
  if err != nil {
    return err
  }
  defer stmt.Close()
  result, err := stmt.Exec(event.Name, event.Description, event.Location, event.DateTime, event.UserId)

  if err != nil {
    return err
  }

  id, err := result.LastInsertId()
  event.ID = id
  return nil
}

func GetAllEvents() ([]Event, error) {
  query := `SELECT * FROM events`
  rows, err := db.DB.Query(query)
  if err != nil {
    return nil, err
  }
  defer rows.Close()

  var events []Event

  for rows.Next() {
    var event Event
    err := rows.Scan(&event.ID, &event.Name, &event.Description, &event.Location, &event.DateTime, &event.UserId)
    if err != nil {
      return nil, err
    }
    events = append(events, event)
  }
  return events, nil
}
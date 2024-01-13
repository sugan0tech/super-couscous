package main

import (
	"event-manager-go/db"
	"event-manager-go/models"
	"net/http"

	"github.com/gin-gonic/gin"
)

func main() {
	db.InitDB()
	server := gin.Default()
	server.GET("/events", getEvents)
	server.POST("/events", createEvent)
	server.Run(":8080")
}

func getEvents(context *gin.Context) {
	events, err := models.GetAllEvents()
	if err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": "could not fetch the events", "error": err})
	}
	context.JSON(http.StatusOK, events)
}

func createEvent(context *gin.Context) {
	var event models.Event
	err := context.ShouldBindJSON(&event)
	if err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": "Could not parse the incoming request", "error": err})
		return
	}
	err = event.Save()
	if err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": "Couldnot save the event", "error": err})
		return
	}

	context.JSON(http.StatusOK, gin.H{"message": "event created", "event": event})
}

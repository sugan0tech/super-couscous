package routes

import (
	"event-manager-go/models"
	"github.com/gin-gonic/gin"
	"net/http"
	"strconv"
)

func getEvents(context *gin.Context) {
	events, err := models.GetAllEvents()
	if err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": "could not fetch the events", "error": err})
	}
	context.JSON(http.StatusOK, events)
}

func getEvent(context *gin.Context) {
	eventId, err := strconv.ParseInt(context.Param("id"), 10, 64)
	if err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": "Bad event id", "error": err})
		return
	}
	event, err := models.GetEvent(eventId)
	if err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": "Could not fetch the event", "error": err})
		return
	}

	context.JSON(http.StatusOK, event)

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

func updateEvent(context *gin.Context) {
	eventId, err := strconv.ParseInt(context.Param("id"), 10, 64)
	if err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": "Bad event id", "error": err})
		return
	}

	_, err = models.GetEvent(eventId)
	if err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": "Unable to fetch the event", "error": err})
	}

	var updatedEvent models.Event
	err = context.ShouldBindJSON(&updatedEvent)
	if err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": "Could not parse the incoming request", "error": err})
		return
	}

  updatedEvent.ID = eventId
  err = updatedEvent.Update()
  if err != nil {
    context.JSON(http.StatusInternalServerError, gin.H{"message":"event updation failed", "error": err})
    return 
  }

	context.JSON(http.StatusOK, gin.H{"message": "event created", "event": updatedEvent})
}

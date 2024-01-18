package routes

import (
	"event-manager-go/models"
	"net/http"

	"github.com/gin-gonic/gin"
)

func singup(context *gin.Context) {
	var user models.User
	err := context.ShouldBindJSON(&user)
	if err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"message": "Could not parse the incoming request", "error": err})
		return
	}
	err = user.Save()
	if err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"message": "Couldnot save the user", "error": err})
		return
	}

	context.JSON(http.StatusOK, gin.H{"message": "user created", "user": user})
}

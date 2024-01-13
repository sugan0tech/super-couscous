package main

import (
	"event-manager-go/db"
	"event-manager-go/routes"
	"github.com/gin-gonic/gin"
)

func main() {
	db.InitDB()
	server := gin.Default()

  routes.RegisterRoutes(server)
	server.Run(":8080")
}


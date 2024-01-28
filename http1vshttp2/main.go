package main

import (
	"fmt"
	"net/http"
)

func main() {
	// Create a new HTTP server
  fs := http.FileServer(http.Dir("./web/"))
	http.Handle("/", fs)

	// Start the HTTP server with HTTP/2 support
	server := &http.Server{
		Addr: ":8080",
	}
	fmt.Println("Server listening on port 8080 with HTTP/2 support")
	go func() {
		// err := server.ListenAndServeTLS("server.crt", "server.key")
		err := server.ListenAndServe()
		if err != nil && err != http.ErrServerClosed {
			fmt.Printf("Error starting server: %s\n", err)
		}
	}()

	// Wait for user input to switch to HTTP/1.1
	fmt.Println("Press ENTER to switch to HTTP/1.1")
	fmt.Scanln()

	// Close the HTTP/2 server
	err := server.Close()
	if err != nil {
		fmt.Printf("Error closing HTTP/2 server: %s\n", err)
	}

	// Start a new HTTP server with HTTP/1.1 support
	http.HandleFunc("/", handler)
	httpServer := &http.Server{
		Addr: ":8080",
	}
	fmt.Println("Server listening on port 8080 with HTTP/1.1 support")
	err = httpServer.ListenAndServe()
	if err != nil && err != http.ErrServerClosed {
		fmt.Printf("Error starting HTTP/1.1 server: %s\n", err)
	}
}

func handler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Hello, HTTP/%d.%d", r.ProtoMajor, r.ProtoMinor)
}


package main

import (
	"log"
	"net/http"
)

func main() {
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		// Push files when requested path is "/"
		log.Println(r.URL.Path)
		if r.URL.Path == "/" {
			pushFiles(w, r)
			return
		}

		// Serve other files as usual
		http.ServeFile(w, r, "web"+r.URL.Path)
	})

	log.Println("magic")
	log.Fatal(http.ListenAndServeTLS(":8080", "server.crt", "server.key", nil))
}

func pushFiles(w http.ResponseWriter, r *http.Request) {
	p, ok := w.(http.Pusher)
	if !ok {
		log.Println("http.Pusher is not supported")
		return
	}

	// Files to push
	files := []string{
		"/web/file1.html",
		"/web/file2.css",
		"/web/file3.js",
	}

	for _, file := range files {
		if err := p.Push(file, nil); err != nil {
			log.Printf("Failed to push %s: %v\n", file, err)
		}
    log.Println("file " ,file)
	}

	// Serve the main HTML file
	http.ServeFile(w, r, "web/index.html")
}


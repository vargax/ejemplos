package main

import (
	"github.com/vargax/ejemplos/go/pgs/controllers"
	"net/http"
)

func main() {
	controllers.RegisterControllers()
	http.ListenAndServe(":3000", nil)
}

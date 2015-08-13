// ------------------------------------------------------
// Librerias
// ------------------------------------------------------
var app = require('express')();			// Servidor WEB
var http = require('http').Server(app);  
var express = require('express');

var io = require('socket.io')(http);    // Libreria para manejar los sockets
var geo = require('geotabuladb');		// Librería para manejo de base de datos 

// ------------------------------------------------------
// Variables
// ------------------------------------------------------
var clients = {}; // Diccionario para guardar los clientes

// ------------------------------------------------------
// Funciones
// ------------------------------------------------------
function getMap(socketId, msg) {
	
	parameters = {
		tableName : 'redprimariawgs84',
	    geometry : 'geom', 					// Columna que tiene la geometría
	    properties : 'all'					// Las demás columnas que quiero recuperar, para específicas se pasan como Array
	    //limit: 10							// Para limitar el número de registros recuperados
	};
	
	geo.geoQuery({
		tableName : 'redprimariawgs84',
	    geometry : 'geom', 					// Columna que tiene la geometría
	    properties : 'all'					// Las demás columnas que quiero recuperar, para específicas se pasan como Array
	    //limit: 10							// Para limitar el número de registros recuperados
	},function(json) {
		console.log('pre_emit '+json);
		clients[socketId].emit('draw_map', json); // Envío al cliente el evento
		console.log('post_emit');
	});	
}

// ------------------------------------------------------
// Inicialización
// ------------------------------------------------------
// Inicializando GeoTabula  // ToDo --> Ajustar para coincidir con la base de datos geotabula
geo.setCredentials({
	type: 'postgis',
	host: 'localhost',
	user: 'tomsa',
	password: 'tomsa',
	database: 'bogotaDB'
})

// Inicializando el servidor...
app.use(express.static(__dirname + '/public'));  // Definiendo el servidor público para el servidor WEB

// Al recibir una solicitud para '/' contesto con '/index.html'
app.get('/', function(req, res){
  res.sendFile(__dirname + '/index.html');
});

// El servidor escucha en el puerto 8080
http.listen(8080, function(){
  console.log('listening on port:8080'); // Println
});

// ------------------------------------------------------
// Manejo de eventos
// ------------------------------------------------------
// Si libreria de sockets recibe una conexión...
io.on('connection', function(socket){
    // Almacenando referencia al socket en el diccionario de clientes...
    if(!clients[socket.id]){
    	console.log('new socket');
        clients[socket.id] = socket;
    }
    
    // Si recibe solicitud de desconexión, eliminar la referencia al socket del diccionario (la libreria se encarga de cerrarlo)
    socket.on('disconnect', function(){
    	console.log('end socket');
        delete clients[socket.id];
    });
    
    // Eventos definidos por el programador...
    socket.on('get_map', function(msg){ // **colocar constantes en JS común!!
        console.log('get_map');
        getMap(socket.id,msg) // respuesta del socket
    });
});
// Eventos / comunicación con el servidor
var socket = io();

socket.on('draw_map', function(msg) {
	console.log('draw_map: '+msg)
    var layer = L.geoJson(msg,{ // La L es leaflet --> MOVERLO A MAP.JS
    	// Aquí se van a colocar los eventos que debe manejar el objeto al ser desplegado
    	style:{color : '#0000FF', weight : 4},
    	onEachFeature : function(feature, layer) {  // El evento para cada uno de los elementos en el diccionario --> Feature hace referencia al objeto espacial
    		layer.on({ // Cuando la capa este activa / se este visualizando --> Hace parte de leaflet               
                mouseover : function() { // Cambia el color del objeto que está bajo el mouse
                    info.update(feature.properties);
                    layer.setStyle({ // CSS debería estar aparte...
                        color : '#FFFF00',
                        weight : 4
                    });
                },
                mouseout : function() {                    
                    info.update();
                    layer.setStyle({ // CSS aparte...
                        color : '#0000FF',
                        weight : 4
                    });
                },
                click : function(e){
                    //console.log(feature.properties.id);
                    map.fitBounds(e.target.getBounds()); // Centra el objeto en el mapa
                }
            });
    	}
    });
    layer.addTo(map);
    layer.bringToBack();
});

socket.emit('get_map', '');
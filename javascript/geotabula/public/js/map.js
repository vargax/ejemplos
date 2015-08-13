// Interacción con el mapa

var map;								// El mapa a visualizar
var layersControl = L.control.layers(); // Para manejar las capas del mapa

function createMap() {
    map = L.map('map').setView([4.66198, -74.09866], 11);  				// Posición inical del mapa (lat, long, zoom)    
    map.addLayer(new L.TileLayer.provider('Esri.WorldGrayCanvas'));     // El mapa base que se va a utilizar (debe importarse la librería correspondiente en index.html)   
    map._layersMaxZoom=16;												// Definie el máximo zoom del mapa
	map._layersMinZoom=10;

    L.control.scale({				// Maneja la escala
        position : 'bottomleft',	// .. donde aparece
        imperial : false			// .. sistema métrico
    }).addTo(map);
    
    info.addTo(map);
}

// Tooltip para desplegar información 
// -- Código para dibujar la información!
var info = L.control();

info.onAdd = function(map) { // --> Esta función crea el DIV para desplegar la información
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};

info.update = function(props) {
    var infoString = '<h4> Data </h4>';
    for (item in props){
        if (isNaN(props[item])){                
            infoString += '<b>'+ item +'</b> ' + props[item] + '</b> <br />';
        } else {
        	infoString += '<b>'+ item +'</b> ' + props[item] + '</b> <br />';                
        }
    }
    this._div.innerHTML = infoString; // 
};
// -- Código para capturar el evento

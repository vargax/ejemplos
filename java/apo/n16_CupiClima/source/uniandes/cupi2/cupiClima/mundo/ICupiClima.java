package uniandes.cupi2.cupiClima.mundo;

import java.io.Serializable;

import com.aetrion.flickr.FlickrException;

import estructurasDatos.IListaEncadenada;

public interface ICupiClima extends Serializable {
	public String[][] buscarCiudad(String criterioBusqueda);
	public String[] darCondiciones(String woeid);
	public String[][] darFotos(String nombreCiudad);
	public String[][] darFotosWoeid(String woeid) throws Exception;
	public IListaEncadenada<String> darCiudades();
	public void registrarAnuncio(String woeid, String texto, String link, String rutaImagen);
	public IListaEncadenada<String[]> darAnuncios();
	public IListaEncadenada<String[]> darCiudadesTemperatura();
}

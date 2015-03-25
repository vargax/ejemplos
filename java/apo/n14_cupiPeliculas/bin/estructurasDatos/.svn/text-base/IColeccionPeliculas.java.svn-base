package estructurasDatos;

import java.io.Serializable;

import mundo.Pelicula;

public interface IColeccionPeliculas extends Serializable {
	/**
	 * Agrega una nueva película a la colección
	 * @param nuevaPelicula: La película a agregar al índice
	 * @throws EstructuraExcepcion: Si se presenta algún error al registrar la película
	 */
	public void agregarPelicula(Pelicula nuevaPelicula) throws EstructuraExcepcion;
	/**
	 * Da la lista de las películas almacenadas
	 * @return Lista que contiene todas las películas almacenadas en la colección
	 */
	public IListaEncadenada<Pelicula> darPeliculas();
	/**
	 * Busca peliculas por nombre
	 * @param nombre: Nombre o parte del nombre de la película buscada 
	 * @return Lista de películas que satisfacen el criterio de búsqueda
	 */
	public IListaEncadenada<Pelicula> buscarPeliculas(String nombre);
	/**
	 * Busca películas por año
	 * @param añoInicial: Año en el cual inicial el rango a buscar
	 * @param añoFinal: Año en el cual finaliza el rango a buscar
	 * @return Lista de películas que satisfacen el criterio de búsqueda
	 */
	public IListaEncadenada<Pelicula> buscarPeliculas(int añoIncial, int añoFinal);
	
	/**
	 * Busca películas que contienen la cadena pasada como parámetro en su descripción
	 * @param criterioBusqueda
	 * @return Lista de películas que satisfacen el criterio de búsqueda
	 */
	public IListaEncadenada<Pelicula> buscarEnDescripcion(String criterioBusqueda);
}

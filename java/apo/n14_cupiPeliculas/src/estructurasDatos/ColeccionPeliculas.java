/**
 * 
 */
package estructurasDatos;

import excepciones.CriterioOrdenamientoInvalidoException;
import mundo.Pelicula;

/**
 * @author cvargasc
 *
 */
public class ColeccionPeliculas implements IColeccionPeliculas {
	/**
	 * Constante Serialización
	 */
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private IIndiceLexicografico<Pelicula> indiceTitulos;
	private IIndiceLexicografico<Pelicula> indiceAños;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public ColeccionPeliculas () {
		indiceTitulos = new IndiceLexicografico<Pelicula>();
		indiceAños = new IndiceLexicografico<Pelicula>();
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/* (non-Javadoc)
	 * @see estructurasDatos.IColeccionPeliculas#agregarPelicula(mundo.Pelicula)
	 */
	@Override
	public void agregarPelicula(Pelicula nuevaPelicula)	throws EstructuraExcepcion {
		try {
			String[] titulo = nuevaPelicula.darNombre().split(" ");
			for (String palabra : titulo) indiceTitulos.agregarObjeto(palabra, nuevaPelicula);
			indiceAños.agregarObjeto(""+nuevaPelicula.darAño(), nuevaPelicula);
					
		} catch (CriterioOrdenamientoInvalidoException e) {
			e.printStackTrace();
			throw new EstructuraExcepcion("Se ha presentado un error al agregar la película: "+e.getMessage());
		}
	}
	/* (non-Javadoc)
	 * @see estructurasDatos.IColeccionPeliculas#darPeliculas(java.lang.String)
	 */
	@Override
	public IListaEncadenada<Pelicula> darPeliculas() {
		return indiceAños.buscarObjeto("");
	}
	/* (non-Javadoc)
	 * @see estructurasDatos.IColeccionPeliculas#buscarPeliculas(java.lang.String)
	 */
	@Override
	public IListaEncadenada<Pelicula> buscarPeliculas(String nombre) {
		return indiceTitulos.buscarObjeto(nombre);
	}

	/* (non-Javadoc)
	 * @see estructurasDatos.IColeccionPeliculas#buscarPeliculas(int, int)
	 */
	@Override
	public IListaEncadenada<Pelicula> buscarPeliculas(int añoIncial, int añoFinal) {
		ListaEncadenada<Pelicula> respuesta = new ListaEncadenada<Pelicula>();
		for (int i = añoIncial; i <= añoFinal; i++) {
			IListaEncadenada<Pelicula> resultado = indiceAños.buscarObjeto(""+i);
			for (Pelicula pelicula : resultado) respuesta.agregar(pelicula);
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see estructurasDatos.IColeccionPeliculas#buscarEnDescripcion(java.lang.String)
	 */
	@Override
	public IListaEncadenada<Pelicula> buscarEnDescripcion(String criterioBusqueda) {
		IListaEncadenada<Pelicula> peliculas = indiceAños.darObjetos();
		ListaEncadenada<Pelicula> respuesta = new ListaEncadenada<Pelicula>();
		
		for (Pelicula pelicula : peliculas)
			if (pelicula.darDescripcion().contains(criterioBusqueda))
				respuesta.agregar(pelicula);
		
		return respuesta;
	}
}

package mundo;

import java.io.File;
import java.rmi.dgc.Lease;

import estructurasDatos.ColeccionPeliculas;
import estructurasDatos.EstructuraExcepcion;
import estructurasDatos.IColeccionPeliculas;
import estructurasDatos.IListaEncadenada;
import estructurasDatos.Lector;

public class CupiPeliculas implements ICupiPeliculas {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private IColeccionPeliculas coleccionPeliculas;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public CupiPeliculas() {
		coleccionPeliculas = new ColeccionPeliculas();
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	@Override
	public String cargarPeliculas(File origenP) throws Exception {
		Lector lector = new Lector(origenP, coleccionPeliculas);
		lector.cargarPeliculas();
		return "Se encontraron "+lector.darNumeroPeliculasEncontradas()+" peliculas. \n  Se agregaron "+
				lector.darNumeroPeliculasAgregadas()+"\n  No se procesaron "+lector.darNumeroErrores();
	}
	@Override
	public IListaEncadenada<Pelicula> darPeliculas() {
		return coleccionPeliculas.darPeliculas();
	}
	@Override
	public IListaEncadenada<Pelicula> buscarPeliculasPorNombre(String nombre) {
		return coleccionPeliculas.buscarPeliculas(nombre);
	}
	@Override
	public IListaEncadenada<Pelicula> buscarPeliculasPorDescripción(String descripcion) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IListaEncadenada<Pelicula> buscarPeliculasPorAño(int año) {
		return coleccionPeliculas.buscarPeliculas(año, año);
	}
	@Override
	public IListaEncadenada<Pelicula> buscarPeliculasPorRangoAños(int anioI,int anioF) {
		return coleccionPeliculas.buscarPeliculas(anioI, anioF);
	}
	@Override
	public void crearIndice(String criterio) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public IListaEncadenada<Pelicula> darIndicesAlfabeticamente() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IListaEncadenada<Pelicula> darPeliculasIndice(String criterioIndice) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public IListaEncadenada<Pelicula> darIndicesMasPopulares() {
		// TODO Auto-generated method stub
		return null;
	}
}

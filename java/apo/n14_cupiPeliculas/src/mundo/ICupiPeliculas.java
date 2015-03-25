/**
 * 
 */
package mundo;

import java.io.File;

import estructurasDatos.IListaEncadenada;

public interface ICupiPeliculas {
    /**
     * 
     * @param archivo
     */
    public String cargarPeliculas(File origenP) throws Exception;
    /**
     * 
     * @return
     */
    public IListaEncadenada<Pelicula> darPeliculas();
    /**
     * 
     * @param nombre
     * @return
     */
    public IListaEncadenada<Pelicula> buscarPeliculasPorNombre( String nombre );

    /**
     * 
     * @param descripcion
     * @return
     */
    public IListaEncadenada<Pelicula> buscarPeliculasPorDescripción( String descripcion );

    /**
     * 
     * @param año
     * @return
     */
    public IListaEncadenada<Pelicula> buscarPeliculasPorAño( int año );

    /**
     * 
     * @param anioI
     * @param anioF
     * @return
     */
    public IListaEncadenada<Pelicula> buscarPeliculasPorRangoAños( int anioI, int anioF );

    /**
     * 
     * @param criterio
     */
    public void crearIndice( String criterio );

    /**
     * 
     * @return
     */
    public IListaEncadenada<Pelicula> darIndicesAlfabeticamente( );

    /**
     * 
     * @param criterioIndice
     * @return
     */
    public IListaEncadenada<Pelicula> darPeliculasIndice( String criterioIndice );

    /**
     * 
     * @return
     */
    public IListaEncadenada<Pelicula> darIndicesMasPopulares( );
}
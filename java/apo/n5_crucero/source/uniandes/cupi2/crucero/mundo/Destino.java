/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_crucero
 * Autor: Catalina Rodríguez - 16-sep-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.crucero.mundo;

import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.SearchParameters;

/**
 * Clase que representa un destino del crucero
 */
public class Destino 
{	
	//-----------------------------------------------------------------
	// Constantes
	//-----------------------------------------------------------------
	
	/**
	 * Número máximo de fotos para el destino
	 */
	public static final int MAX_FOTOS = 10;

	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Ciudad destino
	 */
	private String ciudad;
	
	/**
	 * País destino
	 */
	private String pais;
	
	/**
	 * Arreglo de las fotos del destino
	 */
	private Foto[] fotos;
	
	/**
	 * Índice de la foto actual
	 */
	private int fotoActual;
	
	/**
	 * Número de fotos del destino
	 */
	private int numFotos;
	
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

	/**
	 * Método constructor. <br>
	 * <b>post: </b> Se creó un nuevo destino con las características dadas por parámetro. <br>
	 *               Se inicializo fotoActual en 0. <br>
	 *               Se inicializo numFotos en 0. <br>
	 *               Se inicializo en arreglo de fotos. <br>
	 * @param ciudadP Ciudad destino. ciudadP != null y ciudadP != ""
	 * @param paisP País destino. paisP != null y paisP != ""
	 */
	public Destino(String ciudadP, String paisP)
	{
		ciudad = ciudadP;
		pais = paisP;
		fotoActual = 0;
		numFotos = 0;
		fotos = new Foto[MAX_FOTOS];
	}
	
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

	/**
	 * Retorna la ciudad destino <br>
	 * @return ciudad Ciudad destino. ciudad != null y ciudad != ""
	 */
	public String darCiudad()
	{
		return ciudad;
	}
	
	/**
	 * Retorna el país destino <br>
	 * @return pais País destino. pais != null y pais != ""
	 */
	public String darPais()
	{
		return pais;
	}
	
	/**
	 * Método que obtiene de Flickr las imágenes del destino
	 * <b>post: </b> Se obtiene a lista de fotos de Flickr
	 * @param f Conexión con Flickr. f!=null
	 * @throws Exception Se dispara un excepción si la conexión con Flick falla
	 */
	public void obtenerImagenes(Flickr f) throws Exception
	{
		try 
        {
        	SearchParameters param = new SearchParameters();
        	param.setText(ciudad.toLowerCase() + " " + pais.toLowerCase() + " turismo");
        	
        	PhotosInterface fotosI = f.getPhotosInterface();
        	PhotoList listaFotos = fotosI.search(param, MAX_FOTOS, MAX_FOTOS);
        	int i = 0;
        	for (i = 0; i < MAX_FOTOS && i < listaFotos.size(); i++) 
        	{
				Photo p = (Photo) listaFotos.get(i);
				BufferedImage bi = fotosI.getImage(p, 3);
				Icon icono = new ImageIcon(bi);
				Foto foto = new Foto(p.getTitle(), icono);
				agregarImagen(foto);
			}
          	
        	//Se realiza otra búsqueda si en la primera no se obtienen resultados
        	if(numFotos == 0)
        	{
        		param.setText("ciudad " + ciudad.toLowerCase() + " " + pais.toLowerCase());
            	
            	listaFotos = fotosI.search(param, MAX_FOTOS, MAX_FOTOS);
            	for (i = 0; i < MAX_FOTOS && i < listaFotos.size(); i++) 
            	{
    				Photo p = (Photo) listaFotos.get(i);
    				BufferedImage bi = fotosI.getImage(p, 3);
    				Icon icono = new ImageIcon(bi);
    				Foto foto = new Foto(p.getTitle(), icono);
    				agregarImagen(foto);
    			}
        	}
        }
		catch (Exception e)
		{
			throw new Exception("Error al obtener las imágenes del destino");
		}

	}
	
	/**
	 * Retorna la imagen actual del destino
	 * @return foto Imagen actual del destino. Si no existe la imagen retorna null 
	 */
	public Foto darImagenActual()
	{
		Foto f = fotos[fotoActual];
		return f;
	}
	
	/**
	 * Retorna la imagen anterior del destino
	 * @return foto Imagen anterior del destino. 
	 * @throws Exception Se dispara una excepción si no existe una imagen anterior
	 */
	public Foto darImagenAnterior() throws Exception
	{
		if(fotoActual > 0)
		{
			fotoActual--;
			return darImagenActual();
		}
		else
		{
			throw new Exception("Esta es la primera imagen");
		}
	}
	
	/**
	 * Retorna la imagen siguiente del destino
	 * @return foto Imagen siguiente del destino. 
	 * @throws Exception Se dispara una excepción si no existe una imagen siguiente
	 */
	public Foto darImagenSiguiente() throws Exception
	{
		if(fotoActual < numFotos-1 )
		{			
			fotoActual++;
			return darImagenActual();
		}
		else
		{
			throw new Exception("Esta es la última imagen");
		}
	}
	
	/**
	 * Retorna el arreglo de imágenes del destino
	 * @return fotos Arreglo con la imágenes del destino. fotos != null 
	 */
	public Foto[] darImagenes()
	{
		return fotos;
	}
	
	/**
	 * Agrega una nueva foto al arreglo de imágenes del crucero
	 * <b>pre: </b> El arreglo de fotos ha sido inicializado
	 * <b>post: </b> Se agrego la foto
	 * @param f Foto a agregar. f!=null
	 */
	public void agregarImagen(Foto f)
	{
		fotos[numFotos] = f;
		numFotos++;
	}
}

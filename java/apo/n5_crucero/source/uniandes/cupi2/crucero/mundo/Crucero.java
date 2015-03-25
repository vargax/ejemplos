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

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.REST;

/**
 *  Clase que representa al crucero
 */
public class Crucero
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Lista de destinos del crucero
	 */
	private ArrayList destinos;
	
	/**
	 * Índice del destino actual
	 */
	private int posDestinoActual;
	
	/**
	 * Conexión con Flickr
	 */
	private Flickr flickr;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Método constructor de un nuevo Crucero <br>
     * <b>post: </b> Inicializa el crucero, inicializa la lista de destino, inicializa posDestinoActual en -1
     * y agrega el primer destino: Santa Marta - Colombia
     */
    public Crucero( )
    {
    	destinos = new ArrayList();
    	posDestinoActual = -1;
    	try 
    	{
            conectarFlickr();
			agregarDestino("Santa Marta", "Colombia");
		} 
    	catch (Exception e) 
    	{
            //No se genera excepción pues es el primer destino que se agrega
    	    //Si se genera una excepción es por que no se pudo conectar a flickr
    	    e.printStackTrace( );
		}
    	
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Agrega un nuevo destino y carga sus imagenes con los datos dados y modifica el 
     * índice del destino actual a la posición del destino agregado<br>
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se agrego un nuevo destino a la lista de destinos
     * El índice del nuevo destino en la lista es el nuevo índice del destino actual
     * @param ciudad Ciudad destino. ciudad!=null y ciudad!=""
     * @param pais País destino. pais!=null y pais!=""
     * @throws Exception Se dispara una excepción si es destino ya existe.
     */
    public void agregarDestino(String ciudad, String pais) throws Exception
    {
    	Destino d = buscarDestino(ciudad, pais);
    	if(d == null)
    	{
    		d = new Destino(ciudad, pais);
    		destinos.add(d);
    		posDestinoActual = destinos.size() - 1;
            obtenerImagenesDestinoActual();
    	}
    	else
    	{
    		throw new Exception("El destino ya existe en el crucero");
    	}
    }
    
    /**
     * Elimina el destino actual <br>
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se eliminó el destino actual, el nuevo destino actual es el 
     * primer destino en la lista si no es vacía o -1 de lo contrario
     * @throws Exception Se dispara una excepción si no existen destinos en el crucero
     */
    public void eliminarDestinoActual() throws Exception
    {
    	if(!destinos.isEmpty())
    	{
    		destinos.remove(posDestinoActual);
    		if(!destinos.isEmpty())
    		{
    			posDestinoActual = 0;
    		}
    		else
    		{
    			posDestinoActual = -1;
    		}
    	}
    	else
    	{
    		throw new Exception("El crucero no tiene destinos");
    	}
    }
    
    /**
     * Busca el destino que tenga la ciudad y país dados por parámetro <br>
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retornó el destino cuya ciudad y país coincide con los datos dados
     * @param ciudad Ciudad destino. ciudad!=null y ciudad!=""
     * @param pais País destino. pais!=null y pais!="" 
     * @return destino Destino cuya ciudad y país coincide con los datos dados. 
     * Si no encuentra el destino retorna null
     */
    public Destino buscarDestino(String ciudad, String pais) 
    {
		Destino d = null;
		boolean existe = false;
		for (int i = 0; i < destinos.size() && !existe; i++) 
		{
			Destino temp = (Destino) destinos.get(i);
			if(temp.darCiudad().equalsIgnoreCase(ciudad) && temp.darPais().equalsIgnoreCase(pais))
			{
				d = temp;
				existe = true;
				posDestinoActual = i;
			}
		}	
		return d;
	}
    
    /**
     * Retorna el destino en la posición actual de la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retornó el destino en la posición actual
     * @return destino Destino en la posición actual de la lista
     * si no existen destinos en el crucero retorna null
     */
    public Destino darDestinoActual()
    {
    	if(!destinos.isEmpty())
    	{
    		return (Destino) destinos.get(posDestinoActual);
    	}
    	else
    	{
    		return null;
       	}
    }
    
    /**
     * Retorna el destino en la posición siguiente a la actual en la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retornó el destino en la posición siguiente a la actual, 
     * el índice de la posición actual incrementa en uno
     * @return destino Destino en la posición siguiente a la actual 
     * @throws Exception Se dispara una excepción si la posición actual es la última posición del arreglo
     * @throws Exception Se dispara una excepción si no existen destinos en el crucero 
     */
    public Destino darSiguienteDestino() throws Exception
    {
    	if(!destinos.isEmpty())
    	{
        	if(posDestinoActual < destinos.size() - 1)
        	{
        		posDestinoActual++;
        		return darDestinoActual();
        	}
        	else
        	{
        		throw new Exception("Este es el último destino");
        	}
    	}
    	else
    	{
    		throw new Exception("El crucero no tiene destinos");
    	}
    }
   
    /**
     * Retorna el destino en la posición anterior a la actual en la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retornó el destino en la posición anterior a la actual,
     * el índice de la posición actual disminuye en uno
     * @return destino Destino en la posición anterior a la actual 
     * @throws Exception Se dispara una excepción si la posición actual es la primera posición del arreglo
     * @throws Exception Se dispara una excepción si no existen destinos en el crucero 
     */
    public Destino darAnteriorDestino() throws Exception
    {
    	if(!destinos.isEmpty())
    	{
    		if(posDestinoActual > 0)
	    	{
	    		posDestinoActual--;
	    		return darDestinoActual();
	    	}
	    	else
	    	{
	    		throw new Exception("Este es el primer destino");
	    	}
		}
		else
		{
			throw new Exception("El crucero no tiene destinos");
		}
    }
    
    /**
     * Retorna la imagen del destino en la posición actual de la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retornó la imagen del destino en la posición actual
     * @return foto Imagen del destino en la posición actual de la lista
     * @throws Exception Se dispara una excepción si no existen destinos en el crucero
     */
    public Foto darImagenDestinoActual() throws Exception
    {
    	Destino actual = darDestinoActual();
    	if( actual != null )
    	{
    		return actual.darImagenActual();    		
    	}
    	else
    	{
    		throw new Exception("El crucero no tiene destinos");
    	}
    }
    
    /**
     * Retorna la imagen siguiente del destino en la posición actual en la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retornó la imagen siguiente del destino en la posición actual
     * @return foto Imagen siguiente del destino en la posición actual de la lista
     * @throws Exception Se dispara una excepción si la imagen actual del destino es la última
     */
    public Foto darImagenSiguienteDelDestinoActual() throws Exception
    {
    	Destino actual = darDestinoActual();
    	if( actual != null )
    	{
    		return actual.darImagenSiguiente();
    	}
    	else
    	{
    		throw new Exception("El crucero no tiene destinos");
    	}
    }
   
    /**
     * Retorna la imagen anterior del destino en la posición actual en la lista de destinos
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retornó la imagen anterior del destino en la posición actual
     * @return foto Imagen anterior del destino en la posición actual de la lista
     * @throws Exception Se dispara una excepción si la imagen actual del destino es la primera
     */
    public Foto darImagenAnteriorDelDestinoActual() throws Exception
    {
    	Destino actual = darDestinoActual();
    	if( actual != null )
    	{
    		return actual.darImagenAnterior();
    	}
    	else
    	{
    		throw new Exception("El crucero no tiene destinos");
    	}
    }
    
    /**
     * Realiza la búsqueda de las imágenes en Flickr del destino actual
     * <b>pre: </b> La lista de destinos ha sido inicializada y el destino actual existe
     * <b>post: </b> Se han buscado y guardado las imágenes del destino actual
     * @throws Exception Se dispara un excepción si la conexión con el buscador de imágenes falla
     */
    private void obtenerImagenesDestinoActual() throws Exception
    {
    	if( flickr != null)
    	{
    		Destino actual = darDestinoActual();
    		actual.obtenerImagenes(flickr);
    	}	
    	else
    	{
			throw new Exception("Error al obtener las imágenes del destino");
		}
    }

    /**
     * Método que retorna la lista de destinos del crucero
     * <b>pre: </b> La lista de destinos ha sido inicializada
     * <b>post: </b> Se retornó la lista de destinos
     * @return Lista de destinos
     */
    public ArrayList darDestinos()
    {
    	return destinos;
    }
    
    /**
     * Método que retorna el índice del destino actual
     * @return Índice del destino
     */
    public int darIndiceDestinoActual()
    {
    	return posDestinoActual;
    }
    
    /**
     * Método que realiza la conexión con Flick para obtener la imágenes de los destinos
     */
    public void conectarFlickr()
    {
    	try 
        {
        	InputStream in = new FileInputStream("./data/setup.properties");
            Properties properties = new Properties();
            properties.load(in);
            in.close();
            
            flickr = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
        }
    	catch (Exception e) 
    	{
			flickr = null;
		}
    }
    
    /**
     * Método que retorna el objeto de tipo Flickr que 
     * representa la conexión con el sitio web
     * @return Conexión con Flickr
     */
    public Flickr darConexionFlickr()
    {
    	return flickr;
    }
    
    
	//-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }


}
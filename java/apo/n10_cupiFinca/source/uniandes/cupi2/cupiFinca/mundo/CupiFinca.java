/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiFinca
 * Autor: Luis Ricardo Ruiz Rodríguez - 28-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiFinca.mundo;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.SALOAD;

import uniandes.cupi2.cupiFinca.mundo.excepciones.PersistenciaException;
import uniandes.cupi2.cupiFinca.mundo.excepciones.ProductoNoExisteException;
import uniandes.cupi2.cupiFinca.mundo.excepciones.TerrenoNoExisteException;
import uniandes.cupi2.cupiFinca.mundo.excepciones.TerrenoNoRecolectableException;
import uniandes.cupi2.cupiFinca.mundo.excepciones.TerrenoNoVacioException;
import uniandes.cupi2.cupiFinca.test.CercaTest;

/**
 * Clase que representa la finca.<br>
 * <b>inv: </b><br>
 * La lista de los terrenos está inicializada.<br>
 * El dinero debe ser mayor o igual a 0.<br>
 * El tamaño del terreno debe ser mayor a 0.<br>
 */
public class CupiFinca
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * La cantidad de dinero inicial en la finca
     */
    public static final int DINERO_INICIAL = 300;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La matriz con todos los campos de la finca
     */
    private ITerreno[][] campos;

    /**
     * La lista de terrenos que están activos
     */
    private Collection terrenosActivos;

    /**
     * La cantidad de dinero que tiene la finca
     */
    private int dinero;

    /**
     * El tamaño del terreno de la finca
     */
    private int tamanioTerreno;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la finca dado el ancho y el alto de toda la finca <br>
     * <b> post: </b> Se inicializó la lista de terrenos activos.
     * @param ancho El ancho del campo de la finca. ancho > 0
     * @param alto La altura del campo de la finca. alto > 0
     * @param horizontales La cantidad de terrenos horizontalmente. horizontales > 0
     * @param verticales La cantidad de terrenos verticalmente. verticales > 0
     * @param pTamanio El tamaño de los terrenos. pTamanio > 0
     */
    public CupiFinca( int ancho, int alto, int horizontales, int verticales, int pTamanio )
    {
        campos = new ITerreno[horizontales][verticales];

        dinero = DINERO_INICIAL;
        tamanioTerreno = pTamanio;

        terrenosActivos = new ArrayList();

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la cantidad de dinero que tiene la finca
     * @return La cantidad de dinero que tiene la finca
     */
    public int darDinero( )
    {
        return dinero;
    }

    /**
     * Devuelve el terreno que está en la finca dada la posición en x y y
     * @param x La posición en x. x >= 0
     * @param y La posición en y. y >= 0
     * @return Devuelve el terreno en esa posición, devuelve null en caso de que no haya un terreno en esa posición
     */
    public ITerreno darTerrenoPosicion( int x, int y )
    {
        return campos[ x ][ y ];
    }

    /**
     * Devuelve la finca al estado inicial del juego.<br>
     * <b>pos: </b>La matriz de los terrenos y el arreglo de terrenos activos quedan vacíos
     */
    public void reiniciar( )
    {
        for( int i = 0; i < campos.length; i++ )
        {
            for( int j = 0; j < campos[ i ].length; j++ )
            {
                campos[ i ][ j ] = null;
            }
        }
        terrenosActivos.clear( );
        dinero = DINERO_INICIAL;
        
        verificarInvariante( );
    }

    /**
     * Avanza el ciclo de vida de cada uno de los terrenos activos
     */
    public void actualizarEstadoTerrenos( )
    {
        Iterator iter = terrenosActivos.iterator();
        while (iter.hasNext()) ((ITerreno) iter.next()).avanzarCiclo();
    }

    /**
     * Agrega un nuevo producto a la finca.<br>
     * <b>pre: </b>El precio del producto no supera el que tiene la finca.<br>
     * @param nombreProducto El nombre del nuevo producto. nombreProducto != null
     * @param x La coordenada x del terreno donde sembrar. x >= 0
     * @param y La coordenada y del terreno donde sembrar. y >= 0
     * @throws TerrenoNoVacioException En caso de que se intente producir en un terreno que ya está ocupado
     * @throws PersistenciaException En caso de no leer la imagen del cultivo
     * @throws ProductoNoExisteException En caso de que el producto no existe
     */
    public void producirTerreno( String nombreProducto, int x, int y ) throws TerrenoNoVacioException, PersistenciaException, ProductoNoExisteException
    {
        ITerreno terreno = null;

        // Crea el producto
        if(nombreProducto.equals(Cebolla.NOMBRE_CULTIVO ))			terreno = new Cebolla(x, y, tamanioTerreno, 0);
        else if(nombreProducto.equals(Zanahoria.NOMBRE_CULTIVO ))	terreno = new Zanahoria(x, y, tamanioTerreno, 0);
        // Nombre de la construcción
        else if(nombreProducto.equals(Casa.NOMBRE_CONSTRUCCION ))	terreno = new Casa(x, y, tamanioTerreno);
        else if(nombreProducto.equals(Cerca.NOMBRE_CONSTRUCCION))	terreno = new Cerca(x, y, tamanioTerreno);

        // Agrega el producto
        if( terreno != null )
        {
            if( campos[ x ][ y ] != null )
            {
                throw new TerrenoNoVacioException( x, y );
            }
            dinero -= terreno.darCosto( );

            campos[ x ][ y ] = terreno;
            terrenosActivos.add( terreno );
        }
        else
        {
            throw new ProductoNoExisteException( nombreProducto );
        }
        verificarInvariante( );
    }

    /**
     * Cosecha un producto de una casilla dada
     * @param x La coordenada x de la casilla de la finca. x >= 0
     * @param y La coordenada y de la casilla de la finca. y >= 0
     * @throws TerrenoNoExisteException Lanza una excepción si el terreno no existe.
     * @throws TerrenoNoRecolectableException Lanza una excepción si el producto no ha terminado su etapa de producción
     */
    public void recolectarProducto( int x, int y ) throws TerrenoNoExisteException, TerrenoNoRecolectableException
    {
        ITerreno terreno = campos[ x ][ y ];
        if( terreno == null )
        {
            throw new TerrenoNoExisteException( x, y );
        }

        double ganancia = terreno.darGanancia( );
        if( ganancia > -1 && terreno.esRecolectable( ) )
        {
            dinero += ganancia;
            eliminarTerreno( x, y );
        }
        else
        {
            throw new TerrenoNoRecolectableException( x, y );
        }
        verificarInvariante( );
    }

    /**
     * Elimina el terreno ubicado en la casilla dada.
     * @param x La coordenada x de la casilla de la finca. x >= 0
     * @param y La coordenada y de la casilla de la finca. y >= 0
     * @throws TerrenoNoExisteException Lanza una excepción si el terreno no existe.
     */
    public void eliminarTerreno( int x, int y ) throws TerrenoNoExisteException
    {
        ITerreno terreno = campos[ x ][ y ];
        campos[ x ][ y ] = null;
        if( terreno != null )
        {
            terrenosActivos.remove( terreno );
        }
        else
        {
            throw new TerrenoNoExisteException( x, y );
        }
        verificarInvariante( );
    }

    /**
     * Dibuja las imágenes de cada uno de los terrenos activos en la finca
     * @param graphics El lienzo donde se está dibujando la finca
     */
    public void actualizarDibujosTerrenos( Graphics2D graphics )
    {
    	Iterator iter = terrenosActivos.iterator();
        while (iter.hasNext()) ((ITerreno) iter.next()).dibujar(graphics);
    }

    /**
     * Guarda el estado de la finca en un archivo de texto. Ver documento de descripción.
     * @param archivo El nombre del archivo donde se guarda la finca. archivo != null
     * @throws PersistenciaException En caso de no poder escribir en el archivo
     */
    public void guardarArchivo( File archivo ) throws PersistenciaException
    {
        try {
			PrintWriter pw = new PrintWriter(archivo);
			pw.println(dinero);
			pw.println(terrenosActivos.size());
			
			Iterator i = terrenosActivos.iterator();
			while(i.hasNext()) ((Terreno) i.next()).guardarTerreno(pw);
			
			pw.close();
		} catch (FileNotFoundException e) {
			throw new PersistenciaException("No fue posible persistir la finca: /n" + e.getMessage());
		}
    }

    /**
     * Abre el estado de la finca de un archivo.
     * @param pArchivo El nombre del archivo donde se guarda la finca. pArchivo != null
     * @throws PersistenciaException En caso de no poder leer el archivo
     * @throws ProductoNoExisteException En caso de que el producto no exista
     */
    public void abrirArchivo( File pArchivo ) throws PersistenciaException, ProductoNoExisteException
    {
        reiniciar( );
        try
        {
            BufferedReader reader = new BufferedReader( new FileReader( pArchivo ) );

            String sDinero = reader.readLine( );
            dinero = (int) Double.parseDouble( sDinero );

            String sCantidad = reader.readLine( );
            int cantidad = Integer.parseInt( sCantidad );

            String linea = reader.readLine( );
            while( cantidad > 0 && linea != null )
            {
                ITerreno terreno = null;

                String[] info = linea.split( ";" );
                String nombre = info[ 0 ];
                int pX = Integer.parseInt( info[ 1 ] );
                int pY = Integer.parseInt( info[ 2 ] );

                // Productos para cultivar
                if( nombre.equals( Cebolla.NOMBRE_CULTIVO ) )
                {
                    int tiempo = Integer.parseInt( info[ 3 ] );
                    terreno = new Cebolla( pX, pY, tamanioTerreno, tiempo );

                }
                else if( nombre.equals( Zanahoria.NOMBRE_CULTIVO ) )
                {
                    int tiempo = Integer.parseInt( info[ 3 ] );
                    terreno = new Zanahoria( pX, pY, tamanioTerreno, tiempo );
                }

                // Productos de construcción
                else if( nombre.equals( Casa.NOMBRE_CONSTRUCCION ) )
                {
                    terreno = new Casa( pX, pY, tamanioTerreno );
                }
                else if( nombre.equals( Cerca.NOMBRE_CONSTRUCCION ) )
                {
                    terreno = new Cerca( pX, pY, tamanioTerreno );
                }

                if( terreno != null )
                {
                    campos[ pX ][ pY ] = terreno;
                    terrenosActivos.add( terreno );
                }
                else
                {
                    throw new ProductoNoExisteException( nombre );
                }

                cantidad--;
                linea = reader.readLine( );
            }
            reader.close( );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "Error al abrir el archivo: " + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica que se cumpla la invariante de la clase.<br>
     * <b>inv: </b><br>
     * terrenosActivos != null<br>
     * dinero >= 0<br>
     * tamanioTerreno > 0<br>
     */
    private void verificarInvariante(){
        assert terrenosActivos != null: "La lista de los terrenos está inicializada.";
        assert dinero >= 0: "El dinero debe ser mayor o igual a 0.";
        assert tamanioTerreno > 0: "El tamaño del terreno debe ser mayor a 0."; 
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

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
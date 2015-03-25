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
import java.io.PrintWriter;

/**
 * Clase abstracta que me representa un terreno.<br>
 * <b>Invariante: </b>Debe tener un nombre existente.<br>
 * El costo debe ser mayor a 0<br>
 * El tiempo siempre debe ser un número igual o mayor a 0<br>
 * El estado debe ser un número igual o mayor a 0<br>
 * La posición en x debe ser igual o mayor 0<br>
 * La posición en y debe ser igual o mayor 0<br>
 * El ancho del terreno debe mayor 0<br>
 */
public abstract class Terreno implements ITerreno
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El nombre del producto
     */
    private String nombre;

    /**
     * El costo del producto del terreno
     */
    private int costo;

    /**
     * Si el terreno tiene un producto que se puede recoger
     */
    private boolean recolectable;

    /**
     * La ganancia obtenida del producto del terreno
     */
    protected int ganancia;

    /**
     * La tabla con los tiempos para cada uno de los estados de producción en el terreno
     */
    protected int[] tiempos;

    /**
     * El tiempo que lleva el terreno en haber sido instalado
     */
    protected int tiempo;

    /**
     * El estado de producción del terreno
     */
    protected int estado;

    /**
     * La ubicación en x del terreno
     */
    protected int x;

    /**
     * La ubicación en y del terreno
     */
    protected int y;

    /**
     * El ancho del terreno
     */
    protected int ancho;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la finca dado el ancho y el alto del terreno
     * @param pNombre El nombre del producto. pNombre != null
     * @param pX La coordenada x del terreno. pX >= 0
     * @param pY La coordenada y del terreno. pY >= 0
     * @param pAncho El ancho del campo del terreno. pAncho > 0
     * @param pCosto El costo de producir. pCosto >= 0
     * @param pGanancia La ganancia obtenida al producir. pGanancia >= 0
     * @param pRecolectable En caso de que el producto se pueda recoger.
     * @param pTiempo El tiempo de inicio de la revisión del terreno. pTiempo >= 0
     */
    public Terreno( String pNombre, int pX, int pY, int pAncho, int pCosto, int pGanancia, boolean pRecolectable, int pTiempo )
    {
        nombre = pNombre;
        costo = pCosto;
        ganancia = pGanancia;

        x = pX;
        y = pY;
        ancho = pAncho;

        tiempo = pTiempo;
        recolectable = pRecolectable;
        
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el nombre del producto
     * @return El nombre del producto
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Devuelve el costo de instalar este producto
     * @return El costo de instalación del producto
     */
    public int darCosto( )
    {
        return costo;
    }

    /**
     * Devuelve el tiempo que lleva el producto
     * @return El tiempo que lleva el producto
     */
    public int darTiempo( )
    {
        return tiempo;
    }

    /**
     * Devuelve el estado actual del producto
     * @return El estado actual del producto
     */
    public int darEstado( )
    {
        return estado;
    }
        
    /**
     * Devuelve si el producto del terreno se puede recoger
     * @return true si el producto se puede recoger, false en caso contrario
     */
    public boolean esRecolectable( )
    {
        return recolectable;
    }
    
    /**
     * Avanza en el ciclo de vida del terreno
     */
    public void avanzarCiclo( )
    {
        tiempo++;
        boolean cambioEstado = false;
        for( int i = 0; i < tiempos.length && !cambioEstado; i++ )
        {
            if( tiempo <= tiempos[ 0 ] )
            {
                estado = 0;
            }
            else if( i < tiempos.length - 1 )
            {
                if( tiempos[ i ] < tiempo && tiempo <= tiempos[ i + 1 ] )
                {
                    estado = i + 1;
                    cambioEstado = true;
                }
            }
            else
            {
                estado = i;
            }
        }
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos abstractos
    // -----------------------------------------------------------------

    /**
     * Devuelve el resultado obtenido por recoger el producto.
     * @return Los resultados de haber recogido el terreno.
     */
    public abstract int darGanancia( );

    /**
     * Dibuja el producto en el pedazo del campo
     * @param graphics El campo de dibujo. graphics != null
     */
    public abstract void dibujar( Graphics2D graphics );

    /**
     * Guarda la información del terreno en el archivo que está de parámetro
     * @param pw El archivo donde se está escribiendo. pw != null
     */
    public abstract void guardarTerreno( PrintWriter pw );

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    
    /**
     * Verifica la invariante de la clase.<br>
     * <b>inv: </b>nombre != null<br>
     * costo > 0<br>
     * tiempo > 0<br>
     * estado >= 0<br>
     * x >= 0<br>
     * y >= 0<br>
     * ancho >= 0<br>
     */
    private void verificarInvariante(){
        assert nombre != null: "Debe tener un nombre existente";
        assert costo > 0: "El costo debe ser mayor a 0";
        assert tiempo >= 0: "El tiempo siempre debe ser un número igual o mayor a 0";
        assert estado >= 0: "El estado debe ser un número igual o mayor a 0";
        assert x >= 0: "La posición en x debe ser igual o mayor 0";
        assert y >= 0: "La posición en y debe ser igual o mayor 0";
        assert ancho >= 0: "El ancho del terreno debe mayor 0";
        
    }
}

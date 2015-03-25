/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 
 * Ejercicio: n6_buscaminas 
 * Autor Inicial: Mario Sánchez - 15/07/2005
 * Autor: Milena Vela - 21/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.buscaminas.mundo;

/**
 * Esta clase representa una casilla del campo minado.
 */
public class Casilla
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica que la casilla está vacía
     */
    public static final int VACIA = 0;

    /**
     * Indica que la casilla tiene una bomba
     */
    public static final int MINADA = 1;

    /**
     * Indica que la casilla fue marcada por el usuario
     */
    public static final int MARCADA = 2;

    /**
     * Indica que la casilla no ha sido ni destapada ni marcada por el usuario
     */
    public static final int TAPADA = 3;

    /**
     * Indica que la casilla ya fue destapada por el usuario
     */
    public static final int DESTAPADA = 4;

    /**
     * Imagen para mostrar en las casillas marcadas
     */
    private static final String MARCADA_IMAGEN = "./data/marca.jpg";

    /**
     * Indica que la casilla tiene una bomba que fue encontrada por el usuario
     */
    public static final int MARCADA_EQUIVOCADA = 21;

    /**
     * Imagen para mostrar en las casillas marcadas equivocadas
     */
    private static final String MARCADA_EQUIVOCADA_IMAGEN = "./data/marcaEquivocada.jpg";

    /**
     * Imagen para mostrar en casillas con bombas no marcadas
     */
    private static final String BOMBA_IMAGEN = "./data/bomba.jpg";

    /**
     * Indica que la casilla tiene una bomba que ha sido estallada
     */
    public static final int BOMBA_ESTALLADA = 31;

    /**
     * Imagen para mostrar en casillas con bombas estalladas
     */
    private static final String BOMBA_ESTALLADA_IMAGEN = "./data/bombaEstallada.jpg";

    /**
     * Indica que la casilla no tiene minas cerca
     */
    public static final int CERCA_0 = 40;

    /**
     * Indica que la casilla tiene 1 mina cerca
     */
    public static final int CERCA_1 = 41;

    /**
     * Indica que la casilla tiene 2 minas cerca
     */
    public static final int CERCA_2 = 42;

    /**
     * Indica que la casilla tiene 3 minas cerca
     */
    public static final int CERCA_3 = 43;

    /**
     * Indica que la casilla tiene 4 minas cerca
     */
    public static final int CERCA_4 = 44;

    /**
     * Indica que la casilla tiene 5 minas cerca
     */
    public static final int CERCA_5 = 45;

    /**
     * Indica que la casilla tiene 6 minas cerca
     */
    public static final int CERCA_6 = 46;

    /**
     * Indica que la casilla tiene 7 minas cerca
     */
    public static final int CERCA_7 = 47;

    /**
     * Indica que la casilla tiene 8 minas cerca
     */
    public static final int CERCA_8 = 48;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el estado de la casilla
     */
    private int estado;

    /**
     * Posición horizontal de la casilla
     */
    private int fila;

    /**
     * Posición vertical de la casilla
     */
    private int columna;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva casilla con un estado y la posición horizontal y vertical en el campo minado.
     * @param estadoCasilla Es el estado de la casilla.<br>
     *        Debe ser uno de los siguientes {VACIA, MINADA, MARCADA, TAPADA, DESTAPADA, MARCADA_EQUIVOCADA, BOMBA_ESTALLADA}. {CERCA_0, CERCA_1, CERCA_2, CERCA_3, CERCA_4,
     *        CERCA_5, CERCA_6, CERCA_7, CERCA_8} corresponde al numero de minas alrededor de una casilla destapada.
     * @param laFila Número de fila de la casilla.
     * @param laColumna Número de columna de la casilla.
     * 
     */
    public Casilla( int estadoCasilla, int laFila, int laColumna )
    {
        estado = estadoCasilla;
        fila = laFila;
        columna = laColumna;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el estado de la casilla.
     * @return estado.
     */
    public int darEstado( )
    {
        return estado;
    }

    /**
     * Devuelve el número de fila de la casilla
     * @return fila
     */
    public int darFila( )
    {
        return fila;
    }

    /**
     * Devuelve el número de columna de la casilla
     * @return columna
     */
    public int darColumna( )
    {
        return columna;
    }

    /**
     * Modifica el estado de una casilla
     * @param nuevoEstado Estado de la casilla
     */
    public void cambiarEstado( int nuevoEstado )
    {
        switch( nuevoEstado )
        {
            case VACIA:
                estado = VACIA;
                break;
            case MINADA:
                estado = MINADA;
                break;
            case MARCADA:
                estado = MARCADA;
                break;
            case DESTAPADA:
                estado = DESTAPADA;
                break;
            case TAPADA:
                estado = TAPADA;
                break;
        }
    }

    /**
     * Retorna la imagen que debe mostrarse en la casilla según el estado.
     * @return la ruta hasta la imagen que debe mostrarse. Si no se debe mostrar ninguna imagen entonces retorna null.
     */
    public String darImagen( )
    {
        String strimagen = null;
        switch( estado )
        {
            case MINADA:
                strimagen = BOMBA_IMAGEN;
                break;
            case BOMBA_ESTALLADA:
                strimagen = BOMBA_ESTALLADA_IMAGEN;
                break;
            case MARCADA:
                strimagen = MARCADA_IMAGEN;
                break;
            case MARCADA_EQUIVOCADA:
                strimagen = MARCADA_EQUIVOCADA_IMAGEN;
                break;
        }
        return strimagen;
    }
}

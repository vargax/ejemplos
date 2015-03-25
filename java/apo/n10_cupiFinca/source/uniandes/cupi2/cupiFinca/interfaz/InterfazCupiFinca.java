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

package uniandes.cupi2.cupiFinca.interfaz;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.cupiFinca.mundo.CupiFinca;
import uniandes.cupi2.cupiFinca.mundo.excepciones.PersistenciaException;
import uniandes.cupi2.cupiFinca.mundo.excepciones.ProductoNoExisteException;
import uniandes.cupi2.cupiFinca.mundo.excepciones.TerrenoNoExisteException;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazCupiFinca extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * La constante del estado donde está inactiva la finca
     */
    public static final String ESTADO_INACTIVO = "Estado de inactividad";

    /**
     * La constante del estado donde se establece la entrada de producción de los terrenos
     */
    public static final String ESTADO_PRODUCCION = "Estado de Producción";

    /**
     * La constante del estado donde se establece la recolección de los resultados del producto
     */
    public static final String ESTADO_RECOLECCION = "Estado de Recolección";

    /**
     * La constante del estado donde se establece la eliminación del terrenos ocupados
     */
    public static final String ESTADO_ELIMINACION = "Estado de eliminación";

    /**
     * La dimensión de la casilla del terreno
     */
    public static int DIMENSION = 80;
    

    // -----------------------------------------------------------------
    // Constantes imágenes
    // -----------------------------------------------------------------

    /**
     * La ruta de la imagen del flecha
     */
    public static final String RUTA_FLECHA = "./data/imagenes/flecha.png";

    /**
     * La ruta de la imagen del martillo
     */
    public static final String RUTA_MARTILLO = "./data/imagenes/martillo.png";

    /**
     * La ruta de la imagen de la pala
     */
    public static final String RUTA_PALA = "./data/imagenes/pala.png";
    
    /**
     * La ruta de la imagen de la caneca
     */
    public static final String RUTA_CANECA = "./data/imagenes/caneca.png";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private CupiFinca finca;

    /**
     * La acción que está activada en la finca
     */
    private String estado;
    
    /**
     * El archivo donde se guarda el juego
     */
    private File archivo;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * La barra del menú de la aplicación
     */
    private BarraMenu barraMenu;
    
    /**
     * Panel con los comandos
     */
    private PanelComandos panelComandos;

    /**
     * Panel con los terrenos
     */
    private PanelTerrenos panelTerrenos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la interfaz principal de la finca. <br>
     * <b>post: </b> La interfaz es inicializada.
     */
    public InterfazCupiFinca( )
    {

        // Construye la forma
        setLayout( new BorderLayout( ) );
        setTitle( "CupiFinca" );
        setSize( 900, 530 );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setResizable( false );

        barraMenu = new BarraMenu( this );
        add( barraMenu, BorderLayout.NORTH );
        
        panelTerrenos = new PanelTerrenos( this );
        add( panelTerrenos, BorderLayout.CENTER );

        panelComandos = new PanelComandos( this );
        add( panelComandos, BorderLayout.WEST );

        // Centrar la ventana
        setLocationRelativeTo( null );

        // Crea la clase principal
        int horizontales = panelTerrenos.getPreferredSize( ).width / DIMENSION;
        int verticales = panelTerrenos.getPreferredSize( ).height / DIMENSION;
        finca = new CupiFinca( panelTerrenos.getWidth( ), panelTerrenos.getHeight( ), horizontales, verticales, DIMENSION );

        // Inicializa los atributos no gráficos
        estado = ESTADO_INACTIVO;

        ThreadCicloVida thread = new ThreadCicloVida( this );
        thread.start( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el estado de la finca
     * @return El estado de la finca
     */
    public String darEstado( )
    {
        return estado;
    }

    /**
     * Devuelve la cantidad de dinero que hay en la finca
     * @return La cantidad de dinero de la finca
     */
    public double darCantidadDineroFinca( )
    {
        return finca.darDinero( );
    }

    /**
     * Modifica el estado de la finca
     * @param nEstado El nuevo estado de la finca. nEstado != null
     */
    public void modificarEstado( String nEstado )
    {
        estado = nEstado;
    }
    

    /**
     * Muestra el diálogo para producir en el terreno en la posición x y y en la finca
     * @param x La celda x de la finca. x >= 0
     * @param y La celda y de la finca. y >= 0
     */
    public void mostrarDialogoProducirTerreno( int x, int y )
    {
        DialogoCreacion dialogo = new DialogoCreacion( this, x, y );
        dialogo.setVisible( true );
    }

    /**
     * Comienza a producir en un terreno de la finca un producto específico
     * @param producto El nombre del producto. producto != null
     * @param x La coordenada x del terreno. x >= 0
     * @param y La coordenada y del terreno. y >= 0
     */
    public void producirEnTerreno( String producto, int x, int y )
    {
        try
        {
            finca.producirTerreno( producto, x, y );
            panelTerrenos.refrescar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Indica que hay que recolectar productos en la finca
     * @param x La celda x de la finca. x >= 0
     * @param y La celda y de la finca. y >= 0
     */
    public void recolectarTerreno( int x, int y )
    {
        try
        {
            finca.recolectarProducto( x, y );
            panelTerrenos.refrescar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Indica que hay que eliminar productos en la finca
     * @param x La celda x de la finca. x >= 0
     * @param y La celda y de la finca. y >= 0
     */
    public void eliminarTerreno( int x, int y )
    {
        try
        {
            finca.eliminarTerreno( x, y );
            panelTerrenos.refrescar( );
        }
        catch( TerrenoNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Simula el avance en los estados del terreno
     */
    public void simularEtapaCicloVidaTerrenos( )
    {
        finca.actualizarEstadoTerrenos( );
        panelTerrenos.refrescar( );
    }

    /**
     * Actualiza los dibujos en el panel
     * @param graphics El lienzo de trabajo del panel. graphics != null
     */
    public void actualizarDibujos( Graphics2D graphics )
    {
        finca.actualizarDibujosTerrenos( graphics );
        panelComandos.actualizar( finca.darDinero( ) );
    }

    // -----------------------------------------------------------------
    // Persistencia
    // -----------------------------------------------------------------


    /**
     * reinicia el estado del juego
     */
    public void reiniciar(){
        int opcion  = JOptionPane.showConfirmDialog( this, "¿Deséa guardar la partida actual?", "Nuevo...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE );
        if( opcion == JOptionPane.OK_OPTION){
            guardar();
        }
        finca.reiniciar( );
    }
    
    /**
     * Abre un juego guardado en un archivo
     */
    public void abrir(){
        JFileChooser chooser = new JFileChooser( "./data" );
        chooser.showOpenDialog( this );
        File nArchivo = chooser.getSelectedFile( );
        if(nArchivo != null){
            archivo = nArchivo;
            try
            {
                finca.abrirArchivo( archivo );
            }
            catch( PersistenciaException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
            catch( ProductoNoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        simularEtapaCicloVidaTerrenos( );
    }
    
    /**
     * Guarda el juego en un archivo definido anteriormente. Si no está definido, lo almacena en un nuevo archivo.
     */
    public void guardar(){
        if( archivo == null ){
            guardarComo();
        }else{
            try
            {
                finca.guardarArchivo( archivo );
            }
            catch( PersistenciaException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Guarda el juego en un archivo definido anteriormente. Si no está definido, lo almacena en un nuevo archivo.
     */
    public void guardarComo(){
        JFileChooser chooser = new JFileChooser( "./data" );
        chooser.showSaveDialog( this );
        File nArchivo = chooser.getSelectedFile( );
        if(nArchivo != null){
            archivo = nArchivo;
            try
            {
                finca.guardarArchivo( archivo );
            }
            catch( PersistenciaException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }
    
    /**
     * El método por defecto para salir de la aplicación
     */
    public void dispose(){
        int opcion  = JOptionPane.showConfirmDialog( this, "¿Deséa guardar la partida actual?", "Salir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE );
        if( opcion == JOptionPane.OK_OPTION){
            guardar();
        }
        super.dispose( );
        System.exit( 0 );
    }
    
    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = finca.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = finca.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Los argumentos de ejecución de la aplicación. args != null
     */
    public static void main( String[] args )
    {

        InterfazCupiFinca interfaz = new InterfazCupiFinca( );
        interfaz.setVisible( true );
    }
}
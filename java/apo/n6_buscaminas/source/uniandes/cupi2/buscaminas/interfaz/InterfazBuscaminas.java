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

package uniandes.cupi2.buscaminas.interfaz;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.buscaminas.mundo.CampoMinado;

/**
 * Esta es la clase principal de la interfaz del buscaminas
 */
public class InterfazBuscaminas extends JFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica que en el modo actual se deben marcar las casillas
     */
    public static final int MODO_MARCAR = 0;

    /**
     * Indica que en el modo actual se deben desmarcar las casillas que estén marcadas
     */
    public static final int MODO_DESMARCAR = 1;

    /**
     * Indica que en el modo actual se deben destapar las casillas
     */
    public static final int MODO_DESTAPAR = 2;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el modo actual de juego. Según el modo cambia la acción que se realiza cuando se hace click sobre el campo minado.
     */
    private int modoActual;

    /**
     * Es el campo minado sobre el que se está jugando.
     */
    private CampoMinado campoMinado;

    /**
     * Es el panel donde se muestran los botones para controlar el juego
     */
    private PanelBotones panelBotones;

    /**
     * Es el panel donde se muestra el estado actual del juego
     */
    private PanelBuscaminas panelBuscaminas;

    /**
     * Es el panel donde se muestra la barra de estado del juego
     */
    private PanelEstado panelBarraEstado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz de la aplicación.
     */
    public InterfazBuscaminas( )
    {
        try
        {
            campoMinado = new CampoMinado( new File( "./data/buscaminas.properties" ) );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar el estado inicial " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

        setTitle( "Buscaminas" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setResizable( false );

        modoActual = MODO_DESTAPAR;

        panelBotones = new PanelBotones( this );
        panelBuscaminas = new PanelBuscaminas( this, campoMinado.darFilas(), campoMinado.darColumnas( ) );
        panelBarraEstado = new PanelEstado( );

        setSize( 400, 400 );
        getContentPane( ).add( panelBotones, BorderLayout.NORTH );
        getContentPane( ).add( panelBuscaminas, BorderLayout.CENTER );
        getContentPane( ).add( panelBarraEstado, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el modo actual
     * @param modo El nuevo modo de juego. modo pertenece a {MODO_DESMARCAR, MODO_MARCAR, MODO_DESTAPAR}
     */
    public void cambiarModo( int modo )
    {
        modoActual = modo;
    }

    /**
     * Realiza una jugada en la posición indicada. La jugada que se realiza depende del modo actual.
     * @param x Coordenada x de la posición donde se está jugando
     * @param y Coordenada y de la posición donde se está jugando
     */
    public void jugar( int x, int y )
    {
        switch( modoActual )
        {
            case MODO_MARCAR:
                marcar( x, y );
                break;
            case MODO_DESMARCAR:
                desmarcar( x, y );
                break;
            case MODO_DESTAPAR:
                destapar( x, y );
                break;
        }
    }

    /**
     * Marca la casilla en la posición indicada. <br>
     * Si la casilla ya está marcada o está destapada o el juego ya terminó se informa al usuario.
     * @param x Coordenada x de la posición que se va a marcar
     * @param y Coordenada y de la posición que se va a marcar
     */
    private void marcar( int x, int y )
    {
        try
        {
            campoMinado.marcar( x, y );
            actualizar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Desmarca una posición. <br>
     * Si la casilla no está marcada o el juego ya terminó se informa al usuario.
     * @param x Coordenada x de la posición que se va a desmarcar
     * @param y Coordenada y de la posición que se va a desmarcar
     */
    private void desmarcar( int x, int y )
    {
        try
        {
            campoMinado.desmarcar( x, y );
            actualizar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Destapa una posición.<br>
     * Si la casilla no esta tapada o el juego ya se terminó se informa al usuario
     * @param x Coordenada x de la posición que se va a destapar
     * @param y Coordenada y de la posición que se va a destapar
     */
    private void destapar( int x, int y )
    {
        try
        {
            int resultado = campoMinado.destapar( x, y );
            actualizar( );

            if( resultado == CampoMinado.JUEGO_GANADO )
            {
                int tiempo = campoMinado.darTiempoTotal( );
                JOptionPane.showMessageDialog( this, "Ganó en " + tiempo + " segundos" );
            }
            else if( resultado == CampoMinado.JUEGO_PERDIDO )
            {
                JOptionPane.showMessageDialog( this, "Perdió!" );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Actualiza el panel del buscaminas y la barra de estado
     */
    private void actualizar( )
    {
        panelBuscaminas.actualizar( campoMinado );
        int numeroMinas = campoMinado.darNumeroMinasRestantes( );
        int tiempo = campoMinado.darTiempoTotal( );
       
        panelBarraEstado.actualizarMinas( numeroMinas );
        panelBarraEstado.actualizarTiempo( tiempo );

        switch( modoActual )
        {
            case MODO_MARCAR:
                panelBarraEstado.actualizarModo( "Marcar" );
                break;
            case MODO_DESMARCAR:
                panelBarraEstado.actualizarModo( "Desmarcar" );
                break;
            case MODO_DESTAPAR:
                panelBarraEstado.actualizarModo( "Destapar" );
                break;
        }
    }

    /**
     * Inicia un nuevo juego de buscaminas. El estado inicial del juego es: <br>
     * Todas las casillas están tapadas y sin marcas. <br>
     * Las minas se distribuyeron de manera aleatoria. <br>
     * El tiempo de inicio del juego se inicializa en cero. <br>
     * El modo de juego es destapar.
     */
    public void reiniciar( )
    {
        campoMinado.inicializarJuego( );
        modoActual = MODO_DESTAPAR;
        panelBuscaminas.actualizar( campoMinado );
        panelBuscaminas.actualizar( campoMinado );

        int numeroMinas = campoMinado.darNumeroMinas( );
        int tiempo = campoMinado.darTiempoTotal( );
        panelBarraEstado.actualizarMinas( numeroMinas );
        panelBarraEstado.actualizarTiempo( tiempo );
        panelBarraEstado.actualizarModo( "Destapar" );   

    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = campoMinado.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = campoMinado.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Ejecución
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación
     * @param args Los parámetros para ejecutar la aplicación. No se requiere ninguno.
     */
    public static void main( String[] args )
    {
        InterfazBuscaminas ib = new InterfazBuscaminas( );
        ib.setVisible( true );
    }
}

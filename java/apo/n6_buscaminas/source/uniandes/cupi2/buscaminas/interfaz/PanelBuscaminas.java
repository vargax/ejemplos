/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 
 * Ejercicio: n6_buscaminas 
 * Autor Inicial: Mario Sánchez - 15/07/2005
 * Autor: Milena Vela - 10/30/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.buscaminas.interfaz;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import uniandes.cupi2.buscaminas.mundo.CampoMinado;
import uniandes.cupi2.buscaminas.mundo.Casilla;

/**
 * Este es el panel donde se juega y se muestra el estado actual del campo minado
 */
public class PanelBuscaminas extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazBuscaminas ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Botones de las casillas
     */
    private JButton[][] botonesCasillas;

    /**
     * Ancho de la visualización actual
     */
    private int ancho;

    /**
     * Alto de la visualización actual
     */
    private int alto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel como una grilla del tamaño del campo minado.
     * @param principal Referencia a la ventana principal principal != null.
     * @param columnas número de columnas del panel
     * @param filas número de filas del panel
     */
    public PanelBuscaminas( InterfazBuscaminas principal, int filas, int columnas )
    {
        ventanaPrincipal = principal;
        ancho = columnas;
        alto = filas;

        inicializar( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa la matriz de botones
     */
    public void inicializar( )
    {
        // Filas, Columnas
        setLayout( new GridLayout( alto, ancho ) );
        botonesCasillas = new JButton[alto][ancho];

        // Se llena la matriz
        Font fuente = new Font( "Tahoma", Font.PLAIN, 10 );

        for( int i = 0; i < alto; i++ )
        {
            for( int j = 0; j < ancho; j++ )
            {

                botonesCasillas[ i ][ j ] = new JButton( );
                botonesCasillas[ i ][ j ].addActionListener( this );
                botonesCasillas[ i ][ j ].setActionCommand( i + "," + j );
                botonesCasillas[ i ][ j ].setFont( fuente );
                botonesCasillas[ i ][ j ].setMargin( new Insets( 1, 1, 1, 1 ) );
                add( botonesCasillas[ i ][ j ] );

            }

        }
    }

    /**
     * Actualiza la visualización del campo minado
     * @param campoMinado a mostrar. campoMinado != null.
     */
    public void actualizar( CampoMinado campoMinado )
    {

        Casilla[][] casillas = campoMinado.darCasillas( );

        // realiza un ciclo que recorre cada una de las casillas, revisa su estado, y repinta la casilla dependiendo de su estado
        for( int i = 0; i < campoMinado.darFilas( ); i++ )
        {
            for( int j = 0; j < campoMinado.darColumnas( ); j++ )
            {
                JButton boton = botonesCasillas[ i ][ j ];

                // Cambia el estado de una casilla cuyo estado es BOMBA
                if( casillas[ i ][ j ].darEstado( ) == Casilla.MINADA )
                {
                    boton.setText( "" );
                    boton.setEnabled( true );
                    boton.setContentAreaFilled( false );
                    boton.setIcon( new ImageIcon( casillas[ i ][ j ].darImagen( ) ) );
                }
                // Cambia el estado de una casilla cuyo estado es BOMBA_ESTALLADA
                else if( casillas[ i ][ j ].darEstado( ) == Casilla.BOMBA_ESTALLADA )
                {
                    boton.setText( "" );
                    boton.setEnabled( true );
                    boton.setContentAreaFilled( false );
                    boton.setIcon( new ImageIcon( casillas[ i ][ j ].darImagen( ) ) );
                }
                // Cambia el estado de una casilla cuyo estado es MARCADA
                else if( casillas[ i ][ j ].darEstado( ) == Casilla.MARCADA )
                {
                    boton.setText( "" );
                    boton.setEnabled( true );
                    boton.setContentAreaFilled( false );
                    boton.setIcon( new ImageIcon( casillas[ i ][ j ].darImagen( ) ) );
                }
                // Cambia el estado de una casilla cuyo estado es MARCADA_EQUIVOCADA
                else if( casillas[ i ][ j ].darEstado( ) == Casilla.MARCADA_EQUIVOCADA )
                {
                    boton.setText( "" );
                    boton.setEnabled( true );
                    boton.setIcon( new ImageIcon( casillas[ i ][ j ].darImagen( ) ) );
                }
                // Cambia el estado de una casilla cuyo estado es TAPADA
                else if( casillas[ i ][ j ].darEstado( ) == Casilla.TAPADA )
                {
                    boton.setText( "" );
                    boton.setEnabled( true );
                    boton.setContentAreaFilled( true );
                    boton.setIcon( null );
                }
                else
                {
                    int estado = casillas[ i ][ j ].darEstado( );
                    int numeroMinas = darMinasSegunEstado( estado );

                    if( numeroMinas != 0 )
                        boton.setText( "" + numeroMinas );
                    else
                        boton.setText( "" );

                    boton.setEnabled( false );
                    boton.setContentAreaFilled( false );
                    boton.setIcon( null );

                }
            }
        }

    }

    /**
     * Retorna el número de minas que hay cerca de una casilla según el estado
     * @param estado El estado de la casilla. estado no es BOMBA, BOMBA_ESTALLADA, MARCADA, MARCADA_EQUIVOCADA o TAPADA
     * @return El número de minas que hay cerca de esta casilla
     */
    private int darMinasSegunEstado( int estado )
    {
        int numeroMinas = 0;

        // retorna un número que indica cuantas minas se tienen cerca dependiendo del estado de la casilla
        switch( estado )
        {
            case Casilla.CERCA_0:
                numeroMinas = 0;
                break;
            case Casilla.CERCA_1:
                numeroMinas = 1;
                break;
            case Casilla.CERCA_2:
                numeroMinas = 2;
                break;
            case Casilla.CERCA_3:
                numeroMinas = 3;
                break;
            case Casilla.CERCA_4:
                numeroMinas = 4;
                break;
            case Casilla.CERCA_5:
                numeroMinas = 5;
                break;
            case Casilla.CERCA_6:
                numeroMinas = 6;
                break;
            case Casilla.CERCA_7:
                numeroMinas = 7;
                break;
            case Casilla.CERCA_8:
                numeroMinas = 8;
                break;
        }
        return numeroMinas;
    }

    /**
     * Este método se ejecuta cuando se hace click sobre alguno de los botones que representan las casillas.
     * @param evento - El evento del click sobre uno de los botones
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        String[] coordenada = comando.split( "," );
        int i = Integer.parseInt( coordenada[ 0 ] );
        int j = Integer.parseInt( coordenada[ 1 ] );

        ventanaPrincipal.jugar( i, j );
    }

}

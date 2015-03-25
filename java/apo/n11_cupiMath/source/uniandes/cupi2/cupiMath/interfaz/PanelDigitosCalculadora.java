/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_cupiMath
 * Autor: cupi2 - 15-mar-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiMath.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel para mostrar los dígitos de la calculadora
 */
public class PanelDigitosCalculadora extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    
    /**
     * El número de filas del layout del panel
     */
    private int NUM_FILAS=5;
    
    /**
     * El número de columnas del layout del panel
     */
    private int NUM_COLUMNAS=3;
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
	 * La ventana principal de la aplicación
	 */
	private InterfazCupiMath interfaz;
	
	
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
    /**
     * Construye un nuevo panel para mostrar los dígitos de la calculadora
     * @param ventanaPrincipal La ventana principal de la aplicación. ventanaPrincipal != null
     */
    public PanelDigitosCalculadora(InterfazCupiMath ventanaPrincipal)
    {
    	interfaz=ventanaPrincipal;
        setBorder( new TitledBorder( "Dígitos" ) );
        setLayout( new GridLayout( NUM_FILAS, NUM_COLUMNAS ) );
        setPreferredSize( new Dimension(300,200) );
        inicializarBotonesNumeros( );
    }
	

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Actualiza la expresión aritmética con el ítem recibido por parámetro
     * @param item El nuevo ítem para agregar a la expresión aritmética. item != null
     */
    public void actualizarExpresionAritmetica(String item)
    {
    	interfaz.actualizarExpresionAritmetica(item);
    }

    /**
     * Inicializa los botones de los números
     */
    public void inicializarBotonesNumeros( )
    {
        
        int contadorBotones = 1;
        for( int i = 0; i < NUM_FILAS; i++ )
        {
            for( int j = 0; j < NUM_COLUMNAS; j++ )
            {
                if( i == 3 && j == 0 )
                {
                    JButton btnDel = new JButton( "DEL" );
                    btnDel.addActionListener( this );
                    btnDel.setActionCommand( "DEL" );
                    add( btnDel );
                }
                
                else if(i == 3 && j == 2)
                {
                    JButton btnAc=new JButton("AC" );
                    btnAc.addActionListener( this );
                    btnAc.setActionCommand( "AC" );
                    add( btnAc );
                }
                else if( i == 3 && j == 1 )
                {
                    JButton btnCero = new JButton( "" + 0 );
                    btnCero.addActionListener( this );
                    btnCero.setActionCommand( "" + 0 );
                    add( btnCero );
                }
                else if(i==4)
                {
                    if(j==2)
                    {
                        JButton btnPuntoDecimal = new JButton( "." );
                        btnPuntoDecimal.addActionListener( this );
                        btnPuntoDecimal.setActionCommand( "." );
                        add( btnPuntoDecimal );
                    }
                    
                }
                else
                {
                    JButton btnNumero = new JButton( "" + contadorBotones );
                    btnNumero.addActionListener( this );
                    btnNumero.setActionCommand( "" + contadorBotones );
                    add( btnNumero );

                }
                contadorBotones++;

            }
        }
    }

    /**
     * Procesa el evento disparado por alguno de los botones
     */
    public void actionPerformed( ActionEvent e )
    {
        if(e.getActionCommand()!="DEL" && e.getActionCommand()!="AC")
        {
        	String item=(String)e.getActionCommand();
            actualizarExpresionAritmetica(item);
        }
        else if(e.getActionCommand().equals( "DEL"))
        {
        	interfaz.borrarItemExpresionAritmetica();
        }
        else if(e.getActionCommand().equals( "AC"))
        {
            interfaz.borrarExpresionAritmetica();
        }
    	
    }
}

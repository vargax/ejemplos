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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiMath.mundo.CupiMath;

/**
 * Panel que contiene los operadores de la calculadora
 */
public class PanelOperadores extends JPanel implements ActionListener
{
	
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
     * Construye el panel con botones para todos los operadores de la aplicación
     * @param ventanaPrincipal La ventana principal de la aplicación. ventanaPrincipal != null
     */
    public PanelOperadores(InterfazCupiMath ventanaPrincipal)
    {
    	interfaz=ventanaPrincipal;
        setLayout( new GridLayout( 3, 4 ) );
        setBorder( new TitledBorder("Operadores" ) );
        inicializarBotonesOperadores( );
    }
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Construye un panel con botones para cada uno de los operadores
     */
    public void inicializarBotonesOperadores( )
    {
        for(int i=0; i<CupiMath.OPERADORES_BINARIOS.length;i++)
        {
            JButton btnTemp=new JButton(CupiMath.OPERADORES_BINARIOS[i]);
            btnTemp.addActionListener( this );
            btnTemp.setActionCommand( CupiMath.OPERADORES_BINARIOS[i] );
            add( btnTemp );
        }
        
        for(int i=0; i<CupiMath.OPERADORES_UNARIOS.length;i++)
        {
            JButton btnTemp=new JButton(CupiMath.OPERADORES_UNARIOS[i]);
            btnTemp.addActionListener( this );
            btnTemp.setActionCommand( CupiMath.OPERADORES_UNARIOS[i] );
            add( btnTemp );
        }
        
        for(int i=0; i<CupiMath.TIPOS_PARENTESIS.length;i++)
        {
            JButton btnTemp=new JButton(CupiMath.TIPOS_PARENTESIS[i]);
            btnTemp.addActionListener( this );
            btnTemp.setActionCommand( CupiMath.TIPOS_PARENTESIS[i] );
            add( btnTemp );
        }

    }

    /**
     * Procesa el evento disparado por alguno de los botones.
     * @param e El evento del botón. e != null
     */
    public void actionPerformed( ActionEvent e )
    {
        String item=(String)e.getActionCommand();
        interfaz.actualizarExpresionAritmetica(item);
        
    }
    
    
}

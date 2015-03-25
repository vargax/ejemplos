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

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";
    
    /**
     * Evaluar una expresión aritméticas
     */
    private static final String EVALUAR="EVALUAR";
    
    /**
     * Expresar una expresión en notación polaca
     */
    private static final String NOTACION_POLACA="POLACA";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCupiMath principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /**
     * Botón Opción 1
     */
    private JButton btnOpcion1;

    /**
     * Botón Opción 2
     */
    private JButton btnOpcion2;
    
    /**
     * Botón para evaluar una expresión aritmética
     */
    private JButton btnEvaluar;
    
    /**
     * Botón para convertir la expresión en notación polaca
     */
    private JButton btnNotacionPolaca;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel de extensión
     * @param ventana La ventana principal de la aplicación. ventana != null
     */
    public PanelExtension( InterfazCupiMath ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 2 ) );

        //Botón evaluar
        btnEvaluar=new JButton("EXEC");
        btnEvaluar.setActionCommand( EVALUAR );
        btnEvaluar.addActionListener( this );
        add(btnEvaluar);
        
        //Botón notación polaca
        btnNotacionPolaca=new JButton("Notación polaca" );
        btnNotacionPolaca.setActionCommand( NOTACION_POLACA );
        btnNotacionPolaca.addActionListener( this );
        add( btnNotacionPolaca );
        
        
        //Botón opción 1
        btnOpcion1 = new JButton("Opción 1");
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add(btnOpcion1);
        
        //Botón opción 2
        btnOpcion2 = new JButton("Opción 2");
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add(btnOpcion2);
        
        
        
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento. e != null
     */
    public void actionPerformed( ActionEvent e )
    {
        if(OPCION_1.equals( e.getActionCommand() ))
        {
            principal.reqFuncOpcion1();
        }
        else if(OPCION_2.equals( e.getActionCommand() ))
        {
            principal.reqFuncOpcion2();
        }
        else if(EVALUAR.equals(e.getActionCommand( )))
        {
            principal.evaluarExpresionAritmetica();
        }
        else if(NOTACION_POLACA.equals( e.getActionCommand( ) ))
        {
            principal.convertirEnNotacionPolaca( );
        }
    }

}

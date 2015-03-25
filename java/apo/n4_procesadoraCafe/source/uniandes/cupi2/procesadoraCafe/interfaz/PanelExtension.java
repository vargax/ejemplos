/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_procesadoraCafe
 * Autor: Catalina Rodríguez - 01-sep-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.procesadoraCafe.interfaz;

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
     * Comando Producir Café
     */
    private static final String PRODUCIR = "Producir Café";
    
    /**
     * Comando Vender Café
     */
    private static final String VENDER = "Vender Café";
    
    /**
     * Comando Comprar Insumos
     */
    private static final String COMPRAR = "Comprar Insumo";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazProcesadoraCafe principal;

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
     * Botón Producir Café
     */
    private JButton btnProducir;
    
    /**
     * Botón Vender Café
     */
    private JButton btnVender;
    
    /**
     * Botón Comprar Insumos
     */
    private JButton btnComprar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelExtension( InterfazProcesadoraCafe ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 5 ) );

        //Botón Comprar
        btnComprar = new JButton(COMPRAR);
        btnComprar.setActionCommand(COMPRAR);
        btnComprar.addActionListener(this);
        add(btnComprar);
        
        //Botón Producir
        btnProducir = new JButton(PRODUCIR);
        btnProducir.setActionCommand(PRODUCIR);
        btnProducir.addActionListener(this);
        add(btnProducir);
        
        //Botón Vender
        btnVender = new JButton(VENDER);
        btnVender.setActionCommand(VENDER);
        btnVender.addActionListener(this);
        add(btnVender);
        
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
     * @param e Acción que generó el evento.
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
        else if(COMPRAR.equals( e.getActionCommand() ))
        {
        	principal.comprarInsumos();
        }
        else if(PRODUCIR.equals( e.getActionCommand() ))
        {
        	principal.producirCafe();
        }
        else if(VENDER.equals( e.getActionCommand() ))
        {
        	principal.venderCafe();
        }
    }

}

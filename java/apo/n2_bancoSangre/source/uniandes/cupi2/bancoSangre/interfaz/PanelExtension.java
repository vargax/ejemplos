/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_bancoSangre
 * Autor: Catalina Rodríguez - 11-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bancoSangre.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
     * Comando para registrar una donación
     */
    private static final String REGISTRAR_DONACION = "DONAR";
    
    /**
     * Comando para obtener el tipo de sangre con mayor disponibilidad
     */
    private static final String MAS_DISPONIBLE = "MAS DISPONIBLE";
    
    /**
     * Comando para obtener el tipo de sangre con menor disponibilidad
     */
    private static final String MENOS_DISPONIBLE = "MENOS DISPONIBLE";
    
    /**
     * Comando para obtener el tipo de sangre con mayor número de bolsas suministradas
     */
    private static final String MAS_SUMINISTRADA = "MAS SUMINISTRADA";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazBancoSangre principal;

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
     * Botón Donar
     */
    private JButton btnDonar;

    /**
     * Botón Más Disponible
     */
    private JButton btnMasDisponible;
    
    /**
     * Botón Menos Disponible
     */
    private JButton btnMenosDisponible;
    
    /**
     * Botón Más Suministrada
     */
    private JButton btnMasSuministrada;
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelExtension( InterfazBancoSangre ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new BorderLayout() );

        //Botón registrar donación
        btnDonar = new JButton("Registrar Donación");
        btnDonar.setActionCommand(REGISTRAR_DONACION);
        btnDonar.addActionListener(this);
        add(btnDonar, BorderLayout.WEST);
        
        JPanel exten = new JPanel();
        exten.setLayout(new GridLayout(1, 2));
        exten.setBorder( new EmptyBorder(5,0,0,0));
        
        //Botón opción 1
        btnOpcion1 = new JButton("Opción 1");
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        exten.add(btnOpcion1);
        
        //Botón opción 2
        btnOpcion2 = new JButton("Opción 2");
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        exten.add(btnOpcion2);
        
        add(exten, BorderLayout.SOUTH);
        
        JPanel busquedas = new JPanel();
        busquedas.setLayout(new GridLayout(3, 1));
        busquedas.setBorder( new EmptyBorder(0,5,0,0));
        
        //Botónes de búsquedas
        btnMasDisponible = new JButton("Tipo de sangre mayor disponibilidad");
        btnMasDisponible.setActionCommand(MAS_DISPONIBLE);
        btnMasDisponible.addActionListener(this);
        busquedas.add(btnMasDisponible);
        
        btnMenosDisponible = new JButton("Tipo de sangre menor disponibilidad");
        btnMenosDisponible.setActionCommand(MENOS_DISPONIBLE);
        btnMenosDisponible.addActionListener(this);
        busquedas.add(btnMenosDisponible);
        
        btnMasSuministrada = new JButton("Tipo de sangre más suministrada");
        btnMasSuministrada.setActionCommand(MAS_SUMINISTRADA);
        btnMasSuministrada.addActionListener(this);
        busquedas.add(btnMasSuministrada);
        
        add(busquedas, BorderLayout.CENTER);        
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
        else if(REGISTRAR_DONACION.equals( e.getActionCommand() ))
        {
        	DialogoRegistrarDonacion drd = new DialogoRegistrarDonacion(principal);
        	drd.setVisible(true);
        }
        else if(MAS_DISPONIBLE.equals( e.getActionCommand() ))
        {
        	principal.obtenerTipoMayorDisponibilidad();
        }
        else if(MENOS_DISPONIBLE.equals( e.getActionCommand() ))
        {
        	principal.obtenerTipoMenorDisponibilidad();
        }
        else if(MAS_SUMINISTRADA.equals( e.getActionCommand() ))
        {
        	principal.obtenerTipoMasSuministrado();
        }
    }

}

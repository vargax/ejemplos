package uniandes.cupi2.bodyCupi2.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 * Desplega las horas de entrada, salida y el tiempo de ejercicio
 * de un usuario durante un día
 */
public class PanelRegistroTiempo extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    
    /**
     * Constante asociada al botón para retroceder al anterior registro de tiempo
     */
    private final static String REGISTRO_ANTERIOR="REGISTRO ANTERIOR";
    
    /**
     * Constante asociada al botón para avanzar al siguiente registro de tiempo
     */
    private final static String REGISTRO_SIGUIENTE="REGISTRO SIGUIENTE";
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * El ID del registro de tiempo que se está mostrando
     */
    private String idRegistroTiempo;
    
    /**
     * La ventana principal de la aplicación
     */
    private InterfazBodyCupi2 interfaz;
    
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
    
    /**
     * Etiqueta con el titulo del panel
     */
    private JLabel lblTituloPanel;
    
    /**
     * Etiqueta id usuario
     */
    private JLabel lblIdUsuario;
    
    /**
     * Texto id usuario
     */
    private JTextField txtIdUsuario;
    
    /**
     * Etiqueta hora ingreso
     */
    private JLabel lblHoraIngreso;
    
    /**
     * Texto hora ingreso
     */
    private JTextField txtHoraIngreso;
    
    /**
     * Etiqueta hora salida
     */
    private JLabel lblHoraSalida;
    
    /**
     * Texto hora salida
     */
    private JTextField txtHoraSalida;
    
    /**
     * Etiqueta tiempo de ejercicio
     */
    private JLabel lblTiempoEjercicio;
    
    /**
     * Texto tiempo de ejercicio
     */
    private JTextField txtTiempoEjercicio;
    
    /**
     * Botón para avanzar al siguiente registro de tiempo
     */
    private JButton btnSiguiente;
    
    /**
     * Botón para retroceder al anterior registro de tiempo
     */
    private JButton btnAnterior;
    


    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Construye un nuevo panel con la información del usuario
     * Inicializa idRegistroTiempo=""
     * @param ventanaPrincipal La ventana principal de la aplicación
     */
    public PanelRegistroTiempo(InterfazBodyCupi2 ventanaPrincipal)
    {
        
        interfaz=ventanaPrincipal;
        idRegistroTiempo="";
        setLayout( new GridBagLayout() );
        setBorder( new TitledBorder( "Detalles Registro Tiempo" ) );
        lblTituloPanel=new JLabel();
        lblHoraIngreso=new JLabel("Hora ingreso");
        lblHoraSalida=new JLabel("Hora salida");
        lblIdUsuario=new JLabel( "Id usuario");
        txtHoraIngreso=new JTextField( );
        txtHoraIngreso.setEditable( false );
        txtHoraSalida=new JTextField( );
        txtHoraSalida.setEditable( false );
        txtIdUsuario=new JTextField( );
        txtIdUsuario.setEditable( false );
        lblTiempoEjercicio=new JLabel("Duración ejercicio");
        txtTiempoEjercicio=new JTextField( );
        txtTiempoEjercicio.setEditable( false );
        
        GridBagConstraints constraints= new GridBagConstraints( 0, 0, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets( 2, 2, 2, 2 ), 0, 0 );
        add(lblTituloPanel,constraints);

        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        
        add(lblIdUsuario,constraints);
        
        constraints.gridx=1;
        constraints.gridwidth=2;
        constraints.weightx=1;
        add(txtIdUsuario,constraints);
        
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.weightx=0;
        add(lblHoraIngreso,constraints);
        
        constraints.gridx=1;
        constraints.gridwidth=2;
        add(txtHoraIngreso,constraints);
        
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=1;
        add(lblHoraSalida,constraints);
        
        constraints.gridx=1;
        constraints.gridwidth=2;
        add(txtHoraSalida,constraints);
        
        constraints.gridx=0;
        constraints.gridy=5;
        constraints.gridwidth=1;
        add(lblTiempoEjercicio,constraints);
        
        constraints.gridx=1;
        constraints.gridwidth=2;
        add(txtTiempoEjercicio,constraints);
        
        constraints.gridx=0;
        constraints.gridy=6;
        constraints.gridwidth=1;
        constraints.anchor=GridBagConstraints.FIRST_LINE_START;
        constraints.fill=GridBagConstraints.NONE;
        btnAnterior=new JButton("<<");
        btnAnterior.addActionListener( this );
        btnAnterior.setActionCommand( REGISTRO_ANTERIOR );
        add(btnAnterior,constraints);
        
        constraints.gridx=1;
        btnSiguiente=new JButton(">>");
        btnSiguiente.addActionListener( this );
        btnSiguiente.setActionCommand( REGISTRO_SIGUIENTE );
        add(btnSiguiente,constraints);
    }


    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    
    /**
     * Actualiza el panel con la información de ingreso de un usuario
     * @param elIdUsuario El id del usuario
     * @param elIdRegistroTiempo EL ID del registro de tiempo
     * @param horaIngreso La hora de ingreso del usuario al gimnasio
     * @param horaSalida La hora de salida del usuario al gimnasio
     * @param tiempoEjercicio El tiempo de ejercicio
     */
    public void actualizar(int elIdUsuario,String elIdRegistroTiempo, String horaIngreso, String horaSalida, String tiempoEjercicio )
    {
        idRegistroTiempo=elIdRegistroTiempo;
        txtIdUsuario.setText( ""+ elIdUsuario );
        txtHoraIngreso.setText( horaIngreso );
        txtHoraSalida.setText( horaSalida );
        txtTiempoEjercicio.setText( tiempoEjercicio );        
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
       if(e.getActionCommand( ).equals( REGISTRO_ANTERIOR ))
       {   
           interfaz.actualizarRegistroTiempoAnterior(idRegistroTiempo);
       }
       else if(e.getActionCommand( ).equals( REGISTRO_SIGUIENTE ))
       {
           interfaz.actualizarRegistroTiempoSiguiente(  idRegistroTiempo );
       }
        
    }
    
    
    

}

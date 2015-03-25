package uniandes.cupi2.bodyCupi2.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Panel con la información de un usuario
 */
public class PanelDetallesUsuario extends JPanel implements ActionListener
{
	
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	
	/**
	 * Constante asociada al botón encargado de pasar al siguiente usuario
	 */
	private final static String USUARIO_SIGUIENTE=">>";
	
	/**
	 * Constante asociada al botón encargado de pasar al usuario anterior
	 */
	private final static String USUARIO_ANTERIOR="<<";
	
	
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	/**
	 * El ID del usuario que se está mostrando
	 */
	private int idUsuario;
	
	/**
	 * La ventana principal de la aplicación
	 */
	private InterfazBodyCupi2 interfaz;
	
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	
	/**
	 * La foto del usuario
	 */
	private JLabel lblFoto;
	
	/**
	 * Icono con la foto del usuario
	 */
	private ImageIcon iconoFoto;
	
	 /**
     * Etiqueta cédula del usuario
     */
    private JLabel lblId;
    
    /**
     * Campo de texto cédula del usuario
     */
    private JTextField txtId;
    
    /**
     * Etiqueta nombre
     */
    private JLabel lblNombre;
    
    /**
     * Etiqueta edad
     */
    private JLabel lblEdad;
    
    /**
     * Campo de texto edad
     */
    private JTextField txtEdad;
    
    /**
     * Etiqueta teléfono
     */
    private JLabel lblTelefono;
    
    /**
     * Campo de texto teléfono
     */
    private JTextField txtTelefono;
    
    /**
     * Etiqueta género
     */
    private JLabel lblGenero;
    
    /**
     * Campo de texto género
     */
    private JTextField txtGenero;
    
    /**
     * Campo de texto nombre
     */
    private JTextField txtNombre;
    
    /**
     * Area de texto historia medica
     */
    private JTextArea txtAreaHistoriaMedica;
    
    /**
     * Panel que contiene al área de texto
     */
    private JScrollPane panelAreaTextoHistoriaMedica;
    
    /**
     * Botón para el usuario anterior
     */
    private JButton btnAnterior;
    
    /**
     * Botón para el usuario siguiente
     */
    private JButton btnSiguiente;
    
    /**
     * Panel para insertar los datos simples del usuario
     */
    private JPanel panelDatosSimplesUsuario;
    
    /**
     * El panel con la foto del usuario
     */
    private JPanel panelFoto;
    
    /**
     * Panel con los botones para ir al usuario anterior o siguiente
     */
    private JPanel panelBotones;
    
	
	
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
    
    
    /**
     * Construye un nuevo panel para mostrar los detalles de un usuario
     * idUsuario se inicializa con -1
     * @param ventanaPrincipal La ventana principal de la aplicación
     */
    public PanelDetallesUsuario(InterfazBodyCupi2 ventanaPrincipal)
    {
    	interfaz=ventanaPrincipal;
    	int idUsuario=-1;
    	setBorder(new TitledBorder("Usuario"));
    	setLayout( new BorderLayout( ) );
    	setPreferredSize( new Dimension( 200, 0 ) );
    	lblId=new JLabel("Id:");
    	txtId=new JTextField();
    	txtId.setEditable( false );
    	lblNombre=new JLabel("Nombre:");
    	txtNombre=new JTextField();
    	txtNombre.setEditable( false );
    	lblEdad=new JLabel("Edad:");
    	txtEdad=new JTextField();
    	txtEdad.setEditable( false );
    	lblGenero=new JLabel("Género:");
    	txtGenero=new JTextField();
    	txtGenero.setEditable( false );
    	lblTelefono=new JLabel("Teléfono:");
    	txtTelefono=new JTextField();
    	txtTelefono.setEditable( false );
    	txtAreaHistoriaMedica=new JTextArea();
    	txtAreaHistoriaMedica.setEditable( false );
    	panelAreaTextoHistoriaMedica=new JScrollPane( txtAreaHistoriaMedica);
        panelAreaTextoHistoriaMedica.setBorder( new TitledBorder( "Historia Médica" ) );
        btnAnterior=new JButton(USUARIO_ANTERIOR);
        btnAnterior.addActionListener(this);
        btnAnterior.setActionCommand(USUARIO_ANTERIOR);
        btnSiguiente=new JButton(USUARIO_SIGUIENTE);
        btnSiguiente.addActionListener(this);
        btnSiguiente.setActionCommand(USUARIO_SIGUIENTE);
        lblFoto=new JLabel();
        iconoFoto=new ImageIcon();
        lblFoto.setIcon( iconoFoto );
        
        panelDatosSimplesUsuario=new JPanel();
        panelDatosSimplesUsuario.setLayout(new GridLayout(5,2));
        panelDatosSimplesUsuario.setBorder( new TitledBorder( "Detalles" ) );
        panelDatosSimplesUsuario.add(lblId);
        panelDatosSimplesUsuario.add(txtId);
        panelDatosSimplesUsuario.add(lblNombre);
        panelDatosSimplesUsuario.add(txtNombre);
        panelDatosSimplesUsuario.add(lblEdad);
        panelDatosSimplesUsuario.add(txtEdad);
        panelDatosSimplesUsuario.add(lblGenero);
        panelDatosSimplesUsuario.add(txtGenero);
        panelDatosSimplesUsuario.add(lblTelefono);
        panelDatosSimplesUsuario.add(txtTelefono);
        
        panelBotones=new JPanel();
        panelBotones.setBorder( new TitledBorder( "Navegar usuarios" ) );
        panelBotones.setLayout(new GridLayout(1,2));
        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);
        add(panelBotones,BorderLayout.SOUTH);
        
        panelFoto=new JPanel();
        panelFoto.add(lblFoto);
        panelFoto.setBorder( new TitledBorder( "Foto" ) );
        add(panelFoto,BorderLayout.NORTH);
        
        JPanel panelCentral=new JPanel();
        panelCentral.setLayout( new BorderLayout( ) );
        add(panelCentral, BorderLayout.CENTER);
        panelCentral.add(panelDatosSimplesUsuario,BorderLayout.NORTH);
        panelCentral.add( panelAreaTextoHistoriaMedica, BorderLayout.CENTER);        
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Retorn el id del usuario
     * @return El id del usuario
     */
    public int darIdusuario()
    { 
       return idUsuario;
    }
    
    /**
     * Actualiza el panel con los datos de un usuario
     * @param elId El ID del usuario
     * @param elNombre El nombre del usuario
     * @param laEdad La edad del usuario
     * @param elGenero El genero del usuario
     * @param elTelefono El teléfono del usuario
     * @param laHIstoriaMedica  La historia médica del usuario
     * @param laFoto La foto del usuario
     */
    public void refrescar(int elId, String elNombre, int laEdad, String elGenero, int elTelefono, String laHIstoriaMedica, String laFoto)
    {
        idUsuario=elId;
        txtId.setText(""+elId);
        txtNombre.setText( elNombre );
        txtEdad.setText( ""+laEdad );
        txtGenero.setText( elGenero );
        txtTelefono.setText( ""+elTelefono );
        txtAreaHistoriaMedica.setText( laHIstoriaMedica );
        iconoFoto=new ImageIcon( laFoto);
        lblFoto.setIcon( iconoFoto );
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand( ).equals( USUARIO_ANTERIOR ))
        {   
		    interfaz.pasarUsuarioAnterior(idUsuario);
		}
		else if(e.getActionCommand( ).equals( USUARIO_SIGUIENTE ))
		{
		    interfaz.pasarSiguienteUsuario( idUsuario );		    
		}
		
	}
    
    


}

package uniandes.cupi2.bodyCupi2.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uniandes.cupi2.bodyCupi2.mundo.Suscripcion;
import uniandes.cupi2.bodyCupi2.mundo.Usuario;

/**
 * Panel interior de la clase DialogoCreacionUsuario
 */
public class PanelCreacionUsuario extends JPanel implements ActionListener
{
    
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Constante asociada a la serialización
     */
    private static final long serialVersionUID = 5575599636355378330L;

    /**
     * Constante asociada al comando del botón usado para crear un usuario
     */
    private final static String CREAR_USUARIO="Crear usuario";
    
    /**
     * Constante asociada al comando del botón usado para seleccionar una foto
     */
    private final static String SELECCIONAR_FOTO="Seleccionar foto";
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La ventana principal de la aplicación
     */
    private InterfazBodyCupi2 interfaz;
    
    /**
     * El dia en que comienza la suscripción
     */
    private int dia;
    
    /**
     * El mes en que comienza la suscripción
     */
    private int mes;
    
    /**
     * El año en que comienza la suscripción
     */
    private int anio;
    
    /**
     * La ruta de la foto del usuario
     */
    private String rutaFoto;
    
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
    
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
     * Combobox género
     */
    private JComboBox comboGenero;
    
    /**
     * Etiqueta Foto
     */
    private JLabel lblFoto;
    
    /**
     * Campo de texto Foto
     */
    private JTextField txtFoto;
    
    /**
     * Botón Foto
     */
    private JButton btnFoto;  
    
    /**
     * Campo de texto nombre
     */
    private JTextField txtNombre;
    
    /**
     * Etiqueta historia medica
     */
    private JLabel lblHistoriaMedica;
    
    /**
     * Area de texto historia medica
     */
    private JTextArea txtAreaHistoriaMedica;
    
    /**
     * La etiqueta tipo de suscripción
     */
    private JLabel lblTipoSuscripcion;
    
    /**
     * Combobox con los tipos de suscripciones
     */
    private JComboBox comboTipoSuscripciones;
    
    /**
     * Panel auxiliar para mostrar elementos con tamaños similares 
     */
    private JPanel panelEtiquetas;
    
    /**
     * Panel que contiene al área de texto
     */
    private JScrollPane panelAreaTextoHistoriaMedica;
    
    /**
     * Panel con el botón para crear al usuario
     */
    private JPanel panelBoton;
    
    /**
     * Botón para la creación de un usuario
     */
    private JButton btnCrearUsuario;
    
    /**
     * Panel para agregar la fecha de inicio de la suscripción
     */
    private PanelFechaInicioSuscripcion panelFechaInicioSuscripcion;
    
    /**
     * Panel que contiene al panel del calendario y del botón 
     */
    private JPanel panelContenedorSur;
    
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
   /**
    * Crea un nuevo panel para ingresar la informacion del usuario, <br>
    * y seleccionar el tipo y la fecha de inicio de la suscripción.
    * Inicializa el atributo rutaFoto como una cadena de caracteres vacía
    * @param ventanaPrincipal La ventana principal de la aplicación 
    */
   public PanelCreacionUsuario(InterfazBodyCupi2 ventanaPrincipal)
   {
       interfaz=ventanaPrincipal;
       lblId= new JLabel( "Id (cédula):");
       txtId=new JTextField( );
       lblNombre=new JLabel("Nombre:" );
       txtNombre=new JTextField( );
       lblHistoriaMedica= new JLabel("Historia médica:");
       txtAreaHistoriaMedica= new JTextArea( );
       txtAreaHistoriaMedica.setLineWrap( true );
       txtAreaHistoriaMedica.setWrapStyleWord( true );
       comboTipoSuscripciones=new JComboBox( );
       btnCrearUsuario=new JButton("Crear" );
       btnCrearUsuario.addActionListener( this );
       btnCrearUsuario.setActionCommand( CREAR_USUARIO );
       panelAreaTextoHistoriaMedica=new JScrollPane( txtAreaHistoriaMedica);
       panelAreaTextoHistoriaMedica.setPreferredSize( new Dimension(200,50) );
       lblEdad= new JLabel("Edad:");
       txtEdad=new JTextField();
       lblTelefono=new JLabel("Teléfono:");
       txtTelefono= new JTextField();
       lblGenero= new JLabel("Género:");
       comboGenero=new JComboBox();
       comboGenero.addItem(Usuario.GENERO_MASCULINO);
       comboGenero.addItem(Usuario.GENERO_FEMENINO);
       lblFoto=new JLabel("Foto:");
       txtFoto=new JTextField();
       txtFoto.setEditable(false);
       btnFoto=new JButton("Seleccionar imagen");
       btnFoto.addActionListener(this);
       btnFoto.setActionCommand(SELECCIONAR_FOTO);
       panelBoton=new JPanel( );
       panelBoton.setLayout( new BorderLayout( ) );
       panelBoton.add( BorderLayout.CENTER,btnCrearUsuario );
       lblTipoSuscripcion=new JLabel("Tipo suscripción:");
       comboTipoSuscripciones=new JComboBox();
       comboTipoSuscripciones.addItem( Suscripcion.SUSCRIPCION_AMATEUR );
       comboTipoSuscripciones.addItem( Suscripcion.SUSCRIPCION_REGULAR );
       comboTipoSuscripciones.addItem( Suscripcion.SUSCRIPCION_MASTER );
       
       
       setLayout( new BorderLayout( ) );
       
       panelEtiquetas=new JPanel();
       panelEtiquetas.setLayout( new GridLayout(9,2 ) );
       panelEtiquetas.add(lblId);
       panelEtiquetas.add(txtId);
       panelEtiquetas.add(lblNombre);
       panelEtiquetas.add(txtNombre);
       panelEtiquetas.add(lblEdad);
       panelEtiquetas.add(txtEdad);
       panelEtiquetas.add(lblTelefono);
       panelEtiquetas.add(txtTelefono);
       panelEtiquetas.add(lblGenero);
       panelEtiquetas.add(comboGenero);
       panelEtiquetas.add(lblFoto);
       panelEtiquetas.add(txtFoto);
       panelEtiquetas.add(new JLabel());
       panelEtiquetas.add(btnFoto);
       
       panelEtiquetas.add(lblTipoSuscripcion);
       panelEtiquetas.add(comboTipoSuscripciones);
       panelEtiquetas.add(lblHistoriaMedica);
       
       panelFechaInicioSuscripcion= new PanelFechaInicioSuscripcion( this );
       
       panelContenedorSur=new JPanel();
       panelContenedorSur.setLayout( new BorderLayout( ) );
       panelContenedorSur.add( panelFechaInicioSuscripcion, BorderLayout.CENTER );
       panelContenedorSur.add( panelBoton, BorderLayout.SOUTH );
       
       add(panelEtiquetas,BorderLayout.NORTH);
       add(panelAreaTextoHistoriaMedica,BorderLayout.CENTER);
       add(panelContenedorSur, BorderLayout.SOUTH);
       
       rutaFoto="";
    
       
   }


   //-----------------------------------------------------------------
   // Metodos
   //-----------------------------------------------------------------
   
   
   /**
    * Método llamado por el botón encargado de seleccionar la fecha de inicio de la suscripción.
    * Lanza excepciones en caso que:
    * a. No se llenen todos los campos del usuario
    * b. No se ingrese la fecha del inicio de la suscripción
    * c. El id del usuario, la edad y el teléfono no sean valores numéricos
    * d. No se seleccione una imagen
    * @param elAnio El año en que comienza la suscripción
    * @param elMes El mes en que comienza la suscripción
    * @param elDia El dia en que comienza la suscripción 
    */
   public void actualizarFechaReserva(int elAnio, int elMes, int elDia)
   {
       anio=elAnio;
       mes=elMes;
       dia=elDia;
   }

   /**
    * Atiende el evento generado por el botón para crear el usuario
    */
    public void actionPerformed( ActionEvent e )
    {
        if(e.getActionCommand( ).equals( CREAR_USUARIO ))
        {
        	if(txtId.getText( ).equals( "" )||txtEdad.getText().equals( "" )||txtTelefono.getText().equals( "" )||txtFoto.getText().equals( "" )||txtNombre.getText( ).equals( "" )||txtAreaHistoriaMedica.getText( ).equals( "" ))
        	{
        		JOptionPane.showMessageDialog( this, "Debe llenar todos los campos del usuario", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
        	}
        	
        	if(dia==0||mes==0||anio==0)
        	{
        		JOptionPane.showMessageDialog( this, "Debe ingresar la fecha de la suscripción", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
        	}
            String id=txtId.getText( );
            int valorid=-1;
            try
            {
                valorid=Integer.parseInt( id );
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog( this, "El id debe ser un valor numérico", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }
            
            String edad=txtEdad.getText();
            int valorEdad=-1;
            try
            {
            	valorEdad=Integer.parseInt(edad);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog( this, "La edad debe ser un valor numérico", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }
            
            String telefono=txtTelefono.getText();
            int valorTelefono=-1;
            try
            {
            	valorTelefono=Integer.parseInt(telefono);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog( this, "El teléfono debe ser un valor numérico", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }
            
            String genero=(String)comboGenero.getSelectedItem();
            String foto=rutaFoto;
            String nombre=txtNombre.getText( );
            String tipoSuscripcion=(String)comboTipoSuscripciones.getSelectedItem( );
            String historiaMedica=txtAreaHistoriaMedica.getText( );
            interfaz.guardarDatosUsuario( valorid, nombre, valorEdad, valorTelefono, genero,foto ,historiaMedica,tipoSuscripcion,  anio, mes, dia,0,0 );
            
        }
        
        else if(e.getActionCommand( ).equals( SELECCIONAR_FOTO ))
        {
            
            JFileChooser chooser = new JFileChooser( );
            chooser.setCurrentDirectory( new java.io.File( "./data/imagenes/usuarios" ) );
            chooser.setDialogTitle( "Seleccionar imagen" );
            chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
            chooser.setVisible( true );

            if( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
            {

                File archivoImagen = chooser.getSelectedFile( );
                try
                {
                    rutaFoto = archivoImagen.getCanonicalPath( );
                    
                    String nombreImagen = archivoImagen.getName( );
                    txtFoto.setText( nombreImagen );
                }
                catch( IOException e1 )
                {
                    JOptionPane.showMessageDialog( this, e1.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }

            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe seleccionar una imagen", "Info", JOptionPane.INFORMATION_MESSAGE );

            }
        }
        

        
    }

    
}

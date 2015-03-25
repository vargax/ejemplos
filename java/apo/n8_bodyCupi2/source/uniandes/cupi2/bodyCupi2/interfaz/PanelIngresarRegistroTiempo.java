package uniandes.cupi2.bodyCupi2.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


/**
 * Panel para registrar la entrada y salida de un usuario al gimnasio
 */
public class PanelIngresarRegistroTiempo extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    
    /**
     * Constante asociada al botón que registra una entrada
     */
    private final static String REGISTRAR_ENTRADA="ENTRADA";
    
    /**
     * Constante asociada al botón que registra una salida
     */
    private final static String REGISTRAR_SALIDA="SALIDA";
    
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La ventana principal de la aplicación
     */
    private InterfazBodyCupi2 interfaz;
    
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
    
    /**
     * La etiqueta del id del usuario
     */
    private JLabel lblIdUsuario;
    
    /**
     * El campo de texto del id del usuario
     */
    private JTextField txtIdUsuario;
    
    /**
     * El botón registrar tiempo de entrada de un usuario
     */
    private JButton btnRegistrarEntrada;
    
    /**
     * El botón registrar tiempo de salida de un usuario
     */
    private JButton btnRegistrarSalida;
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    
    /**
     * Construye un nuevo panel para los registros de tiempo del usuario
     * @param ventanaPrincipal La ventana principal de la aplicación
     */
    public PanelIngresarRegistroTiempo(InterfazBodyCupi2 ventanaPrincipal)
    {
        interfaz=ventanaPrincipal;
        lblIdUsuario=new JLabel("Id usuario");
        txtIdUsuario=new JTextField( );
        btnRegistrarEntrada= new JButton("Registrar Entrada");
        btnRegistrarEntrada.setActionCommand( REGISTRAR_ENTRADA );
        btnRegistrarEntrada.addActionListener( this );
        btnRegistrarSalida= new JButton("Registrar Salida");
        btnRegistrarSalida.setActionCommand( REGISTRAR_SALIDA );
        btnRegistrarSalida.addActionListener( this );
        setBorder(new TitledBorder( "Ingresar registro tiempo" ));
        setLayout( new GridBagLayout() );
        GridBagConstraints constraints= new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets( 2, 2, 2, 2 ), 0, 0 );
        add(lblIdUsuario, constraints);
        
        constraints.gridx=1;
        constraints.gridwidth=3;
        
        add(txtIdUsuario, constraints);
        
        constraints.gridy=1;
        constraints.gridx=0;
        constraints.gridwidth=2;
        
        add(btnRegistrarEntrada, constraints);
        
        constraints.gridx=2;
        
        add(btnRegistrarSalida, constraints);
        
        
    }
    
    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------
    
    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if(REGISTRAR_ENTRADA.equals( e.getActionCommand( ) ))
        {
            int idUsuario=-1;
            try
            {
                idUsuario=Integer.parseInt( txtIdUsuario.getText( ) );
                
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog( this, "El id debe ser un valor numérico", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }
            
            interfaz.registrarEntrada(idUsuario);
            
            
            
        }
        
        else if(REGISTRAR_SALIDA.equals( e.getActionCommand( ) ))
        {
            int idUsuario=-1;
            try
            {
                idUsuario=Integer.parseInt( txtIdUsuario.getText( ) );
                
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog( this, "El id debe ser un valor numérico", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }
            
            interfaz.registrarSalida(idUsuario);
            
        }
        
    }
    
}

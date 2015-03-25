package uniandes.cupi2.bodyCupi2.interfaz;

import java.awt.BorderLayout;

import javax.swing.JDialog;

/**
 * Dialogo para ingresar un nuevo usuario a la aplicación
 *
 */
public class DialogoCreacionUsuario extends JDialog
{
    
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    
    /**
     * Constante asociada a la serialización de la aplicación
     */
    private static final long serialVersionUID = 1821922737011445114L;
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * La ventana principal de la aplicacion
     */
    private InterfazBodyCupi2 interfaz;
    
    
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
    
    /**
     * Panel para la creación del usuario
     */
    private PanelCreacionUsuario panelCreacionUsuario;
    
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Construye un diálogo para crear un usuario
     * @param ventanaPrincipal La ventana principal de la aplicación
     */
    public DialogoCreacionUsuario(InterfazBodyCupi2 ventanaPrincipal)
    {
        super(ventanaPrincipal,true);
        interfaz=ventanaPrincipal;
        setSize(380,590);
        setTitle( "Crear usuario" );
        setLayout( new BorderLayout( ) );
        panelCreacionUsuario=new PanelCreacionUsuario(interfaz);
        add(panelCreacionUsuario,BorderLayout.CENTER);
        panelCreacionUsuario.setVisible( true );
        setLocationRelativeTo(null);
    }
    


}

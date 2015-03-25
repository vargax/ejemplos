package uniandes.cupi2.toDoList.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogoOpcion2 extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InterfazToDoList principal;
	
	private JTextField limiteInferior;
    private JTextField limiteSuperior;
    
    private JButton btnEliminar;

	public DialogoOpcion2( InterfazToDoList ventana )
    {
        principal = ventana;
        setTitle( "Eliminar Categorias" );
        setSize( 250, 150 );
        
        limiteInferior = new JTextField();
        limiteSuperior = new JTextField();
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));
        
        panel.add(new JLabel("Limite Inferior"));
        panel.add(limiteInferior);
        
        panel.add(new JLabel("Limite Superior"));
        panel.add(limiteSuperior);
        
        btnEliminar = new JButton( "Eliminar Categorias" );
        btnEliminar.addActionListener( this );
        btnEliminar.setActionCommand( "eliminar" );
        panel.add(btnEliminar);
        
        add(panel);
        
    }
	
	public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( "eliminar" ) )
        {
            principal.eliminarCategorias(limiteInferior.getText(), limiteSuperior.getText());
        }
    }

}

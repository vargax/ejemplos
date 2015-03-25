package uniandes.cupi2.toDoList.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class DialogoCerrar extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private InterfazToDoList principal;
	
	private JRadioButton guardar;
    private JRadioButton noGuardar;
    
    private JButton btnSalir;

	public DialogoCerrar( InterfazToDoList ventana )
    {
        principal = ventana;
        setTitle( "Examen" );
        setSize( 150, 100 );
        
        guardar = new JRadioButton("Salir guardando");
        noGuardar = new JRadioButton("Salir sin guardar");
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        panel.add(guardar,BorderLayout.EAST);
        panel.add(noGuardar,BorderLayout.WEST);
                
        btnSalir = new JButton( "Salir" );
        btnSalir.addActionListener( this );
        btnSalir.setActionCommand( "salir" );
        panel.add(btnSalir);
        
        add(panel);
        
    }
	
	public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( "salir" ) )
        {
            //principal.eliminarCategorias(limiteInferior.getText(), limiteSuperior.getText());
        }
    }
}

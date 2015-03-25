package uniandes.cupi2.bancoSangre.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.bancoSangre.mundo.TipoSangre;

/**
 * Panel en el que se muestra la información del tipo de sangre
 */
public class PanelTipoSangre extends JPanel implements ActionListener
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	
	/**
	 * Comando Suministrar sangre
	 */
	private static final String SUMINISTRAR = "Suministrar";
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicación
	 */
	private InterfazBancoSangre principal;
	
	/**
	 * Tipo de sangre
	 */
	private String tipoSangre;
	
	/**
	 * Factor RH
	 */
	private String rh;
	
    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

	/**
	 * Etiqueta tipo de sangre
	 */
	private JLabel etiquetaTipo;
	
	/**
	 * Etiqueta número de bolsas disponibles
	 */
	private JLabel etiquetaDisponibles;
	
	/**
	 * Etiqueta número de bolsas suministradas
	 */
	private JLabel etiquetaSuministradas;
	
	/**
	 * Botón suministrar bolsas
	 */
	private JButton btnDespachar;
	
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

	/**
	 * Constructor del panel
	 * @param laPrincipal Ventana principal
	 * @param tipoSangreP Tipo de sangre
	 * @param rhP Factor RH
	 */
	public PanelTipoSangre(InterfazBancoSangre laPrincipal, String tipoSangreP, String rhP)
	{
		setLayout(new GridLayout(4, 1));
		setBorder(new TitledBorder( "Tipo de Sangre " + tipoSangreP+rhP));
		tipoSangre = tipoSangreP;
		rh = rhP;
		principal = laPrincipal;
		
		etiquetaTipo = new JLabel(tipoSangre + " " + rh);
		etiquetaTipo.setHorizontalAlignment(JLabel.CENTER);
		etiquetaTipo.setForeground(Color.RED);
		etiquetaTipo.setFont(new Font("Arial", Font.BOLD, 30));
		add(etiquetaTipo);
		
		etiquetaDisponibles = new JLabel("Disponibles: ");
		add(etiquetaDisponibles);
		
		etiquetaSuministradas = new JLabel("Despachadas: ");
		add(etiquetaSuministradas);
		
		btnDespachar = new JButton(SUMINISTRAR);
		btnDespachar.setActionCommand(SUMINISTRAR);
		btnDespachar.addActionListener(this);
		add(btnDespachar);
	}

	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

	/**
	 * Actualiza la información del tipo de sangre
	 * @param tipo Tipo de sangre
	 */
	public void actualizar(TipoSangre tipo)
	{
		etiquetaSuministradas.setText("Suministradas: "+tipo.darCantidadSuministrada());
		etiquetaDisponibles.setText("Disponibles: "+tipo.darCantidadDisponible());
	}
	
    /**
     * Manejo de eventos del usuario
     * @param e Evento de usuario. e != null.
     */
	public void actionPerformed(ActionEvent e) 
	{
		String evento = e.getActionCommand();
		if(evento.equals(SUMINISTRAR))
		{
			principal.suministrar(tipoSangre, rh);
		}
	}
}

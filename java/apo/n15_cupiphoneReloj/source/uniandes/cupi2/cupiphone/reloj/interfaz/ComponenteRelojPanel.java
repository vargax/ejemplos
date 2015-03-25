package uniandes.cupi2.cupiphone.reloj.interfaz;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLabel;

import uniandes.cupi2.cupIphone.core.ICore;
import uniandes.cupi2.cupiphone.reloj.mundo.Reloj;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel principal del componente.
 */
public class ComponenteRelojPanel extends JPanel implements Observer
{

	private JPanel panelReloj;
	private JLabel labelFecha;	
	private Reloj mundo;

	/**
	 * Constructor del Panel.
	 * @param reloj Mundo del componente.
	 */
	public ComponenteRelojPanel( Reloj reloj) 
	{
		mundo = reloj;
		mundo.addObserver(this);
		initialize();
	}

	/**
	 * Inicializa el panel que presenta el reloj
	 */
	private void initialize() 
	{
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 0;
		this.setSize(300, 293);
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.black);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.ipadx = 18;
		gridBagConstraints.ipady = 19;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(11, 0, 10, 0);
		gridBagConstraints.gridy = 0;
		labelFecha = new JLabel("");
		labelFecha.setForeground(Color.red);
		labelFecha.setFont(new Font(Font.MONOSPACED, Font.BOLD | Font.ITALIC, 20));
		panelReloj = new JPanel();
		panelReloj.setLayout(new GridBagLayout());
		panelReloj.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		panelReloj.setBackground(Color.black);
		panelReloj.add(labelFecha, gridBagConstraints);
		
		this.add(panelReloj, gridBagConstraints1);
	}

	/**
	 * Actualiza la informaci—n del reloj cuando cambia el mundo
	 */
	public void update(Observable arg0, Object arg1) {
	    labelFecha.setText((String)arg1);
		
	}

} 

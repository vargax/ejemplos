package uniandes.cupi2.calculadoraPunnett.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PanelCalculadora extends JPanel implements ActionListener {
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	public final static String CREAR = "crear";
	public final static String CALCULAR = "calcular";
	public final static String BORRAR = "borrar";
	//-----------------------------------------------------------------
    // Atributos
	//-----------------------------------------------------------------
	/** Ventana principal del programa **/
	private InterfacePunnett principal;
	//-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	/** JLabel para la esquina superior izquierda del cuadro **/
	private JLabel labSupIzq;
	/** Arreglo de JLabels para las combinaciones de la madre (horizontal) **/
	private JLabel[] labCombinacionesMadre;
	/** Arreglo de JLabels para las combinaciones del padre (vertical) **/
	private JLabel[] labCombinacionesPadre;
	/** Matriz de JLables para los genotipos posibles del hijo **/
	private JLabel[][] labGenotiposHijo;
	
	/** Botón para cargar las propiedades de programa **/
	private JButton butCrear;
	/** Botón para calcular las probabilidades**/
	private JButton butCalcular;
	/** Botón para borrar el cuadro de Punnett actual**/
	private JButton butBorrar;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public PanelCalculadora(InterfacePunnett principalP) {
		// Asignando la interface principal
		principal = principalP;
		// Estableciendo el layout
		setLayout(new BorderLayout());
		// Estableciendo el título del panel
		TitledBorder border = BorderFactory.createTitledBorder( " Cuadro de Punnett " );
		setBorder(border);
		// Creando los botones
		butCrear = new JButton("Crear");
		butCrear.setActionCommand(CREAR);
		butCrear.addActionListener(this);
		
		butCalcular = new JButton("Calcular Probabilidad");
		butCalcular.setActionCommand(CALCULAR);
		butCalcular.addActionListener(this);
		butCalcular.setEnabled(false);
		
		butBorrar = new JButton("Borrar");
		butBorrar.setActionCommand(BORRAR);
		butBorrar.addActionListener(this);
		butBorrar.setEnabled(false);
		
		// Creando el layout para los botones
		JPanel gridBotones = new JPanel();
		gridBotones.setLayout(new GridLayout(1,3));
		gridBotones.add(butCrear);
		gridBotones.add(butCalcular);
		gridBotones.add(butBorrar);
		// Agregando panel botones al panel principal
		add(gridBotones,BorderLayout.SOUTH);
		
		// Pintando el cuadro
		pintarCuadro();
	}
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	/**
	 * Método que pinta el cuadro
	 */
	private void pintarCuadro() {
		// Inicializando JLabels para el Cuadro de Punnett
		// JLabel superior izquierdo
		labSupIzq = new JLabel("");
		labSupIzq.setBorder(new LineBorder(Color.BLACK));

		// JLabels padres
		// Obteniendo el número de combinaciones posibles para cada genotipo
		int combinacionesPosibles = principal.solicitarCombinacionesPosibles();
		
		// Inicializando el arreglo de la madre
		labCombinacionesMadre = new JLabel[combinacionesPosibles];
		for (int i = 0; i < labCombinacionesMadre.length; i++) {
			JLabel tempLabel = new JLabel("");
			tempLabel.setOpaque(true);
			tempLabel.setForeground(Color.WHITE);
			tempLabel.setBackground(Color.GRAY);
			tempLabel.setHorizontalAlignment(JLabel.CENTER);
			tempLabel.setVerticalAlignment(JLabel.CENTER);
			tempLabel.setBorder(new LineBorder(Color.BLACK));
			labCombinacionesMadre[i] = tempLabel;
		}
		
		// Inicializando el arreglo del padre
		labCombinacionesPadre = new JLabel[combinacionesPosibles];
		for (int i = 0; i < labCombinacionesPadre.length; i++) {
			JLabel tempLabel = new JLabel("");
			tempLabel.setOpaque(true);
			tempLabel.setForeground(Color.WHITE);
			tempLabel.setBackground(Color.GRAY);
			tempLabel.setHorizontalAlignment(JLabel.CENTER);
			tempLabel.setVerticalAlignment(JLabel.CENTER);
			tempLabel.setBorder(new LineBorder(Color.BLACK));
			labCombinacionesPadre[i] = tempLabel;
		}
		
		// Inicializando la matriz de los genotipos del hijo
		labGenotiposHijo = new JLabel[combinacionesPosibles][combinacionesPosibles];
		for (int i = 0; i < labGenotiposHijo.length; i++)
			for (int j = 0; j < labGenotiposHijo[i].length; j++) {
				JLabel tempLabel= new JLabel("");
				tempLabel.setOpaque(true);
				tempLabel.setBackground(Color.LIGHT_GRAY);
				tempLabel.setHorizontalAlignment(JLabel.CENTER);
				tempLabel.setVerticalAlignment(JLabel.CENTER);
				tempLabel.setBorder(new LineBorder(Color.BLACK));
				labGenotiposHijo[i][j] = tempLabel;
			}
		
		// Inicializando GridLayout principal y agregando los JLabel
		JPanel gridPunnett = new JPanel();
		gridPunnett.setLayout(new GridLayout(combinacionesPosibles+1, combinacionesPosibles+1));
		// Creando la primera fila
		gridPunnett.add(labSupIzq);
		for (int i = 0; i < labCombinacionesMadre.length; i++) gridPunnett.add(labCombinacionesMadre[i]);
		// Creando las filas 2 a labCombinacionesPadre.length
		for (int i = 0; i < labCombinacionesPadre.length; i++)
			for (int j = 0; j <= labCombinacionesMadre.length; j++)
				if (j==0) gridPunnett.add(labCombinacionesPadre[i]);
				else gridPunnett.add(labGenotiposHijo[i][j-1]);
		// Agregando el gridPunnett al panel principal
		add(gridPunnett,BorderLayout.CENTER);
	}
	/** 
	 * Método que pinta los genotipos posibles para los hijos en el cuadro
	 */
	public void actualizarGenotiposHijo(String[][] genotipos) {
		for (int i = 0; i < labGenotiposHijo.length; i++)
			for (int j = 0; j < labGenotiposHijo[i].length; j++)
				labGenotiposHijo[i][j].setText(genotipos[i][j]);
	}
	/** 
	 * Método que pinta las combinaciones posibles para los padres en el cuadro 
	 * @param combinaciones: Matriz de Strings que contiene las combinaciones posibles para
	 * el padre (combinaciones[0][]) y la madres (combinaciones[1][])
	 */
	public void actualizarCombinacionesPadres(String[] combinacionesPadre, String[] combinacionesMadre) {
		// Actualizando las combinaciones del padre
		for (int i = 0; i < combinacionesPadre.length; i++) labCombinacionesPadre[i].setText(combinacionesPadre[i]);
		// Actualizando las combinaciones de la madre
		for (int i = 0; i < combinacionesMadre.length; i++) labCombinacionesMadre[i].setText(combinacionesMadre[i]);
	}
	/**
	 * Método que limpia el cuadro
	 */
	public void limpiarCuadro() {
		// Limpiando combinaciones padres
		for (int i = 0; i < labCombinacionesPadre.length; i++) labCombinacionesPadre[i].setText(" ");
		for (int i = 0; i < labCombinacionesMadre.length; i++) labCombinacionesMadre[i].setText(" ");
		// Limpiando genotipos hijo
		for (int i = 0; i < labGenotiposHijo.length; i++)
			for (int j = 0; j < labGenotiposHijo[i].length; j++)
				labGenotiposHijo[i][j].setText(" ");
	}
	/**
	 * Método que habilita y deshabilita los botones 
	 */
	public void habilitarOpciones(boolean habilitado) {
		butCalcular.setEnabled(habilitado);
		butBorrar.setEnabled(habilitado);
	}
	//-----------------------------------------------------------------
    // Manejo de eventos
    //-----------------------------------------------------------------
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(CREAR)) {						// Botón Crear
			String[] strGenotipos = principal.genotiposPadres();
			String genotipoPadre = (String)JOptionPane.showInputDialog(this,
					"Genotipo Padre:","Crear Cuadro Punnett",
					JOptionPane.INFORMATION_MESSAGE,null, strGenotipos,null);
			if (genotipoPadre != null) {
			String genotipoMadre = (String)JOptionPane.showInputDialog(this,
					"Genotipo Madre:","Crear Cuadro Punnett",
					JOptionPane.INFORMATION_MESSAGE,null, strGenotipos,null);
				if (genotipoMadre != null) {
					try {
						principal.generarPunnett(genotipoPadre, genotipoMadre);
					} catch (Exception e){
						JOptionPane.showMessageDialog(this, e.getMessage(), "Calculadora de Punnett", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				} else JOptionPane.showMessageDialog( this, "Debe seleccionar un genotipo válido para la madre", "Calculadora de Punnett", JOptionPane.ERROR_MESSAGE );
			} else  JOptionPane.showMessageDialog( this, "Debe seleccionar un genotipo válido para el padre", "Calculadora de Punnett", JOptionPane.ERROR_MESSAGE );
		} else if(comando.equals(BORRAR)) principal.limpiar();	// Botón Borrar
		else if(comando.equals(CALCULAR)) {					// Botón Calcular
			String[][] strFenotipos = principal.solicitarFenotipos();
			String[] respuestas = new String[strFenotipos.length];
			boolean proceder = true;
			for (int i = 0; i < respuestas.length; i++) {
				String[] posibilidades = {strFenotipos[i][1],strFenotipos[i][2]};
				respuestas[i] = (String)JOptionPane.showInputDialog(this,
						"Fenotipo para la característica "+strFenotipos[i][0]+":","Calcular Probabilidad",
						JOptionPane.INFORMATION_MESSAGE, null, posibilidades ,null);
				if (respuestas[i] == null) {
					JOptionPane.showMessageDialog( this, "Debe seleccionar un fenotipo válido para la característica "+strFenotipos[i][0], "Calcular Probabilidad", JOptionPane.ERROR_MESSAGE );
					proceder = false;
					break;
				}
			}
			if (proceder) principal.calcularProbabilidades(respuestas);
		}
	}
}

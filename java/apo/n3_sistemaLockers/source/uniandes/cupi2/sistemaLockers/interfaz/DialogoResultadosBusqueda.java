package uniandes.cupi2.sistemaLockers.interfaz;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Dialogo que muestra los resultados de las búsqueda de casilleros
 */
public class DialogoResultadosBusqueda extends JDialog
{	
	/**
	 * Método constructor del dialogo
	 * @param resultados Lista con las id de casilleros resultados de la búsqueda
	 */
	public DialogoResultadosBusqueda(ArrayList resultados)
	{
		setTitle(" Resultados de la búsqueda ");
		setModal(true);
		setLocationRelativeTo(null);
		
		if(!resultados.isEmpty())
		{
			setLayout(new GridLayout(resultados.size(), 1));
			setSize(250, 40*resultados.size());
			
			for(int i=0; i<resultados.size(); i++)
			{
				String id = (String) resultados.get(i);
				add(new JLabel("  *   " + id));
			}
		}
		else
		{
			setLayout(new GridLayout(1, 1));
			setSize(300, 100);
			
			JLabel etiqueta = new JLabel("No se encontraron casilleros");
			etiqueta.setHorizontalAlignment(JLabel.CENTER);
			add(etiqueta);
		}
		
	}
}

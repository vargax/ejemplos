/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_cupiMath
 * Autor: cupi2 - 15-mar-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiMath.interfaz;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;


/**
 * Panel para mostrar la expresión aritmética ingresada por el usuario
 */
public class PanelExpresionAritmetica extends JPanel
{

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	
    /**
     * Área de texto que contiene la expresión aritmética
     */
    private JTextArea txtAreaExpresion;
    
    /**
     * Panel con scroll para agregar el área de texto de la expresión aritmética
     */
    private JScrollPane scrollExpresion;
    
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Construye un nuevo panel para mostrar una expresión aritmética 
     */
    public PanelExpresionAritmetica()
    {
        setBorder( new TitledBorder( "Expresión Aritmética" ) );
        txtAreaExpresion=new JTextArea( );
        txtAreaExpresion.setFont( new Font(Font.SANS_SERIF, Font.BOLD, 25 ) );
        txtAreaExpresion.setEditable( false );
        scrollExpresion=new JScrollPane(txtAreaExpresion );
        scrollExpresion.setPreferredSize( new Dimension(510,50) );
        add(scrollExpresion);
    }
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    
    /**
     * Actualiza una expresión aritmética con el ítem que llega por parámetro
     * @param item El nuevo ítem para agregar a la expresión aritmética. item != null
     */
    public void actualizarExpresionAritmetica(String item)
    {
    	txtAreaExpresion.setText(txtAreaExpresion.getText()+item);
    }

    /**
     * Borra el último ítem de la expresión aritmética
     */
	public void borrarItem()
	{
		String expr=txtAreaExpresion.getText();
		if(expr!=null&&expr.length()>0)
		{
			String subExpr=expr.substring(0, expr.length()-1);
			txtAreaExpresion.setText(subExpr);
		}
		
		
	}
	
	/**
	 * Retorna el valor de la expresión aritmética
	 * @return EL valor de la expresión aritmética
	 */
	public String darExpresionAritmetica()
	{
	    String expresion=txtAreaExpresion.getText( );
	    return expresion;
	}

	
    /**
     * Borra toda la expresión aritmética del área de texto
     */
    public void borrarExpresion( )
    {
       txtAreaExpresion.setText( "" ); 
    }
    
    
}

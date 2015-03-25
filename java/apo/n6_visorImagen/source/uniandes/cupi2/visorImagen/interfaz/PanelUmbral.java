/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_visorImagen 
 * Autor: Katalina Marcos
 * Modificación: Mario Sánchez - 28/06/2005
 * Modificación: Pablo Barvo - 1-Sep-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.visorImagen.interfaz;

import java.awt.*;

import javax.swing.*;

/**
 * Panel donde se entra el valor del umbral para la binarización
 */
public class PanelUmbral extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
     * Campo para indicar el umbral
     */
    private JTextField txtUmbral;

    /**
     * Etiqueta umbral
     */
    private JLabel labUmbral;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel para recibir el umbral de binarización
     */
    public PanelUmbral( )
    {
        //Establece el distribuidor gráfico
        setLayout( new GridLayout( 1, 2 ) );

        //Crea, inicializa los elementos de la interfaz y adiciona los componentes gráficos
        txtUmbral = new JTextField( );
        txtUmbral.setForeground( Color.BLUE );
        labUmbral = new JLabel( "Umbral:" );
        labUmbral.setHorizontalAlignment( JLabel.CENTER );
        add( labUmbral );
        add( txtUmbral );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el valor del umbral
     * @return umbral Retorna el valor dado por el usuario, o -1 si no es un valor válido
     */
    public double darUmbral( )
    {
        try
        {
            return Double.parseDouble( txtUmbral.getText( ) );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Umbral inválido: " + txtUmbral.getText( ), "Umbral de BinariaaciónMatriz de Convolución", JOptionPane.ERROR_MESSAGE );
            txtUmbral.setText( "0" );
            return -1;
        }
    }

    /**
     * Asigna un nuevo umbral al respectivo campo de texto
     * @param nuevoUmbral
     */
    public void asignarUmbral( double nuevoUmbral )
    {
        txtUmbral.setText( nuevoUmbral + "" );
    }
}
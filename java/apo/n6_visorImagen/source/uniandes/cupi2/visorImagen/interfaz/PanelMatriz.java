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
 * Panel donde se entran los datos de la matriz de convolución
 */
public class PanelMatriz extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
     * Campos donde se indican los valores de la matriz de convolución
     */
    private JTextField factores[][];

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel para recibir los datos de la matriz de convolución
     */
    public PanelMatriz( )
    {
        int dimension = InterfazVisorImagen.DIMENSION_CONVOLUCION;

        factores = new JTextField[dimension][dimension];

        //Establece el distribuidor gráfico
        setLayout( new GridLayout( dimension, dimension ) );

        //Crea, inicializa y adiciona los campos de texto
        for( int i = 0; i < dimension; i++ )
            for( int j = 0; j < dimension; j++ )
            {
                factores[ i ][ j ] = new JTextField( "0" );
                add( factores[ i ][ j ] );
            }
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Limpia los valores de los factores dados
     */
    public void limpiar( )
    {
        for( int i = 0; i < InterfazVisorImagen.DIMENSION_CONVOLUCION; i++ )
            for( int j = 0; j < InterfazVisorImagen.DIMENSION_CONVOLUCION; j++ )
                factores[ i ][ j ].setText( "0" );
    }

    /**
     * Retorna la matriz de convolución definida por el usuario
     * @return matriz de convolución
     */
    public double[][] darMatriz( )
    {
        int dimension = InterfazVisorImagen.DIMENSION_CONVOLUCION;
        double matriz[][] = new double[dimension][dimension];

        for( int i = 0; i < dimension; i++ )
            for( int j = 0; j < dimension; j++ )
            {
                try
                {
                    matriz[ i ][ j ] = Double.parseDouble( factores[ i ][ j ].getText( ) );
                }
                catch( Exception e )
                {
                    JOptionPane.showMessageDialog( this, "Factor inválido: " + factores[ i ][ j ].getText( ), "Matriz de Convolución", JOptionPane.ERROR_MESSAGE );
                    factores[ i ][ j ].setText( "0" );
                    return null;
                }
            }
        return matriz;
    }
}
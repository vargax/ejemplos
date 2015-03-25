/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 * Autor: Luis Ricardo Ruiz Rodríguez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.servidor.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * Clase donde se muestra la lista de artículos de un usuario seleccionado en el panel de usuarios conectados
 */
public class PanelArticulos extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La lista de artículos del usuario
     */
    private JList listaArticulos;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Método constructor. Crea una lista donde se mostrarán los artículos del usuario.
     */
    public PanelArticulos( )
    {
        setLayout( new BorderLayout( ) );
        setPreferredSize( new Dimension( 250, 200 ) );
        
        TitledBorder borde = BorderFactory.createTitledBorder( "Lista artículos" );
        setBorder( borde );
        
        listaArticulos = new JList();
        
        JScrollPane scroll = new JScrollPane( listaArticulos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        add( scroll, BorderLayout.CENTER );
    }
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Actualiza la lista de artículos de un usuario.
     * @param articulos La lista de artículos del usuario seleccionado. articulos != null
     */
    public void actualizarListaArticulos(ArrayList articulos){
        listaArticulos.setListData( articulos.toArray( ) );
    }
}

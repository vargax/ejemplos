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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.blog.servidor.mundo.Usuario;

/**
 * Clase donde se coloca la lista de los usuarios conectados
 */
public class PanelUsuariosConectados extends JPanel implements ListSelectionListener
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La lista de usuarios conectados
     */
    private InterfazServidorBlog interfaz;

    /**
     * La lista de usuarios conectados
     */
    private JList listaUsuariosConectados;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Método constructor. Crea una lista donde se mostrarán los usuarios conectados.
     * @param principal La interfaz principal de la aplicación. interfaz != null
     */
    public PanelUsuariosConectados( InterfazServidorBlog principal )
    {
        interfaz = principal;
        
        setLayout( new BorderLayout( ) );
        setPreferredSize( new Dimension( 300, 200 ) );
        
        TitledBorder borde = BorderFactory.createTitledBorder( "Usuarios Conectados" );
        setBorder( borde );
        
        listaUsuariosConectados = new JList();
        listaUsuariosConectados.addListSelectionListener( this );
        
        JScrollPane scroll = new JScrollPane( listaUsuariosConectados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );       
        add( scroll, BorderLayout.CENTER );
    }
    
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Actualiza los usuarios conectados de la lista.
     * @param usuarios La lista de usuarios conectados al blog. usuarios != null
     */
    public void actualizarListaUsuariosConectados(ArrayList usuarios){
        listaUsuariosConectados.setListData( usuarios.toArray( ) );
        if( !usuarios.isEmpty( )){
            listaUsuariosConectados.setSelectedIndex( 0 );
        }
    }

    //-----------------------------------------------------------------
    // Métodos de eventos
    //-----------------------------------------------------------------
    
    /**
     * Método que maneja los eventos de la lista
     * @param e El evento de la lista. e != null
     */
    public void valueChanged( ListSelectionEvent e )
    {
        Usuario usuario = (Usuario) listaUsuariosConectados.getSelectedValue( );
        if(usuario != null){
            interfaz.actualizarUsuario( usuario.darInfoUsuario( ) );
        }else{
            interfaz.actualizarUsuario( null );
        }
    }
}

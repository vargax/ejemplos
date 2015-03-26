package co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia;

import java.util.ArrayList;

import android.content.Context;
import android.database.SQLException;

import co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.persistencia.BaseDatos;
import co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.persistencia.IBaseDatos;
import co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.persistencia.SQLite;

/**
 *
 * @author c.vargas124	
 */
public class Persistencia {

    // -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
	private IBaseDatos db;
	
    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------
    
    public Persistencia(Context contexto) {
        this.db = new BaseDatos(contexto);
    }

    // -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------
   
    /**
     * Registra un nuevo dato. Todo nuevo dato se registra con la fecha y hora
     * de entrada a al base de datos local y se marca como no enviado.
     * fecha_hora = current_timestamp
     * enviado = 'N'
     * @throws PersistenciaException 
     */
    
    public void registrarDato(String dispositivo, String dato) throws PersistenciaException {
    	Registro tmpRegistro = new Registro(dispositivo, dato);
    	this.db.insertarRegistro(tmpRegistro);
    } 

    /**
     * Recupera todos los registros que no se han marcado como enviados a la
     * fundación. Los registros recuperados se marcan como entregados para
     * envio. Una vez se confirme al entrega se debe notificar al sistema
     * para marcarlos como entregados.
     * return SELECT * FROM DATOS WHERE enviado != 'S'
     * UPDATE DATOS SET enviado = 'E' WHERE enviado != 'S'
     */
    public ArrayList<Registro> recuperarNoEnviados() throws SQLException {
//        Statement s = conexion.createStatement();
//        String consulta = "SELECT * FROM DATOS WHERE enviado != 'S'";
//        ResultSet rs = s.executeQuery(consulta);
        ArrayList<Registro> resultado = new ArrayList<Registro>();
//        while(rs.next()) {
//            Registro tmpRegistro = new Registro(rs.getInt("id"), rs.getDate("fecha_hora"), 
//                    rs.getString("dispositivo") , rs.getString("registro"), rs.getString("enviado"));
//            resultado.add(tmpRegistro);
//        }
//        System.out.println("PERSISTENCIA: Se han recuperando "+resultado.size()+" registros no enviados...");
//        consulta = "UPDATE DATOS SET enviado = 'E' WHERE enviado != 'S'";
//        System.out.println("PERSISTENCIA: Se han marcado los registros no enviados...");        
//        s.executeUpdate(consulta);
        return resultado;
    }
    
    /**
     * Marca como entregados todos los registros que se encuentran actualmente
     * como enviados.
     * UPDATE DATOS SET enviado = 'S' WHERE enviado = 'E'
     */
    public int notificarEntrega() throws SQLException {
        return this.db.marcarEntregados();
    }
}

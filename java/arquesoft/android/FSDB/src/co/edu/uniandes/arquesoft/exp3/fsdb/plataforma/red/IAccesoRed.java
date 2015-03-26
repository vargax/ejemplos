package co.edu.uniandes.arquesoft.exp3.fsdb.plataforma.red;

import java.net.Socket;

/**
 *
 * @author c.vargas124
 */
public interface IAccesoRed {
    
    public Socket solicitarSocket(String ip, int puerto) throws Exception;

}

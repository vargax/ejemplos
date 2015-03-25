package co.edu.uniandes.arquesoft.exp1.fsdb.mockDispositivo;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author c.vargas124
 */
public class MockDispositivo {
    // -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------
    /**
     * El puerto de escucha del servidor
     */
    private final static int puerto = 1375;
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
    private Thread threadServidor;
    private ServerSocket socketServidor;
    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------
    public MockDispositivo() {
        threadServidor = new Thread() {
            public void run() {
                try {
                    System.out.println("MockDispositivo: Iniciando el socket de escucha...");
                    socketServidor = new ServerSocket(puerto);
                    while(true) {
                        Socket cliente = socketServidor.accept();
                        System.out.println("MockDispositivo: Solicitud de conexión detectada...");
                        PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
                        System.out.println("MockDispositivo: Canal de escritura establecido...");
                        String dato = ((int)(Math.random()*100))+";;;"+((int)(Math.random()*100));
                        out.println(dato);
                        cliente.close();
                        System.out.println("MockDispositivo: Dato "+dato+" transmitido...");
                    }
                } catch (Exception e) {
                    System.err.println("MockDispositivo: No fue posible inicializar el socket!");
                    e.printStackTrace();
                }
            }
        };
        System.out.println("MockDispositivo: Iniciando el servidor...");
        threadServidor.start();
    }
    // -------------------------------------------------------------------------
    // Método MAIN
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("MAIN: Instanciando el servidor de escucha...");
        new MockDispositivo();
    }
}

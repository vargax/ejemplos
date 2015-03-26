package co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia;

import java.util.Date;

/**
 *
 * @author c.vargas124
 */
public class Registro {
	// -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------
	public final static String NUEVO = "N";
	public final static String ENVIADO = "E";
	public final static String ENTREGADO = "S";
	// -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
    private int id;
    private long fechaHora;
    private String dispositivo;
    private String registro;
    private String estado;
    
    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------
    public Registro(String dispositivo, String registro) {
        this.id = -1;
        this.fechaHora = System.currentTimeMillis();
        this.dispositivo = dispositivo;
        this.registro = registro;
        this.estado = this.NUEVO;
    }
    
    public Registro(int id, long fecharHora, String dispositivo, String registro, String estado) {
        this.id = id;
        this.fechaHora = fecharHora;
        this.dispositivo = dispositivo;
        this.registro = registro;
        this.estado = estado;
    }
    
    // -------------------------------------------------------------------------
    // Getters / Setters
    // -------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    public long getFechaHora() {
        return fechaHora;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public String getDato() {
        return registro;
    }

    public String getEstado() {
        return estado;
    }
    
    
}

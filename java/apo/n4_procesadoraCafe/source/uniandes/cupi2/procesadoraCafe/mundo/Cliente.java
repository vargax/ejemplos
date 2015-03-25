package uniandes.cupi2.procesadoraCafe.mundo;

/**
 * Clase que modela un cliente de la Procesadora
 */
public class Cliente 
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Nombre del cliente.
	 */
	private String nombre;

	/**
	 * Nit del cliente.
	 */
	private String nit;
	
	/**
	 * Teléfono del cliente.
	 */
	private String telefono;
	
	/**
	 * Registra el número de kilos vendidos al cliente.
	 */
	private double kilosVendidos;
	
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
	/**
	 * Crea un cliente de la Procesadora de Café. <br>
	 * <b>post: </b> Se creó un cliente con las características recibidas por parámetro 
	 * y la cantidad de kilos vendidos en cero <br>
	 * @param nombreP - Corresponde al nombre del cliente - nombreP != null y nombreP != ""
	 * @param nitP - Corresponde al NIT del cliente - nitP != null y nitP != ""
	 * @param telefonoP - Corresponde al teléfono del cliente - telefonoP != null y telefonoP != ""
	 */

	 public Cliente(String nombreP, String nitP, String telefonoP) {
		 nombre = nombreP;
		 nit = nitP;
		 telefono = telefonoP;
		 
		 kilosVendidos = 0;
	 }
	
	
	
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	
    /**
     * Método que sirve para conocer el nombre del cliente. <br>
     * @return nombre - Nombre del cliente - nombre != null y nombre != ""
     */
	public String darNombre()
	{
		return nombre;
	}
	
	/**
     * Método que sirve para conocer el NIT del cliente. <br>
     * @return nit - NIT del cliente - nit != null y nit != ""
     */
	public String darNit()
	{
		return nit;
	}
	
	/**
     * Método que sirve para conocer el teléfono del cliente. <br>
     * @return telefono - Teléfono del cliente - telefono != null y telefono != ""
     */
	public String darTelefono()
	{
		return telefono;
	}
	
	/**
     * Método que sirve para conocer la cantidad de kilos vendidos al cliente. <br>
     * @return kilosVendidos - Kilos vendidos al cliente - kilosVendidos >= 0
     */
	public double darCantidadVendida()
	{
		return kilosVendidos;
	}
	
	
	/**
	 * Método que registra la venta de determinados kilos de cafe al cliente.
	 * <b>post:</b> Se aumentó el número de kilos vendidos al cliente en la cantidad recibida por parámetro.
	 * @param kilos - kilos > 0
	 */
	public void registarVentaCafe(double kilos)
	{
		kilosVendidos += kilos;
	}
	
	/**
     * Retorna una cadena con el nombre del cliente
     * @return La representación del cliente en String
     */
	public String toString()
	{
		return nombre;
	}
}

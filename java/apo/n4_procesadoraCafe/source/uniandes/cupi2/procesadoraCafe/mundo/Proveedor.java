package uniandes.cupi2.procesadoraCafe.mundo;

/**
 * Clase que modela un proveedor de café tostado
 */
public class Proveedor 
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Nombre del proveedor
	 */
	private String nombre;

	/**
	 * Número de cédula del proveedor
	 */
	private int cedula;
	
	/**
	 * Teléfono de contacto del proveedor
	 */
	private String telefono;
	
	/**
	 * Región de origen del café
	 */
	private String origen;
	
	/**
	 * Precio de compra en pesos colombianos por kilo de café tostado
	 */
	private double precio;
		
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
	
	/**
	 * Crea un proveedor de la procesadora de cafe <br />
	 * <b>post:</b> Se creó un proveedor con las características recibidas por parámetro.
	 * @param nombreP - Corresponde al nombre del proveedor - nombreP != null y nombreP != ""
	 * @param cedulaP - Corresponde a la cédula del proveedor - cedulaP > 0
	 * @param telefonoP - Corresponde al teléfono del proveedor - telefonoP != null y telefonoP != ""
	 * @param origenP - Corresponde a la región de origen del café vendido por el proveedor - origenP != null y origenP != ""
	 * @param precioP - Corresponde al precio por kilogramo de café tostado cobrado por el proveedor - precioP > 0
	 */
	public Proveedor(String nombreP, int cedulaP, String telefonoP, String origenP, double precioP)
	{
		nombre = nombreP;
		cedula = cedulaP;
		telefono = telefonoP;
		origen = origenP;
		precio = precioP;
	}
	
	//-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
	
    /**
     * Método que sirve para conocer el nombre del proveedor. <br>
     * @return nombre - Nombre del proveedor - nombre != null y nombre != ""
     */
	public String darNombre()
	{
		return nombre;
	}
	
	 /**
     * Método que sirve para conocer la cédula del proveedor. <br>
     * @return cedula - Cédula del proveedor - Número entero positivo
     */
	public int darCedula()
	{
		return cedula;
	}
	
	 /**
     * Método que sirve para conocer el teléfono del proveedor. <br>
     * @return telefono - Teléfono del proveedor - telefono != null y telefono != ""
     */
	public String darTelefono()
	{
		return telefono;
	}
	
	/**
	 * Método que sirve para conocer el origen del café vendido por el proveedor
	 * @return origen - String que contiene el origen del café - origen != null y origen != ""
	 */
	public String darOrigen() {
		return origen;
	}
    
	/**
	 * Método que sirver para conocer el precio por kilogramo de café tostado cobrado por este proveedor
	 * @return precio - double que representa el precio por kilogramo - precio > 0	
	 */
	public double darPrecio() {
		return precio;
	}
	
	/**
     * Retorna una cadena con el nombre del proveedor
     * @return La representación del proveedor en String
     */
	public String toString()
	{
		return nombre;
	}
}

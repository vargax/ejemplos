package mundo;

import java.io.Serializable;

public class Consumo implements Serializable {
	/**
	 * Constante Serialización
	 */
	private static final long serialVersionUID = -6802501653628861482L;
	/**
	 * Corresponde al nombre del producto de consumo
	 */
	private String nombre;
	/**
	 * Corresponde al valor del producto 
	 */
	private double valor;
	//-------------------------------------------------------------------
	//Constructor
	//-------------------------------------------------------------------
	/**
	 * Constructor de la clase
	 * @param nombreP -corresponde al nombre del producto-nombreP !=null && nombreP!=""
	 * @param valorP- corresponde al valor del producto- valorP!=null && nombreP!=""
	 */
	public Consumo(String nombreP,double valorP)
	{
		nombre = nombreP;
		valor = valorP;
	}
	//--------------------------------------------------------------------
	//dar y asignar
	//--------------------------------------------------------------------
	/**
	 * Método que retorna el nombre del producto
	 */
	public String darNombre() 
	{
		return nombre;
	}
	/**
	 * Método que asigna un nombre al producto
	 * @param nombre-corresponde al nombre del producto-nombre!=null && nombre!=""
	 */
	public void asignarNombre(String nombre)
	{
		this.nombre = nombre;
	}
	/**
	 * Método que retorna el valor del producto
	 * @return - Retorna el valor del producto
	 */
	public double darValor() 
	{
		return valor;
	}
	/**
	 * Método que asigna el valor al producto
	 * @param valor-corresponde al valor del producto-valor!=0 && valor!=null
	 */
	public void asignarValor(double valor) 
	{
		this.valor = valor;
	}
	public String[] getInfoConsumo()
	{
		String []a = new String[2];
		a[0]=nombre;
		a[1] = ""+valor;
		return a;
	}

}

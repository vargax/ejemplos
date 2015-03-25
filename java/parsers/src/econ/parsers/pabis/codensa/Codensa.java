package econ.parsers.pabis.codensa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Codensa {
	// ---------------------------------------------
	// Constantes
	// ---------------------------------------------
	private final static String archivoEntrada = "./data/codensa/input.csv";
	private final static String archivoSalida = "./data/codensa/output.csv";
	// ---------------------------------------------
	// Atributos
	// ---------------------------------------------
	private Modelo[] modelos; 
	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	private Codensa() throws Exception {
		modelos = new Modelo[855];
		lector();
		escritor();
	}
	// ---------------------------------------------
	// Métodos
	// ---------------------------------------------	
	private void lector() throws Exception {
		BufferedReader bf = new BufferedReader(new FileReader(new File(archivoEntrada)));
		String lineaActual = bf.readLine();
		
		for (int i = 0; i < modelos.length; i++) {
			lineaActual = bf.readLine();
			System.out.println(lineaActual);
			String[] columnas = lineaActual.split(",");

			String idm			= columnas[0];
			String tipoUsuario 	= columnas[1];
			
			String a4_ac 		= columnas[2];
			String a5_ac 		= columnas[3];
			String a6_ac 		= columnas[4];
			String a20_ac 		= columnas[5];
			String a21_ac 		= columnas[6];
			String b9_ac 		= columnas[7];
			String c3_ac		= columnas[8];
			String f8_ac		= columnas[9];
			String f11_ac		= columnas[10];
					
			String a4_no 		= columnas[11];
			String a5_no 		= columnas[12];
			String a6_no 		= columnas[13];
			String a20_no 		= columnas[14];
			String a21_no 		= columnas[15];
			String b9_no 		= columnas[16];
			String c3_no		= columnas[17];
			String f8_no		= columnas[18];
			String f11_no		= columnas[19];

			boolean tratamiento = Integer.parseInt(tipoUsuario) == 1;
			String a4 	= tratamiento ? a4_ac 	: a4_no;
			String a5 	= tratamiento ? a5_ac 	: a5_no;
			String a6 	= tratamiento ? a6_ac 	: a6_no;
			String a20 	= tratamiento ? a20_ac 	: a20_no;
			String a21 	= tratamiento ? a21_ac 	: a21_no;
			String b9 	= tratamiento ? b9_ac 	: b9_no;
			String c3 	= tratamiento ? c3_ac 	: c3_no;
			String f8 	= tratamiento ? f8_ac 	: f8_no;
			String f11 	= tratamiento ? f11_ac 	: f11_no;
			
			modelos[i] = new Modelo(idm,tratamiento,a4,a5,a6,a20,a21,b9,c3,f8,f11);
			//System.out.println(modelos[i].toString());
		}
		bf.close();
	}
	private void escritor() throws Exception {
		PrintWriter pw = new PrintWriter(new File(archivoSalida));
		
		String linea = "idm,tratamiento";
		for(int i = 1; i <= 6; i++) linea = linea+",a4_"+i;
		for(int i = 1; i <= 6; i++) linea = linea+",a5_"+i;
		for(int i = 1; i <= 7; i++) linea = linea+",a6_"+i;
		for(int i = 1; i <= 7; i++) linea = linea+",a20_"+i;
		for(int i = 1; i <= 6; i++) linea = linea+",a21_"+i;
		for(int i = 1; i <= 14; i++) linea = linea+",b9_"+i;
		for(int i = 1; i <= 19; i++) linea = linea+",c3_"+i;
		for(int i = 1; i <= 9; i++) linea = linea+",f8_"+i;
		for(int i = 1; i <= 15; i++) linea = linea+",f11_"+i;
		
		pw.println(linea);
		for (int i = 0; i < modelos.length; i++) {
			pw.println(modelos[i]);
		}
		pw.close();
	}
	// ---------------------------------------------
	// Método Main
	// ---------------------------------------------
	public static void main(String[] args) {
		try {
			new Codensa();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Modelo {
	private final static String NULO = "-";
	// ---------------------------------------------
	// Atributos
	// ---------------------------------------------
	/**
	 * Verdadero si el objeto actual pertenece al grupo de tratamiento
	 */
	boolean tratamiento; // ac
	/**
	 * El identificador del objeto
	 */
	int idm;
	
	int[] a4;
	int[] a5;
	int[] a6;
	int[] a20;
	int[] a21;
	int[] b9;
	int[] c3;
	int[] f8;
	int[] f11;
	
	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	public Modelo(String idm, boolean tratamiento, String a4, String a5, String a6, String a20, String a21, String b9, String c3, String f8, String f11) {
		this.idm = Integer.parseInt(idm);
		this.tratamiento = tratamiento;
		
		this.a4 = new int[6];
		this.a5 = new int[6];
		this.a6 = new int[7];
		this.a20 = new int[7];
		this.a21 = new int[6];
		this.b9 = new int[14];
		this.c3 = new int[19];
		this.f8 = new int[9];
		this.f11 = new int[15];
		
		String[] a4temp = a4.split(";");
		String[] a5temp = a5.split(";");
		String[] a6temp = a6.split(";");
		String[] a20temp = a20.split(";");
		String[] a21temp = a21.split(";");
		String[] b9temp = b9.split(";");
		String[] c3temp = c3.split(";");
		String[] f8temp = f8.split(";");
		String[] f11temp = f11.split(";");
		
		for (String s : a4temp)	{
			if(!s.equals(NULO)) {
				int n = Integer.parseInt(s)-1;
				n = n >= this.a4.length ? this.a4.length -1 : n; 
				this.a4[n] = 1;
			}
		}
		for (String s : a5temp)	{
			if(!s.equals(NULO)) {
				int n = Integer.parseInt(s)-1;
				n = n >= this.a5.length ? this.a5.length -1 : n; 
				this.a5[n] = 1;
			}
		}
		for (String s : a6temp)	{
			if(!s.equals(NULO)) {
				int n = Integer.parseInt(s)-1;
				n = n >= this.a6.length ? this.a6.length -1 : n; 
				this.a6[n] = 1;
			}
		}
		for (String s : a20temp) {
			if(!s.equals(NULO)) {
				int n = Integer.parseInt(s)-1;
				n = n >= this.a20.length ? this.a20.length -1 : n; 
				this.a20[n] = 1;
			}
		}
		for (String s : a21temp) {
			if(!s.equals(NULO)) {
				int n = Integer.parseInt(s)-1;
				n = n >= this.a21.length ? this.a21.length -1 : n; 
				this.a21[n] = 1;
			}
		}
		for (String s : b9temp) {
			if(!s.equals(NULO)) {
				int n = Integer.parseInt(s)-1;
				n = n >= this.b9.length ? this.b9.length -1 : n; 
				this.b9[n] = 1;
			}
		}
		for (String s : c3temp) {
			if(!s.equals(NULO)) {
				int n = Integer.parseInt(s)-1;
				n = n >= this.c3.length ? this.c3.length -1 : n; 
				this.c3[n] = 1;
			}
		}
		for (String s : f8temp)	{
			if(!s.equals(NULO)) {
				int n = Integer.parseInt(s)-1;
				n = n >= this.f8.length ? this.f8.length -1 : n; 
				this.f8[n] = 1;
			}
		}
		for (String s : f11temp) {
			if(!s.equals(NULO)) {
				int n = Integer.parseInt(s)-1;
				n = n >= this.f11.length ? this.f11.length -1 : n; 
				this.f11[n] = 1;
			}
		}
	}
	
	public String toString() {
		String respuesta = ""+idm;
		
		if (tratamiento) respuesta = respuesta+",1";
		else respuesta = respuesta+",0";
		
		for (int s : a4) { 
			if (s == 1) respuesta = respuesta+",1";
			else respuesta = respuesta+",0";
		}
		
		for (int s : a5) { 
			if (s == 1) respuesta = respuesta+",1";
			else respuesta = respuesta+",0";
		}
		
		for (int s : a6) { 
			if (s == 1) respuesta = respuesta+",1";
			else respuesta = respuesta+",0";
		}
		
		for (int s : a20) { 
			if (s == 1) respuesta = respuesta+",1";
			else respuesta = respuesta+",0";
		}
		
		for (int s : a21) { 
			if (s == 1) respuesta = respuesta+",1";
			else respuesta = respuesta+",0";
		}
		
		for (int s : b9) { 
			if (s == 1) respuesta = respuesta+",1";
			else respuesta = respuesta+",0";
		}
		
		for (int s : c3) { 
			if (s == 1) respuesta = respuesta+",1";
			else respuesta = respuesta+",0";
		}
		
		for (int s : f8) { 
			if (s == 1) respuesta = respuesta+",1";
			else respuesta = respuesta+",0";
		}
		
		for (int s : f11) { 
			if (s == 1) respuesta = respuesta+",1";
			else respuesta = respuesta+",0";
		}
		return respuesta;
	}
}

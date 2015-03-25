/**
 * Resuelve el problema de la bandera holandesa. 
 * Recibe por la entrada estandar un String.
 * Devuelve un String ordenado donde las R's se encuentran al principio y las A's al final.
 * Utiliza 4 variables
 * Realiza tres asignaciones en el peor de los casos
 * Realiza n iteraciones en el peor de los casos
 */

//Inicio Burocracia //
package vrgx.dialgo.banderaHolandesa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrdenarBandera {
	public static void main(String[] args) {
		try {
			OrdenarBandera bandera = new OrdenarBandera();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public OrdenarBandera() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		System.out.println("Escriba su bandera:");
		while (line != null) {
			line = br.readLine();
			char[] bandera = line.toCharArray();
// Fin Burocracia //
			// INIT //
			int recorredor = 0; int rojo = 0; int azul = bandera.length-1;
			// do //
			while(recorredor <= azul) {
				char temp = bandera[recorredor];
				if(temp == 'r') {bandera[recorredor] = bandera[rojo]; bandera[rojo] = temp; rojo++; recorredor++;} 
				else if (temp == 'a') {bandera[recorredor] = bandera[azul]; bandera[azul] = temp; azul--;}
				else recorredor++;
				System.out.println(new String(bandera));
			}
			// od //
		}
	}
}

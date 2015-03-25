package mock_vafus;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import vafus.plugins.vissim.JVafus;
import vafus.plugins.vissim.exceptions.CommunicationException;
import vafus.plugins.vissim.exceptions.JVissimException;
import vafus.plugins.vissim.interfaces.ISimulation;
import vafus.plugins.vissim.interfaces.IVafus;
import vafus.plugins.vissim.interfaces.IjVissim;

public class MockVafus implements IVafus {

	 private IjVissim jvissim;
	 // Arreglo con las tuplas (ip,puerto)
	 private String[][] servidores = {{"157.253.192.168","3006"}};
	 
	 public MockVafus() throws JVissimException, IOException {
		 this.jvissim = new JVafus(this);
		 // Conectando con los servidores... 
		 for (String[] s : servidores) 
			 try {
				 System.out.println("Conectando con "+s[0]+":"+s[1]);
				 ISimulation simulation = this.jvissim.getSimulation(s[0], s[1]);
				 simulation.loadSimulation(new File("data/test.amn"));
			 } catch (CommunicationException e) {
				e.printStackTrace();
			}
		 
		 // Finalizando la prueba...
		 System.out.println("Presione cualquier tecla para finalizar...");
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 br.readLine();
		 this.jvissim.shutDown();
	 }
	 
	 public static void main(String[] args) {
		 try {
			 System.out.println("Instanciando MockVafus...");
			 new MockVafus();
		 } catch (Exception e) {
			 System.err.println(e.getMessage());
			 e.printStackTrace();
		 }
	 }
}

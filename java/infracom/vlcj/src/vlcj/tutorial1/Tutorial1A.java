package vlcj.tutorial1;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;

public class Tutorial1A {

	public static void main(String[] args) {
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		System.out.println("Librería cargada correctamente...");
	}
}
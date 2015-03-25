package co.edu.uniandes.arquesoft.exp3.fsdb.interfaz;

import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.GestionDatosLocal;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.dispositivos.ConexionException;
import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.PersistenciaException;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	// -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------
	public final String LOG_CAT = "FSDB";
    // -------------------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------------------
	/**
	 * Atributo que representa al núcleo de la aplicación
	 */
	private GestionDatosLocal nucleo;
	
    // -------------------------------------------------------------------------
    // Métodos SuperClase
    // -------------------------------------------------------------------------
	/**
	 * Método equivalente al constructor de la clase.
	 * Inicializa el núcleo de la aplicación.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(this.LOG_CAT, "Instanciando el núcleo");
		this.nucleo = new GestionDatosLocal(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	// -------------------------------------------------------------------------
    // Métodos Requerimientos Funcionales
    // -------------------------------------------------------------------------
	public void recolectarDatos(View vista) {
		try {
			Log.d(this.LOG_CAT, "Solicitando recolección de datos");
			// Recupero los elementos gráficos
			TextView sistole = (TextView) findViewById(R.id.sistole);
			TextView diastole = (TextView) findViewById(R.id.diastole);
			TextView presionArterial = (TextView) findViewById(R.id.presionArterial);
			// Solicito la recolección de los datos
			String[] datos = this.nucleo.recolectarDato();
			// Despliego los datos recuperados en pantalla
			sistole.setText(datos[0]);
			diastole.setText(datos[1]);
			presionArterial.setText(datos[0]+"/"+datos[1]);
		} catch (ConexionException e) {
			Log.e(this.LOG_CAT, "recolectarDatos().ConexionException: "+e.getMessage(), e);
		} catch (PersistenciaException e) {
			Log.e(this.LOG_CAT, "recolectarDatos().PersistenciaException: "+e.getMessage(), e);
		}
	}
	
	public void sincronizarFSDB(View vista) {
		Log.d(this.LOG_CAT, "Solicitando sincronización de datos");
		this.nucleo.sincronizarFSDB();
	}
}

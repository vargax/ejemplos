package co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.GestionDatosRemoto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.uniandes.arquesoft.exp3.fsdb.nucleo.persistencia.Registro;

public class GestionDatosRemoto {
	// -------------------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------------------
	private final String HOST = "192.168.9.112";
	private final String PUERTO = "6745";
	private final String DIRECCION_ENVIO_REGISTROS = "/REST/JSON/tensiometro/registro";
	
	private final String CORRECTO = "true"; 
	// -------------------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------------------
	public boolean enviarDatos(ArrayList<Registro> registros) throws JSONException, ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		
		HttpPut put = new HttpPut("http://"+HOST+":"+PUERTO+DIRECCION_ENVIO_REGISTROS);
		put.setHeader("content-type", "application/json");
		
		JSONObject json = new JSONObject();
		for(Registro r : registros) {
			json.put("id", r.getId());
			json.put("fechaHora", r.getFechaHora());
			json.put("dato", r.getDato());
		}
		
		StringEntity se = new StringEntity(json.toString());
		put.setEntity(se);
		
		HttpResponse resp = client.execute(put);
		return EntityUtils.toString(resp.getEntity()).equals(CORRECTO);
	}

}

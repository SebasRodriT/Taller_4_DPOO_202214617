package uniandes.dpoo.aerolinea.persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public class PersistenciaAerolineaJson  {
	public void cargarAerolinea​(String archivo, Aerolinea aerolinea) throws InformacionInconsistenteException{
		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
	        String linea;
	        while ((linea = reader.readLine()) != null) {
	            // Procesar cada línea del archivo
	            procesarLineaAerolinea(linea, aerolinea);
	        }
	    }
		
	public void procesarLineaAerolinea(String linea, Aerolinea aerolinea1) {
		Gson gson = new Gson();
		Aerolinea objeto = gson.fromJson(linea, Aerolinea.class);
		//aerolinea1.actualizar(objeto);
		}
		
	}

	public void salvarAerolinea​(java.lang.String archivo,Aerolinea aerolinea) throws IOException {
		Gson gson = new Gson();
	    String json = gson.toJson(aerolinea);

	    try (FileWriter writer = new FileWriter(archivo)) {
	        writer.write(json);
	    }
	}

}

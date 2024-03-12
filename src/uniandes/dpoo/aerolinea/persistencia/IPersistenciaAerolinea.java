package uniandes.dpoo.aerolinea.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import com.google.gson.Gson;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;

public interface IPersistenciaAerolinea {
	public static void cargarAerolinea​(String archivo, Aerolinea aerolinea) throws java.io.IOException, InformacionInconsistenteException{
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                procesarLineaAerolinea(linea, aerolinea);
            }
        }
	}
	
	public static void procesarLineaAerolinea(String linea, Aerolinea aerolinea) throws InformacionInconsistenteException {
	    Gson gson = new Gson();
	    Aerolinea objeto = gson.fromJson(linea, Aerolinea.class);
	    //aerolinea.actualizar(objeto);
	}
	
	public static void actualizar(Aerolinea aerolinea) {
	    // Actualizar los atributos de la aerolínea actual con los valores del objeto proporcionado
		Aerolinea aerolinea1 = new Aerolinea();
		Collection<Avion> avion = aerolinea.getAviones();
		Ruta ruta = aerolinea.getRuta(null);
	    //Aerolinea aerolinea1.aerolinea1.agregarAvion((Avion) avion);
	    //Aerolinea aerolinea1.agregarRuta(ruta);
	    
	}
		
		

	public static void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            escribirInfoAerolinea(bw, aerolinea);
        }
    }
	
	public static void escribirInfoAerolinea(BufferedWriter bw, Aerolinea aerolinea)
	        throws IOException {
	    bw.write("Identificador Cliente: " + aerolinea.getCliente(null).getIdentificador());
	    bw.newLine();
	    bw.write("Fecha: " + aerolinea.getVuelo(null, null).getFecha());
	    bw.newLine();
	    bw.write("Codigo Ruta: " + aerolinea.getRuta(null).getCodigoRuta());
	    bw.newLine();
	    bw.write("Cantidad: " + aerolinea.getAviones());
	    bw.newLine();
	}

}

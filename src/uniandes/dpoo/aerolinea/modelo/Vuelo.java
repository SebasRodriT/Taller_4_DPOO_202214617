package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;

/**
 * Esta clase tiene la información de un vuelo particular 
 * que cubre una ruta y se lleva a cabo en una cierta fecha.
 */
public class Vuelo extends java.lang.Object {
	// TODO completar atributos y metodos (Taller 4)
	private String fecha;
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		
	}
	
	public String getRuta() {
		return ruta;
	}

	public String getFecha() {
		return fecha;
	}
	
	public String getAvion() {
		return avion;
	}
	
	public Collection<Tiquete> getTiquetes() {
		return "";
	}
	
	public boolean equals(Object obj) {
		return true;
	}
}

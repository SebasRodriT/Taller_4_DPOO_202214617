package uniandes.dpoo.aerolinea.modelo;

/**
 * En esta clase se organiza la información básica de los aviones que realizan los vuelos
 */
public class Avion extends java.lang.Object {
	// TODO completar atributos y metodos (Taller 4)
	 private String nombre;
	 private int capacidad;
	 
	 public Avion(String nombre, int capacidad) {
		 this.nombre = nombre;
		 this.capacidad = capacidad;
		 
	 }

	public String getNombre() {
		return nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}
	 
	 

}

package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Esta clase tiene la información de un vuelo particular 
 * que cubre una ruta y se lleva a cabo en una cierta fecha.
 */
public class Vuelo extends java.lang.Object {
	// TODO completar atributos y metodos (Taller 4)
	private Ruta ruta;
	private String fecha;
	private Avion avion;
	private Map<String, Tiquete> tiquetes;
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
	}
	
	public Ruta getRuta() {
		return ruta;
	}

	public String getFecha() {
		return fecha;
	}
	
	public Avion getAvion() {
		return avion;
	}
	
	public Collection<Tiquete> getTiquetes() {
		return tiquetes.values();
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
		if (cliente.getTipoCliente() == "Natural" || cliente.getTipoCliente() == "Corporativo") {

			if (!hayEspacioSuficiente(cantidad)) {
	            throw new VueloSobrevendidoException(this);
			}
	        else {
	        	int precioPorTiquete = calculadora.calcularTarifa​(this, cliente);
				int valorTotal = precioPorTiquete * cantidad;
				
				for (int i = 0; i<cantidad; i++){
					//
					Tiquete tiquete = GeneradorTiquetes.generarTiquete(this, cliente, valorTotal);
					cliente.usarTiquetes​(this);
					tiquetes.put(fecha, tiquete);
				}
				return valorTotal;
	        }	
				
	        
		}
		
		return 0;
		
	}
	
	private boolean hayEspacioSuficiente(int cantidadTiquetes) {
		if (avion.getCapacidad() < cantidadTiquetes) {
			return true;
		}
		else {
			return false;
		}
			
	}
	
	
	
	public boolean equals(Object obj) {
		return true;
	}
}

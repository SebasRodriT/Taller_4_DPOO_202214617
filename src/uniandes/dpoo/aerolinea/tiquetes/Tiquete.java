package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Esta clase agrupa la información de un tiquete, expedido para un vuelo específico en una cierta fecha, y que fue comprado por un cliente. Cuando se crea, un tiquete no está usado. 
 * Después de que se haya realizado el vuelo, el tiquete debe quedar marcado como usado.
 */

public class Tiquete extends java.lang.Object {
	private String codigo;
	private int tarifa;
	private boolean usado;
	private Vuelo vuelo;
	private Cliente clienteComprador;
	
	public Tiquete(String codigo, Vuelo vuelo, Cliente clienteComprador, int tarifa) {
		this.codigo = codigo;
		this.clienteComprador = clienteComprador;
		this.tarifa = tarifa;
	}
	
	public Cliente getCliente( ) {
		return clienteComprador;
	}
	
	public Vuelo getVuelo() {
		return vuelo;
	}

	public String getCodigo() {
		return codigo;
	}

	public int getTarifa() {
		return tarifa;
	}
	
	public void marcarComoUsado() {
		 this.usado = true;
	}
	
	public boolean esUsado() {
		return usado;
	}
	
	
	
	

}

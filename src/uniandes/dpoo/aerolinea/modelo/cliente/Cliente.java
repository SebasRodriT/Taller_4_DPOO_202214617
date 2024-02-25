package uniandes.dpoo.aerolinea.modelo.cliente;

import uniandes.dpoo.aerolinea.modelo.Vuelo;

/**
 * Esta clase abstracta define e implementa los aspectos que son comunes para todos los tipos de clientes de la Aerolínea Cada cliente, sin importar su tipo, tiene una lista de tiquetes usados y sin usar.
 */

public abstract class Cliente extends java.lang.Object{
	public Cliente() {
	}
	
	public abstract String getTipoCliente();
	
	public abstract String getIdentificador();
	
	public void agregarTiquete​(Tiquete tiquete) {
	}
	
	public int calcularValorTotalTiquetes() {
		return 0;
	}
	
	public void usarTiquetes​(Vuelo vuelo) {
	}

}

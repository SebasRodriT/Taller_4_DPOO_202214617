package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Esta clase abstracta define e implementa los aspectos que son comunes para todos los tipos de clientes de la Aerolínea Cada cliente, sin importar su tipo, tiene una lista de tiquetes usados y sin usar.
 */

public abstract class Cliente extends java.lang.Object{
	private List<Tiquete> tiquetesSinUsar;
	private List<Tiquete> tiquetesUsados;
	public Cliente() {
		List<Tiquete> tiquetesSinUsar = new ArrayList<Tiquete>();
		List<Tiquete> tiquetesUsados = new ArrayList<Tiquete>();
	}
	
	public abstract String getTipoCliente();
	
	public abstract String getIdentificador();
	
	public void agregarTiquete​(Tiquete tiquete) {
		tiquetesSinUsar.add(tiquete);
	}
	
	public int calcularValorTotalTiquetes() {
		int size = tiquetesSinUsar.size();
		int i = 0;
		int precio_total = 0;
		for (i=0; i<size; i++) {
			int precio = tiquetesSinUsar.get(i).getTarifa(); //Ayuda
			precio_total = precio + precio_total;
		}
		return precio_total;
		
	}
	
	public void usarTiquetes​(Vuelo vuelo) {
		List<Tiquete> tiquetesVuelo = (List<Tiquete>) vuelo.getTiquetes();
		
        for (int i = 0; i < tiquetesVuelo.size(); i++) {
            for (int j = 0; j < tiquetesSinUsar.size(); j++) {
            	Tiquete tiquete = tiquetesSinUsar.get(j);
                if (tiquete.getCliente().getIdentificador().equals(tiquetesVuelo.get(i).getCliente().getIdentificador()) && !tiquete.esUsado()) {
                    tiquete.marcarComoUsado();
                    tiquetesUsados.add(tiquete);
                }
            }
        }
	}

}

package uniandes.dpoo.aerolinea.modelo.tarifas;

import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Esta clase se utiliza para calcular las tarifas en temporada alta, cuando los vuelos no tienen descuento y se aplica la misma tarifa por kilómetro a todos los clientes.
 */

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
	
	protected int COSTO_POR_KM = 1000;
	
	@Override
	public int calcularCostoBase​(Vuelo vuelo,Cliente cliente) {
		Aeropuerto origen = vuelo.getRuta().getOrigen();
		Aeropuerto destino = vuelo.getRuta().getDestino();
		int distancia = Aeropuerto.calcularDistancia(origen, destino);
		int costo_base = COSTO_POR_KM * distancia;
		return costo_base;
	}
	@Override
	public double calcularPorcentajeDescuento​(Cliente cliente) {
		if (cliente.getTipoCliente() == "Natural" || cliente.getTipoCliente() == "Corporativo")  {
			return 1;
		}
		else {
			return 0;
		}
	}
}

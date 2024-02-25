package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Esta clase se utiliza para calcular las tarifas en temporada alta, cuando los vuelos no tienen descuento y se aplica la misma tarifa por kilómetro a todos los clientes.
 */

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
	
	protected int COSTO_POR_KM = 1000;
	@Override
	public int calcularCostoBase​(Vuelo vuelo,Cliente cliente) {
		return 0;
	}
	@Override
	public double calcularPorcentajeDescuento​(Cliente cliente) {
		return 1.0;
	}
}

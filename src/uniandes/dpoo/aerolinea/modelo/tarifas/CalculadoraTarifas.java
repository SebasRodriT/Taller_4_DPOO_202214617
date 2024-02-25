package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Esta clase abstracta define el contrato para las posibles calculadoras de tarifa e implementa algunos métodos que pueden ser utilizadas en varias implementaciones.
 */

public abstract class CalculadoraTarifas extends java.lang.Object {
	
	public static double IMPUESTO = 0.28;
	
	public int calcularTarifa​(Vuelo vuelo,Cliente cliente) {
		return 0;
	}
	
	protected abstract int calcularCostoBase​(Vuelo vuelo,Cliente cliente);
	
	protected abstract double calcularPorcentajeDescuento​(Cliente cliente);
	
	protected int calcularDistanciaVuelo​(Ruta ruta) {
		return 0;
	}
	
	protected int calcularValorImpuestos​(int costoBase) {
		return 0;
	}
}

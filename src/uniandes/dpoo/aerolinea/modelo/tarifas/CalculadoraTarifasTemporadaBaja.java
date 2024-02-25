package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Esta clase se utiliza para calcular las tarifas en temporada baja. En temporada baja, los clientes que son personas naturales tienen una tarifa base diferente a la de los clientes corporativos. Adicionalmente, los clientes 
 * corporativos tienen un descuento diferente según el tamaño (los clientes naturales no tienen descuento).
 */

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{
	
	protected int COSTO_POR_KM_NATURAL = 600;
	protected int COSTO_POR_KM_CORPORATIVO = 900;
	protected double DESCUENTO_PEQ = 0.02;
	protected double DESCUENTO_MEDIANAS = 0.1;
	protected double DESCUENTO_GRANDES = 0.2;
	
	public int calcularCostoBase​(Vuelo vuelo,Cliente cliente) {
		return 0;
	}
	public double calcularPorcentajeDescuento​(Cliente cliente) {
		return 1.0;
	}
	
	
	
	
	

}

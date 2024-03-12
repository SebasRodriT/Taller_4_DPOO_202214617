package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

 
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
		Aeropuerto origen = vuelo.getRuta().getOrigen();
		Aeropuerto destino = vuelo.getRuta().getDestino();
		int distancia = Aeropuerto.calcularDistancia(origen, destino);
		if (cliente.getTipoCliente() == "Natural") {
			int costo_base = COSTO_POR_KM_NATURAL * distancia;
			return costo_base;
			
		}
		else if (cliente.getTipoCliente() == "Corporativo" ){
			ClienteCorporativo clienteCorporativo = (ClienteCorporativo) cliente;
			if (clienteCorporativo.getTamanoEmpresa() == 1) {
				int costo_base = COSTO_POR_KM_CORPORATIVO * distancia;
				int costo_total = (int) (costo_base - (costo_base*DESCUENTO_GRANDES));
				return costo_total;
			}
			else if (clienteCorporativo.getTamanoEmpresa() == 2) {
				int costo_base = COSTO_POR_KM_CORPORATIVO * distancia;
				int costo_total = (int) (costo_base - (costo_base*DESCUENTO_MEDIANAS));
				return costo_total;
			}
			else {
				int costo_base = COSTO_POR_KM_CORPORATIVO * distancia;
				int costo_total = (int) (costo_base - (costo_base*DESCUENTO_PEQ));
				return costo_total;
			}
		}
		else {
			return 0;
			
		}
	}
	public double calcularPorcentajeDescuento​(Cliente cliente) {
		if (cliente.getTipoCliente() == "Natural" || cliente.getTipoCliente() == "Corporativo")  {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	
	
	
	

}

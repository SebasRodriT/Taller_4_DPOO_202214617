package uniandes.dpoo.aerolinea.modelo.cliente;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son personas naturales
 */

public class ClienteNatural extends Cliente {
	
	public static String NATURAL = "Natural";
	private String nombre;
	
	public ClienteNatural(String nombre) {
		this.nombre = nombre;
		
	}
	
	@Override
	public String getIdentificador() {
		// TODO Auto-generated method stub AYUDA
		return null;
	}

	@Override
	public String getTipoCliente() {
		// TODO Auto-generated method stub
		return NATURAL;
	}

	
	

	
	
	
}

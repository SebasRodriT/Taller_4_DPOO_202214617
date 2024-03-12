package uniandes.dpoo.aerolinea.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaAlta;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifasTemporadaBaja;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaAerolinea;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaTiquetes;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * En esta clase se organizan todos los aspectos relacionados con una Aerolínea.
 * 
 * Por un lado, esta clase cumple un rol central como estructurador para todo el resto de elementos: directa o indirectamente, todos están contenidos y se pueden acceder a
 * través de la clase Aerolínea.
 * 
 * Por otro lado, esta clase implementa algunas funcionalidades adicionales a su rol como estructurador, para lo cual se apoya en las otras clases que hacen parte del
 * proyecto.
 */
public class Aerolinea extends java.lang.Object
{
    /**
     * Una lista con los aviones de los que dispone la aerolínea
     */
    private List<Avion> aviones;

    /**
     * Un mapa con las rutas que cubre la aerolínea.
     * 
     * Las llaves del mapa son el código de la ruta, mientras que los valores son las rutas
     */
    private Map<String, Ruta> rutas;

    /**
     * Una lista de los vuelos programados por la aerolínea
     */
    private List<Vuelo> vuelos;

    /**
     * Un mapa con los clientes de la aerolínea.
     * 
     * Las llaves del mapa son los identificadores de los clientes, mientras que los valores son los clientes
     */
    private Map<String, Cliente> clientes;

    /**
     * Construye una nueva aerolínea con un nombre e inicializa todas las contenedoras con estructuras vacías
     */
    public Aerolinea( )
    {
        aviones = new LinkedList<Avion>( );
        rutas = new HashMap<String, Ruta>( );
        vuelos = new LinkedList<Vuelo>( );
        clientes = new HashMap<String, Cliente>( );
    }

    // ************************************************************************************
    //
    // Estos son los métodos que están relacionados con la manipulación básica de los atributos
    // de la aerolínea (consultar, agregar)
    //
    // ************************************************************************************

    /**
     * Agrega una nueva ruta a la aerolínea
     * @param ruta
     */
    public void agregarRuta( Ruta ruta )
    {
        this.rutas.put( ruta.getCodigoRuta( ), ruta );
    }

    /**
     * Agrega un nuevo avión a la aerolínea
     * @param avion
     */
    public void agregarAvion( Avion avion )
    {
        this.aviones.add( avion );
    }

    /**
     * Agrega un nuevo cliente a la aerolínea
     * @param cliente
     */
    public void agregarCliente( Cliente cliente )
    {
        this.clientes.put( cliente.getIdentificador( ), cliente );
    }

    /**
     * Verifica si ya existe un cliente con el identificador dado
     * @param identificadorCliente
     * @return Retorna true si ya existía un cliente con el identificador, independientemente de su tipo
     */
    public boolean existeCliente( String identificadorCliente )
    {
        return this.clientes.containsKey( identificadorCliente );
    }

    /**
     * Busca el cliente con el identificador dado
     * @param identificadorCliente
     * @return Retorna el cliente con el identificador, o null si no existía
     */
    public Cliente getCliente( String identificadorCliente )
    {
        return this.clientes.get( identificadorCliente );
    }

    /**
     * Retorna todos los aviones de la aerolínea
     * @return
     */
    public Collection<Avion> getAviones( )
    {
        return aviones;
    }

    /**
     * Retorna todas las rutas disponibles para la aerolínea
     * @return
     */
    public Collection<Ruta> getRutas( )
    {
        return rutas.values( );
    }

    /**
     * Retorna la ruta de la aerolínea que tiene el código dado
     * @param codigoRuta El código de la ruta buscada
     * @return La ruta con el código, o null si no existe una ruta con ese código
     */
    public Ruta getRuta( String codigoRuta )
    {
        return rutas.get( codigoRuta );
    }

    /**
     * Retorna todos los vuelos de la aerolínea
     * @return
     */
    public Collection<Vuelo> getVuelos( )
    {
        return vuelos;
    }

    /**
     * Busca un vuelo dado el código de la ruta y la fecha del vuelo.
     * @param codigoRuta
     * @param fechaVuelo
     * @return Retorna el vuelo que coincide con los parámetros dados. Si no lo encuentra, retorna null.
     */
    public Vuelo getVuelo( String codigoRuta, String fechaVuelo )
    {
        // TODO implementar
    	for (Vuelo vuelo: vuelos) {
    		if(vuelo.getRuta().equals(codigoRuta) && vuelo.getFecha().equals(fechaVuelo)) {
    			return vuelo;
    		}
    	}
        return null; // No se encontró ningún vuelo
    }

    /**
     * Retorna todos los clientes de la aerolínea
     * @return
     */
    public Collection<Cliente> getClientes( )
    {
        return clientes.values( );
    }

    /**
     * Retorna todos los tiquetes de la aerolínea, los cuales se recolectan vuelo por vuelo
     * @return
     */
    public Collection<Tiquete> getTiquetes( )
    {
        // TODO implementar (AYUDA)
    	List<Tiquete> tiquetesAerolinea = new ArrayList<>();

        // Recorrer cada vuelo
        for (Vuelo vuelo : vuelos) {
            // Obtener los tiquetes del vuelo actual
            List<Tiquete> tiquetesVuelo = (List<Tiquete>) vuelo.getTiquetes();
            // Agregar los tiquetes del vuelo actual a la colección acumulativa
            tiquetesAerolinea.addAll(tiquetesVuelo);
        }

        // Retornar la colección de tiquetes de la aerolínea
        return tiquetesAerolinea;
    }

    // ************************************************************************************
    //
    // Estos son los métodos que están relacionados con la persistencia de la aerolínea
    //
    // ************************************************************************************

    /**
     * Carga toda la información de la aerolínea a partir de un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas leyendo el archivo
     * @throws InformacionInconsistenteException Lanza esta excepción si durante la carga del archivo se encuentra información que no es consistente
     */
    public void cargarAerolinea( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, InformacionInconsistenteException
    {
        // TODO implementar (AYUDA)
    if (!tipoArchivo.equals(CentralPersistencia.JSON.toString()) && !tipoArchivo.equals(CentralPersistencia.PLAIN.toString())) {
        throw new TipoInvalidoException("Tipo de archivo inválido");
    }

    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            // Aquí procesas cada línea del archivo según el tipo de archivo
            if (tipoArchivo.equals(CentralPersistencia.JSON.toString())) {
            	procesarArchivoJSON(archivo);
            }
        }
    } catch (IOException e) {
        throw new IOException("Error leyendo el archivo", e);
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                // Manejar error de cierre de archivo
            }
        }
    }

    // Verificar consistencia de la información cargada, si es necesario
    if (!informacionEsConsistente()) {
        throw new InformacionInconsistenteException("Información inconsistente encontrada durante la carga del archivo");
    }
}

	private boolean informacionEsConsistente() {
	    // Implementa la lógica para verificar si la información cargada es consistente
	    return true; // Ejemplo: siempre devuelve true por ahora
	}

// Definición de excepciones personalizadas
	public class TipoInvalidoException extends Exception {
	    public TipoInvalidoException(String mensaje) {
	        super(mensaje);
	    }
	}

	public class InformacionInconsistenteException extends Exception {
	    public InformacionInconsistenteException(String mensaje) {
	        super(mensaje);
	    }
	}
	
	private void procesarArchivoJSON(String archivo) throws IOException {
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        Vuelo[] vuelosArray = gson.fromJson(reader, Vuelo[].class);
        vuelos = Arrays.asList(vuelosArray);
        reader.close();
    }


    /**
     * Salva la información de la aerlínea en un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas escribiendo en el archivo
     */
    public void salvarAerolinea( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException
    {
        // TODO implementar (Ayuda)
    	if (!tipoArchivo.equals(CentralPersistencia.JSON.toString()) && !tipoArchivo.equals(CentralPersistencia.PLAIN.toString())) {
            throw new TipoInvalidoException("Tipo de archivo inválido");
        }

        if (tipoArchivo.equals(CentralPersistencia.JSON.toString())) {
            salvarAerolineaJSON(archivo);
        } else {
            salvarAerolineaPLAIN(archivo);
        }
         
    }
     
    private void salvarAerolineaJSON(String archivo) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(vuelos); // Convertir la lista de vuelos a JSON
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
        writer.write(json);
        writer.close();
    }
    
    private void salvarAerolineaPLAIN(String archivo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
        for (Vuelo vuelo : vuelos) {
            String linea = vuelo.getRuta().getCodigoRuta()+ "," + vuelo.getRuta().getOrigen()+ "," + vuelo.getRuta().getDestino() + "," + vuelo.getRuta().getDuracion();
            writer.write(linea);
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Carga toda la información de sobre los clientes y tiquetes de una aerolínea a partir de un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas leyendo el archivo
     * @throws InformacionInconsistenteException Lanza esta excepción si durante la carga del archivo se encuentra información que no es consistente con la información de la
     *         aerolínea
     * @throws uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException 
     * @throws uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException 
     */
    public void cargarTiquetes( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, InformacionInconsistenteException, uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException, uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException
    {
        IPersistenciaTiquetes cargador = CentralPersistencia.getPersistenciaTiquetes( tipoArchivo );
        cargador.cargarTiquetes( archivo, this );
    }

    /**
     * Salva la información de la aerlínea en un archivo
     * @param archivo El nombre del archivo.
     * @param tipoArchivo El tipo del archivo. Puede ser CentralPersistencia.JSON o CentralPersistencia.PLAIN.
     * @throws TipoInvalidoException Se lanza esta excepción si se indica un tipo de archivo inválido
     * @throws IOException Lanza esta excepción si hay problemas escribiendo en el archivo
     * @throws uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException 
     */
    public void salvarTiquetes( String archivo, String tipoArchivo ) throws TipoInvalidoException, IOException, uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException
    {
        IPersistenciaTiquetes cargador = CentralPersistencia.getPersistenciaTiquetes( tipoArchivo );
        cargador.salvarTiquetes( archivo, this );
    }

    // ************************************************************************************
    //
    // Estos son los métodos que están relacionados con funcionalidades interesantes de la aerolínea
    //
    // ************************************************************************************

    /**
     * Agrega un nuevo vuelo a la aerolínea, para que se realice en una cierta fecha, en una cierta ruta y con un cierto avión.
     * 
     * Este método debe verificar que el avión seleccionado no esté ya ocupado para otro vuelo en el mismo intervalo de tiempo del nuevo vuelo. No es necesario verificar que
     * se encuentre en el lugar correcto (origen del vuelo).
     * 
     * @param fecha La fecha en la que se realizará el vuelo
     * @param codigoRuta La ruta que cubirá el vuelo
     * @param nombreAvion El nombre del avión que realizará el vuelo
     * @throws Exception Lanza esta excepción si hay algún problema con los datos suministrados
     */
    public void programarVuelo( String fecha, String codigoRuta, String nombreAvion ) throws Exception
    {
        // TODO Implementar el método (Ayuda)
    	Avion avionSeleccionado = null;
    	for (Avion avion : aviones) {
            if (avion.getNombre().equals(nombreAvion)) {
                avionSeleccionado = avion;
                break;
             }
         }
    	
    	if (avionSeleccionado == null) {
            throw new Exception("El avión especificado no está disponible.");
        }
    	
    	for (Vuelo vuelo : vuelos) {
            if (vuelo.getAvion().equals(avionSeleccionado) && vuelo.getFecha().equals(fecha)) {
                throw new Exception("El avión ya está ocupado para otro vuelo en la misma fecha.");
            }
        }
    	
    	Vuelo nuevoVuelo = new Vuelo(getRuta(null), fecha, avionSeleccionado);
        vuelos.add(nuevoVuelo);
    }
 

    /**
     * Vende una cierta cantidad de tiquetes para un vuelo, verificando que la información sea correcta.
     * 
     * Los tiquetes deben quedar asociados al vuelo y al cliente.
     * 
     * Según la fecha del vuelo, se deben usar las tarifas de temporada baja (enero a mayo y septiembre a noviembre) o las de temporada alta (el resto del año).
     * 
     * @param identificadorCliente El identificador del cliente al cual se le venden los tiquetes
     * @param fecha La fecha en la que se realiza el vuelo para el que se van a vender los tiquetes
     * @param codigoRuta El código de la ruta para el que se van a vender los tiquetes
     * @param cantidad La cantidad de tiquetes que se quieren comprar
     * @return El valor total de los tiquetes vendidos
     * @throws VueloSobrevendidoException Se lanza esta excepción si no hay suficiente espacio en el vuelo para todos los pasajeros
     * @throws Exception Se lanza esta excepción para indicar que no se pudieron vender los tiquetes por algún otro motivo
     */
    public int venderTiquetes( String identificadorCliente, String fecha, String codigoRuta, int cantidad ) throws VueloSobrevendidoException, Exception
    {
        // TODO Implementar el método
    	CalculadoraTarifasTemporadaAlta calculadoraTemporadaAlta = new CalculadoraTarifasTemporadaAlta();
    	CalculadoraTarifasTemporadaBaja calculadoraTemporadaBaja = new CalculadoraTarifasTemporadaBaja();
    	
    	Cliente cliente = clientes.get(identificadorCliente);
    	int capacidad = getVuelo(codigoRuta, fecha).getAvion().getCapacidad();
        if (cliente == null) {
            throw new Exception("El cliente no está registrado en el sistema.");
        }

        // Encontrar el vuelo correspondiente
        Vuelo vuelo = getVuelo(codigoRuta, fecha);
        //Tamaño tiquetes mayor a capacidad 

        if (vuelo == null) {
            throw new Exception("No se encontró el vuelo especificado.");
        }

        // Verificar si hay suficiente espacio en el vuelo
        if (cantidad  < capacidad) {
        	// Calcular el precio de los tiquetes según la temporada
        	String mesStr = fecha.substring(5, 7); 
            int mes = Integer.parseInt(mesStr);
            if (mes == 1 || mes == 2 || mes == 3 || mes == 4 || mes == 5 || mes == 9 || mes == 10 || mes == 11 ) {
            	int precio_unitario = calculadoraTemporadaBaja.calcularTarifa​(vuelo, cliente);
            	int valorTotal = (int) (cantidad * precio_unitario);
            	vuelo.venderTiquetes(cliente, calculadoraTemporadaBaja, cantidad);
                cliente.usarTiquetes​(vuelo);
                return valorTotal;

            }
            else if (mes == 6 || mes == 7 || mes == 8 || mes == 12 ) {
            	int precio_unitario = calculadoraTemporadaAlta.calcularTarifa​(vuelo, cliente);
            	int valorTotal = (int) (cantidad * precio_unitario);
            	vuelo.venderTiquetes(cliente, calculadoraTemporadaAlta, cantidad);
                cliente.usarTiquetes​(vuelo);
                return valorTotal;
            }
            else {
            	return 0;
            }
            
        }
        else {
        	throw new VueloSobrevendidoException(vuelo);
        }

        
        
    }

    /**
     * Registra que un cierto vuelo fue realizado
     * @param fecha La fecha del vuelo
     * @param codigoRuta El código de la ruta que recorrió el vuelo
     */
    public void registrarVueloRealizado( String fecha, String codigoRuta )
    {
        // TODO Implementar el método
    	Vuelo vuelo = null;
    	for (Vuelo v: vuelos) {
    		if (v.getRuta().getCodigoRuta().equals(codigoRuta) && v.getFecha().equals(fecha)) {
    			vuelo = v;
    			break;
    		}
    	}
    	vuelos.remove(vuelo);
    
    }

    /**
     * Calcula cuánto valen los tiquetes que ya compró un cliente dado y que todavía no ha utilizado
     * @param identificadorCliente El identificador del cliente
     * @return La suma de lo que pagó el cliente por los tiquetes sin usar
     */
    public String consultarSaldoPendienteCliente( String identificadorCliente )
    {
		// TODO Implementar el método
        int saldo = 0;
        Cliente cliente = null;
        if (this.existeCliente(identificadorCliente)) {
        	cliente = this.getCliente(identificadorCliente);
        	saldo = cliente.calcularValorTotalTiquetes();
        }
        return "El saldo de los tiquetes que no se han utilizado es de " + saldo;
    }

}

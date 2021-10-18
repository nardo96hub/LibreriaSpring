package egg.web.libreria.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egg.web.libreria.entidades.Cliente;
import egg.web.libreria.repositorios.RepositorioCliente;

@Service
public class ServicioCliente {

	@Autowired
	private RepositorioCliente clienteRep;
	
	public void guardarCliente(Long dni,String nombre,String apellido,String telefono) throws Exception {// Cuando funcione web agregar WebException

		//validar(nombre);
		Cliente cliente=clienteRep.buscarClienteNombApe(nombre,apellido);
		
		if(cliente==null) {
				cliente=new Cliente();
				cliente.setNombre(nombre);
		cliente.setAlta(true);
		cliente.setDni(dni);
		cliente.setApellido(apellido);
		cliente.setTelefono(telefono);

		clienteRep.save(cliente);
		}else {
			if(!cliente.getAlta()) {
				cliente.setAlta(true);
				clienteRep.save(cliente);
			}
		}
		
		
	}

	public Cliente altaCliente(Long id) throws Exception {
		Cliente c = clienteRep.buscarClienteId(id);
		if (c == null) {
			throw new Exception("No se encontro autor");
		} else {
			c.setAlta(true);

			return clienteRep.save(c);
		}

	}

	public Cliente bajaCliente(Long id)  {
		Cliente a = clienteRep.buscarClienteId(id);
		
			a.setAlta(false);

			return clienteRep.save(a);
	

	}

	public Cliente editarCliente(Long id,Long dni,String nombre,String apellido,String telefono) throws Exception {
		Optional<Cliente> r = clienteRep.findById(id);// Optional porque findByI
		if (r.isEmpty()) {
			throw new Exception("No se encontro autor");
		} else {
			Cliente c = r.get();
			c.setNombre(nombre);
			
			c.setAlta(true);
			c.setDni(dni);
			c.setApellido(apellido);
			c.setTelefono(telefono);
			return clienteRep.save(c);
		}

	}

	public void eliminarCliente(Long id) throws Exception {
		/*
		 * Esta forma borra de la BDD me interesa poner en baja
		 * if(autorRep.findById(id).isEmpty()) { throw new
		 * Exception("Imposible eliminar autor ya que no existe en la BDD"); }else {
		 * autorRep.deleteById(id); }
		 */

		// Forma correcta

		Cliente cliente = bajaCliente(id);
		//System.out.println("Se elimino el autor:" + autores);

	}

	public Cliente obtenerClienteId(Long id) throws Exception {
		//Optional<Autor> r = autorRep.findById(id);
		Cliente r=clienteRep.buscarClienteId(id);
		if(r==null) {
			throw new Exception("No se encuentra autor en BDD");
		}else {
			return r;
		}
	}
	
	public Cliente obtenerClienteDni(Long dni) {
		return clienteRep.buscarClienteDni(dni);
		
	}

	public List<Cliente> listarClienteActivos() {
		return clienteRep.buscarClienteActivos();
	}

	public List<Cliente> listarTodos() {
		return clienteRep.findAll();
	}

}

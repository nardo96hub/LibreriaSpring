package egg.web.libreria.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.repositorios.RepositorioAutor;

//Etiqueta para decir al servidor que es un servicio
@Service
public class ServicioAutor {

	@Autowired // Es equivalente a new al ser interface se usa esa etiqueta
	private RepositorioAutor autorRep;

	// @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {
	// WebException.class, Exception.class })
	public void guardarAutor(String nombre) throws Exception {// Cuando funcione web agregar WebException

		//validar(nombre);
		Autor autor=autorRep.buscarAutorNombre(nombre);
		
		if(autor==null) {
				autor=new Autor();
				autor.setNombre(nombre);
		autor.setAlta(true);

		 autorRep.save(autor);
		}else {
			if(!autor.getAlta()) {
				autor.setAlta(true);
				autorRep.save(autor);
			}
		}
		
		
	}

	public Autor altaAutor(String id) throws Exception {
		Autor a = autorRep.buscarAutorId(id);
		if (a == null) {
			throw new Exception("No se encontro autor");
		} else {
			a.setAlta(true);

			return autorRep.save(a);
		}

	}

	public Autor bajaAutor(String id) throws Exception {
		Autor a = autorRep.buscarAutorId(id);
		
			a.setAlta(false);

			return autorRep.save(a);
	

	}

	public Autor editarAutor(String id, String nombre) throws Exception {
		Optional<Autor> r = autorRep.findById(id);// Optional porque findByI
		if (r.isEmpty()) {
			throw new Exception("No se encontro autor");
		} else {
			Autor a = r.get();
			a.setNombre(nombre);
			return autorRep.save(a);
		}

	}

	public void eliminarAutor(String id) throws Exception {
		/*
		 * Esta forma borra de la BDD me interesa poner en baja
		 * if(autorRep.findById(id).isEmpty()) { throw new
		 * Exception("Imposible eliminar autor ya que no existe en la BDD"); }else {
		 * autorRep.deleteById(id); }
		 */

		// Forma correcta

		Autor autores = bajaAutor(id);
		//System.out.println("Se elimino el autor:" + autores);

	}

	public Autor obtenerAutor(String id) throws Exception {
		//Optional<Autor> r = autorRep.findById(id);
		Autor r=autorRep.buscarAutorId(id);
		if(r==null) {
			throw new Exception("No se encuentra autor en BDD");
		}else {
			return r;
		}
	}

	public List<Autor> listarActivos() {
		return autorRep.buscarActivos();
	}

	public List<Autor> listarTodos() {
		return autorRep.findAll();
	}

	/*public void validar(String nombre) throws Exception {
		if (nombre.isEmpty() || nombre.isBlank()) {
			throw new Exception("Debe ingresar nombre de Autor");
		}
		if (autorRep.exiteAutorNombre(nombre)) {
			throw new Exception("Este autor ya esta cargado");
		}
	}*/

}

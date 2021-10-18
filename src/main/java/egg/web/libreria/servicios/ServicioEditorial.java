package egg.web.libreria.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.repositorios.RepositorioEditorial;

@Service
public class ServicioEditorial {

	@Autowired
	private RepositorioEditorial repoEdi;
	
	
	public void guardarEditorial(String nombre) throws Exception{
		/*//validar(nombre);
		Editorial edi=new Editorial();
		edi.setNombre(nombre);
		edi.setAlta(true);
		
		
		return repoEdi.save(edi);*/
		
		Editorial editorial=repoEdi.buscarEditorialNombre(nombre);
		if(editorial==null) {
			editorial=new Editorial();
			editorial.setNombre(nombre);
			editorial.setAlta(true);
			repoEdi.save(editorial);
		}else {
			if(!editorial.getAlta()) {
				editorial.setAlta(true);
				repoEdi.save(editorial);
			}
		}
	}
	public Editorial altaEditorial(String id) throws Exception{
		Editorial e=repoEdi.buscarEditorialId(id);
		if(e==null) {
			throw new Exception("No se encontro editorial");
		}else {
			e.setAlta(true);
			return repoEdi.save(e);
		}
	}
	
	public void bajaEditorial(String id)throws Exception{
		Editorial e=repoEdi.buscarEditorialId(id);
		
			e.setAlta(false);
			 repoEdi.save(e);
		
	}
	public Editorial editarEditorial(String id, String nombre)throws Exception{
		Optional<Editorial> r=repoEdi.findById(id);
		if(r.isEmpty()) {
			throw new Exception("No se encontro editorial");
		}else {
			Editorial e =r.get();
			e.setNombre(nombre);
			return repoEdi.save(e);
		}
	}
	
	public void eliminarEditorial(String id)throws Exception{
		bajaEditorial(id);
		//System.out.println("Se elimino el editorial: "+editorial);

	}
	public Editorial objetenerEditorial(String id)throws Exception{
		//Optional<Editorial> oe=repoEdi.findById(id);
		Editorial e=repoEdi.buscarEditorialId(id);
		if(e==null) {
			throw new Exception("No se encuentra el editorial con el id ingresando en la BDD");
		}else {
			return e;
		}
	}
	
	public List<Editorial> listarActivos(){
		return repoEdi.buscarActivos();
	}
	public List<Editorial> listarTodos(){
		return repoEdi.findAll();
	}
	

	
	/*public void validar(String nombre) throws Exception {
		if (nombre.isEmpty() || nombre.isBlank()) {
			throw new Exception("Debe ingresar nombre de Autor");
		}
		if (repoEdi.exiteEditorialNombre(nombre)) {
			throw new Exception("Este autor ya esta cargado");
		}
	}*/
	
}

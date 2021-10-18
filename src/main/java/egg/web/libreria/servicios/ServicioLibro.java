package egg.web.libreria.servicios;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.repositorios.RepositorioAutor;
import egg.web.libreria.repositorios.RepositorioEditorial;
import egg.web.libreria.repositorios.RepositorioLibro;

@Service
public class ServicioLibro {
	@Autowired
	private RepositorioLibro RepoLibro;
	@Autowired
	private RepositorioEditorial RepoEdi;
	@Autowired
	private RepositorioAutor RepoAu;
	
	public void guardarLibro(Long isbn,String t,Integer anio,Integer ej,Integer ejP,Integer ejR,String a,String e)throws Exception {
		validaciones(anio, ej, ejP, ejR);
		
		
		//Busco autor y guardo
		Autor au=RepoAu.buscarAutorNombre(a);
		if(au==null) {
			au=new Autor();
			au.setAlta(true);
			au.setNombre(a);
			RepoAu.save(au);
		}else {
			if(!au.getAlta()) {
				au.setAlta(true);
				RepoAu.save(au);
			}	
		}
		//Busco editorial y guardo
		Editorial ed=RepoEdi.buscarEditorialNombre(e);
		if(ed==null) {
			ed=new Editorial();
			ed.setAlta(true);
			ed.setNombre(e);
			RepoEdi.save(ed);
		}else {
			if(!ed.getAlta()) {
				ed.setAlta(true);
				RepoEdi.save(ed);
			}
		}
		
		Libro l=new Libro();
		l.setIsbn(isbn);
		l.setTitulo(t);
		l.setAnio(anio);
		l.setAutor(au);
		l.setEditorial(ed);
		l.setEjemplares(ej);
		l.setEjemplaresPrestados(ejP);
		l.setEjemplaresRestantes(ejR);
		l.setAlta(true);
		
		 RepoLibro.save(l);
	}
	public Libro altaLibro(String id)throws Exception{
		Libro l=RepoLibro.buscarLibroNombre(id);
		if(l==null) {
			throw new Exception("No se encontro libro");
		}else {
			l.setAlta(true);
			return RepoLibro.save(l);
		}
	}
public Libro bajaLibro(String id)throws Exception{
	Libro l=RepoLibro.buscarLibroId(id);
	if(l==null) {
		throw new Exception("No se encontro libro");
	}else {
		l.setAlta(false);
		return RepoLibro.save(l);
	}
	}

public Libro editarLibro(String id,Long ide,String t,String a,String e,Integer anio,Integer ej,Integer ejP,Integer ejR)throws Exception{
	Optional<Libro> li=RepoLibro.findById(id);
	if(li.isEmpty()) {
		throw new Exception("No se encontro libro");
	}else {
		Libro l=li.get();
		Autor au=RepoAu.buscarAutorNombre(a);
		if(au==null) {
			au=new Autor();
			au.setAlta(true);
			au.setNombre(a);
			RepoAu.save(au);
		}else {
			if(!au.getAlta()) {
				au.setAlta(true);
				RepoAu.save(au);
			}	
		}
		//Busco editorial y guardo
		Editorial ed=RepoEdi.buscarEditorialNombre(e);
		if(ed==null) {
			ed=new Editorial();
			l.setAlta(true);
			l.setAnio(anio);
			l.setTitulo(t);
			l.setEjemplares(ej);
			l.setEjemplaresPrestados(ejP);
			l.setEjemplaresRestantes(ejR);
			l.setAutor(au);
			l.setEditorial(ed);
			RepoEdi.save(ed);
		}else {
			if(!ed.getAlta()) {
				ed.setAlta(true);
				RepoEdi.save(ed);
			}
		}
		
		l.setAlta(true);
		l.setAnio(anio);
		l.setTitulo(t);
		l.setEjemplares(ej);
		l.setEjemplaresPrestados(ejP);
		l.setEjemplaresRestantes(ejR);
		l.setAutor(au);
		l.setEditorial(ed);
		
		return RepoLibro.save(l);
	}
}
public Libro obtenerLibro(String id) throws Exception{
	Optional<Libro> l=RepoLibro.findById(id);
	if(l.isEmpty()) {
		throw new Exception("No se encuentra el id en la BDD");
	}else {
		return l.get();
	}

}
public Libro obtenerLibroNombre(String name) {
	return RepoLibro.buscarLibroNombre(name);
}
public void borrarLibro(String id)throws Exception {
	Libro libro=bajaLibro(id);
	//System.out.println("Se elimino el editorial: "+libro);
}
public List<Libro> listarActivos()
{
	return RepoLibro.buscarActivos();
}
public List<Libro> listarTodo(){
	return RepoLibro.findAll();
}

	public void validaciones(Integer a,Integer ej,Integer ejP,Integer ejR) {
		
		if(a<0) {
			a=(-a);
		}else if(a==0) a=2021;
		
		if(ej<=0) {
			
			ej=(-ej);
			if(ej==0) {
				ej=8;
			}
		}
		
		if(ejP<0) {
			ejP=(-ejP);
		}
		if(ejP>ej) {
			Integer i=ej; ej=ejP;ejP=i;
		}
		
		ejR=ej-ejP;
	}
	
	public RepositorioLibro getRepoLibro() {
		return RepoLibro;
	}
	
}

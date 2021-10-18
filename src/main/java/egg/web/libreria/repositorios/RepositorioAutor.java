package egg.web.libreria.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import egg.web.libreria.entidades.Autor;

@Repository
public interface RepositorioAutor extends JpaRepository<Autor, String>{

	@Query("SELECT a from Autor a where a.alta=true")
	public List<Autor> buscarActivos();
	@Query("SELECT a FROM Autor a")
	public List<Autor> buscarTodos();
	@Query("SELECT a from Autor a where a.nombre=:nombre")
	public Autor buscarAutorNombre(@Param("nombre") String nombre);
	
	
	
	@Query("SELECT a FROM Autor a where a.id like :id ")
	public Autor buscarAutorId(@Param("id") String i);
	
	@Query("SELECT COUNT(a)> 0 FROM Autor a where a.nombre=:nombre and a.alta=true ")
	public boolean exiteAutorNombre(@Param("nombre")String n);
}

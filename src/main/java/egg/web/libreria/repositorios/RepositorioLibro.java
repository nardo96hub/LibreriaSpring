package egg.web.libreria.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.entidades.Editorial;
import egg.web.libreria.entidades.Libro;
@Repository
public interface RepositorioLibro extends JpaRepository<Libro, String>{
	
	@Query("SELECT l FROM Libro l where l.alta=true")
	public List<Libro> buscarActivos();
	
	@Query("SELECT l FROM Libro l where l.id=:id")
	public Libro buscarLibroId(@Param("id")String id);
	
	@Query("SELECT l FROM Libro l where l.titulo=:nombre")
	public Libro buscarLibroNombre(@Param("nombre") String n);
	
	@Query("SELECT l FROM Libro l where l.editorial=:edi")
	public Libro buscarLibroEditorial(@Param("edi") Editorial e);
	
	@Query("SELECT l FROM Libro l where l.autor=:autor")
	public Libro buscarLibroAutor(@Param("autor") Autor a);

	
	@Query("SELECT COUNT(a)> 0 FROM Libro l where l.titulo=:nombre and l.alta=true ")
	public boolean exiteLibroNombre(@Param("nombre")String n);
	
	
}

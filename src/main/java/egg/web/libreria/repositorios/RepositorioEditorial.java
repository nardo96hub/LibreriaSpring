package egg.web.libreria.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import egg.web.libreria.entidades.Editorial;
@Repository //Repository=JPA
public interface RepositorioEditorial extends JpaRepository<Editorial, String> {

	@Query("SELECT e from Editorial e where e.alta=true")
	public List<Editorial> buscarActivos();
	
	@Query("SELECT e FROM Editorial e where e.nombre=:nombre ")
	public Editorial buscarEditorialNombre(@Param("nombre") String nombre);
	
	
	@Query("SELECT e FROM Editorial e where e.id like :id ")
	public Editorial buscarEditorialId(@Param("id") String i);
	
	@Query("SELECT COUNT(e)> 0 FROM Editorial e where e.nombre=:nombre and e.alta=true ")
	public boolean exiteEditorialNombre(@Param("nombre")String n);
	
}

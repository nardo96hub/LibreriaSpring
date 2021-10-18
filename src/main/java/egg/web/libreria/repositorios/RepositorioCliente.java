package egg.web.libreria.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import egg.web.libreria.entidades.Cliente;

@Repository
public interface RepositorioCliente  extends JpaRepository<Cliente, Long>{

	@Query("SELECT c FROM Cliente c where c.dni=:dni")
	public Cliente buscarClienteDni(@Param("dni") Long d);
	
	@Query("SELECT c FROM Cliente c where c.nombre=:n and c.apellido=:a")
	public Cliente buscarClienteNombApe(@Param("n") String n,@Param("a") String a);
	
	@Query("SELECT c FROM Cliente c where c.alta=true")
	public List<Cliente> buscarClienteActivos();
	
	@Query("SELECT c FROM Cliente c where c.id=:id")
	public Cliente buscarClienteId(@Param("id")Long id);
}

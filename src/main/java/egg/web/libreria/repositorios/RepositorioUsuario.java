package egg.web.libreria.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import egg.web.libreria.entidades.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, String> {
	@Query("select u from Usuario u where u.usu = :us")
	public Usuario buscarUsuario(@Param("us" )String usuario );
}

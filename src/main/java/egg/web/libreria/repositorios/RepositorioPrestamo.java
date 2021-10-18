package egg.web.libreria.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import egg.web.libreria.entidades.Prestamo;
@Repository
public interface RepositorioPrestamo extends JpaRepository<Prestamo, Long> {
	@Query("select p from Prestamo p where p.alta=true")
	public List<Prestamo> buscarActivos();
	
	@Query("select p from Prestamo p where p.libro.titulo=:name")
	public List<Prestamo> buscarPrestamoNombreLibro(@Param("name") String nombre);

	@Query("select p from Prestamo p where p.cliente.dni=:DNI")
	public Prestamo buscarPrestamoDniCliente(@Param("DNI") Long dni);
	
	@Query("select p from Prestamo p where p.id=:id")
	public Prestamo buscarPrestamoId(@Param("id") Long id);


}

package egg.web.libreria.servicios;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egg.web.libreria.repositorios.RepositorioPrestamo;
import egg.web.libreria.entidades.Cliente;
import egg.web.libreria.entidades.Libro;
import egg.web.libreria.entidades.Prestamo;
@Service
public class ServicioPrestamo {

	@Autowired
	private RepositorioPrestamo RepoPrestamo;
	@Autowired
	private ServicioCliente ServiCliente;
	@Autowired
	private ServicioLibro ServiLibro;
	
	
	
	public int crearPrestamo(Date fechaP,Date fechaD, String tituloL,Long dniC) throws Exception {
	
			Libro l=ServiLibro.obtenerLibroNombre(tituloL);
			Cliente c=ServiCliente.obtenerClienteDni(dniC);
			
			if(l!=null && c!=null) {
				Prestamo p=new Prestamo();
				guardarPrestamo(p,fechaP,fechaD,l,c);
				return 0;
			}else {
				if(l==null && c==null) {
					return 1;
				}else if(l!=null && c==null) {
					return 2;
				}else return 3;
				
			}
	
		
		
		
		
	}
	
	public void guardarPrestamo(Prestamo p, Date pre,Date dev,Libro l, Cliente c) {
		p.setLibro(l);
		p.setCliente(c);
		p.setFechaPrestamo(pre);
		p.setFechaDevolucion(dev);
		p.setAlta(true);
		RepoPrestamo.save(p);
		
	}
	public void editarPrestamo(Long id, Date pre,Date dev,String l, Long c) {
		
		Prestamo p=RepoPrestamo.buscarPrestamoId(id);
		Cliente cl=ServiCliente.obtenerClienteDni(c);
		Libro li=ServiLibro.getRepoLibro().buscarLibroNombre(l);
		guardarPrestamo(p, pre, dev, li, cl);
		
	}
	
	public void modificarAlta(Long id) throws Exception {
		
		Prestamo p=RepoPrestamo.buscarPrestamoId(id);
		if(p!=null) {
			p.setAlta(!p.isAlta());
			RepoPrestamo.save(p);
		}else {
			throw new Exception("Error no se encontro Prestamo");
		}
		
	}
	public void eliminarPrestamo(Long id){
		try {
			modificarAlta(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public  Prestamo obtenerPrestamo(Long i) {
		return RepoPrestamo.buscarPrestamoId(i);
	}
	
	
	public List<Prestamo> listarPrestamoActivos(){
		return RepoPrestamo.buscarActivos();
	}
	public List<Prestamo>listarTodos(){
		return RepoPrestamo.findAll();
	}
}

package egg.web.libreria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egg.web.libreria.entidades.*;

import egg.web.libreria.servicios.*;


@Controller
@RequestMapping("/")//Primera vista index es la posicion de partida
public class PrincipalControlador {
	
	@Autowired
	private ServicioEditorial serEdi;
	@Autowired
	private ServicioAutor serAutor;
	@Autowired
	private ServicioLibro serLibro;
	@Autowired
	private ServicioCliente serCliente;
	@Autowired
	private ServicioPrestamo serPrestamo;
	
	
	@GetMapping("/")
	public String index() {
		return "index.html"; //Regresa al html index
	}
	@GetMapping("/mostrartodo")
	public String lista(ModelMap modelo) {
		/*
		 * List<Autor> la=serAutor.listarActivos();
		modelo.addAttribute("listaautores",la);
		 */
		List<Autor> la=serAutor.listarTodos();
		List<Editorial> le=serEdi.listarTodos();
		List<Libro> ll=serLibro.listarTodo();
		List<Cliente> lc=serCliente.listarTodos();
		List<Prestamo> lp=serPrestamo.listarTodos();
		modelo.addAttribute("autores",la);
		modelo.addAttribute("editoriales",le);
		modelo.addAttribute("libros",ll);
		modelo.addAttribute("clientes",lc);
		modelo.addAttribute("prestamos",lp);
		return "listarTodo.html";
		
	}
	
}

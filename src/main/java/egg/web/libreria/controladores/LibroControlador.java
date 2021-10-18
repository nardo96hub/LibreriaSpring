package egg.web.libreria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egg.web.libreria.entidades.Libro;
import egg.web.libreria.servicios.ServicioLibro;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
	@Autowired
	private ServicioLibro serLib;
	
	//Controladores carga
	@GetMapping("/cargalibro")
	public String libro() {
		return "crearLibro";
	}
	@PostMapping("/cargalibro")
	public String libro(@RequestParam Long isbn,@RequestParam String titulo,@RequestParam String nombreAutor
	 ,@RequestParam String nombreEditorial,@RequestParam Integer anio,@RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,@RequestParam Integer ejemplaresRestantes
	 ) {
		
		try {
			serLib.guardarLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, nombreAutor, nombreEditorial);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/libro/lista";
	}
	
	//Controlador para listar Libros
	@GetMapping("/lista")
	public String listaLibro(ModelMap mod) {
		List<Libro> ll=serLib.listarActivos();
		mod.addAttribute("listalibro",ll);
		return "listarLibro";
		
	}
	
	//Controlador para eliminar libro
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable String id) {
		try {
			serLib.borrarLibro(id);
			return"redirect:/libro/lista";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "redirect:/";
			
		}
	}
	
	@GetMapping("/editar/{id}")
	public String editar(ModelMap mod,@PathVariable String id) {
		
		try {
			Libro l=serLib.obtenerLibro(id);
			mod.addAttribute(l);
			return "editarLibro";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}
		
	
	}
	
	@PostMapping("/editar/{id}")
	public String editar(@PathVariable String id,@RequestParam Long isbn,@RequestParam String titulo,@RequestParam String nombreAutor
			 ,@RequestParam String nombreEditorial,@RequestParam Integer anio,@RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,@RequestParam Integer ejemplaresRestantes
			 )
	{
		try {
			serLib.editarLibro(id, isbn, titulo, nombreAutor, nombreEditorial, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes);
			return "redirect:/libro/lista";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "crearLibro";
		}
		
	}
	
	
}

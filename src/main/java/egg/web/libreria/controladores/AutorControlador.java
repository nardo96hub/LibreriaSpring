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

import egg.web.libreria.entidades.Autor;
import egg.web.libreria.servicios.ServicioAutor;

@Controller
@RequestMapping("/autor") //Cambiar luego por autores
public class AutorControlador {
	
	@Autowired
	private ServicioAutor serAutor;
	@GetMapping("/registro")
	public String Autor() {
		return "crearAutor.html";
	}
	@PostMapping("/registro")
	public String crearAutor(@RequestParam String nombre) 
	{
			try {
				serAutor.guardarAutor(nombre);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return("redirect:/autor/lista");
		
	}
	
	@GetMapping("/lista")
	public String listarautores(ModelMap modelo) {
		List<Autor> la=serAutor.listarActivos();
		modelo.addAttribute("listaautores",la);
		return "listarAutor.html";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable String id) {
		try {
			serAutor.eliminarAutor(id);
			//System.out.println(serAutor.obtenerAutor(id).getAlta());
		
			return "redirect:/autor/lista";
			
			//return "redirect:/autor/lista";
		}catch(Exception e) {
			return "redirect:/";
		}
	}
	
	
	@PostMapping("/editar/{id}")
	public String editarAutor(@PathVariable String id ,@RequestParam String nombre) {
		try {
			serAutor.editarAutor(id, nombre);
			return "redirect:/autor/lista";
		} catch (Exception e) {
	
		e.getMessage();
		return "crearAutor";
		}
		
		
	
	}
	@GetMapping("/editar/{id}")
	public String editarAutor(ModelMap mod,@PathVariable String id) throws Exception {
		
		
		Autor	a = serAutor.obtenerAutor(id);
		
		mod.addAttribute(a);
		return "editar_AutorEditorial";
	}
	
	
}

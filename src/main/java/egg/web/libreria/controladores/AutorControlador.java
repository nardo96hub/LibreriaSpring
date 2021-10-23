package egg.web.libreria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("isAuthenticated()")
@RequestMapping("/libreria/autor") //Cambiar luego por autores
public class AutorControlador {
	
	@Autowired
	private ServicioAutor serAutor;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/registro")
	public String Autor() {
		return "crearAutor.html";
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/registro")
	public String crearAutor(@RequestParam String nombre) 
	{
			try {
				serAutor.guardarAutor(nombre);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return("redirect:/libreria/autor/lista");
		
	}
	
	@GetMapping("/lista")
	public String listarautores(ModelMap modelo) {
		List<Autor> la=serAutor.listarActivos();
		modelo.addAttribute("listaautores",la);
		return "listarAutor.html";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable String id,ModelMap mod) {
		try {
			serAutor.eliminarAutor(id);
			//System.out.println(serAutor.obtenerAutor(id).getAlta());
		
			return "redirect:/libreria/autor/lista";
			
			//return "redirect:/autor/lista";
		}catch(Exception e) {
			mod.put("error", e.getLocalizedMessage());
			return "redirect:/libreria/";
		}
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/editar/{id}")
	public String editarAutor(@PathVariable String id ,@RequestParam String nombre) {
		try {
			serAutor.editarAutor(id, nombre);
			return "redirect:/libreria/autor/lista";
		} catch (Exception e) {
	
		e.getMessage();
		return "crearAutor";
		}
		
		
	
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/editar/{id}")
	public String editarAutor(ModelMap mod,@PathVariable String id) throws Exception {
		
		
		Autor	a = serAutor.obtenerAutor(id);
		
		mod.addAttribute(a);
		return "editar_AutorEditorial";
	}
	
	
}

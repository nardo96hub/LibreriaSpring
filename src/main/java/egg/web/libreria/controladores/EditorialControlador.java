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

import egg.web.libreria.servicios.ServicioEditorial;
import egg.web.libreria.entidades.Editorial;
@Controller
@RequestMapping("/editorial")
public class EditorialControlador {
	@Autowired
	private ServicioEditorial serEdi;
	
	//controladores carga
	
	@GetMapping("/cargaedi")
	public String Editorial()
	{
		return "crearEditorial.html";
	}
	
	@PostMapping("/cargaedi")
	public String Editorial(@RequestParam String nombre) {
		try {
			serEdi.guardarEditorial(nombre);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "redirect:/editorial/lista";
	}
	
	//Controladores para listar Editorial
	
	@GetMapping("/lista")
	public String listaEdi(ModelMap mod) {
		List<Editorial> le=serEdi.listarActivos();
		mod.addAttribute("listaeditorial",le);
		return "listarEditorial.html";
	}
	
	//Controlador para eliminar
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable String id) {
		try {
			serEdi.eliminarEditorial(id);
			return "redirect:/editorial/lista";
		} catch (Exception e) {
			
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	//Controladores editar
	@GetMapping("/editar/{id}")
	public String editar(ModelMap mod,@PathVariable String id) {
		
		try {
			Editorial e=serEdi.objetenerEditorial(id);
			mod.addAttribute(e);
			return "editarEditorial";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}
	
	}
	
	@PostMapping("/editar/{id}")
	public String editar(@PathVariable String id,@RequestParam String nombre) {
		try {
			serEdi.editarEditorial(id, nombre);
			return "redirect:/editorial/lista";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "crearEditorial";
		}
	}
	
}

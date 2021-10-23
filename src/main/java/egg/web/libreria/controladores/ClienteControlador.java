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

import egg.web.libreria.entidades.Cliente;
import egg.web.libreria.servicios.ServicioCliente;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/libreria/cliente")
public class ClienteControlador {
	@Autowired
	private ServicioCliente serCliente;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/crearCliente")
	public String c()
	{
		return "crearCliente";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/crearCliente")
	public String crearCliente(@RequestParam String nombre,@RequestParam String apellido, @RequestParam Long dni,@RequestParam String telefono) {
		try {
			serCliente.guardarCliente( dni, nombre, apellido, telefono);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/libreria/cliente/lista";
	}
	
	@GetMapping("/lista")
	public String l(ModelMap m) {
		List<Cliente> lc=serCliente.listarClienteActivos();
		m.addAttribute("listacliente",lc);
		return "listarCliente";
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/eliminar/{id}")
	public String e(@PathVariable Long id) {
		try {
			serCliente.eliminarCliente(id);
			return "redirect:/cliente/lista";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/libreria";
		}
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/editar/{id}")
	public String ed(ModelMap m, @PathVariable Long id) {
		try {
			Cliente c=serCliente.obtenerClienteId(id);
			m.addAttribute(c);
			return "editarCliente";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/libreria";
		}
		
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/editar/{id}")
	public String ed(@PathVariable Long id,@RequestParam String nombre,@RequestParam String apellido, @RequestParam Long dni,@RequestParam String telefono) {
		try {
			serCliente.editarCliente(id, dni, nombre, apellido, telefono);
			return "redirect:/libreria/cliente/lista";
		} catch (Exception e) {
			e.getMessage();
			return "redirect:/libreria/cliente/crearCliente";
		}
	}
	
	
}

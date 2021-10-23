package egg.web.libreria.controladores;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egg.web.libreria.servicios.ServicioUsuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

	@Autowired
	private ServicioUsuario ServiUsu;
	
	@GetMapping("/registrar")
	public String r() {
		return "crearUsuario";
	}
	
	
	@PostMapping("/registrar")
	public String re(@RequestParam String usu,@RequestParam String contra,@RequestParam String NyA,@RequestParam String dniU,@RequestParam String fechaNU,@RequestParam String emailU,@RequestParam String telU) throws Exception {
		
		
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha=formato.parse(fechaNU);
		
		
		ServiUsu.crearUsuario(usu, contra, NyA, fecha, dniU, emailU, telU);
		
		return "redirect:/";
	}
}

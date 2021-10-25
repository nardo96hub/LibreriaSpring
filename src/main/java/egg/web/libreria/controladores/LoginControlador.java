package egg.web.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginControlador {
	@GetMapping("/")
	public String prin() {
		return "login";
		
	}
	
	@GetMapping("/login")
	public String l(@RequestParam(required=false) String error,ModelMap mod,@RequestParam(required=false) String username,@RequestParam(required=false)String logout) {
		if(error!=null) {
			//mod.put("error", "Documento o clave incorrecta");
			mod.addAttribute("error","El usuario ingresado o contrasena son incorrectos");
			
		}
		if(username!=null) {
			mod.addAttribute("username",username);
		}
		
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String lo(Model mod) {
		mod.addAttribute("success","Has finalizado la sesion con exito");	
		return "login";
				}
}

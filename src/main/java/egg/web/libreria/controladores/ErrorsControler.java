package egg.web.libreria.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsControler implements ErrorController {

	@RequestMapping(value = "/error",method = {RequestMethod.GET,RequestMethod.POST})
	public String mostrarerrores(Model mod,HttpServletRequest httpservletrequest) {
		String mensajeerror="";
		int codigoerror=(int) httpservletrequest.getAttribute("javax.servlet.error.status_code");
		switch(codigoerror) {
		case 400:
			mensajeerror="El recurso solicitado no existe";
			break;
		case 401:
			mensajeerror="No se encuentra autorizado";
			break;	
		case 403:
			mensajeerror="No tiene permisos para acceder al recurso";
			break;
		case 404:
			mensajeerror="El recurso solicitado no se ha encontrado";
			break;
		case 500:
			mensajeerror="El servidor no pudo realizar la peticion exito";
			break;
		//default:
		}
		
		
		
		mod.addAttribute("codigo",codigoerror);
		mod.addAttribute("mensaje",mensajeerror);
		return "errores";
	}
}

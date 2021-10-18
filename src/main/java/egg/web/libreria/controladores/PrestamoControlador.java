package egg.web.libreria.controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egg.web.libreria.entidades.Prestamo;
import egg.web.libreria.servicios.ServicioPrestamo;

@Controller
@RequestMapping("/prestamo")
public class PrestamoControlador {

	@Autowired
	private ServicioPrestamo serPrestamo;

	@GetMapping("/lista")
	public String l(ModelMap m) {
		List<Prestamo> lp = serPrestamo.listarPrestamoActivos();
		m.addAttribute("listarprestamo", lp);

		return "listarPrestamo";
	}

	
	@GetMapping("/crearPrestamo")
	public String s() {
		return "crearPrestamo";
	}

	@PostMapping("crearPrestamo")
	public String crearPrestamo(@RequestParam String pre, @RequestParam String dev, @RequestParam String titL,
			@RequestParam Long dniC) {

		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date fechap, fechad;
			fechap = formato.parse(pre);
			fechad = formato.parse(dev);
			switch (serPrestamo.crearPrestamo(fechap, fechad, titL, dniC)) {
			case 0:
				return "redirect:/prestamo/lista";
			case 1:
				return "redirect:/";
			case 2:
				return "redirect:/cliente/crearCliente";
			case 3:
				return "redirect:/libro/cargalibro";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}
		return "redirect:/";

	}

	@GetMapping("/eliminar/{id}")
	public String e(@PathVariable Long id) {
		serPrestamo.eliminarPrestamo(id);

		return "redirect:/prestamo/lista";
	}

	@GetMapping("/editar/{id}")
	public String ed(ModelMap m, @PathVariable Long id) {
		Prestamo p = serPrestamo.obtenerPrestamo(id);
		m.addAttribute(p);
		return "editarPrestamo";

	}

	@PostMapping("/editar/{id}")
	public String edi(@PathVariable Long id, @RequestParam String pre, @RequestParam String dev, @RequestParam String titL,
			@RequestParam Long dniC) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechap, fechad;
		try {
			fechap = formato.parse(pre);
			fechad = formato.parse(dev);
			serPrestamo.editarPrestamo(id, fechap, fechad, titL, dniC);
		return "redirect:/prestamo/lista";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}
		
		
	}

}

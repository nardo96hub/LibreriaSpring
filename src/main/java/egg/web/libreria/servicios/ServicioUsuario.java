package egg.web.libreria.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egg.web.libreria.entidades.Usuario;
import egg.web.libreria.enumeracion.Rol;
import egg.web.libreria.repositorios.RepositorioUsuario;


@Service
public class ServicioUsuario  implements UserDetailsService{

	@Autowired
	private RepositorioUsuario RepoUsu;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Usuario crearUsuario(String usuario,String pass,String NyA,Date fechaN,String dni,String email,String telefono) throws Exception {
		
		validar(usuario);
		
		Usuario u=new Usuario();
		u.setAlta(true);
		u.setUsuario(usuario);
		u.setDocumento(dni);
		u.setEmail(email);
		u.setNyA(NyA);
		u.setTelefono(telefono);
		u.setRol(Rol.USER);
		u.setFechaN(fechaN);
		String claveEncriptada=new BCryptPasswordEncoder().encode(pass);
		u.setClave(claveEncriptada);
		
		
		RepoUsu.save(u);
		return u;
	}
	
	
	private void validar(String usuario) throws Exception {
		Usuario u=RepoUsu.buscarUsuario(usuario);
		if(u!=null) {
			throw new Exception("Usuario ya registrado");
		}
		
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario u=RepoUsu.buscarUsuario(username);
		if(u==null) {
		return null;}
	
		
		List<GrantedAuthority> permisos=new ArrayList<>();
		
		GrantedAuthority p1=new SimpleGrantedAuthority("ROLE_"+u.getRol());
		//System.out.println(p1);
		//System.out.println(u.getRol());
		permisos.add(p1);
		ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session=attr.getRequest().getSession(true);
		session.setAttribute("usuariosession", u);
		
		return new User(username,u.getClave(),permisos);
	}

}

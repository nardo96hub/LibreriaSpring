package egg.web.libreria.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import egg.web.libreria.enumeracion.Rol;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	

	private String id;
	@Column(unique=true)
	private String usu;
	private String clave;
	private String documento;
	private String NyA;
	@Temporal(TemporalType.DATE)
	private Date fechaN;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Rol rol;
	private Boolean alta;
	private String telefono;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsuario() {
		return usu;
	}
	public void setUsuario(String usuario) {
		this.usu = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNyA() {
		return NyA;
	}
	public void setNyA(String nyA) {
		NyA = nyA;
	}
	public Date getFechaN() {
		return fechaN;
	}
	public void setFechaN(Date fechaN) {
		this.fechaN = fechaN;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Boolean getAlta() {
		return alta;
	}
	public void setAlta(Boolean alta) {
		this.alta = alta;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usu + ", clave=" + clave + ", documento=" + documento + ", NyA="
				+ NyA + ", fechaN=" + fechaN + ", email=" + email + ", rol=" + rol + ", alta=" + alta + ", telefono="
				+ telefono + "]";
	}

	
	
	
}

package egg.web.libreria.entidades;

//import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Autor {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	private String id;
	/*@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long identificador;*/
	private String nombre;
	private Boolean alta;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getAlta() {
		return alta;
	}
	public void setAlta(Boolean alta) {
		this.alta = alta;
	}
	/*
	public Long getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}
	@Override
	public int hashCode() {
		return Objects.hash(alta, id, identificador, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(alta, other.alta) && Objects.equals(id, other.id)
				&& Objects.equals(identificador, other.identificador) && Objects.equals(nombre, other.nombre);
	}*/
	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", alta=" + alta + "]";
	}
	
	
	
	
}

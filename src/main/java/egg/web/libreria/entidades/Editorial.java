package egg.web.libreria.entidades;

//import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Editorial {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	private String id;
	
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
	@Override
	public int hashCode() {
		return Objects.hash(alta, id, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editorial other = (Editorial) obj;
		return Objects.equals(alta, other.alta) && Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}*/
	@Override
	public String toString() {
		return "Editorial [id=" + id + ", nombre=" + nombre + ", alta=" + alta + "]";
	}
	

	
	
	
	
	
	
	
}

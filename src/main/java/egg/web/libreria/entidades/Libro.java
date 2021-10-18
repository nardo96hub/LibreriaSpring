package egg.web.libreria.entidades;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Libro {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private Long isbn;
	private String titulo;
	private Integer anio;
	private Integer ejemplares;
	private Integer ejemplaresPrestados;
	private Integer ejemplaresRestantes;
	private Boolean alta;
	@OneToOne
	private Autor autor;
	@OneToOne
	private Editorial editorial;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(Integer ejemplares) {
		this.ejemplares = ejemplares;
	}

	public Integer getEjemplaresPrestados() {
		return ejemplaresPrestados;
	}

	public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
		this.ejemplaresPrestados = ejemplaresPrestados;
	}

	public Integer getEjemplaresRestantes() {
		return ejemplaresRestantes;
	}

	public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
		this.ejemplaresRestantes = ejemplaresRestantes;
	}

	public Boolean getAlta() {
		return alta;
	}

	public void setAlta(Boolean alta) {
		this.alta = alta;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alta, anio, autor, editorial, ejemplares, ejemplaresPrestados, ejemplaresRestantes, id,
				isbn, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(alta, other.alta) && Objects.equals(anio, other.anio)
				&& Objects.equals(autor, other.autor) && Objects.equals(editorial, other.editorial)
				&& Objects.equals(ejemplares, other.ejemplares)
				&& Objects.equals(ejemplaresPrestados, other.ejemplaresPrestados)
				&& Objects.equals(ejemplaresRestantes, other.ejemplaresRestantes) && Objects.equals(id, other.id)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", anio=" + anio + ", ejemplares="
				+ ejemplares + ", ejemplaresPrestados=" + ejemplaresPrestados + ", ejemplaresRestantes="
				+ ejemplaresRestantes + ", alta=" + alta + ", autor=" + autor + ", editorial=" + editorial + "]";
	}

	
	
	
	

}

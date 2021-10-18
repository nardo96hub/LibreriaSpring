package egg.web.libreria.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Prestamo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	//
	
	@Temporal(TemporalType.DATE)
	
	private Date fechaPrestamo;
	
	@Temporal(TemporalType.DATE)
	
	private Date fechaDevolucion;
	private boolean alta;
	@OneToOne
	private Libro libro;
	@OneToOne
	private Cliente cliente;
	public Prestamo() {
		
	}
	
	
	public Prestamo( Date fechaPrestamo, Date fechaDevolucion, Libro libro, Cliente cliente) {
		super();
		
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.libro = libro;
		this.cliente = cliente;
		this.alta=true;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public boolean isAlta() {
		return alta;
	}
	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion
				+ ", alta=" + alta + ", libro=" + libro + ", cliente=" + cliente + "]";
	}
	
	
	
	
	
}

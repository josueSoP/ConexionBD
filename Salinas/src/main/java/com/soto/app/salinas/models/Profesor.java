package com.soto.app.salinas.models;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Table(name = "u_Profesor")
@Entity
public class Profesor {
	
	@Column(name="id_profesor")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQUE2")
	@SequenceGenerator(sequenceName = "customer_seque2",allocationSize = 1, name = "CUST_SEQUE2")
	private Long id;
	
	@NotEmpty(message = "Nombre requerido")
	private String nombre;
	
	@NotEmpty(message = "Apellido paterno requerido")
	private String apPat;
	
	@NotEmpty(message = "Apellido Materno requerido")
	private String apMat;
	
	@NotNull(message = "fecha requerido")
	private Date fecha;
	
	@NotNull(message ="Presencial requerido")
	private Integer presencial;
	
	
	
	//gets and Sets

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApPat() {
		return apPat;
	}

	public void setApPat(String apPat) {
		this.apPat = apPat;
	}

	public String getApMat() {
		return apMat;
	}

	public void setApMat(String apMat) {
		this.apMat = apMat;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getPresencial() {
		return presencial;
	}

	public void setPresencial(Integer presencial) {
		this.presencial = presencial;
	}
	
	
	
}

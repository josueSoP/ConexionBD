package com.soto.app.salinas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Table(name = "usuarios")
@Entity
public class Usuario {
	
	@Column(name = "id_usuarios")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQUE1")
	@SequenceGenerator(sequenceName = "customer_seque1",allocationSize = 1, name = "CUST_SEQUE1")
	private Long id;
	
	@NotEmpty(message = "El nombre es requerido")
	private String nombre;
	
	@Column(name="Ap_Paterno")
	@NotEmpty(message = "el apellido paterno es requerido")
	private String apPat;
	
	@Column(name = "Ap_Materno")
	@NotEmpty(message = "El apellido materno es requerido")
	private String apMat;
	
	@NotNull(message = "Se requiere si es activo requerido")
	@Column(name = "activo")
	private Integer activo;
	
	
	
	
	//getters and setters 

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

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	
}

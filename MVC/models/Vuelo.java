package com.soto.app.proyecto4.models;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//1
@Table(name = "jpa_vuelos")
@Entity
public class Vuelo {
	//atributos -> variables -> caracteristicas
	
	//2
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ02")
	@SequenceGenerator(sequenceName = "customer_seq02",allocationSize = 1, name = "CUST_SEQ02")
	private Long id;
	
	//2
	@Column(name = "origen")
	private String origen;
	
	//2
	@Column(name = "destino")
	private String destino;

	//2
	@Column(name = "fecha_salida")
	private Date fechaSalida;

	//2
	@Column(name = "aerolinea")
	private String aerolinea;
	
	//2
	@Column(name = "puerta_salida")
	private String puertaSalida;

	//2
	@Column(name = "cupo")
	private Integer cupo;
	
	//2
	@Column(name = "precio")
	private Float precio;
	
	//2
	@Column(name = "tua")
	private Float tua;

	
	
	
	
	
	
	
	
	
	
	//geters an seters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}

	public String getPuertaSalida() {
		return puertaSalida;
	}

	public void setPuertaSalida(String puertaSalida) {
		this.puertaSalida = puertaSalida;
	}

	public Integer getCupo() {
		return cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Float getTua() {
		return tua;
	}

	public void setTua(Float tua) {
		this.tua = tua;
	}

}

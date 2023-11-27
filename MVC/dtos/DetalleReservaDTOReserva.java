package com.soto.app.proyecto4.dtos;

import com.soto.app.proyecto4.models.Vuelo;

public class DetalleReservaDTOReserva {

	private Long id;
	private Integer cantidad; 
	private Boolean seguroViaje;   
	private Vuelo vuelo;
	
	
	
	
	
	//Gets and Sets
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Boolean getSeguroViaje() {
		return seguroViaje;
	}
	public void setSeguroViaje(Boolean seguroViaje) {
		this.seguroViaje = seguroViaje;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	
}

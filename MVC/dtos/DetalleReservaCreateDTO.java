package com.soto.app.proyecto4.dtos;


public class DetalleReservaCreateDTO {
	private Long id;
	private Integer cantidad; 
	private Boolean seguroViaje;    
	private Long vueloId;   
	private Long reservaId;
	
	
	
	
	
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
	public Long getVueloId() {
		return vueloId;
	}
	public void setVueloId(Long vueloId) {
		this.vueloId = vueloId;
	}
	public Long getReservaId() {
		return reservaId;
	}
	public void setReservaId(Long reservaId) {
		this.reservaId = reservaId;
	}
	
	
	
	
	
	
	
}

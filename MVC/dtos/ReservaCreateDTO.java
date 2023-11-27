package com.soto.app.proyecto4.dtos;

import java.sql.Date;

public class ReservaCreateDTO {

	private Long id;
	private Date fechaReserva;
	private String formaPago;
	private String codigo;
	private String numAsiento;
	private Float montoParcial;
	private Float total;
	private Long pasajeroId;
	
	
	
	
	
	
	//gets and sets
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNumAsiento() {
		return numAsiento;
	}
	public void setNumAsiento(String numAsiento) {
		this.numAsiento = numAsiento;
	}
	public Float getMontoParcial() {
		return montoParcial;
	}
	public void setMontoParcial(Float montoParcial) {
		this.montoParcial = montoParcial;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Long getPasajeroId() {
		return pasajeroId;
	}
	public void setPasajeroId(Long pasajeroId) {
		this.pasajeroId = pasajeroId;
	}
	
	
}

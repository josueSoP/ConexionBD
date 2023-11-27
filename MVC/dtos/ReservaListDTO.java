package com.soto.app.proyecto4.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.soto.app.proyecto4.models.Pasajero;

public class ReservaListDTO {
	private Long id;
	private Date fechaReserva;
	private String formaPago;
	private String codigo;
	private String numAsiento;
	private Float montoParcial;
	private Float total;
	private Pasajero pasajero;
	private List<DetalleReservaDTOReserva> detalleLista = new ArrayList<>();
	
	
	
	
	//getter and setters
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
	public Pasajero getPasajero() {
		return pasajero;
	}
	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}
	public List<DetalleReservaDTOReserva> getDetalleLista() {
		return detalleLista;
	}
	public void setDetalleLista(List<DetalleReservaDTOReserva> detalleLista) {
		this.detalleLista = detalleLista;
	}
	
	
}

package com.soto.app.proyecto4.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//1
@Table(name = "jpa_reservas")
@Entity
public class Reserva {
	//atributos -> variables -> caracteristicas
	
	//2
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ03")
	@SequenceGenerator(sequenceName = "customer_seq03",allocationSize = 1, name = "CUST_SEQ03")
	private Long id;  
	
	//2
	@Column(name = "fecha_reserva")//importa del java util
	private Date fechaReserva;
	
	//2
	@Column(name = "forma_pago")
	private String formaPago;

	//2
	@Column(name = "codigo")
	private String codigo;
	
	//2
	@Column(name = "num_asiento")
	private String numAsiento;
	
	//2
	@Column(name = "monto_parcial")
	private Float montoParcial;
	
	//2
	@Column(name = "total")
	private Float total;
	
	//3
	@JoinColumn(name = "pasajero_id")
	@ManyToOne
	private Pasajero pasajero;
	
	@OneToMany(mappedBy="reserva")
	private List<DetalleReserva> detallesList = new ArrayList<>();
	
	
	
	
	
	
	//geter and seters
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

	public List<DetalleReserva> getDetallesList() {
		return detallesList;
	}

	public void setDetallesList(List<DetalleReserva> detallesList) {
		this.detallesList = detallesList;
	}

		
}

package com.soto.app.proyecto4.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//1
@Table(name = "jpa_detalle_reservas")
@Entity
public class DetalleReserva {
	//atributos -> variables -> caracteristicas
	
	//2
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ04")
	@SequenceGenerator(sequenceName = "customer_seq04",allocationSize = 1, name = "CUST_SEQ04")
	private Long id;

	//2
	@Column(name = "cantidad")
	private Integer cantidad;
	//2
	@Column(name = "seguro_viaje")
	private Boolean seguroViaje;
	
	//3
	@JoinColumn(name = "vuelo_id")
	@ManyToOne
	private Vuelo vuelo;   
	
	//3
	@JoinColumn(name = "reserva_id")
	@ManyToOne
	private Reserva reserva;

	
	
	
	
	
	//geters and seters

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

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	
}

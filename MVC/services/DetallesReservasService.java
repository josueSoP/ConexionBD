package com.soto.app.proyecto4.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soto.app.proyecto4.dao.IDetallesReservasDao;
import com.soto.app.proyecto4.dao.IVuelosDao;
import com.soto.app.proyecto4.dao.IReservasDao;
import com.soto.app.proyecto4.dtos.DetalleReservaCreateDTO;
import com.soto.app.proyecto4.models.DetalleReserva;

@Service
public class DetallesReservasService implements IService<DetalleReserva, DetalleReservaCreateDTO>{
	
	@Autowired
	private IDetallesReservasDao detallesReservasDao;
	@Autowired
	private IVuelosDao vuelosDao;
	@Autowired
	private IReservasDao reservasDao;

	@Override
	public List<DetalleReserva> listar() {
		List<DetalleReserva> detalleReservas= new ArrayList<>();
		detalleReservas = (List<DetalleReserva>) detallesReservasDao.findAll();
		return detalleReservas;
	}

	@Override
	public Optional<DetalleReserva> getById(Long id) {
		Optional<DetalleReserva> detallereservas = detallesReservasDao.findById(id);
		return detallereservas;
	}

	@Override
	public void eliminar(Long id) {
		this.detallesReservasDao.deleteById(id);		
	}

	@Override
	public void guardar(DetalleReservaCreateDTO t) {
		this.detallesReservasDao.save(convertirDTOaDetallReserva(t));			
	}
	
	
	
	//modelo DetalleReserva y DTO DetalleReservaCreateDTO
	private DetalleReserva convertirDTOaDetallReserva(DetalleReservaCreateDTO drDTO) {
		DetalleReserva dr = new DetalleReserva();
		dr.setId(drDTO.getId());
		dr.setCantidad(drDTO.getCantidad());
		dr.setSeguroViaje(drDTO.getSeguroViaje());
		dr.setVuelo(vuelosDao.findById(drDTO.getVueloId()).get());
		dr.setReserva(reservasDao.findById(drDTO.getReservaId()).get());
		return dr;
	}

	
}




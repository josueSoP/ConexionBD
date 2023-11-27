package com.soto.app.proyecto4.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soto.app.proyecto4.dao.IPasajerosDao;
import com.soto.app.proyecto4.dao.IReservasDao;
import com.soto.app.proyecto4.dtos.DetalleReservaDTOReserva;
import com.soto.app.proyecto4.dtos.ReservaCreateDTO;
import com.soto.app.proyecto4.dtos.ReservaListDTO;
import com.soto.app.proyecto4.models.DetalleReserva;
import com.soto.app.proyecto4.models.Reserva;

@Service
public class ReservasService implements IService<ReservaListDTO, ReservaCreateDTO> {
//public class ReservasService implements IService<Reserva> {
//inyecta la dependencia
		@Autowired
		private IReservasDao reservasDao;
		
		@Autowired
		private IPasajerosDao pasajerosDao;
		
		
		@Override
		public List<ReservaListDTO> listar() {
			List<Reserva> reservas= new ArrayList<>();
			reservas = (List<Reserva>) reservasDao.findAll();
			
			List<ReservaListDTO> reservasDTO = new ArrayList<>();
			for (Reserva v : reservas) {
				reservasDTO.add(convertirOriginalADTO(v));
			}
			
			return reservasDTO;
		}


		@Override
		public Optional<ReservaListDTO> getById(Long id) {
			Optional<Reserva> reserva = reservasDao.findById(id);
			
			Optional<ReservaListDTO> obj = Optional.empty();
			obj = Optional.of(this.convertirOriginalADTO(reserva.get()));
			return obj;
		}

		@Override
		public void guardar(ReservaCreateDTO t) {
			this.reservasDao.save(convertirDTOaReserva(t));			
		}


		@Override
		public void eliminar(Long id) {
			this.reservasDao.deleteById(id);			
		}

		
		public Reserva convertirDTOaReserva(ReservaCreateDTO reservadto) {
			Reserva r = new Reserva();
			r.setId(reservadto.getId());
			r.setFechaReserva(reservadto.getFechaReserva());
			r.setFormaPago(reservadto.getFormaPago());
			r.setCodigo(reservadto.getCodigo());
			r.setNumAsiento(reservadto.getNumAsiento());
			r.setMontoParcial(reservadto.getMontoParcial());
			r.setTotal(reservadto.getTotal());
			r.setPasajero(pasajerosDao.findById(reservadto.getPasajeroId()).get());
			return r;
		}
		
		
		public ReservaListDTO convertirOriginalADTO(Reserva reserva) {
			ReservaListDTO vdto = new ReservaListDTO();
			//Modelo Reserva y DTO ReservaListDTO
			vdto.setId(reserva.getId());
			vdto.setFechaReserva(reserva.getFechaReserva());
			vdto.setFormaPago(reserva.getFormaPago());
			vdto.setCodigo(reserva.getCodigo());
			vdto.setNumAsiento(reserva.getNumAsiento());
			vdto.setMontoParcial(reserva.getMontoParcial());
			vdto.setTotal(reserva.getTotal());
			vdto.setPasajero(reserva.getPasajero());
			
			//Modelo (Reserva y DetalleReserva) y DTO (DetalleReservaDTOReserva)
			for(DetalleReserva dv : reserva.getDetallesList()) {
				DetalleReservaDTOReserva dvdto = new DetalleReservaDTOReserva();
				dvdto.setId(dv.getId());
				dvdto.setCantidad(dv.getCantidad());
				dvdto.setSeguroViaje(dv.getSeguroViaje());
				dvdto.setVuelo(dv.getVuelo());
				vdto.getDetalleLista().add(dvdto);
			}
			return vdto;
		}
		
		
}

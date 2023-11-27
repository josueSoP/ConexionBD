package com.soto.app.proyecto4.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soto.app.proyecto4.dao.IPasajerosDao;
import com.soto.app.proyecto4.models.Pasajero;

@Service
public class PasajerosService implements IService<Pasajero, Pasajero>{

	//inyecta la dependencia
	@Autowired
	private IPasajerosDao pasajerosDao;
	
	
	@Override
	public List<Pasajero> listar() {
		List<Pasajero> pasajeros= new ArrayList<>();
		pasajeros = (List<Pasajero>) pasajerosDao.findAll();
		return pasajeros;
	}

	@Override
	public Optional<Pasajero> getById(Long id) {
		Optional<Pasajero> pasajero = pasajerosDao.findById(id);
		return pasajero;
	}

	@Override
	public void guardar(Pasajero t) {
		this.pasajerosDao.save(t);
		
	}

	@Override
	public void eliminar(Long id) {
		this.pasajerosDao.deleteById(id);		
	}

	
}

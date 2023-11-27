package com.soto.app.proyecto4.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soto.app.proyecto4.dao.IVuelosDao;
import com.soto.app.proyecto4.models.Vuelo;

@Service
public class VueloService implements IService<Vuelo, Vuelo>{
	
	@Autowired
	private IVuelosDao vuelosDao;

	@Override
	public List<Vuelo> listar() {
		List<Vuelo> vuelos= new ArrayList<>();
		vuelos = (List<Vuelo>) vuelosDao.findAll();
		return vuelos;
	}

	@Override
	public Optional<Vuelo> getById(Long id) {
		Optional<Vuelo> vuelo = vuelosDao.findById(id);
		return vuelo;
	}

	@Override
	public void guardar(Vuelo t) {
		this.vuelosDao.save(t);
	}

	@Override
	public void eliminar(Long id) {
		this.vuelosDao.deleteById(id);		
	}

}

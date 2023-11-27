package com.soto.app.proyecto4.dao;

import org.springframework.data.repository.CrudRepository;

import com.soto.app.proyecto4.models.Pasajero;

public interface IPasajerosDao extends CrudRepository<Pasajero, Long>{	
}

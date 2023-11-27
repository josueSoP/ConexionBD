package com.soto.app.proyecto4.services;

import java.util.List;
import java.util.Optional;

public interface IService<T, S> {
//public interface IService<T> {
	List<T> listar();
	Optional<T> getById(Long id);
	void guardar(S t);
	void eliminar(Long id);

}

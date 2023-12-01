package com.soto.app.salinas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soto.app.salinas.dao.IProfesoresDao;
import com.soto.app.salinas.models.Profesor;

@Service
public class ProfesoresService implements IService<Profesor> {

	@Autowired
	private IProfesoresDao profesoresDao ;
	
	@Override
	public List<Profesor> listar() {
		List<Profesor> profesores = new ArrayList<>();
		profesores = (List<Profesor>) profesoresDao.findAll();
		return profesores;
	}

	@Override
	public Optional<Profesor> getById(Long id) {
		Optional<Profesor> profesor = profesoresDao.findById(id);
		return profesor;
	}

	@Override
	public void guardar(Profesor p) {
		this.profesoresDao.save(p);	
	}

	@Override
	public void eliminar(Long id) {
		this.profesoresDao.deleteById(id);
	}

}

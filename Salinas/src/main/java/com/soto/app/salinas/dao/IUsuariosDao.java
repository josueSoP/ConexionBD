package com.soto.app.salinas.dao;

import org.springframework.data.repository.CrudRepository;

import com.soto.app.salinas.models.Usuario;

public interface IUsuariosDao extends CrudRepository<Usuario, Long>{

}

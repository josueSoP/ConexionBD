package com.soto.app.escuelas.services;

import com.soto.app.escuelas.models.Escuela;
import com.soto.app.escuelas.models.Estudiante;

import java.util.List;


public interface IEstudiantesService extends IService<Estudiante>{
    List<Escuela> listarEscuelas();
    Long guardarReturnID( Estudiante estudiante);
}

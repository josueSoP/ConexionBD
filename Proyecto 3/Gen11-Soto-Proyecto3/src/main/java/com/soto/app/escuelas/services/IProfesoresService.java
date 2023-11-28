package com.soto.app.escuelas.services;

import com.soto.app.escuelas.models.Escuela;
import com.soto.app.escuelas.models.Profesor;

import java.util.List;


public interface IProfesoresService extends IService<Profesor>{
    List<Escuela> listarEscuelas();
    Long guardarReturnID( Profesor profesor);
}

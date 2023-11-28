package com.soto.app.escuelas.services;

import com.soto.app.escuelas.models.Clase;
import com.soto.app.escuelas.models.Profesor;

import java.util.List;


public interface IClasesService extends IService<Clase>{
    List<Profesor> listarProfesores();
    Long guardarReturnID( Clase clase);
}

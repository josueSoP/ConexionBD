package com.soto.app.escuelas.services;

import com.soto.app.escuelas.models.Calificacion;
import com.soto.app.escuelas.models.Clase;
import com.soto.app.escuelas.models.Estudiante;

import java.util.List;

public interface ICalificacionesService extends IService<Calificacion> {
    List<Estudiante> listarEstudiantes();
    List<Clase> listarClases();
    Long guardarReturnID( Calificacion calificacion);
}

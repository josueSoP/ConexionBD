package com.soto.app.escuelas.services;

import com.soto.app.escuelas.models.Calificacion;
import com.soto.app.escuelas.models.Clase;
import com.soto.app.escuelas.models.Estudiante;
import com.soto.app.escuelas.repositories.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CalificacionesService implements ICalificacionesService {
    private IRepository<Calificacion> calificacionesRepo;
    private IRepository<Estudiante> estudiantesRepo;
    private IRepository<Clase> clasesRepo;

    public CalificacionesService(Connection conn){
        calificacionesRepo = new CalificacionesRepository(conn);
        estudiantesRepo = new EstudiantesRepository(conn);
        clasesRepo= new ClasesRepository(conn);
    }

    @Override
    public List<Calificacion> listar() {
        try {
            return calificacionesRepo.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Calificacion> getById(Long id) {
        try {
            return Optional.ofNullable(calificacionesRepo.getById(id));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Calificacion calificacion) {
        try {
            calificacionesRepo.guardar(calificacion);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            calificacionesRepo.eliminar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }


    @Override
    public List<Estudiante> listarEstudiantes() {
        try{
            return estudiantesRepo.listar();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Clase> listarClases() {
        try{
            return clasesRepo.listar();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Long guardarReturnID(Calificacion calificacion) {
        return null;
    }
}

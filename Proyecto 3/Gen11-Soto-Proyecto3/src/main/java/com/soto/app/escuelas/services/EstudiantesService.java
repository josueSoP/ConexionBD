package com.soto.app.escuelas.services;

import com.soto.app.escuelas.models.Escuela;
import com.soto.app.escuelas.models.Estudiante;
import com.soto.app.escuelas.models.Profesor;
import com.soto.app.escuelas.repositories.EscuelasRepository;
import com.soto.app.escuelas.repositories.EstudiantesRepository;
import com.soto.app.escuelas.repositories.IRepository;
import com.soto.app.escuelas.repositories.ProfesoresRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EstudiantesService implements IEstudiantesService {
    private IRepository<Estudiante> estudiantesRepo;
    private IRepository<Escuela> escuelasRepo;

    public EstudiantesService(Connection conn){
        estudiantesRepo = new EstudiantesRepository(conn);
        escuelasRepo= new EscuelasRepository(conn);
    }

    @Override
    public List<Estudiante> listar() {
        try {
            return estudiantesRepo.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Estudiante> getById(Long id) {
        try {
            return Optional.ofNullable(estudiantesRepo.getById(id));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Estudiante estudiante) {
        try {
            estudiantesRepo.guardar(estudiante);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            estudiantesRepo.eliminar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Escuela> listarEscuelas() {
        try{
            return escuelasRepo.listar();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Long guardarReturnID(Estudiante estudiante) {
        return null;
    }
}

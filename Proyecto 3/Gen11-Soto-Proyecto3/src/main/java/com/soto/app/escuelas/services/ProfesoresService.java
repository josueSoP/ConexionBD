package com.soto.app.escuelas.services;

import com.soto.app.escuelas.models.Escuela;
import com.soto.app.escuelas.models.Profesor;
import com.soto.app.escuelas.repositories.EscuelasRepository;
import com.soto.app.escuelas.repositories.ProfesoresRepository;
import com.soto.app.escuelas.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProfesoresService implements IProfesoresService {
    private IRepository<Profesor> profesoresRepo;
    private IRepository<Escuela> escuelasRepo;

    public ProfesoresService(Connection conn){
        profesoresRepo = new ProfesoresRepository(conn);
        escuelasRepo= new EscuelasRepository(conn);
    }

    @Override
    public List<Profesor> listar() {
        try {
            return profesoresRepo.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Profesor> getById(Long id) {
        try {
            return Optional.ofNullable(profesoresRepo.getById(id));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Profesor profesor) {
        try {
            profesoresRepo.guardar(profesor);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            profesoresRepo.eliminar(id);
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
    public Long guardarReturnID(Profesor profesor) {
        return null;
    }
}

package com.soto.app.escuelas.services;

import com.soto.app.escuelas.models.Clase;
import com.soto.app.escuelas.models.Profesor;
import com.soto.app.escuelas.repositories.IRepository;
import com.soto.app.escuelas.repositories.ClasesRepository;
import com.soto.app.escuelas.repositories.ProfesoresRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClasesService implements IClasesService {
    private IRepository<Clase> clasesRepo;
    private IRepository<Profesor> profesoresRepo;


    public ClasesService(Connection conn){
        clasesRepo = new ClasesRepository(conn);
        profesoresRepo = new ProfesoresRepository(conn);
    }

    @Override
    public List<Clase> listar() {
        try {
            return clasesRepo.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Clase> getById(Long id) {
        try {
            return Optional.ofNullable(clasesRepo.getById(id));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Clase clase) {
        try {
            clasesRepo.guardar(clase);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            clasesRepo.eliminar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }


    @Override
    public List<Profesor> listarProfesores() {
        try{
            return profesoresRepo.listar();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Long guardarReturnID(Clase clase) {
        return null;
    }
}

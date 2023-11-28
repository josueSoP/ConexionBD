package com.soto.app.escuelas.services;

import com.soto.app.escuelas.models.Escuela;
import com.soto.app.escuelas.repositories.EscuelasRepository;
import com.soto.app.escuelas.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EscuelasService implements IService<Escuela> {
    private IRepository<Escuela> escuelasRepo;

    public EscuelasService(Connection conn) {
        escuelasRepo = new EscuelasRepository(conn);
    }

    @Override
    public List<Escuela> listar() {
        try {
            return escuelasRepo.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Escuela> getById(Long id) {
        try {
            return Optional.ofNullable(escuelasRepo.getById(id));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Escuela escuela) {
        try {
            escuelasRepo.guardar(escuela);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            escuelasRepo.eliminar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

}

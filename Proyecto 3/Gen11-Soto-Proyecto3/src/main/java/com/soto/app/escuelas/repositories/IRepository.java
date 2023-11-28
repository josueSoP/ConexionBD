//Interfaz genérica que define métodos abstractos para operaciones básicas de acceso a la BD
package com.soto.app.escuelas.repositories;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T> {

    // Métodos abstractos

    // Método para listar todos los registros de una entidad
    List<T> listar() throws SQLException;

    // Método para obtener un registro por su identificador
    T getById(Long id) throws SQLException;

    // Método para guardar un nuevo registro o actualizar uno existente
    void guardar(T t) throws SQLException;

    // Método para eliminar un registro por su identificador
    void eliminar(Long id) throws SQLException;
}


package com.soto.app.escuelas.repositories;

import com.soto.app.escuelas.models.Calificacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionesRepository implements IRepository<Calificacion>{

    //atributos
    private Connection conn; //importar de java.sql

    //constructor
    public CalificacionesRepository(Connection conn){
        this.conn = conn;
    }


    @Override
    public List<Calificacion> listar() throws SQLException {
        List<Calificacion> calificaciones = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM calificaciones")){
            while (rs.next()){
                Calificacion a = this.getCalificacion(rs);
                calificaciones.add(a);
            }
            return calificaciones;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Calificacion getById(Long id) throws SQLException {
        Calificacion calificacion = null;
        try (PreparedStatement stmt =
                conn.prepareStatement("SELECT * FROM calificaciones WHERE id_calificacion = ?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    calificacion = this.getCalificacion(rs);
                }
            }
        }
        return calificacion;
    }

    @Override
    public void guardar(Calificacion calificacion) throws SQLException {
    String sql = "";
    if (calificacion.getId() != null && calificacion.getId() > 0){
        sql = "update calificaciones set estudiante_id=?,clase_id=?,calificacion=?,nota=? where id_calificacion=?";
    } else {
        sql = "insert into calificaciones (id_calificacion,estudiante_id,clase_id,calificacion,nota) " +
                "values (SEQUENCE5.NEXTVAL,?,?,?,?)";
    }
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
        if (calificacion.getId() != null && calificacion.getId() > 0){
            stmt.setLong(1, calificacion.getEstudianteId());
            stmt.setLong(2, calificacion.getClaseId());
            stmt.setDouble(3,calificacion.getCalificacion());
            stmt.setString(4, calificacion.getNota());
            stmt.setLong(5, calificacion.getId());
        }else {
            stmt.setLong(1, calificacion.getEstudianteId());
            stmt.setLong(2, calificacion.getClaseId());
            stmt.setDouble(3,calificacion.getCalificacion());
            stmt.setString(4, calificacion.getNota());
        }
        stmt.executeUpdate();
    }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from calificaciones where id_calificacion=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    //mapear/transformar un arreglo
    private Calificacion getCalificacion(ResultSet rs) throws  SQLException{
        Calificacion p = new Calificacion();
        p.setId(rs.getLong("ID_CALIFICACION"));
        p.setEstudianteId(rs.getLong("ESTUDIANTE_ID"));
        p.setClaseId(rs.getLong("CLASE_ID"));
        p.setCalificacion(rs.getDouble("CALIFICACION"));
        p.setNota(rs.getString("NOTA"));
        return p;
    }
}

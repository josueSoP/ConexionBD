package com.soto.app.escuelas.repositories;

import com.soto.app.escuelas.models.Profesor;
import com.soto.app.escuelas.models.enums.Semestres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesoresRepository implements IRepository<Profesor>{

    //atributos
    private Connection conn; //importar de java.sql

    //constructor
    public ProfesoresRepository(Connection conn){
        this.conn = conn;
    }


    @Override
    public List<Profesor> listar() throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM profesores")){
            while (rs.next()){
                Profesor a = this.getProfesor(rs);
                profesores.add(a);
            }
            return profesores;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profesor getById(Long id) throws SQLException {
        Profesor profesor = null;
        try (PreparedStatement stmt =
                conn.prepareStatement("SELECT * FROM profesores WHERE id_profesor = ?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    profesor = this.getProfesor(rs);
                }
            }
        }
        return profesor;
    }

    @Override
    public void guardar(Profesor profesor) throws SQLException {
        String sql = "";
        if (profesor.getId() != null && profesor.getId() > 0) {
            sql = "UPDATE profesores SET escuela_id=?, nombre=?, ap_paterno=?, ap_materno=?, " +
                    "direccion_prof=?, correo=?, telefono=?, salario=?, fecha_contrato=? WHERE id_profesor=?";
        } else {
            sql = "INSERT INTO profesores (id_profesor, escuela_id, nombre, ap_paterno, ap_materno, direccion_prof, " +
                    "correo, telefono, salario, fecha_contrato) " +
                    "VALUES (SEQUENCE2.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            if (profesor.getId() != null && profesor.getId() > 0){
                stmt.setLong(1, profesor.getEscuelaId());
                stmt.setString(2, profesor.getNombre());
                stmt.setString(3, profesor.getApPaterno());
                stmt.setString(4, profesor.getApMaterno());
                stmt.setString(5, profesor.getDireccionProf());
                stmt.setString(6, profesor.getCorreo());
                stmt.setString(7, profesor.getTelefono());
                stmt.setDouble(8, profesor.getSalario());
                stmt.setDate(9, Date.valueOf(profesor.getFechaContrato()) );
                stmt.setLong(10, profesor.getId());
            }else {
                stmt.setLong(1, profesor.getEscuelaId());
                stmt.setString(2, profesor.getNombre());
                stmt.setString(3, profesor.getApPaterno());
                stmt.setString(4, profesor.getApMaterno());
                stmt.setString(5, profesor.getDireccionProf());
                stmt.setString(6, profesor.getCorreo());
                stmt.setString(7, profesor.getTelefono());
                stmt.setDouble(8, profesor.getSalario());
                stmt.setDate(9, Date.valueOf(profesor.getFechaContrato()) );
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from profesores where id_profesor=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    //mapear/transformarun arreglo
    private Profesor getProfesor(ResultSet rs) throws  SQLException{
        Profesor p = new Profesor();
        p.setId(rs.getLong("ID_PROFESOR"));
        p.setEscuelaId(rs.getLong("ESCUELA_ID"));
        p.setNombre(rs.getString("NOMBRE"));
        p.setApPaterno(rs.getString("AP_PATERNO"));
        p.setApMaterno(rs.getString("AP_MATERNO"));
        p.setDireccionProf(rs.getString("DIRECCION_PROF"));
        p.setCorreo(rs.getString("CORREO"));
        p.setTelefono(rs.getString("TELEFONO"));
        p.setSalario(rs.getDouble("SALARIO"));
        p.setFechaContrato(rs.getDate("FECHA_CONTRATO").toLocalDate());
        return p;
    }
}

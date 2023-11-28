package com.soto.app.escuelas.repositories;

import com.soto.app.escuelas.models.Estudiante;
import com.soto.app.escuelas.models.enums.Semestres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudiantesRepository implements IRepository<Estudiante>{

    //atributos
    private Connection conn; //importar de java.sql

    //constructor
    public EstudiantesRepository(Connection conn){
        this.conn = conn;
    }
    @Override
    public List<Estudiante> listar() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM estudiantes")){
            while (rs.next()){
                Estudiante a = this.getEstudiante(rs);
                estudiantes.add(a);
            }
            return estudiantes;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Estudiante getById(Long id) throws SQLException {
        Estudiante estudiante = null;
        try (PreparedStatement stmt =
                conn.prepareStatement("SELECT * FROM estudiantes WHERE id_estudiante = ?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    estudiante = this.getEstudiante(rs);
                }
            }
        }
        return estudiante;
    }

    @Override
    public void guardar(Estudiante estudiante) throws SQLException {
    String sql = "";
    if (estudiante.getId() != null && estudiante.getId() > 0){
        sql = "update estudiantes set escuela_id=?, nombre=?, ap_paterno=?, "+
                "ap_materno=?, direccion_alum=?, semestre=?, telefono=?, correo=?, fecha_nacimiento=?, "+
                "activo=?, egreso=? where id_estudiante=?";
    } else {
        sql = "insert into estudiantes (id_estudiante, escuela_id, nombre, ap_paterno, ap_materno, "+
                "direccion_alum, semestre, telefono, correo, fecha_nacimiento, activo, egreso) " +
                "values (SEQUENCE3.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
    }
    //está creando un objeto PreparedStatement a partir de la conexión (conn) y la consulta SQL proporcionada (sql).
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
        if (estudiante.getId() != null && estudiante.getId() > 0){
            // Configurar parámetros para la actualización.
            stmt.setLong(1, estudiante.getEscuelaId());
            stmt.setString(2, estudiante.getNombre());
            stmt.setString(3, estudiante.getApPaterno());
            stmt.setString(4, estudiante.getApMaterno());
            stmt.setString(5, estudiante.getDireccionAlum());
            stmt.setString(6, estudiante.getSemestre().name());
            stmt.setString(7, estudiante.getTelefono());
            stmt.setString(8, estudiante.getCorreo());
            stmt.setDate(9, Date.valueOf(estudiante.getFechaNacimiento()) );
            stmt.setInt(10, estudiante.getActivo() ? 1: 0);
            stmt.setInt(11, estudiante.getEgreso() ? 1: 0);
            stmt.setLong(12, estudiante.getId());
        }else {
            stmt.setLong(1, estudiante.getEscuelaId());
            stmt.setString(2, estudiante.getNombre());
            stmt.setString(3, estudiante.getApPaterno());
            stmt.setString(4, estudiante.getApMaterno());
            stmt.setString(5, estudiante.getDireccionAlum());
            stmt.setString(6, estudiante.getSemestre().name());
            stmt.setString(7, estudiante.getTelefono());
            stmt.setString(8, estudiante.getCorreo());
            stmt.setDate(9, Date.valueOf(estudiante.getFechaNacimiento()) );
            stmt.setInt(10, estudiante.getActivo() ? 1: 0);
            stmt.setInt(11, estudiante.getEgreso() ? 1: 0);
        }
        stmt.executeUpdate();// Ejecutar la consulta de actualización o inserción.
    }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from estudiantes where id_estudiante=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    //mapear/transformarun arreglo
    private Estudiante getEstudiante(ResultSet rs) throws  SQLException{
        Estudiante e = new Estudiante();
        e.setId(rs.getLong("ID_ESTUDIANTE"));
        e.setEscuelaId(rs.getLong("ESCUELA_ID"));
        e.setNombre(rs.getString("NOMBRE"));
        e.setApPaterno(rs.getString("AP_PATERNO"));
        e.setApMaterno(rs.getString("AP_MATERNO"));
        e.setDireccionAlum(rs.getString("DIRECCION_ALUM"));
        e.setSemestre(Semestres.valueOf(rs.getString("SEMESTRE")) );
        e.setTelefono(rs.getString("TELEFONO"));
        e.setCorreo(rs.getString("CORREO"));
        e.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO").toLocalDate());
        e.setActivo(rs.getBoolean("ACTIVO"));
        e.setEgreso(rs.getBoolean("EGRESO"));
        return e;

    }
}

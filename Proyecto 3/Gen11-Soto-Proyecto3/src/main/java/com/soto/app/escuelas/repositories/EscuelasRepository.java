package com.soto.app.escuelas.repositories;

import com.soto.app.escuelas.models.Escuela;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscuelasRepository implements IRepository<Escuela>{
    //atributos
    private Connection conn; //importar de java.sql

    //constructor
    public EscuelasRepository(Connection conn){
        this.conn = conn;
    }
    @Override
    public List<Escuela> listar() throws SQLException {
        List<Escuela> escuelas = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ESCUELAS")){
            while (rs.next()){
                Escuela a = this.getEscuela(rs);
                escuelas.add(a);
            }
            return escuelas;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Escuela getById(Long id) throws SQLException {
        Escuela escuela = null;
        try (PreparedStatement stmt =
                conn.prepareStatement("SELECT * FROM escuelas WHERE ID_ESCUELA = ?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    escuela = this.getEscuela(rs);
                }
            }
        }
        return escuela;
    }

    @Override
    public void guardar(Escuela escuela) throws SQLException {
    String sql = "";
    if (escuela.getId() != null && escuela.getId() > 0){
        sql = "update escuelas set nombre=?,direccion=?,telefono=?,director_nombre=?, " +
                "director_app=?,director_apm=?,pagina_web=? " +
                "where id_escuela=?";
    } else {
        sql = "insert into escuelas (id_escuela,nombre,direccion,telefono,director_nombre, "+
                "director_app,director_apm,pagina_web) " +
                "values (SEQUENCE1.NEXTVAL,?,?,?,?,?,?,?)";
    }
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
        if (escuela.getId() != null && escuela.getId() > 0){
            stmt.setString(1, escuela.getNombre());
            stmt.setString(2, escuela.getDireccion());
            stmt.setString(3, escuela.getTelefono());
            stmt.setString(4, escuela.getDirectorNombre());
            stmt.setString(5, escuela.getDirectorApp());
            stmt.setString(6, escuela.getDirectorApm());
            stmt.setString(7, escuela.getPaginaWeb());
            stmt.setLong(8, escuela.getId());
        }else {
            stmt.setString(1, escuela.getNombre());
            stmt.setString(2, escuela.getDireccion());
            stmt.setString(3, escuela.getTelefono());
            stmt.setString(4, escuela.getDirectorNombre());
            stmt.setString(5, escuela.getDirectorApp());
            stmt.setString(6, escuela.getDirectorApm());
            stmt.setString(7, escuela.getPaginaWeb());
        }
        stmt.executeUpdate();
    }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from escuelas where id_escuela=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    //mapear/transformarun arreglo
    private Escuela getEscuela(ResultSet rs) throws  SQLException{
        Escuela e = new Escuela();
        e.setId(rs.getLong("ID_ESCUELA"));
        e.setNombre(rs.getString("NOMBRE"));
        e.setDireccion(rs.getString("DIRECCION"));
        e.setTelefono(rs.getString("TELEFONO"));
        e.setDirectorNombre(rs.getString("DIRECTOR_NOMBRE"));
        e.setDirectorApp(rs.getString("DIRECTOR_APP"));
        e.setDirectorApm(rs.getString("DIRECTOR_APM"));
        e.setPaginaWeb(rs.getString("PAGINA_WEB"));
        return e;

    }
}

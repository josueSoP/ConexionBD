package com.soto.app.escuelas.repositories;

import com.soto.app.escuelas.models.Clase;
import com.soto.app.escuelas.models.enums.Materias;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasesRepository implements IRepository<Clase>{

    //atributos
    private Connection conn; //importar de java.sql

    //constructor
    public ClasesRepository(Connection conn){
        this.conn = conn;
    }


    @Override
    public List<Clase> listar() throws SQLException {
        List<Clase> clases = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM clases")){
            while (rs.next()){
                Clase a = this.getClase(rs);
                clases.add(a);
            }
            return clases;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Clase getById(Long id) throws SQLException {
        Clase clase = null;
        try (PreparedStatement stmt =
                conn.prepareStatement("SELECT * FROM clases WHERE id_clase = ?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    clase = this.getClase(rs);
                }
            }
        }
        return clase;
    }

    @Override
    public void guardar(Clase clase) throws SQLException {
    String sql = "";
    if (clase.getId() != null && clase.getId() > 0){
        sql = "update clases set profesor_id=?,materia=?,salon=?,hr_inicio=?, " +
                "hr_fin=?,lunes=?,martes=?,miercoles=?,jueves=?,viernes=?,sabado=?,domingo=? " +
                "where id_clase=?";
    } else {
        sql = "insert into clases (id_clase,profesor_id,materia,salon,hr_inicio," +
                "hr_fin,lunes,martes,miercoles,jueves,viernes,sabado,domingo) " +
                "values (SEQUENCE4.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
    }
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
        if (clase.getId() != null && clase.getId() > 0){
            stmt.setLong(1, clase.getProfesorId());
            stmt.setString(2, clase.getMateria().name());
            stmt.setString(3, clase.getSalon());
            stmt.setString(4, clase.getHrInicio());
            stmt.setString(5, clase.getHrFin());
            stmt.setInt(6, clase.getLunes() ? 1 : 0);
            stmt.setInt(7, clase.getMartes() ? 1 : 0);
            stmt.setInt(8, clase.getMiercoles() ? 1 : 0);
            stmt.setInt(9, clase.getJueves() ? 1 : 0);
            stmt.setInt(10, clase.getViernes() ? 1 : 0);
            stmt.setInt(11, clase.getSabado() ? 1 : 0);
            stmt.setInt(12, clase.getDomingo() ? 1 : 0);
            stmt.setLong(13, clase.getId());
        }else {
            stmt.setLong(1, clase.getProfesorId());
            stmt.setString(2, clase.getMateria().name());
            stmt.setString(3, clase.getSalon());
            stmt.setString(4, clase.getHrInicio());
            stmt.setString(5, clase.getHrFin());
            stmt.setInt(6, clase.getLunes() ? 1 : 0);
            stmt.setInt(7, clase.getMartes() ? 1 : 0);
            stmt.setInt(8, clase.getMiercoles() ? 1 : 0);
            stmt.setInt(9, clase.getJueves() ? 1 : 0);
            stmt.setInt(10, clase.getViernes() ? 1 : 0);
            stmt.setInt(11, clase.getSabado() ? 1 : 0);
            stmt.setInt(12, clase.getDomingo() ? 1 : 0);
        }
        stmt.executeUpdate();
    }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from clases where id_clase=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    //mapear/transformar un arreglo
    private Clase getClase(ResultSet rs) throws  SQLException{
        Clase c = new Clase();
        c.setId(rs.getLong("ID_CLASE"));
        c.setProfesorId(rs.getLong("PROFESOR_ID"));
        c.setMateria(Materias.valueOf(rs.getString("MATERIA")));
        c.setSalon(rs.getString("SALON"));
        c.setHrInicio(rs.getString("HR_INICIO"));
        c.setHrFin(rs.getString("HR_FIN"));
        c.setLunes(rs.getBoolean("LUNES"));
        c.setMartes(rs.getBoolean("MARTES"));
        c.setMiercoles(rs.getBoolean("MIERCOLES"));
        c.setJueves(rs.getBoolean("JUEVES"));
        c.setViernes(rs.getBoolean("VIERNES"));
        c.setSabado(rs.getBoolean("SABADO"));
        c.setDomingo(rs.getBoolean("DOMINGO"));

        return c;
    }
}

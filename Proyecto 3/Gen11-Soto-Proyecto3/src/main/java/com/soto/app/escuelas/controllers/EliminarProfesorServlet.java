package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Profesor;
import com.soto.app.escuelas.services.ProfesoresService;
import com.soto.app.escuelas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/profesores/eliminar")
public class EliminarProfesorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");

        //declaramos un objeto de tipo servicio
        IService<Profesor> service = new ProfesoresService(conn);
        Long id;

        try{
            id=Long.parseLong(req.getParameter("id"));//231
        }catch(NumberFormatException e){
            id = 0L;
        }
        if(id>0){
            Optional<Profesor> o = service.getById(id);
            if(o.isPresent()){
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+"/profesores/listar");
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el profesor en la base de datos");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath()+"profesores/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}

package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Escuela;
import com.soto.app.escuelas.services.EscuelasService;
import com.soto.app.escuelas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/escuelas/eliminar")
public class EliminarEscuelaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");

        //declaramos un objeto de tipo servicio
        IService<Escuela> service = new EscuelasService(conn);
        Long id;

        try{
            id=Long.parseLong(req.getParameter("id"));//231
        }catch(NumberFormatException e){
            id = 0L;
        }
        if(id>0){
            Optional<Escuela> o = service.getById(id);
            if(o.isPresent()){
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+"/escuelas/listar");
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el escuela en la base de datos");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath()+"escuelas/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}

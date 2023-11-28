package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Profesor;
import com.soto.app.escuelas.services.IProfesoresService;
import com.soto.app.escuelas.services.ProfesoresService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/profesores/detalle")
public class DetalleProfesorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");
        //declaramos un objeto de tipo servicio
        IProfesoresService service = new ProfesoresService(conn);
        req.setAttribute("escuelas", service.listarEscuelas());
        List<Profesor> profesorList = service.listar();
        req.setAttribute("profesores", profesorList);

        Long id;

        try{
            id=Long.parseLong(req.getParameter("id"));//231
        }catch(NumberFormatException e){
            id = 0L;
        }
        Profesor profesor = new Profesor();
        if(id>0){
            Optional<Profesor> o = service.getById(id);
            if(o.isPresent()){
                profesor = o.get();
                req.setAttribute("profesor", profesor);
                getServletContext().getRequestDispatcher("/detalleProfesor.jsp").forward(req,resp);
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "Noexiste el profesor en la base de datos");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath()+"profesores/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}

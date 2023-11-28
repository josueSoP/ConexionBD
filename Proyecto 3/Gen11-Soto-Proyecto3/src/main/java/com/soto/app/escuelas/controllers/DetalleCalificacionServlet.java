package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Calificacion;
import com.soto.app.escuelas.services.CalificacionesService;
import com.soto.app.escuelas.services.ICalificacionesService;
import com.soto.app.escuelas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/calificaciones/detalle")
public class DetalleCalificacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");
        //declaramos un objeto de tipo servicio
        ICalificacionesService service = new CalificacionesService(conn); //*
        req.setAttribute("clases", service.listarClases());
        req.setAttribute("estudiantes", service.listarEstudiantes());
        List<Calificacion> calificacionList = service.listar();
        req.setAttribute("calificaciones", calificacionList);

        Long id;

        try{
            id=Long.parseLong(req.getParameter("id"));//231
        }catch(NumberFormatException e){
            id = 0L;
        }
        Calificacion calificacion = new Calificacion();
        if(id>0){
            Optional<Calificacion> o = service.getById(id);
            if(o.isPresent()){
                calificacion = o.get();
                req.setAttribute("calificacion", calificacion);
                getServletContext().getRequestDispatcher("/detalleCalificacion.jsp").forward(req,resp);
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "Noexiste el calificacion en la base de datos");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath()+"calificaciones/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}

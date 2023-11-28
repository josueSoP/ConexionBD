package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Calificacion;
import com.soto.app.escuelas.services.ICalificacionesService;
import com.soto.app.escuelas.services.IService;
import com.soto.app.escuelas.services.CalificacionesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/calificaciones/editar")
public class EdicionCalificacionServlet extends HttpServlet {
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
                getServletContext().getRequestDispatcher("/edicionCalificacion.jsp").forward(req,resp);
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe la calificacion en la base de datos");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath()+"calificaciones/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Calificacion> service = new CalificacionesService(conn);

        String estudianteIdString = req.getParameter("estudianteId");
        String claseIdString = req.getParameter("claseId");
        String calificacionString = req.getParameter("calificacion");
        String nota = req.getParameter("nota");


        ////convertimos de String a tipos Long
        Long estudianteId, claseId;
        try {
            estudianteId = Long.parseLong(estudianteIdString);
            claseId = Long.parseLong(claseIdString);

        } catch (NumberFormatException e) {
            estudianteId = null; // Manejar el error de conversión si es necesario
            claseId = null; // Manejar el error de conversión si es necesario
        }
        ////convertimos de String a tipos Double
        Double calificacionDouble;
        try {
            calificacionDouble = Double.parseDouble(calificacionString);
        } catch (NumberFormatException e) {
            calificacionDouble = null; // Manejar el error de conversión si es necesario
        }




        //validaciones por si se deja en blanco un input
        Map<String, String> errores = new HashMap<>();

        if (estudianteIdString == null || estudianteIdString.isBlank()) {
            errores.put("estudianteIdString", "La escuela es requerida!");
        }
        if (claseIdString == null || claseIdString.isBlank()) {
            errores.put("claseIdString", "La clase es requerida!");
        }
        if (calificacionString == null || calificacionString.isBlank()) {
            errores.put("calificacionString", "La calificacion es requerida!");
        }

        long id;
        id = Long.parseLong(req.getParameter("id"));
        Calificacion calificacion = new Calificacion();
        calificacion.setId(id);
        calificacion.setEstudianteId(estudianteId);
        calificacion.setClaseId(claseId);
        calificacion.setCalificacion(calificacionDouble);
        calificacion.setNota(nota);

        if (errores.isEmpty()) {
            service.guardar(calificacion);
            resp.sendRedirect(req.getContextPath() + "/calificaciones/listar");
        }else {
            ICalificacionesService servicee = new CalificacionesService(conn); //*
            req.setAttribute("clases", servicee.listarClases());
            req.setAttribute("estudiantes", servicee.listarEstudiantes());
            List<Calificacion> calificacionList = service.listar();
            req.setAttribute("calificaciones", calificacionList);
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaCalificacion.jsp").forward(req, resp);
        }
    }
}

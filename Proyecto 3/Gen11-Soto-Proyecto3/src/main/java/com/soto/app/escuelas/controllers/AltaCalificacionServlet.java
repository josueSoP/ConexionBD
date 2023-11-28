package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Calificacion;
import com.soto.app.escuelas.models.Estudiante;
import com.soto.app.escuelas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;


@WebServlet("/calificaciones/alta")
public class AltaCalificacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=(Connection) req.getAttribute("conn");
        ICalificacionesService service = new CalificacionesService(conn);
        List<Estudiante> estudiantes = service.listarEstudiantes();
        req.setAttribute("estudiantes", estudiantes); // Agrega esta línea
        req.setAttribute("clases", service.listarClases());
        getServletContext().getRequestDispatcher("/altaCalificacion.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Calificacion> service = new CalificacionesService(conn);

        String estudianteIdString = req.getParameter("estudianteId");
        String claseIdString = req.getParameter("claseId");
        String calificacionString = req.getParameter("calificacion");
        String nota = req.getParameter("nota");
        // Verifica si nota es nulo y, en ese caso, asigna una cadena vacía
        if (nota == null) {
            nota = "";
        }


        ////convertimos de String a tipos Long
        Long estudianteId;
        try {
            estudianteId = Long.parseLong(estudianteIdString);

        } catch (NumberFormatException e) {
            estudianteId = null; // Manejar el error de conversión si es necesario
        }
        Long claseId;
        try {
            claseId = Long.parseLong(claseIdString);

        } catch (NumberFormatException e) {
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


        if (errores.isEmpty()) {
            Calificacion calificacion = new Calificacion();
            calificacion.setId(0L);
            calificacion.setEstudianteId(estudianteId);
            calificacion.setClaseId(claseId);
            calificacion.setCalificacion(calificacionDouble);
            calificacion.setNota(nota);
            service.guardar(calificacion);
            resp.sendRedirect(req.getContextPath() + "/calificaciones/listar");
        }else {
            ICalificacionesService servicee = new CalificacionesService(conn);
            List<Estudiante> estudiantes = servicee.listarEstudiantes();
            req.setAttribute("estudiantes", estudiantes); // Agrega esta línea
            req.setAttribute("clases", servicee.listarClases());
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaCalificacion.jsp").forward(req, resp);
        }
    }
}

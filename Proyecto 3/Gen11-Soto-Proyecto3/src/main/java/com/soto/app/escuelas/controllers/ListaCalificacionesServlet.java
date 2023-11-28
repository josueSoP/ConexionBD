package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Calificacion;
import com.soto.app.escuelas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/calificaciones/listar")
public class ListaCalificacionesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");
        // Declaramos un objeto de tipo servicio
        ICalificacionesService service = new CalificacionesService(conn); //*

        req.setAttribute("clases", service.listarClases());
        req.setAttribute("estudiantes", service.listarEstudiantes());
        List<Calificacion> calificacion = service.listar();
        req.setAttribute("calificaciones", calificacion);
        getServletContext().getRequestDispatcher("/listaCalificaciones.jsp").forward(req, resp);
    }
}

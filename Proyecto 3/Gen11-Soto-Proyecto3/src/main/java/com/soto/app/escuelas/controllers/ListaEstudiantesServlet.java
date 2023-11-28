package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Estudiante;
import com.soto.app.escuelas.models.Profesor;
import com.soto.app.escuelas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/estudiantes/listar")
public class ListaEstudiantesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");
        // Declaramos un objeto de tipo servicio
        IEstudiantesService service = new EstudiantesService(conn); //*

        req.setAttribute("escuelas", service.listarEscuelas()); //*
        List<Estudiante> estudiante = service.listar();
        req.setAttribute("estudiantes", estudiante);
        getServletContext().getRequestDispatcher("/listaEstudiantes.jsp").forward(req, resp);
    }
}

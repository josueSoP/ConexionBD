package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Clase;
import com.soto.app.escuelas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/clases/listar")
public class ListaClasesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");
        // Declaramos un objeto de tipo servicio
        IClasesService service = new ClasesService(conn); //*

        req.setAttribute("profesores", service.listarProfesores()); //*
        List<Clase> clase = service.listar();
        req.setAttribute("clases", clase);
        getServletContext().getRequestDispatcher("/listaClases.jsp").forward(req, resp);
    }
}

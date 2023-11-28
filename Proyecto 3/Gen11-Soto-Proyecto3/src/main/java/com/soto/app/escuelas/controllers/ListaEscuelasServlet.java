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
import java.util.List;

@WebServlet("/escuelas/listar")
public class ListaEscuelasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");

        // Declaramos un objeto de tipo servicio
        IService<Escuela> service = new EscuelasService(conn);
        List<Escuela> escuelas = service.listar();
        req.setAttribute("escuelas", escuelas);
        getServletContext().getRequestDispatcher("/listaEscuelas.jsp").forward(req, resp);
    }
}

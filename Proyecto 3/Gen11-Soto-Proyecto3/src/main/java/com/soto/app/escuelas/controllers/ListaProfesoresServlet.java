package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Profesor;
import com.soto.app.escuelas.services.IProfesoresService;
import com.soto.app.escuelas.services.ProfesoresService;
import com.soto.app.escuelas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/profesores/listar")
public class ListaProfesoresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");
        // Declaramos un objeto de tipo servicio
        IProfesoresService service = new ProfesoresService(conn);

        req.setAttribute("escuelas", service.listarEscuelas());
        List<Profesor> profesor = service.listar();
        req.setAttribute("profesores", profesor);
        getServletContext().getRequestDispatcher("/listaProfesores.jsp").forward(req, resp);
    }

}

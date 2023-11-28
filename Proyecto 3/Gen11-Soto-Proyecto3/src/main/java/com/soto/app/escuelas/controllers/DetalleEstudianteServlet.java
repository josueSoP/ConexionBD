package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Estudiante;
import com.soto.app.escuelas.services.EstudiantesService;
import com.soto.app.escuelas.services.IEstudiantesService;
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

@WebServlet("/estudiantes/detalle")
public class DetalleEstudianteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");

        //declaramos un objeto de tipo servicio
        IEstudiantesService service = new EstudiantesService(conn); //*
        req.setAttribute("escuelas", service.listarEscuelas()); //*
        List<Estudiante> estudianteList = service.listar();
        req.setAttribute("estudiantes", estudianteList);

        Long id;

        try{
            id=Long.parseLong(req.getParameter("id"));//231
        }catch(NumberFormatException e){
            id = 0L;
        }
        Estudiante estudiante = new Estudiante();
        if(id>0){
            Optional<Estudiante> o = service.getById(id);
            if(o.isPresent()){
                estudiante = o.get();
                req.setAttribute("estudiante", estudiante);
                getServletContext().getRequestDispatcher("/detalleEstudiante.jsp").forward(req,resp);
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "Noexiste el estudiante en la base de datos");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath()+"estudiantes/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}

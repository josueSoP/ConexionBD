package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Clase;
import com.soto.app.escuelas.services.ClasesService;
import com.soto.app.escuelas.services.IClasesService;
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

@WebServlet("/clases/detalle")
public class DetalleClaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");
        //declaramos un objeto de tipo servicio
        IClasesService service = new ClasesService(conn); //*
        req.setAttribute("profesores", service.listarProfesores()); //*
        List<Clase> claseList = service.listar();
        req.setAttribute("clases", claseList);


        Long id;

        try{
            id=Long.parseLong(req.getParameter("id"));//231
        }catch(NumberFormatException e){
            id = 0L;
        }
        Clase clase = new Clase();
        if(id>0){
            Optional<Clase> o = service.getById(id);
            if(o.isPresent()){
                clase = o.get();
                req.setAttribute("clase", clase);
                getServletContext().getRequestDispatcher("/detalleClase.jsp").forward(req,resp);
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "Noexiste el clase en la base de datos");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath()+"clases/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }
}

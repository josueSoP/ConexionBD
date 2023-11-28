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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/profesores/alta")
public class AltaProfesorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=(Connection) req.getAttribute("conn");
        IProfesoresService service = new ProfesoresService(conn);
        req.setAttribute("escuelas",service.listarEscuelas());
        getServletContext().getRequestDispatcher("/altaProfesor.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Profesor> service = new ProfesoresService(conn);

        String idEscuelaString = req.getParameter("escuelaId");
        String nombre = req.getParameter("nombre");
        String apPaterno = req.getParameter("apPaterno");
        String apMaterno = req.getParameter("apMaterno");
        String direccionProf = req.getParameter("direccionProf");
        String correo = req.getParameter("correo");
        String telefono = req.getParameter("telefono");
        String saliarioString = req.getParameter("salario");
        String fechaContratoString = req.getParameter("fechaContrato");


        ////convertimos de String a tipos Long
        Long escuelaId;
        try {
            escuelaId = Long.parseLong(idEscuelaString);
        } catch (NumberFormatException e) {
            escuelaId = null; // Manejar el error de conversión si es necesario
        }
        //convertios de String a tipo Fecha
        LocalDate fechaContrato;
        try {
            fechaContrato = LocalDate.parse(fechaContratoString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            fechaContrato = null;
        }
        ////convertimos de String a tipos Double
        Double salario;
        try {
            salario = Double.parseDouble(saliarioString);
        } catch (NumberFormatException e) {
            salario = null; // Manejar el error de conversión si es necesario
        }



        //validaciones por si se deja en blanco un input
        Map<String, String> errores = new HashMap<>();

        if (idEscuelaString == null || idEscuelaString.isBlank()) {
            errores.put("escuelaId", "La escuela es requerida!");
        }
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es requerido!");
        }
        if (apPaterno == null || apPaterno.isBlank()) {
            errores.put("apPaterno", "El apellido materno es requerido!");
        }
        if (apMaterno == null || apMaterno.isBlank()) {
            errores.put("apMaterno", "El apellido paterno requerido!");
        }
        if (direccionProf == null || direccionProf.isBlank()) {
            errores.put("direccionProf", "El nombre es requerido!");
        }
        if (correo == null || correo.isBlank()) {
            errores.put("correo", "El correo es requerido!");
        }
        if (telefono == null || telefono.isBlank()) {
            errores.put("telefono", "El telefono es requerido!");
        }
        if (saliarioString == null || saliarioString.isBlank()) {
            errores.put("saliarioString", "El salario es requerido!");
        }
        if (fechaContratoString == null || fechaContratoString.isBlank()) {
            errores.put("fechaContratoString", "La fecha de contratacion es requerida!");
        }
        if (errores.isEmpty()) {
            Profesor profesor = new Profesor();
            profesor.setId(0L);
            profesor.setEscuelaId(escuelaId);
            profesor.setNombre(nombre);
            profesor.setApPaterno(apPaterno);
            profesor.setApMaterno(apMaterno);
            profesor.setDireccionProf(direccionProf);
            profesor.setCorreo(correo);
            profesor.setTelefono(telefono);
            profesor.setSalario(salario);
            profesor.setFechaContrato(fechaContrato);
            service.guardar(profesor);
            resp.sendRedirect(req.getContextPath() + "/profesores/listar");
        }else {
            IEstudiantesService servicee = new EstudiantesService(conn);
            req.setAttribute("escuelas", servicee.listarEscuelas()); //*
            List<Estudiante> estudianteList = servicee.listar();
            req.setAttribute("estudiantes", estudianteList);
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaProfesor.jsp").forward(req, resp);
        }
    }
}

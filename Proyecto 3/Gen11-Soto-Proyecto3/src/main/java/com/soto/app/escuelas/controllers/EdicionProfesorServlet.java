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
import java.util.Optional;

@WebServlet("/profesores/editar")
public class EdicionProfesorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");

        //declaramos un objeto de tipo servicio
        IProfesoresService service = new ProfesoresService(conn);
        req.setAttribute("escuelas", service.listarEscuelas());
        List<Profesor> profesorList = service.listar();
        req.setAttribute("profesores", profesorList);


        Long id;

        try{
            id=Long.parseLong(req.getParameter("id"));//231
        }catch(NumberFormatException e){
            id = 0L;
        }
        Profesor profesor = new Profesor();
        if(id>0){
            Optional<Profesor> o = service.getById(id);
            if(o.isPresent()){
                profesor = o.get();
                req.setAttribute("profesor", profesor);
                getServletContext().getRequestDispatcher("/edicionProfesor.jsp").forward(req,resp);
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe la profesor en la base de datos");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath()+"profesores/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
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

        long id;
        id = Long.parseLong(req.getParameter("id"));
        Profesor profesor = new Profesor();
        profesor.setId(id);
        profesor.setEscuelaId(escuelaId);
        profesor.setNombre(nombre);
        profesor.setApPaterno(apPaterno);
        profesor.setApMaterno(apMaterno);
        profesor.setDireccionProf(direccionProf);
        profesor.setCorreo(correo);
        profesor.setTelefono(telefono);
        profesor.setSalario(salario);
        profesor.setFechaContrato(fechaContrato);


        if (errores.isEmpty()) {
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

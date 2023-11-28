package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Estudiante;
import com.soto.app.escuelas.models.Estudiante;
import com.soto.app.escuelas.models.enums.Semestres;
import com.soto.app.escuelas.services.EstudiantesService;
import com.soto.app.escuelas.services.IEstudiantesService;
import com.soto.app.escuelas.services.IService;
import com.soto.app.escuelas.services.EstudiantesService;
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

@WebServlet("/estudiantes/editar")
public class EdicionEstudianteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");

        //declaramos un objeto de tipo servicio
        IEstudiantesService service = new EstudiantesService(conn); //*
        req.setAttribute("escuelas", service.listarEscuelas()); //*
        List<Estudiante> estudianteList = service.listar();
        req.setAttribute("estudiantes", estudianteList );

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
                getServletContext().getRequestDispatcher("/edicionEstudiante.jsp").forward(req,resp);
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe la estudiante en la base de datos");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath()+"estudiantes/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Estudiante> service = new EstudiantesService(conn);

        String escuelaIdString = req.getParameter("escuelaId");
        String nombre = req.getParameter("nombre");
        String apPaterno = req.getParameter("apPaterno");
        String apMaterno = req.getParameter("apMaterno");
        String direccionAlum = req.getParameter("direccionAlum");
        String semestreString = req.getParameter("semestre");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        String fechaString = req.getParameter("fechaNacimiento");
        String activoString = req.getParameter("activo");
        String egresoString = req.getParameter("egreso");



        ////convertimos de String a tipos Long
        Long escuelaId;
        try {
            escuelaId = Long.parseLong(escuelaIdString);
        } catch (NumberFormatException e) {
            escuelaId = null; // Manejar el error de conversión si es necesario
        }
        ////convertimos de String a tipos Objeto (Semestres)
        Semestres semestre;
        try{
            semestre = Semestres.valueOf(semestreString);// VOLVO
        }catch(DateTimeParseException e ){
            semestre= null;
        }
        //convertimos de String a tipo Fecha
        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            fechaNacimiento = null;
        }
        ////convertimos de String a tipos a Integer
        Boolean activo, egreso;
        try{
            activo = Boolean.parseBoolean(activoString);
            egreso = Boolean.parseBoolean(egresoString);
        }catch(DateTimeParseException e ){
            activo= null;
            egreso = null;
        }



        //validaciones por si se deja en blanco un input
        Map<String, String> errores = new HashMap<>();

        if (escuelaIdString == null || escuelaIdString.isBlank()) {
            errores.put("escuelaId", "La escuela es requerida!");
        }
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es requerido!");
        }
        if (apPaterno == null || apPaterno.isBlank()) {
            errores.put("apPaterno", "El apellido paterno es requerido!");
        }
        if (apMaterno == null || apMaterno.isBlank()) {
            errores.put("apMaterno", "El apellido materno es requerido!");
        }
        if (direccionAlum == null || direccionAlum.isBlank()) {
            errores.put("direccionProf", "La direccion es requerida!");
        }
        if (semestreString == null || semestreString.isBlank()) {
            errores.put("semestreString", "El semestre es requerido!");
        }
        if (telefono == null || telefono.isBlank()) {
            errores.put("telefono", "El telefono es requerida!");
        }
        if (correo == null || correo.isBlank()) {
            errores.put("directorApp", "El correo es requerido!");
        }
        if (fechaString == null || fechaString.isBlank()) {
            errores.put("fechaNacimiento", "La fecha de nacimiento requerida!");
        }


        long id;
        id = Long.parseLong(req.getParameter("id"));
        Estudiante estudiante = new Estudiante();
        estudiante.setId(id);
        estudiante.setEscuelaId(escuelaId);
        estudiante.setNombre(nombre);
        estudiante.setApPaterno(apPaterno);
        estudiante.setApMaterno(apMaterno);
        estudiante.setDireccionAlum(direccionAlum);
        estudiante.setSemestre(semestre);
        estudiante.setTelefono(telefono);
        estudiante.setCorreo(correo);
        estudiante.setFechaNacimiento(fechaNacimiento);
        estudiante.setActivo(activo);
        estudiante.setEgreso(egreso);

        if (errores.isEmpty()) {
            service.guardar(estudiante);
            resp.sendRedirect(req.getContextPath() + "/estudiantes/listar");
        }else {
            IEstudiantesService servicee = new EstudiantesService(conn); //*
            req.setAttribute("escuelas", servicee.listarEscuelas()); //*
            List<Estudiante> estudianteList = service.listar();
            req.setAttribute("estudiantes", estudianteList);
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaEstudiante.jsp").forward(req, resp);
        }
    }
}

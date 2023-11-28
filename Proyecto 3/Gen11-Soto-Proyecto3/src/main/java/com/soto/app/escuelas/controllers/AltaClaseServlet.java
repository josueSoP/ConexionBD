package com.soto.app.escuelas.controllers;

import com.soto.app.escuelas.models.Clase;
import com.soto.app.escuelas.models.enums.Materias;
import com.soto.app.escuelas.models.enums.Semestres;
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
import java.util.Map;

@WebServlet("/clases/alta")
public class AltaClaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=(Connection) req.getAttribute("conn");
        IClasesService service = new ClasesService(conn);
        req.setAttribute("profesores",service.listarProfesores());
        getServletContext().getRequestDispatcher("/altaClase.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Clase> service = new ClasesService(conn);

        String profesorIdString = req.getParameter("profesorId");
        String materiaString = req.getParameter("materia");
        String salon = req.getParameter("salon");
        String hrInicio = req.getParameter("hrInicio");
        String hrFin = req.getParameter("hrFin");

        //CHECKBOXs
        String CheckLunes[], CheckMartes[],CheckMiercoles[], CheckJueves[]; // []
        String CheckViernes[], CheckSabado[],CheckDomingo[]; // []

        CheckLunes = req.getParameterValues("lunes");
        CheckMartes = req.getParameterValues("martes");
        CheckMiercoles = req.getParameterValues("miercoles");
        CheckJueves = req.getParameterValues("jueves");
        CheckViernes = req.getParameterValues("viernes");
        CheckSabado = req.getParameterValues("sabado");
        CheckDomingo = req.getParameterValues("domingo");

        Boolean lunesCheckbox,martesCheckbox,miercolesCheckbox;
        Boolean juevesCheckbox,viernesCheckbox,sabadoCheckbox,domingoCheckbox;

        //LUNES
        if (CheckLunes != null) {
            lunesCheckbox = true;
        } else {
            lunesCheckbox = false;
        }
        //MARTES
        if (CheckMartes != null) {
            martesCheckbox = true;
        } else {
            martesCheckbox = false;
        }
        //MIERCOLES
        if (CheckMiercoles != null) {
            miercolesCheckbox = true;
        } else {
            miercolesCheckbox = false;
        }
        //JUEVES
        if (CheckJueves != null) {
            juevesCheckbox = true;
        } else {
            juevesCheckbox = false;
        }
        //VIERNES
        if (CheckViernes != null) {
            viernesCheckbox = true;
        } else {
            viernesCheckbox = false;
        }
        //SABADO
        if (CheckSabado != null) {
            sabadoCheckbox = true;
        } else {
            sabadoCheckbox = false;
        }
        //DOMING
        if (CheckDomingo != null) {
            domingoCheckbox = true;
        } else {
            domingoCheckbox = false;
        }

        ////convertimos de String a tipos Long
        Long profesorId;
        try {
            profesorId = Long.parseLong(profesorIdString);
        } catch (NumberFormatException e) {
            profesorId = null; // Manejar el error de conversión si es necesario
        }
        ////convertimos de String a tipos Objeto (Materias)
        Materias materia;
        try{
            materia = Materias.valueOf(materiaString);// VOLVO
        }catch(DateTimeParseException e ){
            materia= null;
        }


        //validaciones por si se deja en blanco un input
        Map<String, String> errores = new HashMap<>();

        if (profesorIdString == null || profesorIdString.isBlank()) {
            errores.put("profesorId", "El profesor es requerido!");
        }
        if (materiaString == null || materiaString.isBlank()) {
            errores.put("materia", "La materia es requerida!");
        }
        if (salon == null || salon.isBlank()) {
            errores.put("salon", "El salon es requerido!");
        }
        if (hrInicio == null || hrInicio.isBlank()) {
            errores.put("hrInicio", "La hora de inicio de la clase requerida!");
        }
        if (hrFin == null || hrFin.isBlank()) {
            errores.put("hrFin", "La hora de fin de la clase requerida!");
        }



        if (errores.isEmpty()) {
            Clase clase = new Clase();
            clase.setId(0L);
            clase.setProfesorId(profesorId);
            clase.setMateria(materia);
            clase.setSalon(salon);
            clase.setHrInicio(hrInicio);
            clase.setHrFin(hrFin);
            clase.setLunes(lunesCheckbox);
            clase.setMartes(martesCheckbox);
            clase.setMiercoles(miercolesCheckbox);
            clase.setJueves(juevesCheckbox);
            clase.setViernes(viernesCheckbox);
            clase.setSabado(sabadoCheckbox);
            clase.setDomingo(domingoCheckbox);
            service.guardar(clase);
            resp.sendRedirect(req.getContextPath() + "/clases/listar");
        }else {
            IClasesService servicee = new ClasesService(conn);
            req.setAttribute("profesores",servicee.listarProfesores());
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaClase.jsp").forward(req, resp);
        }
    }
}

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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/escuelas/alta")
public class AltaEscuelaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaEscuela.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Escuela> service = new EscuelasService(conn);

        String nombre = req.getParameter("nombre");
        String direccion = req.getParameter("direccion");
        String telefono = req.getParameter("telefono");
        String directorNombre = req.getParameter("directorNombre");
        String directorApp = req.getParameter("directorApp");
        String directorApm = req.getParameter("directorApm");
        String paginaWeb = req.getParameter("paginaWeb");


        //validaciones por si se deja en blanco un input
        Map<String, String> errores = new HashMap<>();

        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es requerido!");
        }
        if (direccion == null || direccion.isBlank()) {
            errores.put("direccion", "La direccion es requerida!");
        }
        if (telefono == null || telefono.isBlank()) {
            errores.put("telefono", "El telefono es requerido!");
        }
        if (directorNombre == null || directorNombre.isBlank()) {
            errores.put("directorNombre", "El nombre es requerido!");
        }
        if (directorApp == null || directorApp.isBlank()) {
            errores.put("directorApp", "El apellido paterno es requerido!");
        }
        if (directorApm == null || directorApm.isBlank()) {
            errores.put("directorApm", "El apellido materno es requerido!");
        }

        if (errores.isEmpty()) {
            Escuela escuela = new Escuela();
            escuela.setId(0L);
            escuela.setNombre(nombre);
            escuela.setDireccion(direccion);
            escuela.setTelefono(telefono);
            escuela.setDirectorNombre(directorNombre);
            escuela.setDirectorApp(directorApp);
            escuela.setDirectorApm(directorApm);
            escuela.setPaginaWeb(paginaWeb);
            service.guardar(escuela);
            resp.sendRedirect(req.getContextPath() + "/escuelas/listar");
        }else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaEscuela.jsp").forward(req, resp);
        }
    }
}

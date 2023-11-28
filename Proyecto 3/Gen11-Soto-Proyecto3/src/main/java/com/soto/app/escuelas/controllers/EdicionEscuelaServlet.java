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
import java.util.Optional;

@WebServlet("/escuelas/editar")
public class EdicionEscuelaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");

        //declaramos un objeto de tipo servicio
        IService<Escuela> service = new EscuelasService(conn);
        Long id;

        try{
            id=Long.parseLong(req.getParameter("id"));//231
        }catch(NumberFormatException e){
            id = 0L;
        }
        Escuela escuela = new Escuela();
        if(id>0){
            Optional<Escuela> o = service.getById(id);
            if(o.isPresent()){
                escuela = o.get();
                req.setAttribute("escuela", escuela);
                getServletContext().getRequestDispatcher("/edicionEscuela.jsp").forward(req,resp);
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe la escuela en la base de datos");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath()+"escuelas/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parametro en la url");
        }
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

        long id;
        id = Long.parseLong(req.getParameter("id"));
        Escuela escuela = new Escuela();
        escuela.setId(id);
        escuela.setNombre(nombre);
        escuela.setDireccion(direccion);
        escuela.setTelefono(telefono);
        escuela.setDirectorNombre(directorNombre);
        escuela.setDirectorApp(directorApp);
        escuela.setDirectorApm(directorApm);
        escuela.setPaginaWeb(paginaWeb);

        if (errores.isEmpty()) {
            service.guardar(escuela);
            resp.sendRedirect(req.getContextPath() + "/escuelas/listar");
        }else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaEscuela.jsp").forward(req, resp);
        }
    }
}

package com.soto.app.escuelas.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

//api que calcula la edad a partir de una fecha de nacimiento
public class APIEdad extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtenemos el parámetro 'fechaNacimiento' de la solicitud
        String fechaNacimientoStr = req.getParameter("fechaNacimiento");

        // Verificamos si se proporcionó la fecha de nacimiento
        if (fechaNacimientoStr == null || fechaNacimientoStr.isEmpty()) {
            resp.getWriter().write("Por favor, proporcione la fecha de nacimiento en el parámetro 'fechaNacimiento'.");
        } else {
            try {
                // Parseamos la fecha de nacimiento
                LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);

                // Calculamos la edad actual
                LocalDate fechaActual = LocalDate.now();
                Period edad = Period.between(fechaNacimiento, fechaActual);

                // Enviamos la respuesta con la edad calculada
                resp.getWriter().write("Su edad actual es: " + edad.getYears() + " años, " + edad.getMonths() + " meses y " + edad.getDays() + " días.");
            } catch (Exception e) {
                resp.getWriter().write("Error al procesar la fecha de nacimiento. Asegúrese de usar el formato yyyy-MM-dd.");
            }
        }
    }
}

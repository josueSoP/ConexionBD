//Esta clase implementa un filtro de servlet que gestiona la conexión a la base de datos y la proporciona a las solicitudes del servlet.
//El filtro se aplica a todas las URL (/*) y utiliza la clase ConexionBD para obtener la conexión a la base de datos.
package com.soto.app.escuelas.filter;

import com.soto.app.escuelas.utils.ConexionBD;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.sql.Connection;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    // Método para obtener una conexión a la base de datos utilizando la clase ConexionBD de carpeta UTILS
    private Connection getConnection() {
        return ConexionBD.getInstance();
    }

    // Método que se ejecuta cuando se aplica el filtro a las solicitudes
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Connection conn = this.getConnection();
        // Asigna la conexión a la solicitud para que los servlets la puedan utilizar
        request.setAttribute("conn", conn);
        try {
            // Continúa con la cadena de filtros y servlets
            chain.doFilter(request, response);
        } catch (IOException e) {
            // Captura excepciones de tipo IOException
        } catch (ServletException e) {
            // Lanza una excepción de tipo RuntimeException si se produce una ServletException
            throw new RuntimeException(e);
        }
    }
}

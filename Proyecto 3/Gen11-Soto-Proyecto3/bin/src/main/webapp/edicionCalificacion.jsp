<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*, javax.naming.InitialContext, javax.sql.DataSource" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="com.soto.app.escuelas.models.enums.Materias" %>

<%
   Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
   List<Estudiante> estudiantes = (List<Estudiante>) request.getAttribute("estudiantes");
   List<Clase> clases = (List<Clase>) request.getAttribute("clases");
   Calificacion calificacion = (Calificacion) request.getAttribute("calificacion");
%>

<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Edicion Calificacion"); %>
<%@ include file="header.jsp" %>



<div class="container">
    <div class="row">
        <div class="col-12">
            <h2>Formulario edicion de calificaciones</h2>
        </div>
    </div>
    <br>
    <% if(errores != null && errores.size()>0){ %>
        <ul class="alert alert-danger mx-5 px-5">
            <% for(String error: errores.values()){%>
                <li><%=error%></li>
            <% } %>
        </ul>
    <% } %>

    <div class="row">
        <form action="<%=request.getContextPath()%>/calificaciones/editar" method="post">
            <input type="hidden", name="id" value="<%=calificacion.getId()%>"
            <div class="col-md-12">

                <div class="form-group">
                    <label for="">Estudiante</label>
                    <select name="estudianteId" id="estudianteId" class="form-control" value="<%=calificacion.getEstudianteId() !=null? calificacion.getEstudianteId(): "" %>">
                            <% for(Estudiante p: estudiantes){
                            String selected = (p.getId() == calificacion.getEstudianteId()) ? "selected" : "";
                            %>
                            <option value="<%= p.getId() %>"<%=selected%> ><%=p.getNombre() +" "+ p.getApPaterno() +" "+ p.getApMaterno() %></option>
                           <% } %>
                    </select>
                    <%
                        if(errores != null && errores.containsKey("estudianteId")){
                            out.println("<span class='text-danger'>"+errores.get("estudianteId")+ "</span>");
                        }
                    %>
                </div>

                <div class="form-group">
                    <label for="">Clase</label>
                    <select name="claseId" id="claseId" class="form-control" value="<%=calificacion.getClaseId() !=null? calificacion.getClaseId(): "" %>">
                        <% for(Clase c: clases){
                        String selected = (c.getId() == calificacion.getClaseId()) ? "selected" : "";
                        %>
                        <option value="<%= c.getId() %>"<%=selected%> ><%=c.getId() +" "+ c.getMateria().name() %></option>
                       <% } %>
                    </select>
                    <%
                        if(errores != null && errores.containsKey("claseId")){
                            out.println("<span class='text-danger'>"+errores.get("claseId")+ "</span>");
                        }
                    %>
                </div>

                <div class="form-group">
                    <label for="">calificacion</label>
                    <input type="text" name="calificacion" id="calificacion" class="form-control" value="<%=calificacion.getCalificacion() !=null? calificacion.getCalificacion(): "" %>">
                    <%
                        if(errores != null && errores.containsKey("calificacion")){
                            out.println("<span class='text-danger'>"+errores.get("calificacion")+ "</span>");
                        }
                    %>
                </div>

                <div class="form-group">
                    <label for="">nota</label>
                    <input type="text" name="nota" id="nota" class="form-control" value="<%=calificacion.getNota() !=null? calificacion.getNota(): "" %>">
                    <%
                        if(errores != null && errores.containsKey("nota")){
                            out.println("<span class='text-danger'>"+errores.get("nota")+ "</span>");
                        }
                    %>
                </div>

                <div class="form-group text-right">
                    <button type="submit" class="btn btn-success">Guardar</button>
                </div>

            </div>
        </form>
    </div>
</div>

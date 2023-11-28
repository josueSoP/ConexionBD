<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*, javax.naming.InitialContext, javax.sql.DataSource" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="com.soto.app.escuelas.models.enums.Materias" %>

<%
   Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
   List<Profesor> profesores =(List<Profesor>) request.getAttribute("profesores");
%>

<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Alta Clases"); %>
<%@ include file="header.jsp" %>



<div class="container">
    <div class="row">
        <div class="col-12">
            <h2>Formulario Alta de Clases</h2>
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
        <form action="<%=request.getContextPath()%>/clases/alta" method="post">
            <div class="row">
                <div class="col-md-6">

                    <div class="form-group">
                        <label for="">Profesor</label>
                        <select name="profesorId" id="profesorId" class="form-control" value="${param.profesorId}">
                                <% for(Profesor p: profesores){
                                %>
                                <option value="<%= p.getId() %>"><%=p.getNombre() +" "+ p.getApPaterno() +" "+ p.getApMaterno() %></option>
                               <% }%>
                        </select>
                        <%
                            if(errores != null && errores.containsKey("escuelaId")){
                                out.println("<span class='text-danger'>"+errores.get("escuelaId")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Materia</label>
                        <select name="materia" id="materia" class="form-control" value="${param.materia}>
                            <%
                            for (Materias e : Materias.values()) {
                             %>
                                <option value="<%= e.name() %>"><%= e.getDescripcion() %></option>
                             <%
                             }
                             %>
                        </select>
                        <%
                            if(errores != null && errores.containsKey("materia")){
                                out.println("<span class='text-danger'>"+errores.get("materia")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Salon</label>
                        <input type="text" name="salon" id="salon" class="form-control" value="${param.salon}">
                        <%
                            if(errores != null && errores.containsKey("salon")){
                                out.println("<span class='text-danger'>"+errores.get("salon")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Hora de Inicio</label>
                        <input type="text" name="hrInicio" id="hrInicio" class="form-control" value="${param.hrInicio}">
                        <%
                            if(errores != null && errores.containsKey("hrInicio")){
                                out.println("<span class='text-danger'>"+errores.get("hrInicio")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Hora de fin</label>
                        <input type="text" name="hrFin" id="hrFin" class="form-control" value="${param.hrFin}">
                        <%
                            if(errores != null && errores.containsKey("hrFin")){
                                out.println("<span class='text-danger'>"+errores.get("directorApp")+ "</span>");
                            }
                        %>
                    </div>
                </div>
                <div class="col-md-6">

                    <div class="form-group">
                        <label for="">Lunes</label>
                        <input type="checkbox" name="lunes" id="lunes" class="form-check-input" value="${param.lunes}" checked>
                    </div>

                    <div class="form-group">
                        <label for="">Martes</label>
                        <input type="checkbox" name="martes" id="martes" class="form-check-input" value="${param.martes}" checked>
                    </div>

                    <div class="form-group">
                        <label for="">Miercoles</label>
                        <input type="checkbox" name="miercoles" id="miercoles" class="form-check-input" value="${param.miercoles}" checked>
                    </div>

                    <div class="form-group">
                        <label for="">Jueves</label>
                        <input type="checkbox" name="jueves" id="jueves" class="form-check-input" value="${param.jueves}" checked>
                    </div>

                    <div class="form-group">
                        <label for="">Viernes</label>
                        <input type="checkbox" name="viernes" id="viernes" class="form-check-input" value="${param.viernes}" checked>
                    </div>

                    <div class="form-group">
                        <label for="">Sabado</label>
                        <input type="checkbox" name="sabado" id="sabado" class="form-check-input" value="${param.sabado}" checked>
                    </div>

                    <div class="form-group">
                        <label for="">Domingo</label>
                        <input type="checkbox" name="domingo" id="domingo" class="form-check-input" value="${param.domingo}" checked>
                    </div>

                    <div class="form-group text-right">
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </div>
                </div>

            </div>
        </form>
    </div>
    </div>
</div>

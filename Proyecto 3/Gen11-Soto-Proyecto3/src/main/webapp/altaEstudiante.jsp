<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*, javax.naming.InitialContext, javax.sql.DataSource" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="com.soto.app.escuelas.models.enums.Semestres" %>

<%
   Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
   List<Escuela> escuelas =(List<Escuela>) request.getAttribute("escuelas");
%>

<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Alta Estudiante"); %>
<%@ include file="header.jsp" %>



<div class="container">
    <div class="row">
        <div class="col-12">
            <h2>Formulario Alta de Estudiante</h2>
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
        <form action="<%=request.getContextPath()%>/estudiantes/alta" method="post">
            <div class="row">
                <div class="col-md-6">

                            <div class="form-group">
                                <label for="">Escuelas</label>
                                <select name="escuelaId" id="escuelaId" class="form-control" value="${param.escuelaId}">
                                        <% for(Escuela e: escuelas){
                                        %>
                                        <option value="<%= e.getId() %>"><%=e.getNombre() %></option>
                                       <% }%>
                                </select>
                                <%
                                    if(errores != null && errores.containsKey("escuelaId")){
                                        out.println("<span class='text-danger'>"+errores.get("escuelaId")+ "</span>");
                                    }
                                %>
                            </div>

                        <div class="form-group">
                            <label for="">Nombre</label>
                            <input type="text" name="nombre" id="nombre" class="form-control" value="${param.nombre}">
                            <%
                                if(errores != null && errores.containsKey("nombre")){
                                    out.println("<span class='text-danger'>"+errores.get("nombre")+ "</span>");
                                }
                            %>
                        </div>

                        <div class="form-group">
                            <label for="">Apellido Paterno</label>
                                <input type="text" name="apPaterno" id="apPaterno" class="form-control" value="${param.apPaterno}">                            <%
                                if(errores != null && errores.containsKey("apPaterno")){
                                    out.println("<span class='text-danger'>"+errores.get("apPaterno")+ "</span>");
                                }
                            %>
                        </div>

                        <div class="form-group">
                            <label for="">Apellido Materno</label>
                            <input type="text" name="apMaterno" id="apMaterno" class="form-control" value="${param.apMaterno}">
                            <%
                                if(errores != null && errores.containsKey("apMaterno")){
                                    out.println("<span class='text-danger'>"+errores.get("apMaterno")+ "</span>");
                                }
                            %>
                        </div>

                        <div class="form-group">
                            <label for="">Direccion</label>
                            <input type="text" name="direccionAlum" id="direccionAlum" class="form-control" value="${param.direccionAlum}">
                            <%
                                if(errores != null && errores.containsKey("direccionAlum")){
                                    out.println("<span class='text-danger'>"+errores.get("direccionAlum")+ "</span>");
                                }
                            %>
                        </div>
                </div>
                <div class="col-md-6">

                        <div class="form-group">
                            <label for="">Semestre</label>
                            <select name="semestre" id="semestre" class="form-control" value="${param.semestre}>
                                <%
                                for (Semestres e : Semestres.values()) {
                                 %>
                                    <option value="<%= e.name() %>"><%= e.getDescripcion() %></option>
                                 <%
                                 }
                                 %>
                            </select>
                            <%
                                if(errores != null && errores.containsKey("semestre")){
                                    out.println("<span class='text-danger'>"+errores.get("semestre")+ "</span>");
                                }
                            %>
                        </div>

                        <div class="form-group">
                            <label for="">Telefono</label>
                            <input type="text" name="telefono" id="telefono" class="form-control" value="${param.telefono}">
                            <%
                                if(errores != null && errores.containsKey("telefono")){
                                    out.println("<span class='text-danger'>"+errores.get("telefono")+ "</span>");
                                }
                            %>
                        </div>

                        <div class="form-group">
                            <label for="">Correo</label>
                            <input type="text" name="correo" id="correo" class="form-control" value="${param.correo}">
                            <%
                                if(errores != null && errores.containsKey("correo")){
                                    out.println("<span class='text-danger'>"+errores.get("correo")+ "</span>");
                                }
                            %>
                        </div>
                        <div class="form-group">
                            <label for="">Fecha Nacimiento</label>
                            <input type="text" name="fechaNacimiento" id="fechaNacimiento" class="form-control" value="${param.fechaNacimiento}">
                            <%
                                if(errores != null && errores.containsKey("fechaNacimiento")){
                                    out.println("<span class='text-danger'>"+errores.get("fechaNacimiento")+ "</span>");
                                }
                            %>
                        </div>

                        <div class="form-group">
                            <label for=""> Activo </label>
                            <input type="checkbox" name="activo" id="activo" class="form-check-input" value="${param.activo}" checked>
                        </div>

                        <div class="form-group">
                             <label for=""> Egreso </label>
                             <input type="checkbox" name="egreso" id="egreso" class="form-check-input" value="${param.egreso}" checked>
                         </div>

                </div>
                <div class="form-group text-right">
                    <button type="submit" class="btn btn-success">Guardar</button>
                </div>
               </div>
            </div>
        </form>
    </div>
</div>

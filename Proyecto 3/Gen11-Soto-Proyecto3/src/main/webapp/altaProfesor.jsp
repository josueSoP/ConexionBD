<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*, javax.naming.InitialContext, javax.sql.DataSource" %>
<%@page import="com.soto.app.escuelas.models.*" %>

<%
   Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
   List<Escuela> escuela =(List<Escuela>) request.getAttribute("escuelas");
%>

<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Alta Profesor"); %>
<%@ include file="header.jsp" %>



<div class="container">
    <div class="row">
        <div class="col-12">
            <h2>Formulario Alta de Profesor</h2>
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
        <form action="<%=request.getContextPath()%>/profesores/alta" method="post">
            <div class="row">
                <div class="col-md-6">

                    <div class="form-group">
                        <label for="">Escuelas</label>
                        <select name="escuelaId" id="escuelaId" class="form-control" value="${param.escuelaId}">
                                <% for(Escuela e: escuela){
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
                        <label for="">nombre</label>
                        <input type="text" name="nombre" id="nombre" class="form-control" value="${param.nombre}">
                        <%
                            if(errores != null && errores.containsKey("nombre")){
                                out.println("<span class='text-danger'>"+errores.get("nombre")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">apPaterno</label>
                        <input type="text" name="apPaterno" id="apPaterno" class="form-control" value="${param.apPaterno}">
                        <%
                            if(errores != null && errores.containsKey("apPaterno")){
                                out.println("<span class='text-danger'>"+errores.get("apPaterno")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">apMaterno</label>
                        <input type="text" name="apMaterno" id="apMaterno" class="form-control" value="${param.apMaterno}">
                        <%
                            if(errores != null && errores.containsKey("apMaterno")){
                                out.println("<span class='text-danger'>"+errores.get("apMaterno")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">direccionProf</label>
                        <input type="text" name="direccionProf" id="direccionProf" class="form-control" value="${param.direccionProf}">
                        <%
                            if(errores != null && errores.containsKey("direccionProf")){
                                out.println("<span class='text-danger'>"+errores.get("direccionProf")+ "</span>");
                            }
                        %>
                    </div>

                </div>
                <div class="col-md-6">

                    <div class="form-group">
                        <label for="">correo</label>
                        <input type="text" name="correo" id="correo" class="form-control" value="${param.correo}">
                        <%
                            if(errores != null && errores.containsKey("correo")){
                                out.println("<span class='text-danger'>"+errores.get("correo")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">telefono</label>
                        <input type="text" name="telefono" id="telefono" class="form-control" value="${param.telefono}">
                        <%
                            if(errores != null && errores.containsKey("telefono")){
                                out.println("<span class='text-danger'>"+errores.get("telefono")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">salario</label>
                        <input type="text" name="salario" id="salario" class="form-control" value="${param.salario}">
                        <%
                            if(errores != null && errores.containsKey("salario")){
                                out.println("<span class='text-danger'>"+errores.get("salario")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">fechaContrato</label>
                        <input type="text" name="fechaContrato" id="fechaContrato" class="form-control" value="${param.fechaContrato}">
                        <%
                            if(errores != null && errores.containsKey("fechaContrato")){
                                out.println("<span class='text-danger'>"+errores.get("fechaContrato")+ "</span>");
                            }
                        %>
                    </div>

                    <div class="form-group text-right">
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </div>

                </div>
                </div>
            </div>
        </form>
    </div>
</div>

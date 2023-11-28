<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="java.time.format.*" %>

<%
   Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
   List<Escuela> escuelas =(List<Escuela>) request.getAttribute("escuelas");
   Profesor profesor = (Profesor) request.getAttribute("profesor");
   String fecha = profesor.getFechaContrato() != null ?profesor.getFechaContrato().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
%>

<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Edicion Profesor"); %>
<%@ include file="header.jsp" %>



<div class="container">
    <div class="row">
        <div class="col-12">
            <h2>Formulario Edicion Profesor </h2>
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
        <form action="<%=request.getContextPath()%>/profesores/editar" method="post">
            <input type="hidden", name="id" value="<%=profesor.getId()%>"
                <div class="row">
                    <!-- izquierda -------------------------------------------------------------------------------------->
                        <div class="col-md-6">


                            <div class="form-group">
                                <label for="">Escuelas</label>
                                <select name="escuelaId" id="escuelaId" class="form-control" value="${profesor.getEscuelaId() !=null? profesor.getEscuelaId(): "" }">
                                        <% for(Escuela e: escuelas){
                                        String selected = (e.getId() == profesor.getEscuelaId()) ? "selected" : "";
                                        %>
                                        <option value="<%= e.getId() %>"<%=selected%> > <%=e.getNombre() %></option>
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
                                <input type="text" name="nombre" id="nombre" class="form-control" value="<%=profesor.getNombre() !=null? profesor.getNombre(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("nombre")){
                                        out.println("<span class='text-danger'>"+errores.get("nombre")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Apellido Paterno</label>
                                <input type="text" name="apPaterno" id="apPaterno" class="form-control" value="<%=profesor.getApPaterno() !=null? profesor.getApPaterno(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("apPaterno")){
                                        out.println("<span class='text-danger'>"+errores.get("apPaterno")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Apellido Materno</label>
                                <input type="text" name="apMaterno" id="apMaterno" class="form-control" value="<%=profesor.getApMaterno() !=null? profesor.getApMaterno(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("apMaterno")){
                                        out.println("<span class='text-danger'>"+errores.get("apMaterno")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">direccionProf</label>
                                <input type="text" name="direccionProf" id="direccionProf" class="form-control" value="<%=profesor.getDireccionProf() !=null? profesor.getDireccionProf(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("direccionProf")){
                                        out.println("<span class='text-danger'>"+errores.get("direccionProf")+ "</span>");
                                    }
                                %>
                            </div>


                        </div>


                        <!-- Derecha -------------------------------------------------------------------------------------->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Telefono</label>
                                <input type="text" name="telefono" id="telefono" class="form-control" value="<%=profesor.getTelefono() !=null? profesor.getTelefono(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("telefono")){
                                        out.println("<span class='text-danger'>"+errores.get("telefono")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Correo</label>
                                <input type="text" name="correo" id="correo" class="form-control" value="<%=profesor.getCorreo() !=null? profesor.getCorreo(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("correo")){
                                        out.println("<span class='text-danger'>"+errores.get("correo")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Salario</label>
                                <input type="text" name="salario" id="salario" class="form-control" value="<%=profesor.getSalario() !=null? profesor.getSalario(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("salario")){
                                        out.println("<span class='text-danger'>"+errores.get("salario")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Fecha de contratacion</label>
                                <input type="text" name="fechaContrato" id="fechaContrato" class="form-control" value="<%=fecha%>">
                                <%
                                    if(errores != null && errores.containsKey("fechaContrato")){
                                        out.println("<span class='text-danger'>"+errores.get("fechaContrato")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group text-right"> <!-- Alineamos el contenido a la derecha -->
                                <button type="submit" class="btn btn-success">Guardar</button>
                            </div>

                        </div><!--fin derecha---------------------------------------------------------------------------------->

                    </div>
                </div>
        </form>
    </div>
</div>
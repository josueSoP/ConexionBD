<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="com.soto.app.escuelas.models.enums.Semestres" %>
<%@page import="java.time.format.*" %>

<%
   Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
   List<Escuela> escuelas =(List<Escuela>) request.getAttribute("escuelas");
   Estudiante estudiante = (Estudiante) request.getAttribute("estudiante");
   String fecha = estudiante.getFechaNacimiento() != null ?estudiante.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";

   Boolean estadoActivo = estudiante.getActivo();
   Boolean estadoEgreso = estudiante.getEgreso();
   String activo = estadoActivo == true ? "checked": "";
   String egreso = estadoEgreso == true ? "checked": "";
%>

<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Edicion Estudiante"); %>
<%@ include file="header.jsp" %>



<div class="container">
    <div class="row">
        <div class="col-12">
            <h2>Edicion Estudiante: <%= estudiante.getId()%></h2>
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
        <form action="<%=request.getContextPath()%>/estudiantes/editar" method="post">
            <input type="hidden", name="id" value="<%=estudiante.getId()%>"
                <div class="row">
                    <!-- izquierda -------------------------------------------------------------------------------------->
                        <div class="col-md-6">

                            <div class="form-group">
                                <label for="">Escuelas</label>
                                <select name="escuelaId" id="escuelaId" class="form-control" value="${estudiante.getEscuelaId() !=null? estudiante.getEscuelaId(): "" }">
                                        <% for(Escuela e: escuelas){
                                        String selected = (e.getId() == estudiante.getEscuelaId()) ? "selected" : "";
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
                                <input type="text" name="nombre" id="nombre" class="form-control" value="<%=estudiante.getNombre() !=null? estudiante.getNombre(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("nombre")){
                                        out.println("<span class='text-danger'>"+errores.get("nombre")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Apellido Paterno</label>
                                <input type="text" name="apPaterno" id="apPaterno" class="form-control" value="<%=estudiante.getApPaterno() !=null? estudiante.getApPaterno(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("apPaterno")){
                                        out.println("<span class='text-danger'>"+errores.get("apPaterno")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Apellido Materno</label>
                                <input type="text" name="apMaterno" id="apMaterno" class="form-control" value="<%=estudiante.getApMaterno() !=null? estudiante.getApMaterno(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("apMaterno")){
                                        out.println("<span class='text-danger'>"+errores.get("apMaterno")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Direccion</label>
                                <input type="text" name="direccionAlum" id="direccionAlum" class="form-control" value="<%=estudiante.getDireccionAlum() !=null? estudiante.getDireccionAlum(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("direccionAlum")){
                                        out.println("<span class='text-danger'>"+errores.get("direccionAlum")+ "</span>");
                                    }
                                %>
                            </div>


                        </div>


                        <!-- Derecha -------------------------------------------------------------------------------------->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Semestre</label>

                                <select name="semestre" id="semestre" class="form-control">
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
                                <input type="text" name="telefono" id="telefono" class="form-control" value="<%=estudiante.getTelefono() !=null? estudiante.getTelefono(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("telefono")){
                                        out.println("<span class='text-danger'>"+errores.get("telefono")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Correo</label>
                                <input type="text" name="correo" id="correo" class="form-control" value="<%=estudiante.getCorreo() !=null? estudiante.getCorreo(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("correo")){
                                        out.println("<span class='text-danger'>"+errores.get("correo")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Fecha de Nacimiento</label>
                                <input type="text" name="fechaNacimiento" id="fechaNacimiento" class="form-control" value="<%=fecha%>">
                                <%
                                    if(errores != null && errores.containsKey("fechaNacimiento")){
                                        out.println("<span class='text-danger'>"+errores.get("fechaNacimiento")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Activo</label>
                                <input type="checkbox" name="activo" id="activo" class="form-check-input" value="0" <%=activo%> >
                            </div>

                            <div class="form-group">
                                <label for="">Egreso</label>
                                <input type="checkbox" name="egreso" id="egreso" class="form-check-input" value="0" <%=egreso%> >
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
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>


<%
   Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
   Escuela escuela = (Escuela) request.getAttribute("escuela");
%>

<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Edicion Escuela"); %>
<%@ include file="header.jsp" %>



<div class="container">
    <div class="row">
        <div class="col-12">
            <h2>Formulario Edicion Escuela</h2>
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
        <form action="<%=request.getContextPath()%>/escuelas/editar" method="post">
            <input type="hidden", name="id" value="<%=escuela.getId()%>"
                <div class="row">
                    <!-- izquierda -------------------------------------------------------------------------------------->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Nombre</label>
                                <input type="text" name="nombre" id="nombre" class="form-control" value="<%=escuela.getNombre() !=null? escuela.getNombre(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("nombre")){
                                        out.println("<span class='text-danger'>"+errores.get("nombre")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Direccion</label>
                                <input type="text" name="direccion" id="direccion" class="form-control" value="<%=escuela.getDireccion() !=null? escuela.getDireccion(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("direccion")){
                                        out.println("<span class='text-danger'>"+errores.get("direccion")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Telefono</label>
                                <input type="text" name="telefono" id="telefono" class="form-control" value="<%=escuela.getTelefono() !=null? escuela.getTelefono(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("telefono")){
                                        out.println("<span class='text-danger'>"+errores.get("telefono")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Nombre del Director</label>
                                <input type="text" name="directorNombre" id="directorNombre" class="form-control" value="<%=escuela.getDirectorNombre() !=null? escuela.getDirectorNombre(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("directorNombre")){
                                        out.println("<span class='text-danger'>"+errores.get("directorNombre")+ "</span>");
                                    }
                                %>
                            </div>

                        </div>


                        <!-- Derecha -------------------------------------------------------------------------------------->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Apellido Paterno</label>
                                <input type="text" name="directorApp" id="directorApp" class="form-control" value="<%=escuela.getDirectorApp() !=null? escuela.getDirectorApp(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("directorApp")){
                                        out.println("<span class='text-danger'>"+errores.get("directorApp")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Apellido Materno</label>
                                <input type="text" name="directorApm" id="directorApm" class="form-control" value="<%=escuela.getDirectorApm() !=null? escuela.getDirectorApm(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("directorApm")){
                                        out.println("<span class='text-danger'>"+errores.get("directorApm")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Pagina Web</label>
                                <input type="text" name="paginaWeb" id="paginaWeb" class="form-control" value="<%=escuela.getPaginaWeb() !=null? escuela.getPaginaWeb(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("paginaWeb")){
                                        out.println("<span class='text-danger'>"+errores.get("paginaWeb")+ "</span>");
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
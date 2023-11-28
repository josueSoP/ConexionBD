<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>


<%
   //obtenemos o recuperamos la lista de calificaciones que seteamos en el request desde el servlet
   List<Calificacion> calificaciones =  (List<Calificacion>) request.getAttribute("calificaciones");
   List<Estudiante> estudiantes =  (List<Estudiante>) request.getAttribute("estudiantes");
   List<Clase> clases =  (List<Clase>) request.getAttribute("clases");
%>


<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Listado Calificaciones"); %>
<%@ include file="header.jsp" %>





<div class="container">
    <div class="row">
        <div class="col-6">
            <h2>Listado de Calificaciones</h2>
        </div>
        <div class="col-6">
            <a href="<%=request.getContextPath()%>/calificaciones/alta" class="btn btn-success">Alta Calificacion</a>
        </div>
    </div>

    <div class="row">
        <div class="co-12">
            <div class="table-responsive">
                <br>
                <table class="table table-bordered table-striped" id="tablaCalificaciones" width="100%" cellspacing="0">

                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Estudiante</th>
                            <th>Clase</th>
                            <th>Calificacion</th>
                            <th>Nota</th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                        <% for (Calificacion c : calificaciones) {
                            String nombreEstudiante = "";
                            for (Estudiante estu : estudiantes) {
                                if (c.getEstudianteId() == estu.getId()) {
                                    nombreEstudiante = estu.getNombre() +" "+ estu.getApPaterno() +" "+ estu.getApMaterno();
                                    break;
                                }
                            }
                            String nombreMateria = "";
                            for (Clase clas : clases) {
                                if (c.getClaseId() == clas.getId()) {
                                    nombreMateria = clas.getId() +" "+ clas.getMateria().getDescripcion();
                                    break;
                                }
                            }
                        %>
                            <tr>
                                <td><%=c.getId() %></td>
                                <td><%= nombreEstudiante %></td>
                                <td><%= nombreMateria %></td>
                                <td><%=c.getCalificacion() %></td>
                                <td><%=c.getNota() != null ? c.getNota() : ""%></td>
                                <td><a href="<%=request.getContextPath()%>/calificaciones/detalle?id=<%=c.getId()%>" class="btn btn-success">Detalle</a></td>
                                <td><a href="<%=request.getContextPath()%>/calificaciones/editar?id=<%=c.getId()%>" class="btn btn-primary">Editar</a></td>
                                <td><a href="<%=request.getContextPath()%>/calificaciones/eliminar?id=<%=c.getId()%>" class="btn btn-danger">Eliminar</a></td>
                            </tr>
                            <% } %>
                    </tbody>

                </table>


            </div>
        </div>
    </div>

</div>

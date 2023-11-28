<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>


<%
   //obtenemos o recuperamos la lista de estudiantes que seteamos en el request desde el servlet
   List<Estudiante> estudiantes =  (List<Estudiante>) request.getAttribute("estudiantes");
   List<Escuela> escuelas =  (List<Escuela>) request.getAttribute("escuelas");
%>


<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Listado Estudiantes"); %>
<%@ include file="header.jsp" %>





<div class="container">
    <div class="row">
        <div class="col-6">
            <h2>Listado de Estudiantes</h2>
        </div>
        <div class="col-6">
            <a href="<%=request.getContextPath()%>/estudiantes/alta" class="btn btn-success">Alta Estudiante</a>
        </div>
    </div>

    <div class="row">
        <div class="co-12">
            <div class="table-responsive">
                <br>
                <table class="table table-bordered table-striped" id="tablaEstudiantes" width="100%" cellspacing="0">

                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Escuela</th>
                            <th>Nombre</th>
                            <th>Direccion</th>
                            <th>Semestre</th>
                            <th>Telefono</th>
                            <th>Correo</th>
                            <th>Activo</th>
                            <th>Egreso</th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                        <% for (Estudiante e : estudiantes) {
                            String nombreEscuela = "";
                            for (Escuela escu : escuelas) {
                                if (e.getEscuelaId() == escu.getId()) {
                                    nombreEscuela = escu.getNombre();
                                    break;
                                }
                            }
                            %>
                            <tr>
                                <td><%=e.getId()%></td>
                                <td><%= nombreEscuela %></td>
                                <td><%=e.getNombre() +" "+ e.getApPaterno() +" "+ e.getApMaterno()%></td>
                                <td><%=e.getDireccionAlum()%></td>
                                <td><%=e.getSemestre().getDescripcion()%></td>
                                <td><%=e.getTelefono()%></td>
                                <td><%=e.getCorreo()%></td>
                                <td><%=e.getActivo() != true ? "NO" : "SI"%></td>
                                <td><%=e.getEgreso() != true ? "NO" : "SI"%></td>
                                <td><a href="<%=request.getContextPath()%>/estudiantes/detalle?id=<%=e.getId()%>" class="btn btn-success">Detalle</a></td>
                                <td><a href="<%=request.getContextPath()%>/estudiantes/editar?id=<%=e.getId()%>" class="btn btn-primary">Editar</a></td>
                                <td><a href="<%=request.getContextPath()%>/estudiantes/eliminar?id=<%=e.getId()%>" class="btn btn-danger">Eliminar</a></td>
                            </tr>
                            <% } %>
                    </tbody>

                </table>


            </div>
        </div>
    </div>

</div>

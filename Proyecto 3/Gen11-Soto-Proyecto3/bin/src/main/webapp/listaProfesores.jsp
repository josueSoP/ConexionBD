<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>


<%
   //obtenemos o recuperamos la lista de profesores que seteamos en el request desde el servlet
   List<Profesor> profesores =  (List<Profesor>) request.getAttribute("profesores");
   List<Escuela> escuelas =  (List<Escuela>) request.getAttribute("escuelas");

%>


<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Listado Profesores"); %>
<%@ include file="header.jsp" %>





<div class="container">
    <div class="row">
        <div class="col-6">
            <h2>Listado de Profesores</h2>
        </div>
        <div class="col-6">
            <a href="<%=request.getContextPath()%>/profesores/alta" class="btn btn-success">Alta Profesor</a>
        </div>
    </div>

    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <br>
                <table class="table table-bordered table-striped" id="tablaProfesores" width="100%" cellspacing="0">

                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Escuela</th>
                            <th>Nombre</th>
                            <th>Direccion</th>
                            <th>Correo</th>
                            <th>Telefono</th>
                            <th>Salario</th>
                            <th>Fecha Contratacion</th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                        <% for (Profesor p : profesores) {
                            String nombreEscuela = "";
                            for (Escuela escu : escuelas) {
                                if (p.getEscuelaId() == escu.getId()) {
                                    nombreEscuela = escu.getNombre();
                                    break;
                                }
                            }
                        %>
                        <tr>
                            <td><%=p.getId() %></td>
                            <td><%= nombreEscuela %></td>
                            <td><%=p.getNombre() +" "+ p.getApPaterno() +" "+ p.getApMaterno()%></td>
                            <td><%=p.getDireccionProf() %></td>
                            <td><%=p.getCorreo() %></td>
                            <td><%=p.getTelefono() %></td>
                            <td><%=p.getSalario() %></td>
                            <td><%=p.getFechaContrato() %></td>
                            <td><a href="<%=request.getContextPath()%>/profesores/detalle?id=<%=p.getId()%>" class="btn btn-success">Detalle</a></td>
                            <td><a href="<%=request.getContextPath()%>/profesores/editar?id=<%=p.getId()%>" class="btn btn-primary">Editar</a></td>
                            <td><a href="<%=request.getContextPath()%>/profesores/eliminar?id=<%=p.getId()%>" class="btn btn-danger">Eliminar</a></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>

            </div>
        </div>
    </div>

</div>

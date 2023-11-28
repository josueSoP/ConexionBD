<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>


<%
   //obtenemos o recuperamos la lista de clases que seteamos en el request desde el servlet
   List<Clase> clases =  (List<Clase>) request.getAttribute("clases");
   List<Profesor> profesores =  (List<Profesor>) request.getAttribute("profesores");
%>


<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Listado Clases"); %>
<%@ include file="header.jsp" %>





<div class="container">
    <div class="row">
        <div class="col-6">
            <h2>Listado de Clases</h2>
        </div>
        <div class="col-6">
            <a href="<%=request.getContextPath()%>/clases/alta" class="btn btn-success">Alta Clase</a>
        </div>
    </div>

    <div class="row">
        <div class="co-12">
            <div class="table-responsive">
                <br>
                <table class="table table-bordered table-striped" id="tablaClases" width="100%" cellspacing="0">

                    <thead>
                        <tr>
                            <th>ID Clase</th>
                            <th>Profesor</th>
                            <th>Materia</th>
                            <th>Salon</th>
                            <th>Inicio</th>
                            <th>Fin</th>
                            <th>L</th>
                            <th>M</th>
                            <th>X</th>
                            <th>J</th>
                            <th>V</th>
                            <th>S</th>
                            <th>D</th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                        <% for (Clase c : clases) {
                            String nombreProfesor = "";
                            for (Profesor prof : profesores) {
                                if (c.getProfesorId() == prof.getId()) {
                                    nombreProfesor = prof.getNombre() +" "+ prof.getApPaterno() +" "+ prof.getApMaterno();
                                    break;
                                }
                            }
                            %>
                            <tr>
                                <td><%=c.getId()%></td>
                                <td><%= nombreProfesor %></td>
                                <td><%=c.getMateria().getDescripcion()%></td>
                                <td><%=c.getSalon() %></td>
                                <td><%=c.getHrInicio() %></td>
                                <td><%=c.getHrFin() %></td>
                                <td><%=c.getLunes() == true ? "*" : "" %></td>
                                <td><%=c.getMartes() == true ? "*" : "" %></td>
                                <td><%=c.getMiercoles() == true ? "*" : "" %></td>
                                <td><%=c.getJueves() == true ? "*" : "" %></td>
                                <td><%=c.getViernes() == true ? "*" : "" %></td>
                                <td><%=c.getSabado() == true ? "*" : "" %></td>
                                <td><%=c.getDomingo() == true ? "*" : "" %></td>
                                <td><a href="<%=request.getContextPath()%>/clases/detalle?id=<%=c.getId()%>" class="btn btn-success">Detalle</a></td>
                                <td><a href="<%=request.getContextPath()%>/clases/editar?id=<%=c.getId()%>" class="btn btn-primary">Editar</a></td>
                                <td><a href="<%=request.getContextPath()%>/clases/eliminar?id=<%=c.getId()%>" class="btn btn-danger">Eliminar</a></td>
                            </tr>
                        <% } %>
                    </tbody>

                </table>


            </div>
        </div>
    </div>

</div>

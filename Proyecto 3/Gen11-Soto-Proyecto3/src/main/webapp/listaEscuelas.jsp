<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>

<%
   //obtenemos o recuperamos la lista de escuelas que seteamos en el request desde el servlet
   List<Escuela> escuelas =  (List<Escuela>) request.getAttribute("escuelas");
%>


<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Listado Escuelas"); %>
<%@ include file="header.jsp" %>





<div class="container">
    <div class="row">
        <div class="col-6">
            <h2>Listado de Escuelas</h2>
        </div>
        <div class="col-6">
            <a href="<%=request.getContextPath()%>/escuelas/alta" class="btn btn-success">Alta Escuela</a>
        </div>
    </div>

    <div class="row">
        <div class="co-12">
            <div class="table-responsive">
                <br>
                <table class="table table-bordered table-striped" id="tablaEscuelas" width="100%" cellspacing="0">

                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Direccion</th>
                            <th>Telefono</th>
                            <th>Nombre Director</th>
                            <th>Pagina Web</th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                        <% for(Escuela e: escuelas){ %>
                            <tr>
                                <td><%=e.getId()%></td>
                                <td><%=e.getNombre()%></td>
                                <td><%=e.getDireccion()%></td>
                                <td><%=e.getTelefono()%></td>
                                <td><%=e.getDirectorNombre() +" "+ e.getDirectorApp() +" "+ e.getDirectorApm()%></td>
                                <td><%=e.getPaginaWeb()%></td>
                                <td><a href="<%=request.getContextPath()%>/escuelas/detalle?id=<%=e.getId()%>" class="btn btn-success">Detalle</a></td>
                                <td><a href="<%=request.getContextPath()%>/escuelas/editar?id=<%=e.getId()%>" class="btn btn-primary">Editar</a></td>
                                <td><a href="<%=request.getContextPath()%>/escuelas/eliminar?id=<%=e.getId()%>" class="btn btn-danger">Eliminar</a></td>
                            </tr>
                            <% } %>
                    </tbody>

                </table>


            </div>
        </div>
    </div>

</div>

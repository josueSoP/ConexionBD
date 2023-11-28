<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="java.time.format.*" %>

<%
   Escuela escuela = (Escuela) request.getAttribute("escuela");
%>

<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Detalle Escuela"); %>
<%@ include file="header.jsp" %>


<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="card border">
                <div class="card-header">
                    <h3><strong>Detalles de la escuela No. <%=escuela.getId()%> </strong></h3>
                </div>
            </div>

            <div class="car-body">
                <ul class="list-group">
                    <li class="list-group-item"><strong>Nombre: </strong><%=escuela.getNombre()%></li>
                    <li class="list-group-item"><strong>Direccion: </strong><%=escuela.getDireccion()%></li>
                    <li class="list-group-item"><strong>Telefono: </strong><%=escuela.getTelefono()%></li>
                    <li class="list-group-item"><strong>Nombre Director: </strong><%=escuela.getDirectorNombre()%></li>
                    <li class="list-group-item"><strong>Apellido Paterno: </strong><%=escuela.getDirectorApp()%></li>
                    <li class="list-group-item"><strong>Apellido Materno: </strong><%=escuela.getDirectorApm() %></li>
                    <li class="list-group-item"><strong>paginaWeb: </strong><%=escuela.getPaginaWeb() %></li>
                </ul>
            </div>
            <div class="col-6">
                <a href="<%=request.getContextPath()%>/escuelas/listar" class="btn btn-primary">Regresar</a>
            </div>
            <br>
        </div>
    </div>

</div>

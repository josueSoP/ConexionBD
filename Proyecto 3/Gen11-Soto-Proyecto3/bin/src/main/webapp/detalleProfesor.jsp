<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="java.time.format.*" %>

<%
   Profesor profesor = (Profesor) request.getAttribute("profesor");
   Long escuelaId = profesor.getEscuelaId();
   List<Profesor> profesores =  (List<Profesor>) request.getAttribute("profesores");
   List<Escuela> escuelas =  (List<Escuela>) request.getAttribute("escuelas");
   String fecha = profesor.getFechaContrato() != null ?profesor.getFechaContrato().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
%>


<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Detalles Profesor"); %>
<%@ include file="header.jsp" %>


<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="card border">
                <div class="card-header">
                    <h3><strong>Detalles del profesor No. <%=profesor.getId()%> </strong></h3>
                </div>
            </div>

            <div class="car-body">
                <%
                   Map<Long, String> escuelaMap = new HashMap<>();
                   for (Escuela escu : escuelas) {
                       escuelaMap.put(escu.getId(), escu.getNombre());
                   }
                %>
                <ul class="list-group">
                    <li class="list-group-item"><strong>Escuela: </strong><%= escuelaMap.get(profesor.getEscuelaId()) %> </li>
                    <li class="list-group-item"><strong>Nombre: </strong><%=profesor.getNombre()%></li>
                    <li class="list-group-item"><strong>Apellido Paterno: </strong><%=profesor.getApPaterno()%></li>
                    <li class="list-group-item"><strong>Apellido Materno: </strong><%=profesor.getApMaterno()%></li>
                    <li class="list-group-item"><strong>Direccion: </strong><%=profesor.getDireccionProf()%></li>
                    <li class="list-group-item"><strong>Correo: </strong><%=profesor.getCorreo() %></li>
                    <li class="list-group-item"><strong>Telefono: </strong><%=profesor.getTelefono() %></li>
                    <li class="list-group-item"><strong>Salario: </strong><%=profesor.getSalario() %></li>
                    <li class="list-group-item"><strong>Fecha de Contrato: </strong><%=fecha %></li>
                </ul>
            </div>
            <div class="col-6">
                <a href="<%=request.getContextPath()%>/profesores/listar" class="btn btn-primary">Regresar</a>
            </div>
            <br>
        </div>
    </div>

</div>

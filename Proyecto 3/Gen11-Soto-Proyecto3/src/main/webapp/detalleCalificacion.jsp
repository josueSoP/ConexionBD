<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="java.time.format.*" %>

<%
   List<Clase> clases =  (List<Clase>) request.getAttribute("clases");
   List<Estudiante> estudiantes =  (List<Estudiante>) request.getAttribute("estudiantes");

   Calificacion calificacion = (Calificacion) request.getAttribute("calificacion");
   Long estudianteId = calificacion.getEstudianteId();
   Long claseId = calificacion.getClaseId();
   Double calificaciones = calificacion.getCalificacion();

%>


<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Detalles Calificacion"); %>
<%@ include file="header.jsp" %>


<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="card border">
                <div class="card-header">
                    <h3><strong>Detalles de la calificacion No. <%=calificacion.getId()%> </strong></h3>
                </div>
            </div>

            <div class="car-body">
                <%
                   Map<Long, String> estudianteMap = new HashMap<>();
                   for (Estudiante estu : estudiantes) {
                       estudianteMap.put(estu.getId(), estu.getNombre() +" "+ estu.getApPaterno() +" "+ estu.getApMaterno());
                   }
                %>
                <%
                   Map<Long, String> claseMap = new HashMap<>();
                   for (Clase clas : clases) {
                       claseMap.put(clas.getId(), clas.getMateria().getDescripcion());
                   }
                %>
                <ul class="list-group">
                    <li class="list-group-item"><strong>Estudiante Id: </strong><%= estudianteMap.get(calificacion.getEstudianteId()) %> </li>
                    <li class="list-group-item"><strong>Clase: </strong><%= claseMap.get(calificacion.getClaseId()) %> </li>
                    <li class="list-group-item"><strong>Calificacion: </strong><%= calificaciones %></li>
                    <li class="list-group-item"><strong>Nota: </strong><%=calificacion.getNota()%></li>
                </ul>
            </div>
            <div class="col-6">
                <a href="<%=request.getContextPath()%>/calificaciones/listar" class="btn btn-primary">Regresar</a>
            </div>
            <br>
        </div>
    </div>

</div>


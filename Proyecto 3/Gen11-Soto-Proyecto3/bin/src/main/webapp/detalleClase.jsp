<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="java.time.format.*" %>

<%
   Long profesorId = clase.getProfesorId();
   List<Profesor> profesores =  (List<Profesor>) request.getAttribute("profesores");
   List<Clase> clases =  (List<Clase>) request.getAttribute("clases");

   Clase clase = (Clase) request.getAttribute("clase");
   Long profesorId = clase.getProfesorId();
   Boolean estadoL = clase.getLunes();
   Boolean estadoM = clase.getMartes();
   Boolean estadoX = clase.getMiercoles();
   Boolean estadoJ = clase.getJueves();
   Boolean estadoV = clase.getViernes();
   Boolean estadoS = clase.getSabado();
   Boolean estadoD = clase.getDomingo();

   String lunes = estadoL == true ? "SI": "";
   String martes = estadoM == true ? "SI": "";
   String miercoles = estadoX == true ? "SI": "";
   String jueves = estadoJ == true ? "SI": "";
   String viernes = estadoV == true ? "SI": "";
   String sabado = estadoS == true ? "SI": "";
   String domingo = estadoD == true ? "SI": "";
%>


<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Detalles Clase"); %>
<%@ include file="header.jsp" %>


<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="card border">
                <div class="card-header">
                    <h3><strong>Detalles de la clase No. <%=clase.getId()%> </strong></h3>
                </div>
            </div>

            <div class="car-body">
                <%
                   Map<Long, String> profesorMap = new HashMap<>();
                   for (Profesor prof : profesores) {
                       profesorMap.put(prof.getId(), prof.getNombre() +" "+ prof.getApPaterno() +" "+ prof.getApMaterno());
                   }
                %>
                <ul class="list-group">
                    <li class="list-group-item"><strong>Profesor: </strong><%= profesorMap.get(clase.getProfesorId()) %> </li>
                    <li class="list-group-item"><strong>Materia: </strong><%=clase.getMateria().getDescripcion()%></li>
                    <li class="list-group-item"><strong>Salon: </strong><%=clase.getSalon()%></li>
                    <li class="list-group-item"><strong>Hora de Inicio: </strong><%=clase.getHrInicio()%></li>
                    <li class="list-group-item"><strong>Hora de Fin: </strong><%=clase.getHrFin()%></li>
                    <li class="list-group-item"><strong>Lunes: </strong><%= lunes %></li>
                    <li class="list-group-item"><strong>Martes: </strong><%= martes %></li>
                    <li class="list-group-item"><strong>Miercoles: </strong><%= miercoles %></li>
                    <li class="list-group-item"><strong>Jueves: </strong><%= jueves %></li>
                    <li class="list-group-item"><strong>Viernes: </strong><%= viernes %></li>
                    <li class="list-group-item"><strong>Sabado: </strong><%= sabado %></li>
                    <li class="list-group-item"><strong>Domingo: </strong><%= domingo %></li>
                </ul>
            </div>
            <div class="col-6">
                <a href="<%=request.getContextPath()%>/clases/listar" class="btn btn-primary">Regresar</a>
            </div>
            <br>
        </div>
    </div>

</div>
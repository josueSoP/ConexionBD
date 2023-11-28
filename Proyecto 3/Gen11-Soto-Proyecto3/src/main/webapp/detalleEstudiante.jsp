<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="java.time.format.*" %>

<%
   List<Estudiante> estudiantes =  (List<Estudiante>) request.getAttribute("estudiantes");
   List<Escuela> escuelas =  (List<Escuela>) request.getAttribute("escuelas");

   Estudiante estudiante = (Estudiante) request.getAttribute("estudiante");
   Long escuelaId = estudiante.getEscuelaId();
   String fecha = estudiante.getFechaNacimiento() != null ?estudiante.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
   Boolean estadoActivo = estudiante.getActivo();
   Boolean estadoEgreso = estudiante.getEgreso();

   String activo = estadoActivo == true ? "SI": "NO";
   String egreso = estadoEgreso == true ? "SI": "NO";
%>


<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Detalles Estudiante"); %>
<%@ include file="header.jsp" %>


<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="card border">
                <div class="card-header">
                    <h3><strong>Detalles del estudiante No. <%=estudiante.getId()%> </strong></h3>
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
                    <li class="list-group-item"><strong>Escuela: </strong><%= escuelaMap.get(estudiante.getEscuelaId()) %> </li>
                    <li class="list-group-item"><strong>Nombre: </strong><%=estudiante.getNombre()%></li>
                    <li class="list-group-item"><strong>Apellido Paterno: </strong><%=estudiante.getApPaterno()%></li>
                    <li class="list-group-item"><strong>Apellido Materno: </strong><%=estudiante.getApMaterno()%></li>
                    <li class="list-group-item"><strong>Direccion: </strong><%=estudiante.getDireccionAlum()%></li>
                    <li class="list-group-item"><strong>Semestre: </strong><%=estudiante.getSemestre().getDescripcion()%></li>
                    <li class="list-group-item"><strong>Telefono: </strong><%=estudiante.getTelefono() %></li>
                    <li class="list-group-item"><strong>Correo: </strong><%=estudiante.getCorreo() %></li>
                    <li class="list-group-item"><strong>Fecha de Nacimiento: </strong><%=fecha %></li>
                    <li class="list-group-item"><strong>En Activo o Estudiando: </strong><%=activo %></li>
                    <li class="list-group-item"><strong>Egresado: </strong><%=egreso %></li>
                </ul>
            </div>
            <div class="col-6">
                <a href="<%=request.getContextPath()%>/estudiantes/listar" class="btn btn-primary">Regresar</a>
            </div>
            <br>
        </div>
    </div>

</div>
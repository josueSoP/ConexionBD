<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.escuelas.models.*" %>
<%@page import="com.soto.app.escuelas.models.enums.*" %>

<%
   Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
   List<Profesor> profesores =(List<Profesor>) request.getAttribute("profesores");
   Clase clase = (Clase) request.getAttribute("clase");
   String enumMateria = clase.getMateria().getDescripcion();

   Boolean estadoL = clase.getLunes();
   String lunes = estadoL == true ? "checked": "";
   Boolean estadoM = clase.getMartes();
   String martes = estadoM == true ? "checked": "";
   Boolean estadoX = clase.getMiercoles();
   String miercoles = estadoX == true ? "checked": "";
   Boolean estadoJ = clase.getJueves();
   String jueves = estadoJ == true ? "checked": "";
   Boolean estadoV = clase.getViernes();
   String viernes = estadoV == true ? "checked": "";
   Boolean estadoS = clase.getSabado();
   String sabado = estadoS == true ? "checked": "";
   Boolean estadoD = clase.getDomingo();
   String domingo = estadoD == true ? "checked": "";
%>

<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Edicion Clase"); %>
<%@ include file="header.jsp" %>



<div class="container">
    <div class="row">
        <div class="col-12">
            <h2>Formulario Edicion Clase No. <%= clase.getId()%> </h2>
        </div>
    </div>
    <br>
    <% if(errores != null && errores.size()>0){ %>
        <ul class="alert alert-danger mx-5 px-5">
            <% for(String error: errores.values()){%>
                <li><%=error%></li>
            <% } %>
        </ul>
    <% } %>

    <div class="row">
        <form action="<%=request.getContextPath()%>/clases/editar" method="post">
            <input type="hidden", name="id" value="<%=clase.getId()%>"
                <div class="row">
                    <!-- izquierda -------------------------------------------------------------------------------------->
                        <div class="col-md-6">

                            <div class="form-group">
                                <label for="">Profesores</label>
                                <select name="profesorId" id="profesorId" class="form-control" value="${clase.getProfesorId() !=null? clase.getProfesorId(): "" }">
                                        <% for(Profesor p: profesores){
                                        String selected = (p.getId() == clase.getProfesorId()) ? "selected" : "";
                                        %>
                                        <option value="<%= p.getId() %>"<%=selected%> ><%=p.getNombre() +" "+ p.getApPaterno() +" "+ p.getApMaterno() %></option>
                                       <% } %>
                                </select>
                                <%
                                    if(errores != null && errores.containsKey("profesorId")){
                                        out.println("<span class='text-danger'>"+errores.get("profesorId")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Materia</label>
                                <select name="materia" id="materia" class="form-control">
                                    <% for (Materias e : Materias.values()) { %>
                                        <option value="<%= e.name() %>"<%=(enumMateria.equals(e.getDescripcion() )) ? "selected" : ""%>  > <%=e.getDescripcion() %></option>
                                    <% } %>
                                </select>
                                <%
                                    if(errores != null && errores.containsKey("materia")){
                                        out.println("<span class='text-danger'>"+errores.get("materia")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">salon</label>
                                <input type="text" name="salon" id="salon" class="form-control" value="<%=clase.getSalon() !=null? clase.getSalon(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("salon")){
                                        out.println("<span class='text-danger'>"+errores.get("salon")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Hora de inicio </label>
                                <input type="text" name="hrInicio" id="hrInicio" class="form-control" value="<%=clase.getHrInicio() !=null? clase.getHrInicio(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("hrInicio")){
                                        out.println("<span class='text-danger'>"+errores.get("hrInicio")+ "</span>");
                                    }
                                %>
                            </div>

                            <div class="form-group">
                                <label for="">Hora de fin</label>
                                <input type="text" name="hrFin" id="hrFin" class="form-control" value="<%=clase.getHrFin() !=null? clase.getHrFin(): "" %>">
                                <%
                                    if(errores != null && errores.containsKey("direccionProf")){
                                        out.println("<span class='text-danger'>"+errores.get("direccionProf")+ "</span>");
                                    }
                                %>
                            </div>


                        </div>


                        <!-- Derecha -------------------------------------------------------------------------------------->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="">Lunes</label>
                                <input type="checkbox" name="lunes" id="lunes" class="form-check-input" value="1" <%=lunes%> >
                            </div>

                            <div class="form-group">
                                <label for="">Martes</label>
                                <input type="checkbox" name="martes" id="martes" class="form-check-input" value="1" <%=martes%> >
                            </div>

                            <div class="form-group">
                                <label for="">miercoles</label>
                                <input type="checkbox" name="miercoles" id="miercoles" class="form-check-input" value="1" <%=miercoles%> >
                            </div>

                            <div class="form-group">
                                <label for="">Jueves</label>
                                <input type="checkbox" name="jueves" id="jueves" class="form-check-input" value="1" <%=jueves%> >
                            </div>

                            <div class="form-group">
                                <label for="">Vierns</label>
                                <input type="checkbox" name="viernes" id="viernes" class="form-check-input" value="1" <%=viernes%> >
                            </div>

                            <div class="form-group">
                                <label for="">Sabado</label>
                                <input type="checkbox" name="sabado" id="sabado" class="form-check-input" value="1" <%=sabado%> >
                            </div>

                            <div class="form-group">
                                <label for="">Domingo</label>
                                <input type="checkbox" name="domingo" id="domingo" class="form-check-input" value="1" <%=domingo%> >
                            </div>



                            <div class="form-group text-right"> <!-- Alineamos el contenido a la derecha -->
                                <button type="submit" class="btn btn-success">Guardar</button>
                            </div>

                        </div><!--fin derecha---------------------------------------------------------------------------------->

                    </div>
                </div>
        </form>
    </div>
</div>
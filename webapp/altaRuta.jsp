<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.soto.app.rutas.models.*" %>




<%
   //recuperamos la lista de choferes que seteamos en el request desde el servlet
   List<Chofer> choferes =  (List<Chofer>) request.getAttribute("choferes");
   List<Camion> camiones =  (List<Camion>) request.getAttribute("camiones");
   List<Ruta> rutas =  (List<Ruta>) request.getAttribute("rutas");
%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rutas Alta</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
         integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
         crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<script src="//cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>


<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.27.0/moment-with-locales.min.js"></script>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.css" integrity="sha512-bYPO5jmStZ9WI2602V2zaivdAnbAhtfzmxnEGh9RwtlI00I9s8ulGe4oBa5XxiC6tCITJH/QG70jswBhbLkxPw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>
   <style>
        .loader {
            border: 16px solid #f3f3f3;
            border-top: 16px solid #3498db;
            border-radius: 50%;
            width: 120px;
            height: 120px;
            animation: spin 2s linear infinite;
            position: fixed;
            z-index: 9999;
            top: 50%;
            left: 50%;
            margin: -60px 0 0 -60px;
            display: none;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>

</head>
<body>

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header" id="div1">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                                    <a class="navbar-brand" href="#" id="enlace1">Rutas App</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Choferes<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/choferes/listar">Lista Choferes</a></li>
                            <li><a href="<%=request.getContextPath()%>/choferes/alta">Alta Chofer</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Camiones<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/camion/listar">Lista Camiones</a></li>
                            <li><a href="<%=request.getContextPath()%>/camion/alta">Alta Camion</a></li>
                        </ul>
                    </li>


                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Rutas<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/rutas/alta">Alta Ruta</a></li>
                        </ul>
                    </li>

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>







    <div class="container body-content" >
        <script src="//maps.googleapis.com/maps/api/js?key=AIzaSyCWeeateTaYGqsHhNcmoDfT7Us-vLDZVPs&amp;sensor=false&amp;language=en"></script>
       <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>Iniciar Ruta</h2>
                </div>
                <div style="display:block"><input type="text" name="" id="txtEsOD"></div>
            </div>
            <br>
            <div class="row">
                <!-- izquierda -------------------------------------------------------------------------------------->
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="">Chofer</label>
                            <select name="chofer" id="chofer" class="form-control">
                                <option value="">seleccionar</option>
                                <%for(Chofer c: choferes){%>
                                    <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                                <%}%>
                            </select>
                    </div>


                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-9">
                                <label for="">Origen</label>
                                <input type="text" name="origen" id="origen" class="form-control">

                                <input type="hidden" name="idOrigen" id="idOrigen" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <button class="btn btn-primary btn-xs" Style="margin-top: 30px;" onclick="getDireccion(1);">Obtener Datos</button>
                            </div>
                        </div>
                    </div>
                    <div class="for-group">
                        <label for="">Fecha salida</label>
                        <input type="date" name="Fsalida" id="Fsalida" class="form-control" value="2023-11-05" min="2023-01-01" max="2024-12-31">
                    </div>


                    <div class="for-group">
                        <label for="">Distancia</label>
                        <input type="text" name="distancia" id="distancia" class="form-control">
                    </div>
                </div>



                <!-- Derecha -------------------------------------------------------------------------------------->
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="">Camion</label>
                            <select name="camion" id="camion" class="form-control">
                                <option value="">seleccionar</option>
                                <%for(Camion c: camiones){%>
                                    <option value="<%=c.getId()%>" data-capacity="<%= c.getCapacidad() %>"><%=c.getMatricula()%></option>
                                    <%}%>
                            </select>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-9">
                                <label for="">Destino</label>
                                <input type="text" name="destino" id="destino" class="form-control">

                                <input type="hidden" name="idDestino" id="idDestino" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <button class="btn btn-primary btn-xs" Style="margin-top: 30px;" onclick="getDireccion(2);">Obtener Datos</button>
                            </div>
                        </div>
                    </div>
                    <div class="for-group">
                        <label for="">Fecha estimada de llegada</label>
                        <input type="date" name="FELlegada" id="FELlegada" class="form-control" value="2023-11-06" min="2023-01-01" max="2024-12-31">
                    </div>

                    <div class="for-group">
                        <label for="">Capacidad Camion</label>
                        <input type="text" name="capCamion" id="capCamion" class="form-control">
                    </div>

                    <div class="form-group">
                        <button class="btn btn-success">Iniciar Ruta</button>
                    </div>
                </div>
                <!--fin derecha---------------------------------------------------------------------------------->
            </div>
       </div>
    </div>
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <div class="row">
                        <div class="col-med-12">
                            <h4>Guardar Direccion</h4>
                        </div>
                    </div>
                </div>

                <div class="modal-body">
                    <!-- cargamos el spinner -->
                    <div id="loader" class="loader"></div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="">Calle</label>
                                <input type="text" name="Calle" id="Calle" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="">Numero</label>
                                <input type="text" name="Numero" id="Numero" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="">Colonia</label>
                                <input type="text" name="Colonia" id="Colonia" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="">Ciudad</label>
                                <input type="text" name="Ciudad" id="Ciudad" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="">Estado</label>
                                <input type="text" name="Estado" id="Estado" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="">CP</label>
                                <input type="text" name="CP" id="CP" class="form-control">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <div class="row">
                        <div class="col-md-10 col-md-offset-1">
                            <div class="col-md-4">
                                <button class="btn btn-success" onclick="btnGuardarDir()">Guardar</button>
                            </div>
                            <div class="col-md-4 col-md-offset-4">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

        <script>
            function LimpiarDatos(){
                $("#Calle").val("");
                $("#Numero").val("");
                $("#Colonia").val("");
                $("#Ciudad").val("");
                $("#Estado").val("");
                $("#CP").val("");
            }

            function getDireccion(fuente){
                        LimpiarDatos();
                        $('#myModal').modal('show');
                        var direccion = "";
                        if (fuente == 1) { // la dirección es un origen
                            direccion = $('#origen').val();
                        } else { // la dirección es un destino
                            direccion = $("#destino").val();
                        }
                        $("#txtEsOD").val(fuente);
                        if (direccion != "") {
                        //llamamos/comsumir/hacer una peticion a la api de google aps para obtener los datos completos de la direccion
                            var geocoder = new google.maps.Geocoder();
                            geocoder.geocode({ 'address': direccion }, function (results, status){
                                console.log(results);
                                console.log(status);
                                if (status == google.maps.GeocoderStatus.OK) {
                                    var numresults = results[0].address_components.length;
                                    for (var indice = 0; indice < numresults; indice++) {
                                        var numresultstypes = results[0].address_components[indice].types.length;
                                        for (var propiedad = 0; propiedad < numresultstypes; propiedad++) {
                                            var Tipo = results[0].address_components[indice].types[propiedad];
                                            var Descripcion = results[0].address_components[indice].long_name;
                                            switch (Tipo) {
                                                case "route":
                                                    $("#Calle").val(Descripcion);
                                                    break;
                                                case "street_number":
                                                    $("#Numero").val(Descripcion);
                                                    break;
                                                case "sublocality_level_1":
                                                    $("#Colonia").val(Descripcion);
                                                    break;
                                                case "locality":
                                                    $("#Ciudad").val(Descripcion);
                                                    break;
                                                case "administrative_area_level_1":
                                                    $("#Estado").val(Descripcion);
                                                    break;
                                                case "postal_code":
                                                    $("#CP").val(Descripcion);
                                                    break;
                                            }
                                        }
                                    }

                                    if (results[0].address_components.length > 0) {
                                        if(fuente == 1){
                                            $("#origen").val(results[0].formatted_address);
                                        } else {
                                            $("#destino").val(results[0].formatted_address);
                                        }
                                    }
                                } else {
                                    swal("Error de Google", "Google no obtuvo datos", "warning");
                                }
                            });
                        } else {
                            swal({
                                title: "¿Estás seguro?",
                                text: "No podemos obtener datos si no proporcionas una dirección",
                                showCancelButton: true,
                                confirmButtonClass: "btn-warning",
                                confirmButtonText: "Sí, quiero capturar la información",
                                closeOnConfirm: true,
                                closeOnCancel: true
                            }, function (isConfirm) {
                                if (!isConfirm) {
                                    $('#myModal').modal('hide');
                                }
                            });
                        }
                    }

            //funcion que se ejecuta cuando se da clic en el botn guardar direccion
            function btnGuardarDir(){
                // Muestra el spinner (animación)
                document.getElementById('loader').style.display = 'block';

                //recupera los valores de las cajas de texto
                var calle = $("#Calle").val(); //Boulevard San Felipe
                var numero = $("#Numero").val(); //224
                var colonia = $("#Colonia").val(); //Valle del Angel
                var ciudad = $("#Ciudad").val(); //Heroica Puebla de Zaragoza
                var estado = $("#Estado").val(); //Puebla
                var cp = $("#CP").val(); //72040
                var urlDestino = "/Gen11-Rutas/api/direcciones"

                //hacer la peticion a mi api, mandandole los valores de las cajas
                $.ajax({
                    type: 'POST',
                    url: urlDestino,
                    data:{"calle":calle, "numero":numero,"colonia":colonia,"ciudad":ciudad,"estado":estado,"cp":cp},
                    //esperar la respuesta del servidor
                    success: function(resp){ //callback

                        //Mostrar la respuesta
                        console.log(resp);
                        $('#myModal').modal('hide');
                        if($("#txtEsOD").val() == 1){ //es origen
                            $('#idOrigen').val(resp.message);
                        }
                        else{
                            // Oculta el spinner después de guardar la dirección
                            document.getElementById('loader').style.display = 'none';
                            $('#idDestino').val(resp.message);
                        }
                        swal("Exito","Direccion guardada correctamente","success")
                    }
                });
            }

            //funcion para traer la capacidad del camion
            document.getElementById('camion').addEventListener( 'change', function() {
              var opcion = this.options[this.selectedIndex];

              if (opcion.value !== '') {
                  var capacity = opcion.getAttribute('data-capacity');
                  document.getElementById('capCamion').value = capacity;
              } else {
                  document.getElementById('capCamion').value = '';
              }
            } );



    </script>
</body>
</html>
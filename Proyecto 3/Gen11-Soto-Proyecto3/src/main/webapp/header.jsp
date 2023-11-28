<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= request.getAttribute("TituloDinamico") %></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
         integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
         crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
    <script src="//cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
                <a class="navbar-brand" href="/Gen11-Soto-Proyecto3/escuelas/listar" id="enlace1">Escuelas App</a>
            </div>


            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Escuelas<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/escuelas/listar">Lista de Escuelas</a></li>
                            <li><a href="<%=request.getContextPath()%>/escuelas/alta">Alta de Escuela</a></li>
                        </ul>
                    </li>


                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Profesores<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/profesores/listar">Lista de Profesores </a></li>
                            <li><a href="<%=request.getContextPath()%>/profesores/alta">Alta Profesor</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Alumnos<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/estudiantes/listar">Lista de Estudiantes</a></li>
                            <li><a href="<%=request.getContextPath()%>/estudiantes/alta">Alta Estudiante</a></li>
                        </ul>
                    </li>


                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false"> Clases <span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/clases/listar">Lista de Clases</a></li>
                            <li><a href="<%=request.getContextPath()%>/clases/alta">Alta Clase</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false"> Calificaciones <span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/calificaciones/listar">Lista de Calificaciones</a></li>
                            <li><a href="<%=request.getContextPath()%>/calificaciones/alta">Alta Calificacion</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false"> Consumos <span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/api/pokemon">Consumo externo</a></li>
                            <li><a href="<%=request.getContextPath()%>/api/mia">consumo mi app</a></li>
                        </ul>
                    </li>

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>
    
    </body>
</html>
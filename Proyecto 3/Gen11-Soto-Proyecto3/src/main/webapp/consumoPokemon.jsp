<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>


<%
   Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");

%>

<!-- encabezado y narvar dinamico -->
<% request.setAttribute("TituloDinamico", "Pokemon del dia"); %>
<%@ include file="header.jsp" %>


<div class="container">
    <div class="row">

            <div class="row">
                <div class="col-md-4">
                    <!-- Input en medio y botón a su lado -->
                    <div class="input-group mb-3">
                        <div class="form-group text-right">
                            <input type="text" class="form-control" id="inputPokemon" name="inputPokemon" placeholder="Ingresa el numero del pokemon" >
                            <button type="submit" class="btn btn-success" onclick="pokemonPorNumero()">Buscar</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <!-- Tres inputs en la izquierda -->
                    <div class="form-group">
                        <label for="">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" >
                    </div>
					<div class="form-group">
						<label for="">Numero de pokedex</label>
						<input type="text" class="form-control" id="pokedex" name="pokedex" >
					</div>
					<div class="form-group">
						<label for="">Peso</label>
						<input type="text" class="form-control" id="peso" name="peso">
					</div>
                </div>
                <div class="col-md-6">
					<!-- Dos inputs en la derecha -->
					<div class="form-group">
						<label for="">Habilidad</label>
						<input type="text" class="form-control" id="habilidad" name="habilidad" >
					</div>
                    <div class="form-group">
                        <label for="">Altura</label>
                        <input type="text" class="form-control" id="altura" name="altura">
                    </div>
                    <div class="form-group">
                        <label for="">tipo</label>
                        <input type="text" class="form-control" id="tipo" name="tipo" >
                    </div>

                </div>
            </div>

    </div>
</div>



<script>
    function limpiarCajas() {
        $("#nombre").text("");
        $("#pokedex").text("");
        $("#peso").text("");
        $("#habilidad").text("");
        $("#altura").text("");
        $("#tipo").text("");
    }


    function pokemonPorNumero() {
        limpiarCajas();
        numeroPoke = $("#inputPokemon").val();
        let link = "https://pokeapi.co/api/v2/pokemon/" + numeroPoke;
        console.log(link);

        // Hacer la solicitud Ajax con jQuery
        $.ajax({
        url: link,
        method: 'GET',
        success: function(buscaPokemon) {
			console.log(buscaPokemon)
            // Manipula la respuesta de la API aquí
            $("#nombre").val(buscaPokemon.name);
            $("#pokedex").val(buscaPokemon.order);
            $("#peso").val(buscaPokemon.weight);
            $("#altura").val(buscaPokemon.height);
            $("#tipo").val(buscaPokemon.types[1].type.name);
            $("#habilidad").val(buscaPokemon.abilities[0].ability.name);
        },
        error: function(error) {
            console.error('Error:', error);
        }
    });
  }
 </script>

</body>
</html>




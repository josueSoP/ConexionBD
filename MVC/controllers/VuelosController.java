package com.soto.app.proyecto4.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soto.app.proyecto4.models.Vuelo;
import com.soto.app.proyecto4.services.IService;

@RestController
@RequestMapping("api/vuelos")
@CrossOrigin(origins = "*")
public class VuelosController {
	//inyectar la dependencia
		@Autowired
		IService<Vuelo, Vuelo> vueloService;
		
		@PostMapping
		public Map<String, String> guardar(@RequestBody Vuelo c){
			vueloService.guardar(c);
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("msg", "Vuelo guardado correctamente");
			return respuesta;
		}
		
		
		@GetMapping
		public List<Vuelo> listar(){
			return vueloService.listar();
		}
		
		@GetMapping("/obtener/{id}")
		public Vuelo obtenerPorId(@PathVariable(name = "id") Long id) {
			Optional<Vuelo> vuelo = vueloService.getById(id);
			if (vuelo.isPresent()) {
				return vuelo.get();
			} else {
				return null;
			}
		}
		
		
		@DeleteMapping("/eliminar")
	    public Map<String,String> eliminar(@RequestParam(name = "id") Long id ) {
			vueloService.eliminar(id);
	        Map<String,String> vuelo = new HashMap<>();
	        vuelo.put("msg", "Vuelo eliminado correctamente");
	        return vuelo;
	    }
		
		
		@PutMapping("/actualizar/{id}")
		public Map<String, String> actualizar(@RequestBody Vuelo c, @PathVariable(name = "id") Long id){
			Optional<Vuelo> vuelo = vueloService.getById(id);
			if (vuelo.isPresent()) {
				c.setId(id);
				vueloService.guardar(c);
			} else {
				throw new RuntimeException("La Vuelo no existe en la bd");
			}
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("msg", "Vuelo actualizado correctamente");
			return respuesta;
		}
}

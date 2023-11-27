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

import com.soto.app.proyecto4.models.Pasajero;
import com.soto.app.proyecto4.services.IService;

@RestController
@RequestMapping("api/pasajeros")
@CrossOrigin(origins = "*")
public class PasajerosController {
	//inyectar la dependencia
	@Autowired
	IService<Pasajero, Pasajero> pasajerosService;
	
	
	@PostMapping
	public Map<String, String> guardar(@RequestBody Pasajero c){
		pasajerosService.guardar(c);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Pasajero guardado correctamente");
		return respuesta;
	}
	
	@GetMapping
	public List<Pasajero> listar(){
		return pasajerosService.listar();
	}
	
	@GetMapping("/obtener/{id}")
	public Pasajero obtenerPorId(@PathVariable(name = "id") Long id) {
		Optional<Pasajero> pasajero = pasajerosService.getById(id);
		if (pasajero.isPresent()) {
			return pasajero.get();
		} else {
			return null;
		}
	}
	
	
	@DeleteMapping("/eliminar")
    public Map<String,String> eliminar(@RequestParam(name = "id") Long id ) {
        pasajerosService.eliminar(id);
        Map<String,String> respuesta = new HashMap<>();
        respuesta.put("msg", "Pasajero eliminado correctamente");
        return respuesta;
    }
	
	
	@PutMapping("/actualizar/{id}")
	public Map<String, String> actualizar(@RequestBody Pasajero c, @PathVariable(name = "id") Long id){
		Optional<Pasajero> pasajero = pasajerosService.getById(id);
		if (pasajero.isPresent()) {
			c.setId(id);
			pasajerosService.guardar(c);
		} else {
			throw new RuntimeException("El pasajero no existe en la bd");
		}
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Pasajero actualizado correctamente");
		return respuesta;
	}
	
	
}

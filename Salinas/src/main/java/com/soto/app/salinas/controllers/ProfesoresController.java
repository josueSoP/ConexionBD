package com.soto.app.salinas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.soto.app.salinas.models.Profesor;
import com.soto.app.salinas.services.IService;

@RestController
@RequestMapping("api/profesores")
@CrossOrigin(origins="*")
public class ProfesoresController {
	
	@Autowired
	IService<Profesor> profesorService;
	
	@GetMapping
	public List<Profesor> listar(){
		return profesorService.listar();
	}
	
	@PostMapping
	public Map<String, String> save(@RequestBody Profesor p){
		profesorService.guardar(p);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "profesor guardado correctamente");
		return respuesta;
	}
	
	@DeleteMapping("/eliminar")
	public Map<String, String> delete(@RequestParam(name="id") Long id){
		profesorService.eliminar(id);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "profesor eliminado correctamente");
		return respuesta;
	}
	
	@PutMapping("/actualizar/{id}")
	public Map<String, String> actualizar(@RequestBody Profesor p, @RequestParam(name="id") Long id){
		Optional<Profesor> profesor = profesorService.getById(id);
		if (profesor.isPresent()) {
			p.setId(id);
			profesorService.guardar(p);
		}else {
			throw new RuntimeException("no se encontro el profesor");
		}
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Profesor actualizado correctamente");
		return respuesta;
	}
	
	@GetMapping("/obtener/{id}")
	public Profesor ObtenerProfPorId(@PathVariable(name="id") Long id) {
		Optional<Profesor> profesor = profesorService.getById(id);
		if(profesor.isPresent()) {
			return profesor.get();
		}else {
			return null;
		}		
	}
	
}

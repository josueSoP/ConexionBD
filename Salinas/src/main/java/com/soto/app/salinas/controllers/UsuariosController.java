package com.soto.app.salinas.controllers;

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

import com.soto.app.salinas.models.Usuario;
import com.soto.app.salinas.services.IService;

@RestController
@RequestMapping("api/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosController {
	//inyectar la dependencia
	@Autowired
	IService<Usuario> usuariosService;
	
	@GetMapping
	public List<Usuario> listar(){
		return usuariosService.listar();
	}
	
	@PostMapping
	public Map<String, String> save(@RequestBody Usuario u){
		usuariosService.guardar(u);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg","Usuario guardado correctamente");
		return respuesta;
	}
	
	@DeleteMapping("/eliminar")
	public Map<String, String> eliminar(@RequestParam(name="id") Long id){
		usuariosService.eliminar(id);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "usuario eliminado correctamente");
		return respuesta;
	}
	
	@PutMapping("/actualizar/{id}")
	public Map<String, String> actualizar(@RequestBody Usuario u, @PathVariable(name="id")Long id){
		Optional<Usuario> usuario = usuariosService.getById(id);
		if(usuario.isPresent()) {
			u.setId(id);
			usuariosService.guardar(u);
		}else {
			throw new RuntimeException("El pasajero no existe en la BD");
		}
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Usuario actualizado correctamente");
		return respuesta;
	}
	
	@GetMapping("obtener/{id}")
	public Usuario obtenerPorId(@PathVariable(name="id") Long id){
		Optional<Usuario> usuario = usuariosService.getById(id);
		if(usuario.isPresent()) {
			return usuario.get();
		}else {
			return null;
		}
	}	
}

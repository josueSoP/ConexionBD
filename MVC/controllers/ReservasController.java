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

import com.soto.app.proyecto4.dtos.ReservaCreateDTO;
import com.soto.app.proyecto4.dtos.ReservaListDTO;
import com.soto.app.proyecto4.services.IService;

@RestController
@RequestMapping("api/reservas")
@CrossOrigin(origins = "*")
public class ReservasController {
	//inyectar la dependencia
	@Autowired
	IService<ReservaListDTO, ReservaCreateDTO> reservaService;

	
	@PostMapping
	public Map<String, String> guardar(@RequestBody ReservaCreateDTO c){
		reservaService.guardar(c);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Reserva guardado correctamente");
		return respuesta;
	}
	
	@GetMapping
	public List<ReservaListDTO> listar(){
		return reservaService.listar();
	}
	
	@GetMapping("/obtener/{id}")
	public ReservaListDTO obtenerPorId(@PathVariable(name = "id") Long id) {
		Optional<ReservaListDTO> reserva = reservaService.getById(id);
		if (reserva.isPresent()) {
			return reserva.get();
		} else {
			return null;
		}
	}
	
	
	@DeleteMapping("/eliminar")
    public Map<String,String> eliminar(@RequestParam(name = "id") Long id ) {
		reservaService.eliminar(id);
        Map<String,String> respuesta = new HashMap<>();
        respuesta.put("msg", "Reserva eliminado correctamente");
        return respuesta;
    }
	
	
	@PutMapping("/actualizar/{id}")
	public Map<String, String> actualizar(@RequestBody ReservaCreateDTO c, @PathVariable(name = "id") Long id){
		Optional<ReservaListDTO> reserva = reservaService.getById(id);
		if (reserva.isPresent()) {
			c.setId(id);
			reservaService.guardar(c);
		} else {
			throw new RuntimeException("La Reserva no existe en la bd");
		}
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Reserva actualizado correctamente");
		return respuesta;
	}
	
	
}

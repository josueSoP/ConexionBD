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

import com.soto.app.proyecto4.dtos.DetalleReservaCreateDTO;
import com.soto.app.proyecto4.models.DetalleReserva;
import com.soto.app.proyecto4.services.IService;

@RestController
@RequestMapping("api/detalleReservas")
@CrossOrigin(origins = "*")
public class DetallesReservasController {
	//inyectar la dependencia
		@Autowired
		IService<DetalleReserva, DetalleReservaCreateDTO> detalleReservaService;
		
		
		@PostMapping
		public Map<String, String> guardar(@RequestBody DetalleReservaCreateDTO c){
			detalleReservaService.guardar(c);
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("msg", "Detalle reserva guardado correctamente");
			return respuesta;
		}
		
		@GetMapping
		public List<DetalleReserva> listar(){
			return detalleReservaService.listar();
		}
		
		@GetMapping("/obtener/{id}")
		public DetalleReserva obtenerPorId(@PathVariable(name = "id") Long id) {
			Optional<DetalleReserva> detallereserva = detalleReservaService.getById(id);
			if (detallereserva.isPresent()) {
				return detallereserva.get();
			} else {
				return null;
			}
		}
		
		
		@DeleteMapping("/eliminar")
	    public Map<String,String> eliminar(@RequestParam(name = "id") Long id ) {
			detalleReservaService.eliminar(id);
	        Map<String,String> respuesta = new HashMap<>();
	        respuesta.put("msg", "Detalle reserva eliminado correctamente");
	        return respuesta;
	    }
		
		
		@PutMapping("/actualizar/{id}")
		public Map<String, String> actualizar(@RequestBody DetalleReservaCreateDTO c, @PathVariable(name = "id") Long id){
			Optional<DetalleReserva> detallereserva = detalleReservaService.getById(id);
			if (detallereserva.isPresent()) {
				c.setId(id);
				detalleReservaService.guardar(c);
			} else {
				throw new RuntimeException("El Detalle reserva no existe en la bd");
			}
			Map<String, String> respuesta = new HashMap<>();
			respuesta.put("msg", "Detalle reserva actualizado correctamente");
			return respuesta;
		}	
}

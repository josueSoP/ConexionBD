package com.soto.app.proyecto4.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soto.app.proyecto4.enums.Estados;

@RestController
@RequestMapping("api/estados")
@CrossOrigin(origins = "*")
public class EnumController {
	
	@GetMapping
    public List<Estados> listar() {
        return Arrays.asList(Estados.values());
    }
	
}
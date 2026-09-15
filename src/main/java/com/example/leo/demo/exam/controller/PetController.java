package com.example.leo.demo.exam.controller;

import com.example.leo.demo.exam.model.Pet;
import com.example.leo.demo.exam.model.PetResponse;
import com.example.leo.demo.exam.service.PetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // GET > obtiene un Pet desde la API externa
    @GetMapping("/{petId}")
    public Pet getPet(@PathVariable Long petId) {
        return petService.getPet(petId);
    }

    // POST > crea un Pet y devuelve respuesta con metadata
    @PostMapping
    public PetResponse createPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }
}

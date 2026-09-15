package com.example.leo.demo.exam.service;

import com.example.leo.demo.exam.client.PetClient;
import com.example.leo.demo.exam.model.Pet;
import com.example.leo.demo.exam.model.PetResponse;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private final PetClient petClient;

    public PetService(PetClient petClient) {
        this.petClient = petClient;
    }

    // GET > consume la API externa
    public Pet getPet(Long petId) {
        Pet pet = petClient.getPetById(petId);
        System.out.println("Pet info: " + pet); // Log en consola
        return pet;
    }

    // POST > crea un Pet en la API externa y devuelve respuesta con metadata
    public PetResponse createPet(Pet pet) {
        Pet createdPet = petClient.createPet(pet); // ahora sí llama al POST externo
        if (createdPet != null) {
            System.out.println("Pet creado en API externa: " + createdPet.getName() + " - " + createdPet.getStatus());
            return new PetResponse(createdPet.getName(), createdPet.getStatus());
        } else {
            System.out.println("Error al crear Pet en API externa");
            return new PetResponse(pet.getName(), "failed");
        }
    }
}

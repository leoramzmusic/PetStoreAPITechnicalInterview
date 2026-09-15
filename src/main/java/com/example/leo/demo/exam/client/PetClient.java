package com.example.leo.demo.exam.client;

import com.example.leo.demo.exam.model.Pet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PetClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://petstore.swagger.io/v2/pet/";

    // GET: consulta un Pet por ID
    public Pet getPetById(Long petId) {
        try {
            return restTemplate.getForObject(BASE_URL + petId, Pet.class);
        } catch (Exception e) {
            System.out.println("Error al consumir API externa: " + e.getMessage());
            Pet fallback = new Pet();
            fallback.setId(petId);
            fallback.setName("Pet not found");
            fallback.setStatus("unknown");
            return fallback;
        }
    }

    // POST: crea un Pet en la API externa
    public Pet createPet(Pet pet) {
        try {
            return restTemplate.postForObject("https://petstore.swagger.io/v2/pet", pet, Pet.class);
        } catch (Exception e) {
            System.out.println("Error al crear Pet en API externa: " + e.getMessage());
            return null;
        }
    }
}



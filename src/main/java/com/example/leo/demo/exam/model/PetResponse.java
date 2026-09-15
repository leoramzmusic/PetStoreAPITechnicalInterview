package com.example.leo.demo.exam.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PetResponse {
    private String transactionId;
    private LocalDateTime dateCreated;
    private String name;
    private String status;

    public PetResponse(String name, String status) {
        this.transactionId = UUID.randomUUID().toString();
        this.dateCreated = LocalDateTime.now();
        this.name = name;
        this.status = status;
    }

    // Getters
    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}

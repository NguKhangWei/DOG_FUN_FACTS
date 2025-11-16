package com.khangwei.dogfunfacts.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khangwei.dogfunfacts.dtos.DogFactDto;
import com.khangwei.dogfunfacts.services.DogFactService;

@RestController
@RequestMapping("/dogfacts")
public class DogFactController {
    private final DogFactService service;

    public DogFactController(DogFactService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<List<DogFactDto>> getAllDogFacts(){
        return this.service.getAllDogFacts();
    }
}

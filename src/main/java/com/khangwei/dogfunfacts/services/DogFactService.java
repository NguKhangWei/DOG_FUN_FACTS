package com.khangwei.dogfunfacts.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khangwei.dogfunfacts.dtos.DogFactDto;
import com.khangwei.dogfunfacts.entities.DogFact;
import com.khangwei.dogfunfacts.mappers.DogFactMapper;
import com.khangwei.dogfunfacts.repositories.DogFactRepository;

@Service
public class DogFactService {
    private final DogFactRepository repository;
    private final DogFactMapper mapper;
    private final ObjectMapper MAPPER = new ObjectMapper();
    private final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private final HttpRequest HTTP_REQUEST = HttpRequest.newBuilder()
                                             .uri(URI.create("https://dogapi.dog/api/v2/facts?limit=1"))
                                             .build();

    public DogFactService(DogFactRepository repository, DogFactMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    private void generateDogFact(){
        String dogFact = HTTP_CLIENT.sendAsync(HTTP_REQUEST, HttpResponse.BodyHandlers.ofString())
                  .thenApply(response -> {
                        try {
                            return MAPPER.readTree(response.body());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                  .thenApply(root -> root.path("data").get(0)
                  .path("attributes").path("body").asText())
                  .join();

        DogFact dogFactEntity = new DogFact(null, dogFact);
        this.repository.save(dogFactEntity);
    
    }

    public ResponseEntity<List<DogFactDto>> getAllDogFacts(){
        this.generateDogFact();
        List<DogFactDto> dogFacts = this.repository.findAll()
                                        .stream()
                                        .map(this.mapper::toDto)
                                        .toList();
        return ResponseEntity.ok(dogFacts);
    }

}

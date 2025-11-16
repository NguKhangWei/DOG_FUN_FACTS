package com.khangwei.dogfunfacts.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khangwei.dogfunfacts.dtos.DogFactDto;
import com.khangwei.dogfunfacts.services.DogFactService;

@Controller
@RequestMapping("/dogfacts")
public class DogFactController {
    private final DogFactService service;

    public DogFactController(DogFactService service){
        this.service = service;
    }

    @GetMapping
    public String getAllDogFacts(Model model){
        List<DogFactDto> dogFacts = this.service.getAllDogFacts();
        model.addAttribute("dogFact", dogFacts);
        return "index";
    }

    @PostMapping("/newfact")
    public String getNewDogFact(){
        this.service.generateDogFact();
        return "redirect:/dogfacts";
    }
}

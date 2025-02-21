package org.example.turistguide2.controller;

import org.example.turistguide2.service.TouristAttractionRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/attractionlist")
public class TouristAttractionController {
    private final TouristAttractionRepoService touristAttractionRepoService;

    @Autowired
    public TouristAttractionController(TouristAttractionRepoService touristAttractionRepoService){
        this.touristAttractionRepoService = touristAttractionRepoService;
    }

    @GetMapping
    public String getAttraction(){
        return "attractionList";
    }
}

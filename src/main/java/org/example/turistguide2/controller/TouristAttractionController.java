package org.example.turistguide2.controller;

import org.example.turistguide2.model.TouristAttraction;
import org.example.turistguide2.service.TouristAttractionRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/attractions")
public class TouristAttractionController {
    private final TouristAttractionRepoService touristAttractionRepoService;

    @Autowired
    public TouristAttractionController(TouristAttractionRepoService touristAttractionRepoService) {
        this.touristAttractionRepoService = touristAttractionRepoService;
    }

    @GetMapping("/list")
    public String getAttraction() {
        return "attractionList";
    }

    @GetMapping("/add")
    public String showAddAttractionForm() {
        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@RequestParam String name,
                                 @RequestParam String description,
                                 @RequestParam String city) {
        TouristAttraction newAttraction = new TouristAttraction(name, description, city, new ArrayList<>());
        touristAttractionRepoService.addTouristAttractionToList(newAttraction);
        return "redirect:/attractions/list";
    }
}

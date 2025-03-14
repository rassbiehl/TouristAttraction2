package org.example.turistguide2.controller;

import org.example.turistguide2.model.TouristAttraction;
import org.example.turistguide2.model.Tags;
import org.example.turistguide2.service.TouristAttractionRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristAttractionController {
    private final TouristAttractionRepoService touristAttractionRepoService;

    @Autowired
    public TouristAttractionController(TouristAttractionRepoService touristAttractionRepoService) {
        this.touristAttractionRepoService = touristAttractionRepoService;
    }

    @GetMapping
    public String getAttractions(@RequestParam(value = "search", required = false) String search, Model model) {
        List<TouristAttraction> filteredAttractions = new ArrayList<>(touristAttractionRepoService.getFirstAttractions()); /* by standard it's the first
        10 attractions that will be shown on the html side. */

        if (search != null && !search.isEmpty()) { // if the search bar is not empty the html file will search for what contains search-url-attribute
            filteredAttractions.clear();
            for (TouristAttraction attraction : touristAttractionRepoService.getTouristAttractionList()) {
                if (attraction.getName().toLowerCase().contains(search.toLowerCase())) {
                    filteredAttractions.add(attraction);
                }
            }
        }
        model.addAttribute("attractions", filteredAttractions);
        return "attractionList";
    }

    @GetMapping("/{name}/tags")
    public String getTags(@PathVariable String name, Model model) {

        TouristAttraction touristAttraction = touristAttractionRepoService.findAttractionByName(name);

        model.addAttribute("viewAttraction", touristAttraction);
        return "tags";
    }

    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable("name") String name, Model model) {
        TouristAttraction touristAttraction = touristAttractionRepoService.findAttractionByName(name);

        if (touristAttraction == null) {
            return "redirect:/attractions";
        }

        model.addAttribute("attraction", touristAttraction);
        model.addAttribute("tags", Tags.values());
        return "updateAttraction";
    }

    @PostMapping("/update")
    public String updateAttraction(@RequestParam("name") String name,
                                   @RequestParam(value = "description", required = false) String newDescription,
                                   @RequestParam(value = "selectedTags", required = false) List<Tags> selectedTags) {

        TouristAttraction touristAttraction = touristAttractionRepoService.findAttractionByName(name);

        if (touristAttraction == null) {
            throw new RuntimeException("Attraction not found: " + name);
        }

        if (newDescription != null && !newDescription.trim().isEmpty()) {
            touristAttraction.setDescription(newDescription);
        }

        if (selectedTags != null) {
            touristAttraction.setTags(selectedTags);
        }

        touristAttractionRepoService.updateTouristAttraction(touristAttraction);

        return "redirect:/attractions#attractions";
    }

    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction touristAttraction = new TouristAttraction();

        model.addAttribute("tags", Tags.values());
        model.addAttribute("touristAttraction", touristAttraction);
        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction touristAttraction,
                                 @RequestParam List<Tags> tags) {
        touristAttraction.setTags(tags);
        touristAttractionRepoService.addTouristAttractionToList(touristAttraction);
        return "redirect:/attractions#attractions";
    }

    /*
        // Display the delete confirmation page
        @GetMapping("/attractions/{name}/delete")
        public String showDeleteForm(@PathVariable("name") String name, Model model) {
            TouristAttraction attraction = touristAttractionRepoService.findAttractionByName(name);
            if (attraction != null) {
                model.addAttribute("attraction", attraction);
                return "delete_attraction"; // Return the delete confirmation page
            }
            return "error"; // Handle error if the attraction is not found
        }
    */
    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name, RedirectAttributes redirectAttributes) {
        boolean isDeleted = touristAttractionRepoService.deleteTouristAttractionFromList(name);

        if (isDeleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Attraction '" + name + "' deleted.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Attraction '" + name + "' not found.");
        }

        return "redirect:/attractions#attractions";
    }

}

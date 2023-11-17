package com.assignment.daraspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PetitionController {

    @GetMapping("/newPetition")
    public String petitionForm(Model model) {
        model.addAttribute("newPetition", new Petition());
        return "newPetition";
    }

    @PostMapping("/newPetition")
    public String petitionSubmit(@ModelAttribute Petition petition, Model model) {
        model.addAttribute("newPetition", petition);
        return "viewPetition";
    }

}

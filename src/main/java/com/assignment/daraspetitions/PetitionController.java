package com.assignment.daraspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PetitionController {

    List<Petition> petitionList = new ArrayList<>();
    int count = 0;

    @GetMapping("/newPetition")
    public String petitionForm(Model model) {
        model.addAttribute("newPetition", new Petition());
        return "newPetition";
    }

    @GetMapping("/showPetition")
    public String viewPetitions(Model model) {
        model.addAttribute("petitionList", petitionList);
        return "showPetition";
    }

    @PostMapping("/newPetition")
    public String petitionSubmit(@ModelAttribute Petition petition, Model model) {
        model.addAttribute("petition", petition);
        petition.setId(count);
        petitionList.add(petition);
        System.out.println(petitionList.toString());
        count++;
        return "viewPetition";
    }

    @PostMapping("/showPetition/{id}")
    public String petitionById(Model model) {
        model.addAttribute("petitionList", petitionList);
        return "viewPetition";
    }

}
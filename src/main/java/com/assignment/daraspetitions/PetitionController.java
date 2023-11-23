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

    public int findByTitle(String title){
        for (Petition p : petitionList){
            if(p.getTitle().equals(title)){
                return p.getId();
            }
        }

        return -1;
    }

    @GetMapping("/newPetition")
    public String petitionForm(Model model) {
        model.addAttribute("newPetition", new Petition());
        return "newPetition";
    }

    @GetMapping("/showAllPetitions")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitionList", petitionList);
        return "showAllPetitions";
    }

    @PostMapping("/newPetition")
    public String petitionSubmit(@ModelAttribute Petition petition, Model model) {
        model.addAttribute("petition", petition);
        petition.setId(count);
        petitionList.add(petition);
        System.out.println(petitionList.get(0).getDescription());
        count++;
        return "redirect:/";
    }

    @RequestMapping("/viewPetition/")
    public String petitionById(@RequestParam(value="id", required = false) Integer id,  Model model) {
        model.addAttribute("id", id);
        model.addAttribute("petitionList", petitionList);
        System.out.println("HELLO" + id);
        return "viewPetition";
    }

    @GetMapping("/searchPetitions")
    public String searchSubmit(Model model) {
        //model.addAttribute("searchPetition");
        return "searchPetitions";
    }

    @RequestMapping("/searchPetitions/")
    public String petitionByTitle(@RequestParam(value="title", required = false) String title, Model model) {
        model.addAttribute("title", title);

        int id = findByTitle(title);
        model.addAttribute("id", id);

        model.addAttribute("petitionList", petitionList);
        System.out.println("HELLO" + title);
        System.out.println("Index is " + findByTitle(title));
        return "viewPetition";
    }

}
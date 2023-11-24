package com.assignment.daraspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PetitionController {

    //declare and initialize variables
    List<Petition> petitionList = new ArrayList<>();
    HashMap<Integer, ArrayList<User>> userSign = new HashMap<>();
    int count = 0;
    int numOfSigns = 0;

    //method for searching by title
    public int findByTitle(String title){
        for (Petition p : petitionList){
            if(p.getTitle().equals(title)){
                return p.getId();
            }
        }

        return -1;
    }

    //get the petition creation
    @GetMapping("/newPetition")
    public String petitionForm(Model model) {
        model.addAttribute("newPetition", new Petition());
        return "newPetition";
    }

    //list all the petitions
    @GetMapping("/showAllPetitions")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitionList", petitionList);
        return "showAllPetitions";
    }

    //post a new petition
    @PostMapping("/newPetition")
    public String petitionSubmit(@ModelAttribute Petition petition, Model model) {
        model.addAttribute("petition", petition);
        petition.setSignatureCount(numOfSigns);
        petition.setId(count);
        petitionList.add(petition);
        //System.out.println(petitionList.get(0).getDescription() + petitionList.get(0).getId() + petitionList.get(0).getSignatureCount());
        count++;
        return "redirect:/";
    }

    @GetMapping("/viewPetition")
    public String getUser(@ModelAttribute Model model){
        model.addAttribute("user", new User());
        return "viewPetition";
    }

    //post the petition signature
    @PostMapping("/signPetition/")
    public String petitionSignature(@RequestParam(value="id", required = false) @ModelAttribute Integer id, User user, Petition petition, Model model){
        model.addAttribute("userSign", userSign);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        model.addAttribute("petitionList", petitionList);
        model.addAttribute("numOfSigns", numOfSigns);
        userSign.computeIfAbsent(id, i -> new ArrayList<>()).add(user);
        petitionList.get(id).setSignatureCount(petitionList.get(id).getSignatureCount() + 1);
        return "redirect:/viewPetition/?id=" + id;
    }

    //retrieve the petition based on id
    @RequestMapping("/viewPetition/")
    public String petitionById(@RequestParam(value="id", required = false) Integer id, Petition petition,  Model model) {
        model.addAttribute("userSign", userSign);
        model.addAttribute("id", id);
        model.addAttribute("petitionList", petitionList);
        model.addAttribute("user", new User());
        return "viewPetition";
    }

    // return the petition search
    @GetMapping("/searchPetitions")
    public String searchSubmit(Model model) {
        return "searchPetitions";
    }

    //retrieve the petition based on searched title
    @RequestMapping("/searchPetitions/")
    public String petitionByTitle(@RequestParam(value="title", required = false) String title, Model model) {
        model.addAttribute("userSign", userSign);
        model.addAttribute("title", title);
        model.addAttribute("user", new User());
        int id = findByTitle(title);
        model.addAttribute("id", id);
        model.addAttribute("petitionList", petitionList);
        return "viewPetition";
    }

}
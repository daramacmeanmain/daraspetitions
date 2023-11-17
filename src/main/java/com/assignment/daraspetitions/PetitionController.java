package com.assignment.daraspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PetitionController {

    @GetMapping("/index")
    public String indexForm(Model model) {
        model.addAttribute("index", new Index());
        return "index";
    }

    @PostMapping("/index")
    public String petitionSubmit(@ModelAttribute Index index, Model model) {
        model.addAttribute("index", index);
        return "petition";
    }

}

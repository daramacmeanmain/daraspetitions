package com.assignment.daraspetitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DaraspetitionsApplication {

	@GetMapping("/index")
	public String indexForm(Model model) {

		model.addAttribute("index", new Petition());

		return "index";
	}

	@PostMapping("/index")
	public String petitionForm(@ModelAttribute Petition petition, Model model) {
		model.addAttribute("index", petition);
		return "petition";
	}

	public static void main(String[] args) {

		SpringApplication.run(DaraspetitionsApplication.class, args);
	}

}

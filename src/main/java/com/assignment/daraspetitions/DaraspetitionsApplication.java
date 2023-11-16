package com.assignment.daraspetitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DaraspetitionsApplication {

	@RequestMapping("/")
	public String indexForm(Model model) {

		model.addAttribute("index", new Index());

		return "/index.html";
	}

	@RequestMapping("/petition")
	public String petitionForm(@ModelAttribute Petition petition, Model model) {
		model.addAttribute("petition", petition);
		return "Hello Again";
	}

	public static void main(String[] args) {

		SpringApplication.run(DaraspetitionsApplication.class, args);
	}

}

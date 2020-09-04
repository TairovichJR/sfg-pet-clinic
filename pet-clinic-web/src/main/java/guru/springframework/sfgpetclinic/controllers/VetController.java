package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.sfgpetclinic.services.VetService;

@Controller
@RequestMapping("/vets")
public class VetController {

	private final VetService vetService;
	
	public VetController(VetService vetService) {
		this.vetService = vetService;
	}

	@RequestMapping(value = {"","/","/index","/index.html"}, method=RequestMethod.GET) 
	public String listVets(Model model) {
		
		model.addAttribute("vets", vetService.findAll());
		return "vets/index";
	}
}

package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.sfgpetclinic.services.OwnerService;

@Controller
@RequestMapping("/owners")
public class OwnerController {

	private final OwnerService ownerService;
	
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
	
	@RequestMapping(value = {"","/","/index","/index.html"}, method=RequestMethod.GET) 
	public String listOwners(Model model) {
		
		model.addAttribute("owners", ownerService.findAll());
		return "owners/index";
	}
	
	@RequestMapping(value = {"/find"}, method=RequestMethod.GET) 
	public String findOwners() {
		return "NotImplemented";
	}
	
	
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject("owner",ownerService.findById(ownerId));
		return mav;
	}
	
	
	
	
	
	
	
}



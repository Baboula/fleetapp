package com.kindsonthegenius.fleetapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kindsonthegenius.fleetapp.models.Country;
import com.kindsonthegenius.fleetapp.services.CountryService;

@Controller
public class CountryController {

	@Autowired
	private CountryService countryService;

	// access to countries list
	@GetMapping("/countries")
	public String getCountries(Model model) {		
		List<Country> countryList = countryService.getCountries();

		// add  all countries in model
		model.addAttribute("countries", countryList);	
		return "country";
	}
	private static final String redirection = "redirect:/countries";

	// access to add form of country
	@PostMapping("/countries/addNew")
	public String addNew(Country country) {
		countryService.save(country);
		return redirection;
	}

	// Get country by id
	@RequestMapping("countries/findById")
	@ResponseBody
	public Optional<Country> findById(int id) {
	  return countryService.findById(id);	
	}	

	// update country and redirect to countries list
	@RequestMapping(value="/countries/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Country country) {
		countryService.save(country);
		return redirection;
	}

	// delete country and show new list of countries
	@RequestMapping(value="/countries/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		countryService.delete(id);
		return redirection;
	}
	
}

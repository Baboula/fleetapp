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
import com.kindsonthegenius.fleetapp.models.State;
import com.kindsonthegenius.fleetapp.services.CountryService;
import com.kindsonthegenius.fleetapp.services.StateService;

@Controller
public class StateController {

	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;

	// access to states list
	@GetMapping("/states")
	public String getStates(Model model) {		
		List<State> stateList = stateService.getStates();	
		model.addAttribute("states", stateList);	
		
		List<Country> countryList = countryService.getCountries();	
		model.addAttribute("countries", countryList);
		
		return "State";
	}

	private static final String redirection = "redirect:/states";

	// add new state
	@PostMapping("/states/addNew")
	public String addNew(State state) {
		stateService.save(state);
		return redirection;
	}

	// Get state by id
	@RequestMapping("states/findById")
	@ResponseBody
	public Optional<State> findById(int id) {
	  return stateService.findById(id);	
	}	

	// update state
	@RequestMapping(value="/states/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(State state) {
		stateService.save(state);
		return redirection;
	}

	// delete state
	@RequestMapping(value="/states/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		stateService.delete(id);
		return redirection;
	}
	

}

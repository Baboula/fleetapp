package com.kindsonthegenius.fleetapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kindsonthegenius.fleetapp.models.Client;
import com.kindsonthegenius.fleetapp.services.ClientService;
import com.kindsonthegenius.fleetapp.services.CountryService;
import com.kindsonthegenius.fleetapp.services.StateService;

@Controller
public class ClientController {

	@Autowired private ClientService clientService;
	@Autowired private CountryService countryService;
	@Autowired private StateService stateService;

	// access to the clients list
	@GetMapping("/clients")
	public String getClients(Model model) {

		// add all clients in model
		model.addAttribute("clients", clientService.getClients());	

		// add all countries in model
		model.addAttribute("countries", countryService.getCountries());

		// add all states in model
		model.addAttribute("states", stateService.getStates());

		return "Client";
	}	

	// send infos of new client
	@PostMapping("/clients/addNew")
	public String addNew(Client client) {
		clientService.save(client);
		return "redirect:/clients";
	}

	// Get client by id
	@RequestMapping("clients/findById")
	@ResponseBody
	public Optional<Client> findById(int id) {
	  return clientService.findById(id);	
	}	

	// Update infos of client
	@RequestMapping(value="/clients/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Client client) {
		clientService.save(client);
		return "redirect:/clients";
	}

	// delete client by id and show new list of clients
	@RequestMapping(value="/clients/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		clientService.delete(id);
		return "redirect:/clients";
	}
	
}

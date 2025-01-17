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

import com.kindsonthegenius.fleetapp.models.VehicleMake;
import com.kindsonthegenius.fleetapp.services.VehicleMakeService;

@Controller
public class VehicleMakeController {

	@Autowired private VehicleMakeService vehicleMakeService;

	// access to vehicles makes list
	@GetMapping("/vehicleMakes")
	public String getStates(Model model) {		

		List<VehicleMake> vehicleMakeList = vehicleMakeService.getVehicleMakes();
		
		model.addAttribute("vehicleMakes", vehicleMakeList);
		return "vehicleMake";
	}

	private static final String redirection = "redirect:/vehicleMakes";

	// add new vehicle make
	@PostMapping("/vehicleMakes/addNew")
	public String addNew(VehicleMake vehicleMake) {
		vehicleMakeService.save(vehicleMake);
		return redirection;
	}

	// Get vehicle make by id
	@RequestMapping("vehicleMakes/findById")
	@ResponseBody
	public Optional<VehicleMake> findById(int id) {
	  return vehicleMakeService.findById(id);	
	}	

	// update vehicle make
	@RequestMapping(value="/vehicleMakes/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleMake vehicleMake) {
		vehicleMakeService.save(vehicleMake);
		return redirection;
	}

	// delete vehicle make
	@RequestMapping(value="/vehicleMakes/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleMakeService.delete(id);
		return redirection;
	}
}

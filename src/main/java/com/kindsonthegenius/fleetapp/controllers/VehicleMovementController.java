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

import com.kindsonthegenius.fleetapp.models.VehicleMovement;
import com.kindsonthegenius.fleetapp.services.LocationService;
import com.kindsonthegenius.fleetapp.services.VehicleMovementService;
import com.kindsonthegenius.fleetapp.services.VehicleService;

@Controller
public class VehicleMovementController {

	@Autowired private VehicleMovementService vehicleMovementService;
	@Autowired private VehicleService vehicleService;
	@Autowired private LocationService locationService;

	// access to vehicles movements list
	@GetMapping("/vehicleMovements")
	public String getVehicleMovements(Model model) {		
		model.addAttribute("vehicleMovements", vehicleMovementService.getVehicleMovements());	
		
		model.addAttribute("vehicles", vehicleService.getVehicles());
		model.addAttribute("locations", locationService.getLocations());

		return "VehicleMovement";
	}

	private static final String redirection = "redirect:/vehicleMovements";

	// add new vehicle movement
	@PostMapping("/vehicleMovements/addNew")
	public String addNew(VehicleMovement vehicleMovement) {
		vehicleMovementService.save(vehicleMovement);
		return redirection;
	}

	// Get vehicle movement by id
	@RequestMapping("vehicleMovements/findById")
	@ResponseBody
	public Optional<VehicleMovement> findById(int id) {
	  return vehicleMovementService.findById(id);	
	}	

	// update vehicle movement
	@RequestMapping(value="/vehicleMovements/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleMovement vehicleMovement) {
		vehicleMovementService.save(vehicleMovement);
		return redirection;
	}

	// delete vehicle movement
	@RequestMapping(value="/vehicleMovements/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleMovementService.delete(id);
		return redirection;
	}

}

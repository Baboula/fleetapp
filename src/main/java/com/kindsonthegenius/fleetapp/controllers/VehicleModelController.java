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

import com.kindsonthegenius.fleetapp.models.VehicleModel;
import com.kindsonthegenius.fleetapp.services.VehicleModelService;

@Controller
public class VehicleModelController {

	@Autowired private VehicleModelService vehicleModelService;

	// access to vehicles models list
	@GetMapping("/vehicleModels")
	public String getVehicleModels(Model model) {		
		
		List<VehicleModel> vehicleModelList = vehicleModelService.getVehicleModels();	

		model.addAttribute("vehicleModels", vehicleModelList);
		return "vehicleModel";
	}

	private static final String redirection = "redirect:/vehicleModels";

	// add new vehicle model
	@PostMapping("/vehicleModels/addNew")
	public String addNew(VehicleModel vehicleModel) {
		vehicleModelService.save(vehicleModel);
		return redirection;
	}

	// Get vehicle model by id
	@RequestMapping("vehicleModels/findById")
	@ResponseBody
	public Optional<VehicleModel> findById(int id) {
	  return vehicleModelService.findById(id);	
	}	

	// update vehicle model
	@RequestMapping(value="/vehicleModels/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleModel vehicleModel) {
		vehicleModelService.save(vehicleModel);
		return redirection;
	}

	// delete vehicle model
	@RequestMapping(value="/vehicleModels/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleModelService.delete(id);
		return redirection;
	}
}

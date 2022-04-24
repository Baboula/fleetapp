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

import com.kindsonthegenius.fleetapp.models.VehicleStatus;
import com.kindsonthegenius.fleetapp.services.VehicleStatusService;

@Controller
public class VehicleStatusController {

	@Autowired private VehicleStatusService vehicleStatusService;

	// access to vehicles status list
	@GetMapping("/vehicleStatuses")
	public String getVehicleStatuss(Model model) {		
		
		List<VehicleStatus> vehicleStatusList = vehicleStatusService.getVehicleStatuses();	

		model.addAttribute("vehicleStatuses", vehicleStatusList);
		return "vehicleStatus";
	}	

	// add  new vehicle status
	@PostMapping("/vehicleStatuses/addNew")
	public String addNew(VehicleStatus vehicleStatus) {
		vehicleStatusService.save(vehicleStatus);
		return "redirect:/vehicleStatuses";
	}

	// Get vehicle status bt id
	@RequestMapping("vehicleStatuses/findById")
	@ResponseBody
	public Optional<VehicleStatus> findById(int id) {
	  return vehicleStatusService.findById(id);	
	}	

	// update vehicle status
	@RequestMapping(value="/vehicleStatuses/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleStatus vehicleStatus) {
		vehicleStatusService.save(vehicleStatus);
		return "redirect:/vehicleStatuses";
	}

	// delete vehicle status
	@RequestMapping(value="/vehicleStatuses/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleStatusService.delete(id);
		return "redirect:/vehicleStatuses";
	}
}

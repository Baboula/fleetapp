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

import com.kindsonthegenius.fleetapp.models.VehicleMaintenance;
import com.kindsonthegenius.fleetapp.services.SupplierService;
import com.kindsonthegenius.fleetapp.services.VehicleMaintenanceService;
import com.kindsonthegenius.fleetapp.services.VehicleService;

@Controller
public class VehicleMaintenanceController {


	@Autowired private VehicleMaintenanceService vehicleMaintenanceService;
	@Autowired private VehicleService vehicleService;
	@Autowired private SupplierService supplierService;

	//access to vehicles in maintenance list
	@GetMapping("/vehicleMaintenances")
	public String getVehicleMaintenances(Model model) {		
		model.addAttribute("vehicleMaintenances", vehicleMaintenanceService.getVehicleMaintenances());	
		
		model.addAttribute("vehicles", vehicleService.getVehicles());
		model.addAttribute("suppliers", supplierService.getSuppliers());

		return "VehicleMaintenance";
	}

	private static final String redirection = "redirect:/vehicleMaintenances";

	// add new vehicle in maintenance
	@PostMapping("/vehicleMaintenances/addNew")
	public String addNew(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceService.save(vehicleMaintenance);
		return redirection;
	}

	// Get vehiculeMaintenance by id
	@RequestMapping("vehicleMaintenances/findById")
	@ResponseBody
	public Optional<VehicleMaintenance> findById(int id) {
	  return vehicleMaintenanceService.findById(id);	
	}	

	// update vehicleMaintenance
	@RequestMapping(value="/vehicleMaintenances/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceService.save(vehicleMaintenance);
		return redirection;
	}

	// delete vehicleMaintenance
	@RequestMapping(value="/vehicleMaintenances/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleMaintenanceService.delete(id);
		return redirection;
	}

}

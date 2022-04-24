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

import com.kindsonthegenius.fleetapp.models.InvoiceStatus;
import com.kindsonthegenius.fleetapp.services.InvoiceStatusService;

@Controller
public class InvoiceStatusController {

	@Autowired private InvoiceStatusService invoiceStatusService;

	// access to invoiceStatus list
	@GetMapping("/invoiceStatuses")
	public String getStates(Model model) {		
		
		List<InvoiceStatus> invoiceStatusList = invoiceStatusService.getInvoiceStatuses();
		model.addAttribute("invoiceStatuses", invoiceStatusList);
		return "invoiceStatus";
	}	

	// add new invoiceStatus
	@PostMapping("/invoiceStatuses/addNew")
	public String addNew(InvoiceStatus invoiceStatus) {
		invoiceStatusService.save(invoiceStatus);
		return "redirect:/invoiceStatuses";
	}

	// Get invoiceStatus by id
	@RequestMapping("invoiceStatuses/findById")
	@ResponseBody
	public Optional<InvoiceStatus> findById(int id) {
	  return invoiceStatusService.findById(id);	
	}	

	// update invoiceStatus
	@RequestMapping(value="/invoiceStatuses/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(InvoiceStatus invoiceStatus) {
		invoiceStatusService.save(invoiceStatus);
		return "redirect:/invoiceStatuses";
	}

	// delete invoiceStatus
	@RequestMapping(value="/invoiceStatuss/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		invoiceStatusService.delete(id);
		return "redirect:/invoiceStatuses";
	}
}

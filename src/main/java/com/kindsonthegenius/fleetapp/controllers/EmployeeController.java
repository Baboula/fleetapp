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

import com.kindsonthegenius.fleetapp.models.Employee;
import com.kindsonthegenius.fleetapp.services.CountryService;
import com.kindsonthegenius.fleetapp.services.EmployeeService;
import com.kindsonthegenius.fleetapp.services.EmployeeTypeService;
import com.kindsonthegenius.fleetapp.services.JobTitleService;
import com.kindsonthegenius.fleetapp.services.StateService;

@Controller
public class EmployeeController {

	@Autowired private EmployeeService employeeService;
	@Autowired private JobTitleService jobTitleService;
	@Autowired private EmployeeTypeService employeeTypeService;
	@Autowired private CountryService countryService;
	@Autowired private StateService stateService;

	//access to employees list
	@GetMapping("/employees")
	public String getEmployees(Model model, String keyword) {	

		// add all jobTitles, employeeTypes, countries and states in model
		model.addAttribute("jobTitles", jobTitleService.getJobTitles());		
	    model.addAttribute("employeeTypes", employeeTypeService.getEmployeeTypes());
		model.addAttribute("countries", countryService.getCountries());			
		model.addAttribute("states", stateService.getStates());		

		if(keyword != null){
			model.addAttribute("employees", employeeService.findByKeyword(keyword));
		}
		else 
		{
		     model.addAttribute("employees", employeeService.getEmployees());			
		}
		
		return "Employee";
	}	

	// filter for employees
	@GetMapping("/employeesFiltered")
	public String getEmployeesFiltered(Model model, String keyword) {	
		
		model.addAttribute("jobTitles", jobTitleService.getJobTitles());		
	    model.addAttribute("employeeTypes", employeeTypeService.getEmployeeTypes());
		model.addAttribute("countries", countryService.getCountries());			
		model.addAttribute("states", stateService.getStates());		

	   model.addAttribute("employees", employeeService.getEmployees());
		
		return "EmployeeFiltered";
	}
	
	
	// access to add form for employee
	@PostMapping("/employees/addNew")
	public String addNew(Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees";
	}

	// Get employee by id
	@RequestMapping("employees/findById")
	@ResponseBody
	public Optional<Employee> findById(int id) {
	  return employeeService.findById(id);	
	}	

	// update employee
	@RequestMapping(value="/employees/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees";
	}

	// delete employee
	@RequestMapping(value="/employees/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		employeeService.delete(id);
		return "redirect:/employees";
	}

	//Assign Employee Username
	@RequestMapping(value = "/employees/assignUsername")
	public  String assignUsername(int id){
		employeeService.assignUsername(id);
		return "redirect:/employees";
	}

}

package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomer(Model model) {
		
		List<Customer> customers = customerService.getCustomers();
		
		model.addAttribute("customers",customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Customer theCustomer = new Customer();
		
		model.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
				
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(Model model, @RequestParam("customerId") int theId) {
		
		Customer theCustomer = customerService.getCustomer(theId);
		
		model.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer( @RequestParam("customerId") int theId) {
		
		Customer theCustomer = customerService.getCustomer(theId);
		customerService.deleteCustomer(theCustomer);
		
		
		return "redirect:/customer/list";
	}
	
	@PostMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String theSearchName, Model model) {
		
		List<Customer> customers= customerService.searchCustomer(theSearchName);
		
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}

}

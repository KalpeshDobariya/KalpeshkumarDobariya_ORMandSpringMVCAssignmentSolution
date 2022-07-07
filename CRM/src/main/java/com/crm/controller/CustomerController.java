package com.crm.controller;

import java.util.List;

import com.crm.entity.Customer;
import com.crm.service.CustomerDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping ("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerDetail customerDetail;
	
	// to see the list of "customer"; 
		@RequestMapping ("/list")
		public String listCustomers(Model theModel) {
			System.out.println("request received");
			List<Customer> theCustomers = customerDetail.findAll();
			theModel.addAttribute("Customers",theCustomers);
			return "list-Customers";	}
	
		
    // to update the detail of "customer"; 
	@RequestMapping("/showFormForUpdate") 
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) { 	
	    Customer theCustomer = customerDetail.findById(theId); 
	    theModel.addAttribute("Customer", theCustomer); 
	    return "Customers-form"; }
	
	
	
	// for add new customer into the list ; 
	@RequestMapping ("/showFormForAdd")
	public String FormForAdd(Model theModel) {
		Customer theCustomer= new Customer();
		theModel.addAttribute("Customer",theCustomer);
		return "Customers-form"; }
	
	    
	// save customer detail and return to "Customer-list"; 
	@PostMapping("/save") 
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstName") String firstName, 
		@RequestParam("lastName") String lastName, 
		@RequestParam("email") String email) {
				
		//System.out.println(id); 
		Customer theCustomer;
		
		  if (id != 0) { theCustomer = customerDetail.findById(id);
		  theCustomer.setFirstName(firstName); theCustomer.setLastName(lastName);
		  theCustomer.setEmail(email); } else 
		 
			theCustomer = new Customer(firstName,lastName,email); 
		    // save the customer detail
		    customerDetail.save(theCustomer); 
		    // use a redirect to prevent duplicate submissions 
		    return  "redirect:/customer/list"; }
		

		
	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
			//delete the customer
			customerDetail.deleteById(theId);
			//redirect to main page
			return  "redirect:/customer/list";}
		

}

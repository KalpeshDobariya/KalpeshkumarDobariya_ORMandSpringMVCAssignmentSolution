package com.crm.service;

import java.util.List;
import com.crm.entity.Customer;


public interface CustomerDetail {
	    
	    public List<Customer> findAll();
	    
	    public Customer findById(int theId);
		
	    public void save(Customer theCustomer);
		
		public void deleteById(int theId);
		
		
}

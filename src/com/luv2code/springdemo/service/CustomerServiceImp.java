package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}


	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		
		customerDAO.saveCustomer(customer);	
	}
	
	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		Customer customer = customerDAO.getCustomer(theId);
		return customer;
	}
	
	@Override
	@Transactional
	public void deleteCustomer(Customer theCustomer) {
		customerDAO.deleteCustomer(theCustomer);
	}


	@Override
	@Transactional
	public List<Customer> searchCustomer(String theSearchName) {
		
		List<Customer> custoemrs= customerDAO.searchCustomer(theSearchName);

		return custoemrs;
	}

}

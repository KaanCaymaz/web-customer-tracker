package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		Session session=sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery =session.createQuery("from Customer order by lastName asc",Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int id) {

		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		
		return customer;
	}
	
	@Override
	public void deleteCustomer(Customer theCustomer) {
		
		Session session = sessionFactory.getCurrentSession();
		
		//session.delete(theCustomer);
		
		Query theQuery = session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theCustomer.getId());
		
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomer(String theSearchName) {

		Session session = sessionFactory.getCurrentSession();
		Query theQuery;
		  
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
        	 theQuery =session.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	}
	
	

}

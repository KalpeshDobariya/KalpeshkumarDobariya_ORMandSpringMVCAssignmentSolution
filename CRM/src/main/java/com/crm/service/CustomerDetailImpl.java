package com.crm.service;

import com.crm.entity.Customer;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class CustomerDetailImpl implements CustomerDetail{
	
		private SessionFactory sessionFactory;
        private Session session;

		@Autowired
		CustomerDetailImpl(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
			try {
				session = sessionFactory.getCurrentSession();
			}
			catch (HibernateException e){
				session = sessionFactory.openSession();
			}
		}
		
		
		
		//Print All Record
		 @Transactional
		 @Override
		 public List<Customer> findAll() {
			Transaction tx = session.beginTransaction();
			List<Customer> customers = session.createQuery("from Customer").list();
			tx.commit();
			return customers; }
			 
				
		//Save or Insert customer Record
		 @Transactional
		 @Override
		 public void save(Customer theCustomer) {
			 Transaction tx = session.beginTransaction(); 
			 session.save(theCustomer);
			 tx.commit();}

		
		//Delete customer Record 
		 @Transactional
		 @Override
		 public void deleteById(int id) {
		    Transaction tx = session.beginTransaction(); 
		    Customer customer = session.get(Customer.class,id); 
		    if (customer != null) 
		        session.delete(customer);
		    tx.commit();}


		@Override
		public Customer findById(int id) {
				    Customer customer = new Customer();
					Transaction tx = session.beginTransaction();
					customer = session.get(Customer.class, id);
					tx.commit();
					return customer;}

		
}

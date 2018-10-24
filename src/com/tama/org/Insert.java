package com.tama.org;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Insert {

	public static void main(String[] args) {

		Transaction tx = null;

		try {

			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			tx = session.beginTransaction();

			Customer cust1 = new Customer();
		    cust1.setName("John");
		    cust1.setAge(28);
		    cust1.setCity("ranchi");
		   
		    Customer cust2 = new Customer();
		    cust2.setName("Jacob");
		    cust2.setAge(30);
		    cust2.setCity("patna");
		   
		    Customer cust3 = new Customer();
		    cust3.setName("Jhony");
		    cust3.setAge(32);
		    cust3.setCity("ranchi");
		   
		    Customer cust4 = new Customer();
		    cust4.setName("James");
		    cust4.setAge(26);
		    cust4.setCity("patna");
		   
		    Customer cust5 = new Customer();
		    cust5.setName("Jaby");
		    cust5.setAge(36);
		    cust5.setCity("ranchi");
		   
		   
		    session.save(cust1);
		    session.save(cust2);
		    session.save(cust3);
		    session.save(cust4);
		    session.save(cust5);
		    
		    tx.commit();
		    System.out.println("record inserted");

		} 
		catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		}
	}
}
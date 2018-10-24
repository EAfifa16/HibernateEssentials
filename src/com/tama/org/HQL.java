package com.tama.org;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HQL {

	public static void main(String[] args) {

		Transaction tx = null;

		try {

			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			tx = session.beginTransaction();

			// 1. getting all customers in table
			System.out.println("\n all customers in table");
			Query q = session.createQuery("from Customer cust");
			List<Customer> l1 = q.list();
			for (Customer cust : l1) {
				System.out.println(cust);
			}

			// 2. getting customers using id (Unique Result)
			System.out.println("\n customers by : Id");
			Query q1 = session.createQuery("from Customer where id=:id");
			q1.setInteger("id", 3);
			Customer cust = (Customer)q1.uniqueResult();
			System.out.println(cust);

			// 3. getting Customer by city
			System.out.println("\n Customer by city");
			Query q3 = session.createQuery("from Customer where city=:city");
			q3.setString("city", "patna");
			List<Customer> l3 = q3.list();
			for (Customer customer : l3) {
				System.out.println(customer);
			}

			// 4. checking and getting first 3 customer belonging to a city ranchi
			System.out.println("\n checking and getting first 3 customer belonging to a city");
			Query q4 = session.createQuery("from Customer where city=:city");
			q4.setString("city", "ranchi");
			q4.setFirstResult(0);
			q4.setMaxResults(2);
			List<Customer> l4 = q4.list();
			for (Customer customer : l4) {
				System.out.println(customer);
			}

			tx.commit();
			session.close();

		} 
		catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		}
	}
}

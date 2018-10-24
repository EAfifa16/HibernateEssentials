package com.tama.org;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class QBC {

	public static void main(String[] args) {

		Transaction tx = null;

		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			tx = session.beginTransaction();

			// 1. getting all customers in table
			System.out.println("\n all customers in table");
			Criteria ct1 = session.createCriteria(Customer.class);
			List<Customer> list = ct1.list();
			for (Customer cust : list)
				System.out.println(cust);

			// 2. getting customers using id
			System.out.println("\n customers by : Id");
			Criteria ct2 = session.createCriteria(Customer.class);
			ct2.add(Restrictions.eq("id", 2));
			Customer cust=(Customer)ct2.uniqueResult();
			System.out.println(cust);
			
			// 3. getting Customer by city
			System.out.println("\n Customer by : city");
			Criteria ct3 = session.createCriteria(Customer.class);
			ct3.add(Restrictions.eq("city", "ranchi"));
			List<Customer> list1 = ct3.list();
			for (Customer cust1 : list1) {
				System.out.println(cust1);
			}			
			
			// 4. getting Customer using city and Id (Using Criterion)
			System.out.println("\n Customer by city and Id");
			Criteria ct4=session.createCriteria(Customer.class);
			Criterion city=Restrictions.eq("city","ranchi"); 
			Criterion id=Restrictions.eq("id", 1); 
			ct4.add(Restrictions.and(city,id));
			Customer cust1=(Customer)ct4.uniqueResult();
			System.out.println(cust1);
			
			tx.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		}
	}
}

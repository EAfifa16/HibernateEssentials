package com.tama.org;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RetriveUpdate {

	public static void main(String[] args) {

		Transaction tx = null;

		try {

			SessionFactory sf = HibernateUtil.getSessionFactory();

			Session session1 = sf.openSession();
			Customer cust = (Customer) session1.get(Customer.class, 1);
			System.out.println(cust);
			session1.close();

			// Update employee object, which is in detached state
			cust.setAge(25);

			Session session2 = sf.openSession();
			tx = session2.beginTransaction();
			session2.update(cust);
			System.out.println(cust);

			tx.commit();
			session2.close();

		} 
		catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		}
	}
}

package com.tama.org;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RetriveMerge {

	public static void main(String[] args) {

		Transaction tx = null;

		try {

			SessionFactory sf = HibernateUtil.getSessionFactory();

			Session session1 = sf.openSession();
			Customer cust = (Customer) session1.get(Customer.class, 1);
			session1.close();

			// Update employee object, which is in detached state
			cust.setAge(35);

			Session session2 = sf.openSession();
			Customer cust1 = (Customer) session2.get(Customer.class, 1);
			System.out.println(cust1);
			tx = session2.beginTransaction();

			// update will throw org.hibernate.NonUniqueObjectException			
			session2.update(cust);
			
			// so we do merge			
//			session2.merge(cust);

			tx.commit();

		} 
		catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		}
	}
}

package com.tama.org;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.PropertyProjection;

public class Projection {

	public static void main(String[] args) {

		Transaction tx = null;

		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();

			// using projection for partial or limited records
			Criteria ct = session.createCriteria(Customer.class);
			PropertyProjection pt = Projections.property("age");
			ct.setProjection(pt);
			List l1 = ct.list();
			for (Object obj : l1) {
				System.out.println(obj);
			}

			Criteria ct1 = session.createCriteria(Customer.class);
			PropertyProjection pt1 = Projections.property("name");
			PropertyProjection pt2 = Projections.property("age");
			PropertyProjection pt3 = Projections.property("city");

			ProjectionList pList = Projections.projectionList();
			pList.add(pt1);
			pList.add(pt2);
			pList.add(pt3);

			ct1.setProjection(pList);
			List list = ct1.list();

			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				System.out.println("name : " + obj[0] + ", age : " + obj[1] + ", city : " + obj[2]);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

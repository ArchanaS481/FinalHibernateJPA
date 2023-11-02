package com.deeptech.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ManageEmployee {

	private static SessionFactory sf;
	
	public static void main(String[] args) {
	
		try 
		{
		  sf = new Configuration().configure().addAnnotatedClass(Employee.class).buildSessionFactory();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//Creating Objects
		ManageEmployee em = new  ManageEmployee();
		Integer emp1 = em.addEmployee("Anu","Siri","Developer",25000); 
		Integer emp2 = em.addEmployee("Shankar","S","Admin",35000); 
		Integer emp3 = em.addEmployee("Santhosh","S","Developer",40000); 
		Integer emp4 = em.addEmployee("Gowda","MR","Developer",30000);
		em.listEmployee();
		
		
	}
	public void listEmployee() {
		Session s = sf.openSession();
		Transaction tx = null;
		
		try {
			tx = s.beginTransaction();
			Query<Employee> q = s.createQuery("form Employee");
			List<Employee> e = q.list();
			for(Employee emp:e)
			{
				System.out.println(emp.getFirstname()+"\t"+emp.getLastName()+"\t"+emp.getDesignation()+"\t"+emp.getSalary());
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	public Integer addEmployee(String fname,String lname,String designation,double sal) 
	{
		Session s = sf.openSession();
		Transaction tx = null;
		Integer employeeId = null;
		try
		{
			tx = s.beginTransaction();
			Employee emp = new Employee();
			emp.setFirstname(fname);
			emp.setLastName(lname);
			emp.setDesignation(designation);
			emp.setSalary(sal);
			employeeId = (Integer)s.save(emp);
			System.out.println("Employee Record is saved");
			tx.commit();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();

		}
		finally
		{
			s.close();
			//sf.close();
		}
		return employeeId;
	}
	

}

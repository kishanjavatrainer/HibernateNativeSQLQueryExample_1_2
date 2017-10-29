package com.infotech.client;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.infotech.entities.Person;
import com.infotech.util.HibernateUtil;

public class HibernateNativeSQLClientTest {

	public static void main(String[] args) {
		//getPersonInfoV1();
		//getPersonInfoV2();
		//getPersonInfoV3();
		//getPersonEntityV1();
		getPersonEntityV2();
	}
	
	//Hibernate native query selecting entities with explicit result set
	private static void getPersonEntityV2() {

		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			 List<Person> list = session.createNativeQuery("SELECT id, name, nickName, address, createdOn, version FROM Person").addEntity(Person.class).list();
			 list.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	// Hibernate native query selecting entities
	private static void getPersonEntityV1() {

		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			 List<Person> list = session.createNativeQuery("SELECT *FROM Person").addEntity(Person.class).list();
			 list.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	//Hibernate native query with result set selection that’s a partially explicit
	private static void getPersonInfoV3() {

		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			 List<Object[]> list = session.createNativeQuery("SELECT id,name FROM Person").
					 addScalar("id", LongType.INSTANCE).addScalar("name").list();
			for (Object[] objects : list) {
				Long id  =(Long)objects[0];
				String name=(String)objects[1];
				
				System.out.println("Person Id:"+id);
				System.out.println("Person Name:"+name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Hibernate native query with explicit result set selection
	private static void getPersonInfoV2() {

		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			 List<Object[]> list = session.createNativeQuery("SELECT id,name FROM Person").
					 addScalar("id", LongType.INSTANCE).addScalar("name", StringType.INSTANCE).list();
			for (Object[] objects : list) {
				Long id  =(Long)objects[0];
				String name=(String)objects[1];
				
				System.out.println("Person Id:"+id);
				System.out.println("Person Name:"+name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	//Hibernate native query selecting all columns
	private static void getPersonInfoV1() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Object[]> list = session.createNativeQuery("SELECT *FROM Person").list();
			for (Object[] objects : list) {
				BigInteger id  =(BigInteger)objects[0];
				String name=(String)objects[3];
				
				System.out.println("Person Id:"+id);
				System.out.println("Person Name:"+name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

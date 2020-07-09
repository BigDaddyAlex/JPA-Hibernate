package net.codejava.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookManager {
    protected SessionFactory sessionFactory;
 
    protected void setup() {
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    	        .configure() // configures settings from hibernate.cfg.xml
    	        .build();
    	try {
    	    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    	} catch (Exception e) {
    		e.printStackTrace();
    	    StandardServiceRegistryBuilder.destroy(registry);
    	}
    }
 
    protected void exit() {
    	sessionFactory.close();
    }
 
    protected void create() {
    	Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setPrice(32.59f);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
    }
   
    public static void main(String[] args) {
        // code to run the program
    	BookManager manager = new BookManager();
    	manager.setup();
    	manager.create();
    	manager.exit();
    }
}
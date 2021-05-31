package com.control;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.to.Player;

public class HibernateMain {

	public static void main(String[] args) {
		try {
        Configuration configuration =new Configuration().configure();//here we are setting up the configuration
        configuration.addAnnotatedClass(com.to.Player.class);//here we are adding the annotated class ;i.e player
        //using this line it will
        //help us to apply the configuration file that we have set up
        StandardServiceRegistryBuilder builder =
        		new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        
        //here we are creating the session factory and passing the configuration info
        SessionFactory factory =configuration.buildSessionFactory(builder.build());
        
        
        Session session =factory.openSession();
        Transaction transaction = session.beginTransaction();
        Player p1=new Player(1,"Badsha","India",25);//create, //update: BY changing in xml tag; create->update
        //Player p2=new Player(2,"Sayantan","India",26);
        session.save(p1);
        //session.save(p2);
        
        //read
        Player p= session.get(Player.class, 1);//we want to print the first record
        System.out.println("The details :" +p);
        
        
        //update the player details
        p.setPlayerName("Bishal");
        p.setAge(26);
        
        
        //delete
        session.delete(p);
        
        
        //code for player id will auto incremented :- 
        //for that we will add @GeneratedValue(strategy=GenerationType.IDENTITY) tag in player class 
        //and BY changing in the xml tag; update->create 
        Player p2=new Player("Badsha","India",25);
        Player p3=new Player("Syantan","India",26);
        session.save(p2);
        session.save(p3);
        
        transaction.commit();
        session.close();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}

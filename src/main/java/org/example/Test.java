package org.example;

import org.example.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Test {

    public static void main(String[] args) {
        StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Product p = new Product();
        p.setBrand("Adidas");
        p.setPrice(100.00);
        p.setStock(30);
        session.save(p);
        System.out.println("ID du produit : "+ p.getId());

        Product p1 = new Product();
        p1.setBrand("Nike");
        p1.setPrice(120.00);
        p1.setStock(75);
        session.save(p1);
        System.out.println("ID du produit : "+ p1.getId());

        Product p2 = new Product();
        p2.setBrand("New Balance");
        p2.setPrice(20.00);
        p2.setStock(175);
        session.save(p2);
        System.out.println("ID du produit : "+ p2.getId());

        Product p3 = new Product();
        p3.setBrand("Puma");
        p3.setPrice(70.00);
        p3.setStock(55);
        session.save(p3);
        System.out.println("ID du produit : "+ p3.getId());

        Product p4 = new Product();
        p4.setBrand("Reebok");
        p4.setPrice(70.00);
        p4.setStock(55);
        session.save(p4);
        System.out.println("ID du produit : "+ p4.getId());


        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}

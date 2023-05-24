package org.example;

import org.example.controller.IHM;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        IHM ihm = new IHM();
        ihm.start();

        StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        ProductService ps = new ProductService();
        ps.begin();

          //Exo1
//        ps.create(new Product("Toshiba", "kjfhj4e", new Date("2016/12/12"), 2010.00, 40));
//        ps.create(new Product("Sony", "sfgsdg5b", new Date("2015/10/10"), 2020.00, 50));
//        ps.create(new Product("Dell", "sfzfz7a", new Date("2018/10/01"), 1000.00, 20));
//        ps.create(new Product("Asus", "sdsdgf8y", new Date("2016/11/02"), 2200.00, 60));
//        ps.create(new Product("Hp", "dfdfze4r", new Date("2016/05/05"), 2500.00, 30));

        //Info produit id = 2 :

//        Product p = ps.findById(2);
//        System.out.println(p);

        //Supprimer le produit id = 3 :

//        ps.delete(ps.findById(3));

        //Modifier le produit id = 1 :

//        p = ps.findById(1);
//        if(p != null){
//            p.setBrand("Sony");
//            p.setRef("djghsj8h");
//            p.setDate(new Date("2019/05/05"));
//            p.setPrice(5000.00);
//            ps.update(p);
//        }

        //Exo2
        //récuperation de la liste des produits :
//        System.out.println("#####################");
//        System.out.println("liste des produits : ");
//        System.out.println("#####################");
//        Query<Product> productQuery = session.createQuery("from Product");
//        List<Product> products = productQuery.list();
//        for(Product prod: products){
//            System.out.println(prod.toString());
//        }
//        List<Product> products = ps.findAll();
//        for (Product pr: products) {
//            System.out.println(pr.getId()+" - "+ pr.getBrand()+", "+ pr.getRef());
//        }
//        System.out.println("############################");
//        System.out.println("produits avec filtre prix");
//        System.out.println("############################");
        //Récup avec filtre prod sup à 2200 euros
//        System.out.println("prod sup à 2200 euros : ");
//        Query<Product> productQuery1 = session.createQuery("from Product where price > 2200");
        //récupération d'une liste avec les produits dont le prix est > à 2200
//        List<Product> productList = productQuery1.list();
//        for(Product prod: productList){
//            System.out.println(prod.toString());
//        }
//        try{
//            List<Product> products1 = ps.filteredByPrice(4000);
//            for (Product pr: products1) {
//                System.out.println(pr.getId() + " - "+ pr.getBrand()+", " + pr.getRef());
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        System.out.println("############################");
//        System.out.println("produits avec filtre date");
//        System.out.println("############################");

        //récupération d'une liste de produits achetés entre deux dates :

//        System.out.println("liste de produits achetés entre deux dates : ");
//        try{
//            List<Product> produits2 = ps.filteredByDate(new Date("2015/01/20"),new Date("2016/12/23"));
//            for (Product pr: produits2) {
//                System.out.println(pr.getId() + " - "+ pr.getBrand()+", " + pr.getRef());
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.DATE, 90);
//        Date fromDate = null, toDate = null;
//        String fromDateStr = formatter.format(new Date());
//        String toDateStr = formatter.format(c.getTime());
//        try {
//            fromDate = formatter.parse(fromDateStr);
//            toDate = formatter.parse(toDateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


//        Query<Product> productQuery2 = session.createQuery("from Product as p WHERE Date BETWEEN :fromDate AND :toDate  ");
//        productQuery2.setParameter("fromDate", "2015-10-10");
//        productQuery2.setParameter("toDate", "2016-11-02");
//        List<Product> productList2 = productQuery2.list();
//        for(Product prod: productList2){
//            System.out.println(prod.getBrand());
//        }
//        System.out.println("###########");
//        session.getTransaction().commit();
//        session.close();
//        sessionFactory.close();

          //Exo3

//        System.out.println("############################");
//        System.out.println("produits filtrer par date par l'utilisateur");
//        System.out.println("############################");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Merci de saisir une date (dd-MM-yyyy) : ");
//        String d1 = scanner.nextLine();
//        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(d1);
//        System.out.println("Merci de saisir une date (dd-MM-yyyy) : ");
//        String d2 = scanner.nextLine();
//        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(d2);
//        try{
//            List<Product> produits2 = ps.filteredByDate(date1,date2);
//            for (Product pr: produits2) {
//                System.out.println(pr.getId() + " - "+ pr.getBrand()+", " + pr.getRef());
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        System.out.println("############################");
//        System.out.println("produits filtrer par stock max par l'utilisateur");
//        System.out.println("############################");
//        try {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Merci de saisir le stock maximum : ");
//            int max = scanner.nextInt();
//            List<Product> productList = ps.filteredByStockMax(max);
//            for (Product pr: productList) {
//                System.out.println(pr.getId() + " - "+ pr.getBrand()+", " + pr.getRef());
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }

      ps.end();
        }
    }



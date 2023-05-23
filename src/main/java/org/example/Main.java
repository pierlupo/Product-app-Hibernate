package org.example;

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

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        //Exo1
//        ProductService ps = new ProductService();
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

        //récuperation de la liste des produits :
        System.out.println("###########");
        System.out.println("liste des produits : ");
        Query<Product> productQuery = session.createQuery("from Product");
        List<Product> products = productQuery.list();
        for(Product prod: products){
            System.out.println(prod.toString());
        }

        System.out.println("###########");
        //Récup avec filtre prod sup à 2200 euros
        System.out.println("###########");
        System.out.println("prod sup à 2200 euros : ");
        Query<Product> productQuery1 = session.createQuery("from Product where price > 2200");
        System.out.println("###########");
        //récupération d'une liste avec les produits dont le prix est > à 2200
        List<Product> productList = productQuery1.list();
        for(Product prod: productList){
            System.out.println(prod.toString());
        }

        //récupération d'une liste de produits achetés entre deux dates :
        System.out.println("###########");
        System.out.println("liste de produits achetés entre deux dates : ");
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 90);
        Date fromDate = null, toDate = null;
        String fromDateStr = formatter.format(new Date());
        String toDateStr = formatter.format(c.getTime());
        try {
            fromDate = formatter.parse(fromDateStr);
            toDate = formatter.parse(toDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Query<Product> productQuery2 = session.createQuery("from Product as p WHERE Date BETWEEN :stDate AND :edDate  ");
        productQuery2.setParameter("stDate", fromDate);
        productQuery2.setParameter("edDate", toDate);
        List<Product> productList2 = productQuery2.list();
        for(Product prod: productList2){
            System.out.println(prod.getBrand());
        }
        System.out.println("###########");
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}

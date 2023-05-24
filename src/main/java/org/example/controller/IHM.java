package org.example.controller;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IHM {

    private static ProductService productService;
    private static Scanner scanner;

    public IHM() {
        scanner = new Scanner(System.in);
        productService = new ProductService();
    }


    public void start() throws ParseException {
        String choice;
        do {
            menu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> getAllProducts();
                case "2" -> getProductsFilteredByDate();
                case "3" -> getProductsFilteredByStock();
                case "4" -> getStockValue();
                case "5" -> getAvgPrice();
                case "6" -> addProduct();
                case "7" -> getProductById();
                case "8" -> deleteProductById();
                case "9" -> updateProduct();
                case "10" -> getBrandsProductList();
                case "11" -> productStockMin();
                case "12" -> productPriceMin();
            }
        } while (!choice.equals("0"));
        productService.end();
    }

    private void menu() {
        System.out.println("----------------");
        System.out.println("EXO HIBERNATE");
        System.out.println("----------------");
        System.out.println("******************");
        System.out.println("Choose an option :");
        System.out.println("******************");
        System.out.println(".1  -- Product List ");
        System.out.println(".2  -- Product filtered by date ");
        System.out.println(".3  -- Product filtered by stock ");
        System.out.println(".4  -- Value of the stock of a brand ");
        System.out.println(".5  -- Average price of products ");
        System.out.println(".6  -- Add a new product ");
        System.out.println(".7  -- Product list by brand ");
        System.out.println(".8  -- Delete a product (by id)");
        System.out.println(".9  -- Update a product ");
        System.out.println(".10 -- Product list by brand ");
        System.out.println(".11 -- Display the products with a bigger price than the one entered :");
        System.out.println(".12 -- Display the products with a stock below the value entered :");
        System.out.println(".0  -- Quit ");
    }

       private static void getAllProducts(){
       System.out.println("###################");
       System.out.println("List of products : ");
       System.out.println("###################");
           List<Product> products = productService.findAll();
        for (Product pr: products) {
            System.out.println(pr);
        }
    }

    private static void getProductsFilteredByDate() throws ParseException {
        System.out.println("################################################");
        System.out.println("Products filtered by date entered by the user : ");
        System.out.println("################################################");
        System.out.println("Merci de saisir une date (dd-MM-yyyy) : ");
        String d1 = scanner.nextLine();
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(d1);
        System.out.println("Merci de saisir une date (dd-MM-yyyy) : ");
        String d2 = scanner.nextLine();
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(d2);
        try {
            List<Product> produits2 = productService.filteredByDate(date1, date2);
            for (Product pr : produits2) {
                System.out.println(pr.getId() + " - " + pr.getBrand() + ", " + pr.getRef());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getProductsFilteredByStock() {
        System.out.println("#########################################################");
        System.out.println("Products filtered by the stock max entered by the user : ");
        System.out.println("#########################################################");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the stock max : ");
            int max = scanner.nextInt();
            List<Product> productList = productService.filteredByStockMax(max);
            for (Product pr : productList) {
                System.out.println(pr.getId() + " - " + pr.getBrand() + ", " + pr.getRef());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void getStockValue() {
        getAllProducts();
        System.out.println("##############");
        System.out.println("Stock Value : ");
        System.out.println("##############");

            System.out.println("Please enter the brand from which you want to know the stock's value : ");
            String brandChosen = scanner.nextLine();
        try {
            System.out.println("The value of the stock is : "+ productService.StockValue(brandChosen) + " euros ");
        }
        catch(Exception ex) {
            System.out.println("The value equals 0");
        }

    }

    private static void getAvgPrice() {
        System.out.println("############################");
        System.out.println("Average price of products : ");
        System.out.println("############################");
        try {
            System.out.println("The average price is : "+ productService.avgPrice() + " euros ");
        }
        catch(Exception ex) {
            System.out.println("error ");
        }

    }
    private static void getBrandsProductList() {
        //Example with more than one brand
        List<String> brands = new ArrayList<>();
        brands.add("HP");
        brands.add("samsung");
        List<Product> products = null;
        try {
            products = productService.filteredByBrand(brands);
            for (Product pr : products) {
                System.out.println(pr.getId());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProduct(){
        System.out.println("Please enter the brand : ");
        String brand = scanner.nextLine();
        System.out.println("Please enter the ref : ");
        String ref = scanner.nextLine();
        System.out.println("Please enter the date (dd/MM/yyyy) : ");
        String dateS = scanner.nextLine();
        System.out.println("Please enter the price : ");
        double price = scanner.nextDouble();
        System.out.println("Please enter the stock : ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateS);
            productService.create(new Product(brand,ref,date,price,stock));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void getProductById(){
        System.out.println("Please enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Product p = productService.findById(id);
        System.out.println(p);
    }
    private void deleteProductById(){
        System.out.println("Please enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Product p = productService.findById(id);
        productService.delete(p);
    }

    private void updateProduct(){
        System.out.println("Please enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Product p = productService.findById(id);
        System.out.println("Please enter the brand : ");
        String brand = scanner.nextLine();
        p.setBrand(brand);
        System.out.println("Please enter the ref : ");
        String ref = scanner.nextLine();
        p.setRef(ref);
        System.out.println("Please enter the date (dd/MM/yyyy) : ");
        String dateS = scanner.nextLine();
        System.out.println("Please enter the price : ");
        double price = scanner.nextDouble();
        p.setPrice(price);
        System.out.println("Please enter the stock : ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        p.setStock(stock);
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateS);
            p.setDate(date);
            productService.update(p);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void productStockMin() {
        try {
            System.out.println("Please enter the stock maximum : ");
            int max = scanner.nextInt();
            scanner.nextLine();
            List<Product> productList = productService.filteredByStockMax(max);
            for (Product pr : productList) {
                System.out.println(pr.getId() + " " + pr.getRef());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void productPriceMin(){
        try{
            System.out.println("Please enter the price minimum : ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            List<Product> products1 = productService.filteredByPrice(price);
            for (Product pr: products1) {
                System.out.println(pr.getId() + " " + pr.getRef());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

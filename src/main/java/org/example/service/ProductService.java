package org.example.service;

import org.example._interface.Repository;
import org.example.entity.Product;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ProductService extends BaseService implements Repository<Product> {

    public ProductService(){
        super();
    }
    @Override
    public boolean create(Product element) {
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(element);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Product element) {
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(element);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Product element) {
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(element);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        session = sessionFactory.openSession();
        product = (Product) session.get(Product.class, id);
        session.close();
        return product;
    }

    public List<Product> findAll(){
        List<Product> productList = null;
        session = sessionFactory.openSession();
        Query<Product> productQuery = session.createQuery("from Product");
        productList = productQuery.list();
        session.close();
        return productList;
    }

    public List<Product> filteredByPrice(double min)throws Exception {
        if (min >= 0) {
            session = sessionFactory.openSession();
            Query<Product> productQuery = session.createQuery("from Product where price > :min");
            productQuery.setParameter("min", min);
            session.close();
            return productQuery.list();
        }
        throw new Exception("error value");
    }

    public List<Product> filteredByDate(Date min, Date max) throws Exception{
        if(min.before(max)){
            session = sessionFactory.openSession();
            Query<Product> productQuery = session.createQuery("from Product where Date >= :min and Date <= :max");
            productQuery.setParameter("min", min);
            productQuery.setParameter("max", max);
            session.close();
            return productQuery.list();
        }
        throw new Exception("error date");
    }

    public List<Product> filteredByStockMax(int max)throws Exception {
        if (max >= 0) {
            session = sessionFactory.openSession();
            Query<Product> productQuery = session.createQuery("from Product where Stock < :max");
            productQuery.setParameter("max", max);
             session.close();
            return productQuery.list();
        }
        throw new Exception("error value");
    }

    public double StockValue(String brand) {
            session = sessionFactory.openSession();
            Query<Double> query = session.createQuery("select sum(price)from Product where brand =:brand ");
            query.setParameter("brand", brand);
            double value = query.uniqueResult();
             session.close();
            return value;

    }


    public double avgPrice() {
            session = sessionFactory.openSession();
            Query<Double> query =  session.createQuery("select avg(price)from Product ");
            double average = query.uniqueResult();
            session.close();
            return average;
        }


    public List<Product> filteredByBrand(List<String> brands)throws Exception {
        if (brands.size() > 0) {
            session = sessionFactory.openSession();
            Query<Product> productQuery = session.createQuery("from Product where brand in :brands");
            productQuery.setParameter("brands", brands);
            List<Product> productList = productQuery.list();
            session.getTransaction().commit();
            session.close();
            return productList;
        }
        throw new Exception("error");
    }

    public boolean deleteByBrand(String brand)throws Exception{
        if(brand != null) {
            session = sessionFactory.openSession();
            Query query = session.createQuery("select sum(Stock)from Product where brand = :brand ");
            query.setParameter("brand", brand);
            session.getTransaction().begin();
            int nbrows = query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            return nbrows > 0;
        }
        throw new Exception("error");
    }

    public void begin(){

        session = sessionFactory.openSession();

    }

    public void end(){
        //session.close();
        sessionFactory.close();
    }
}
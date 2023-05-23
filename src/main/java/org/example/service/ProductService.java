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
        session = sessionFactory.openSession();
        Query<Product> productQuery = session.createQuery("from Product");
        session.close();
        return productQuery.list();
    }

    public List<Product> filterByPrice(double min)throws Exception {
        if (min >= 0) {
            session = sessionFactory.openSession();
            Query<Product> productQuery = session.createQuery("from Product where price > :min");
            productQuery.setParameter("min", min);
            session.close();
            return productQuery.list();
        }
        throw new Exception("error value");
    }

    public List<Product> filterByDate(Date min, Date max) throws Exception{
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
}
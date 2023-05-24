package org.example.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String brand;

    private String ref;

    @Temporal(TemporalType.DATE)
    private Date Date;

    private Double price;

    private int Stock;

    public Product() {
    }

    public Product(String brand, Double price, int stock) {
        this.brand = brand;
        this.price = price;
        Stock = stock;
    }

    public Product(String brand, String ref, java.util.Date date, Double price, int stock) {
        this.brand = brand;
        this.ref = ref;
        Date = date;
        this.price = price;
        Stock = stock;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", ref='" + ref + '\'' +
                ", Date=" + Date +
                ", price=" + price +
                ", Stock=" + Stock +
                '}';
    }
}

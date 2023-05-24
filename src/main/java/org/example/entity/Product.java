package org.example.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    private double price;

    private int Stock;

    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

    public Product() {
    }

    public Product(String brand, String ref, Date date, double price, int stock, List<Image> images, List<Comment> comments) {
        this.brand = brand;
        this.ref = ref;
        Date = date;
        this.price = price;
        Stock = stock;
        this.images = images;
        this.comments = comments;
    }

//    public Product(String brand, String ref, Date date, double price, int stock, String imagesStr, String commentsStr) {
//        this.brand = brand;
//        this.ref = ref;
//        Date = date;
//        this.price = price;
//        Stock = stock;
//        this.images = images;
//        this.comments = comments;
//        Image image = new Image();
//        image.setUri(imagesStr);
//        this.images.add(image);
//    }

    public Product(String brand, double price, int stock) {
        this.brand = brand;
        this.price = price;
        Stock = stock;
    }

    public Product(String brand, String ref, Date date, double price, int stock) {
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

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public void addImage(Image image){
        if(image != null){
            this.images =images;
        }
    }

    public void addComment(Comment comment){
        if(comment != null){
        this.comments =comments;
}
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

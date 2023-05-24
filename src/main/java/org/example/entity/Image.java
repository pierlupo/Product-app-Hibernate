package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String uri;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="produit_id")
    private Product product;

    public Image() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                '}';
    }
}

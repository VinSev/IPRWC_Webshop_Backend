package nl.hsleiden.IPRWC_Webshop_Backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Size(max = 50)
    private String name;
    @NotBlank
    @Size(max = 1000)
    private String description;
    @NotBlank
    private float price;
    @NotBlank
    private String imageLink;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getImageLink() {
        return imageLink;
    }
}
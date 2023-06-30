package org.lessons.pizzeria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pizze")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Il nome della pizza è obbligatorio")
    @Size(max = 100, message = "Il nome della pizza non può superare i 100 caratteri")
    @Column(nullable = false, unique = true)
    private String name;
    @Lob
    @NotBlank(message = "La descrizione della pizza è obbligatoria")
    @Size(max = 250, message = "La descrizione della pizza non può superare i 250 caratteri")
    private String description;
    @NotBlank(message = "L''URL della pizza è obbligatorio")
    private String photo;
    @NotNull(message = "Il prezzo della pizza è obbligatorio")
    @Min(value = 0, message = "Il prezzo della pizza non può essere inferiore a 0")
    @Column(nullable = false)
    private BigDecimal price;
    @OneToMany(mappedBy = "pizza")
    private List<SpecialOffer> specialOffers; //relazione offerte

    @ManyToMany
    @JoinTable(
            name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
package org.lessons.pizzeria.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.lessons.pizzeria.model.Ingredient;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public class PizzaForm {

    private Integer id;
    @NotBlank(message = "Il nome della pizza è obbligatorio")
    @Size(max = 100, message = "Il nome della pizza non può superare i 100 caratteri")
    private String name;

    @NotBlank(message = "La descrizione della pizza è obbligatoria")
    @Size(max = 250, message = "La descrizione della pizza non può superare i 250 caratteri")
    private String description;
    @NotBlank(message = "L''URL della pizza è obbligatorio")
    private String photo;

    //Upload Immagine nel db
    private MultipartFile picFile;
    @NotNull(message = "Il prezzo della pizza è obbligatorio")
    @Min(value = 0, message = "Il prezzo della pizza non può essere inferiore a 0")
    private BigDecimal price;

    private List<Ingredient> ingredients;

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

    public MultipartFile getPicFile() {
        return picFile;
    }

    public void setPicFile(MultipartFile picFile) {
        this.picFile = picFile;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}

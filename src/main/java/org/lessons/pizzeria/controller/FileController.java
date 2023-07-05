package org.lessons.pizzeria.controller;

import org.lessons.pizzeria.exceptions.PizzaNotFoundExceptions;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    PizzaService pizzaService;

    //metodo x cercare la pizza e dargli la pic
    @RequestMapping("/pic/{pizzaId}")
    public ResponseEntity<byte[]> getPizzaPic(@PathVariable Integer pizzaId) {
        try {
            Pizza pizza = pizzaService.getById(pizzaId);
            MediaType mediaType = MediaType.IMAGE_JPEG;
            return ResponseEntity.ok().contentType(mediaType).body(pizza.getPicFile());
        } catch (PizzaNotFoundExceptions e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

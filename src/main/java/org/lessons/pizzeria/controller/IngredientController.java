package org.lessons.pizzeria.controller;

import org.lessons.pizzeria.model.Ingredient;
import org.lessons.pizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;

    //gestisce index ingredients e form
    @GetMapping
    public String index(Model model) {
        //tutti ingredients dal db
        List<Ingredient> ingredientList = ingredientRepository.findAll();
        //passo al model un attr ingredients con tutti gli ingredienti
        model.addAttribute("ingredients", ingredientList);
        //passo al model un attributo per mappare il form su oggetto di tipo ingredient
        model.addAttribute("ingredientObj", new Ingredient());
        return "/ingredients/index";
    }

    @PostMapping("/save")
    public String save() {

        return "/ingredients/index";
    }
}

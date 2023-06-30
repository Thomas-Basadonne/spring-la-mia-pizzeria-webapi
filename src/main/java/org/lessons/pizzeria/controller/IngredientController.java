package org.lessons.pizzeria.controller;

import jakarta.validation.Valid;
import org.lessons.pizzeria.model.Ingredient;
import org.lessons.pizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;

    //gestisce index ingredients e form
    @GetMapping
    public String index(Model model, @RequestParam("edit") Optional<Integer> ingredientId) {
        //tutti ingredients dal db
        List<Ingredient> ingredientList = ingredientRepository.findAll();
        //passo al model un attr ingredients con tutti gli ingredienti
        model.addAttribute("ingredients", ingredientList);
        //passo al model un attributo per mappare il form su oggetto di tipo ingredient
        Ingredient ingredientObj;
        // se ho il param ingredientId lo cerco sul db
        if (ingredientId.isPresent()) {
            Optional<Ingredient> ingredientDb = ingredientRepository.findById(ingredientId.get());
            // se cè valorizzo ingredientObj con i dati del db
            if (ingredientDb.isPresent()) {
                ingredientObj = ingredientDb.get();
            } else {
                //altrimenti nuovo
                ingredientObj = new Ingredient();
            }
        } else {
            ingredientObj = new Ingredient();
        }
        model.addAttribute("ingredientObj", ingredientObj);
        return "/ingredients/index";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("ingredientObj") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "/ingredients/index";
        }
        // Salvataggio dell'ingrediente nel repository
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }
}

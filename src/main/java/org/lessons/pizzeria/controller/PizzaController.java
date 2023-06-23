package org.lessons.pizzeria.controller;

import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    // dipende dalla repository
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String list(Model model) {
        List<Pizza> pizze = pizzaRepository.findAll();
        model.addAttribute("pizzeList", pizze);
        if (pizze.isEmpty()) {
            model.addAttribute("noPizzeMessage", "Non ci sono pizze disponibili al momento.");
        }
        return "/pizza/index";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer pizzaId, Model model) {
        //cerca pizza con quell'id
        Optional<Pizza> result = pizzaRepository.findById(pizzaId);

        if (result.isPresent()) {
            //passa pizza a view
            model.addAttribute("pizza", result.get());
            return "/pizza/detail";
        } else {
            //error 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con ID " + pizzaId + "non trovata");
        }

    }

}

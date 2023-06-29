package org.lessons.pizzeria.controller;

import jakarta.validation.Valid;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.model.SpecialOffer;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.lessons.pizzeria.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class SpecialOfferController {
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    SpecialOfferRepository specialOfferRepository;

    @GetMapping("/create")
    public String create(@RequestParam("pizzaId") Integer pizzaId, Model model) {
        //creo nuova offerta
        SpecialOffer specialOffer = new SpecialOffer();
        //Data di initio con la data di oggi
        specialOffer.setStartDate(LocalDate.now());
        // carico la pizza passata come param
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);

        if (pizza.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id " + pizzaId + " non trovata");
        }

        specialOffer.setPizza(pizza.get());

        // aggiungo al model attributo con offer
        model.addAttribute("specialOffer", specialOffer);
        return "/offers/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult) {
        //valido
        if (bindingResult.hasErrors()) {
            //se ci sono errori ricarico form
            return "/offers/form";
        }
        // se no errori salvo
        specialOfferRepository.save(formSpecialOffer);
        //redirect al dettaglio pizza
        return "redirect:/pizze/" + formSpecialOffer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        //cerco offer su db
        Optional<SpecialOffer> specialOffer = specialOfferRepository.findById(id);
        if (specialOffer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        //lo passo al model
        model.addAttribute("specialOffer", specialOffer.get());
        return "/offers/form";
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult) {
        Optional<SpecialOffer> specialOfferToEdit = specialOfferRepository.findById(id);
        if (specialOfferToEdit.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        formSpecialOffer.setId(id);
        specialOfferRepository.save(formSpecialOffer);
        return "redirect:/pizze/" + formSpecialOffer.getPizza().getId();
    }


}

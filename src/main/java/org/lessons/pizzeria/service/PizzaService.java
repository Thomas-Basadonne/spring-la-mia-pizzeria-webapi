package org.lessons.pizzeria.service;

import org.lessons.pizzeria.exceptions.InvalidPizzaNameExceptions;
import org.lessons.pizzeria.exceptions.PizzaNotFoundExceptions;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    //metodo che restituisce index pizze filtrate o non in base al param
    public List<Pizza> gettAll(Optional<String> keywordOpt) {
        if (keywordOpt.isEmpty()) {
            return pizzaRepository.findAll();
        } else return pizzaRepository.findByName(keywordOpt.get());
    }

    //libro preso per id o restituisce eccezione
    public Pizza getById(Integer id) throws PizzaNotFoundExceptions {
        Optional<Pizza> pizzaOpt = pizzaRepository.findById(id);
        if (pizzaOpt.isPresent()) {
            return pizzaOpt.get();
        } else {
            throw new PizzaNotFoundExceptions("Pizza con id " + id + " non trovata");
        }
    }

    //salva una pizza a partire da quella passata come param
    public Pizza create(Pizza pizza) throws InvalidPizzaNameExceptions {
        //valido nome unique
        if (!isUniqueName(pizza)) {
            throw new InvalidPizzaNameExceptions(pizza.getName());
        }
        //creo nuova pizza da salvare
        Pizza pizzaToPersist = new Pizza();
        //copio tt i campi
        pizzaToPersist.setName(pizza.getName());
        pizzaToPersist.setDescription(pizza.getDescription());
        pizzaToPersist.setPhoto(pizza.getPhoto());
        pizzaToPersist.setPrice(pizza.getPrice());
        pizzaToPersist.setIngredients(pizza.getIngredients());
        //salvo la pizza
        return pizzaRepository.save(pizzaToPersist);
    }

    //metodo custom x controllo name unique nel db
    private boolean isUniqueName(Pizza pizzaForm) {
        List<Pizza> result = pizzaRepository.findByName(pizzaForm.getName());
        return result.isEmpty(); // Restituisce true se il nome non esiste, false se esiste già
    }
}

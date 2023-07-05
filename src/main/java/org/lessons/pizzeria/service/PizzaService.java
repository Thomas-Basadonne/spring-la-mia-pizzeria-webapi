package org.lessons.pizzeria.service;

import org.lessons.pizzeria.dto.PizzaForm;
import org.lessons.pizzeria.exceptions.InvalidPizzaNameExceptions;
import org.lessons.pizzeria.exceptions.PizzaNotFoundExceptions;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        pizzaToPersist.setPicFile(pizza.getPicFile());
        //salvo la pizza
        return pizzaRepository.save(pizzaToPersist);
    }

    //metodo che crea un new pizza a partire da PizzaForm
    public Pizza create(PizzaForm pizzaForm) throws InvalidPizzaNameExceptions {
        //converto PizzaForm in Pizza
        Pizza pizza = mapPizzaFormToPizza(pizzaForm);
        //lo salvo nel db
        return create(pizza);
    }

    //metodo x creare pizzaForm a partire da id Pizza salvato sul db
    public PizzaForm getPizzaFormById(Integer id) throws PizzaNotFoundExceptions {
        Pizza pizza = getById(id);
        return mapPizzaToPizzaForm(pizza);

    }

    public Pizza update(PizzaForm pizzaForm) throws PizzaNotFoundExceptions {
        // converto Pizzaform in Pizza
        Pizza pizza = mapPizzaFormToPizza(pizzaForm);
        //cerco book su db
        Pizza pizzaDb = getById(pizza.getId());
        //valido name
        if (!pizza.getName().equals(pizzaDb.getName()) && !isUniqueName(pizza)) {
            throw new InvalidPizzaNameExceptions(pizzaDb.getName());
        }
        pizzaDb.setName(pizza.getName());
        pizzaDb.setDescription(pizza.getDescription());
        pizzaDb.setPrice(pizza.getPrice());
        pizzaDb.setPhoto(pizza.getPhoto());
        pizzaDb.setPicFile(pizza.getPicFile());
        pizzaDb.setIngredients(pizza.getIngredients());
        // salvo la pizza
        return pizzaRepository.save(pizzaDb);
    }

    //metodo custom x controllo name unique nel db
    private boolean isUniqueName(Pizza pizzaForm) {
        List<Pizza> result = pizzaRepository.findByName(pizzaForm.getName());
        return result.isEmpty(); // Restituisce true se il nome non esiste, false se esiste già
    }

    // metodo x convertire un PizzaForm in una Pizza
    private Pizza mapPizzaFormToPizza(PizzaForm pizzaForm) {
        //creo new Pizza vuota
        // creo un nuovo PizzaForm vuoto
        Pizza pizza = new Pizza();
        // copio i campi con corrispondenza esatta
        pizza.setId(pizzaForm.getId());
        pizza.setName(pizzaForm.getName());
        pizza.setDescription(pizzaForm.getDescription());
        pizza.setPhoto(pizzaForm.getPhoto());
        pizza.setPrice(pizzaForm.getPrice());
        pizza.setIngredients(pizzaForm.getIngredients());
        // converto la pic
        pizza.setPicFile(multipartFileToByteArray(pizzaForm.getPicFile()));
        return pizza;
    }

    private PizzaForm mapPizzaToPizzaForm(Pizza pizza) {
        //creo new Pizza vuota
        // creo un nuovo PizzaForm vuoto
        PizzaForm pizzaForm = new PizzaForm();
        // copio i campi con corrispondenza esatta
        pizzaForm.setId(pizza.getId());
        pizzaForm.setName(pizza.getName());
        pizzaForm.setDescription(pizza.getDescription());
        pizzaForm.setPhoto(pizza.getPhoto());
        pizzaForm.setPrice(pizza.getPrice());
        pizzaForm.setIngredients(pizza.getIngredients());

        return pizzaForm;
    }

    private byte[] multipartFileToByteArray(MultipartFile mpf) {
        byte[] bytes = null;
        if (mpf != null && !mpf.isEmpty()) {
            try {
                bytes = mpf.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}

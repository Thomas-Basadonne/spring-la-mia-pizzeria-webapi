package org.lessons.pizzeria.api;

import jakarta.validation.Valid;
import org.lessons.pizzeria.exceptions.InvalidPizzaNameExceptions;
import org.lessons.pizzeria.exceptions.PizzaNotFoundExceptions;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.lessons.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/pizze")
public class PizzaRestController {
//controller risorsa crud

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    //servizio per lista pizze
    @GetMapping
    public List<Pizza> index(@RequestParam Optional<String> keyword) {
        //return pizzaRepository.findAll();
        //utilizzo pizza service x ricerca
        return pizzaService.gettAll(keyword);
    }

    //servizio detail pizza
    @GetMapping("/{id}")
    public Pizza get(@PathVariable Integer id) {
        //cerco pizza by id sul db
        try {
            return pizzaService.getById(id);
        } catch (PizzaNotFoundExceptions e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //servizio x create pizza, arriva come JSON nel request body
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        try {
            return pizzaService.create(pizza);
        } catch (InvalidPizzaNameExceptions e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //servizio x cancellare pizza
    @DeleteMapping
    public void delete(@PathVariable Integer id) {
        pizzaRepository.deleteById(id);
    }

    //servizio x edit pizza
    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
        pizza.setId(id);
        return pizzaRepository.save(pizza);
    }

    //servizio paginazione
    @GetMapping("/page")
    public Page<Pizza> page(/*@RequestParam(defaultValue = "3") Integer size, @RequestParam(defaultValue = "0") Integer page */ Pageable pageable) {
        //creo pageble da size e page
        //Pageable pageable = PageRequest.of(page, size);
        //restituisco page estratta dal findAll
        return pizzaRepository.findAll(pageable);
    }
}

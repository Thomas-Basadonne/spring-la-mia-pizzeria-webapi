package org.lessons.pizzeria.repository;

import org.lessons.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    //ricerca pizza x nome

    List<Pizza> findByName(String name);
}

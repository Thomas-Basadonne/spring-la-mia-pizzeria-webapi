package org.lessons.pizzeria.exceptions;

public class PizzaNotFoundExceptions extends RuntimeException {
    public PizzaNotFoundExceptions(String message) {
        super(message);
    }
}

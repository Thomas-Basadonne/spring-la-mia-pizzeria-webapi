package org.lessons.pizzeria.exceptions;

public class InvalidPizzaNameExceptions extends RuntimeException {
    public InvalidPizzaNameExceptions(String message) {
        super(message);
    }
}

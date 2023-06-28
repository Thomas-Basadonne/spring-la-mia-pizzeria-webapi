package org.lessons.pizzeria.controller;

import jakarta.validation.Valid;
import org.lessons.pizzeria.messages.AlertMessage;
import org.lessons.pizzeria.messages.AlertMessageType;
import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    // dipende dalla repository
    @Autowired
    private PizzaRepository pizzaRepository;

   /* @GetMapping
    public String list(Model model) {
        List<Pizza> pizze = pizzaRepository.findAll();
        model.addAttribute("pizzeList", pizze);
        if (pizze.isEmpty()) {
            model.addAttribute("noPizzeMessage", "Non ci sono pizze disponibili al momento.");
        }
        return "/pizza/index";
    } */

    @GetMapping
    public String list(@RequestParam(name = "keyword", required = false) String searchString, Model model) {
        List<Pizza> pizze;
        // se non ho il param fai la ricerca all
        if (searchString == null || searchString.isBlank()) {
            pizze = pizzaRepository.findAll();
        } else {
            // altrimenti ricerca con param
            pizze = pizzaRepository.findByName(searchString);
        }
        model.addAttribute("pizzeList", pizze);
        model.addAttribute("searchInput", searchString == null ? "" : searchString);
        if (pizze.isEmpty()) {
            model.addAttribute("noPizzeMessage", "Non ci sono pizze disponibili al momento.");
        }
        return "/pizza/index";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer pizzaId, Model model) {
        //cerca pizza con quell'id
        Pizza pizza = getPizzaById(pizzaId);
        //passa pizza a view
        model.addAttribute("pizza", pizza);
        return "/pizza/detail";


    }

    //Controller che gestisce la pagina create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        //return "/pizza/create";
        return "/pizza/form"; // template unico create/update
    }

    //Controller che gestisce il form della pagina create
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //dati pizza sono nell'oggetto pizzaForm
        // controllo dati in validazione
        if (!isUniqueName(pizzaForm)) {
            //aggiungo a mano errore in BindingResult
            bindingResult.addError(new FieldError("pizza", "name", pizzaForm.getName(), false, null, null, "Il nome deve essere unico"));
        }
        if (bindingResult.hasErrors()) {
            //ci sono errori!
            //rigenero form con dati pizza pre caricati
            return "/pizza/create";
        }
        //persisto pizzaForm, metodo save fa un create sql se non esiste oggetto con stessa pk se no update
        pizzaRepository.save(pizzaForm);
        //redirect home se va tutto bene
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "Pizza aggiunta alla lista!"));
        return "redirect:/pizze";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Pizza pizza = getPizzaById(id);
        //recupero dati della pizza e la aggiungo al model
        model.addAttribute("pizza", pizza);
        return "/pizza/form"; // template unico create/update
    }

    @PostMapping("edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Pizza pizzaToEdit = getPizzaById(id);//pizza prima di essere modificata
        //valido pizzaForm
        //se i nomi sono diversi dai errore
        if (!pizzaToEdit.getName().equals(pizzaForm.getName()) && !isUniqueName(pizzaForm)) {
            bindingResult.addError(new FieldError("pizza", "name", pizzaForm.getName(), false, null, null, "Il nome deve essere unico"));
        }
        if (bindingResult.hasErrors()) {
            return "pizza/form";
        }
        //trasferisco dati che non sono presenti nel form x non perderli
        pizzaForm.setId(pizzaToEdit.getId());
        //edit e salvataggio dati
        pizzaRepository.save(pizzaForm);
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "Pizza modificata con successo!"));
        return "redirect:/pizze";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        //esiste il questa pizza?
        Pizza pizzaToDelete = getPizzaById(id);
        //lo cancelliamo
        pizzaRepository.delete(pizzaToDelete);
        // add success message
        redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "Pizza " + pizzaToDelete.getName() + " cancellata!"));
        //redirect index
        return "redirect:/pizze";
    }

    //metodo custom x controllo name unique nel db
    private boolean isUniqueName(Pizza pizzaForm) {
        List<Pizza> result = pizzaRepository.findByName(pizzaForm.getName());
        return result.isEmpty(); // Restituisce true se il nome non esiste, false se esiste già
    }

    //metodo custom x selezionare pizza da db o tirare un eccezione
    private Pizza getPizzaById(Integer id) {
        //verifico se esite pizza con quel id
        Optional<Pizza> result = pizzaRepository.findById(id);
        // if not => 404
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con id " + id + " non trovata");
        }
        return result.get();
    }
}

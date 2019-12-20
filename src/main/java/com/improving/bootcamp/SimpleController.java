package com.improving.bootcamp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Random;

@Controller
public class SimpleController {
    private BookRepository bookRepository;

    public SimpleController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }

    @GetMapping("/home") //calls to that url will end up here...GET is the default method for @RequestMethod
    public String home(ModelMap model, Principal principal) { //spring will refer to this variable as message because that is whats listed in param parenthesis
        setCommonAttributes(model, principal);
        return "index";
    }

    @PostMapping("/add") //changing this from RequestMapping to PostMapping FORCES uses to use the form...cannot add books by typing out the whoel URL with title and author
    public String add(ModelMap model, @Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Principal principal) { //marking book as @ModelAttribute allows us to use any field in Book class as request parameters
        if(bindingResult.hasErrors()){
            setCommonAttributes(model, principal);
            return "index";//resolving to the index view...if there are errors, do NOT add the book and go back to home page
        }
        bookRepository.add(book);
        return "redirect:/home"; //after this redirects back to HOME page...so we don't get "confirm resubmission" popup when refreshing (which we would get if stayed on add page)
    }

    private String getMessage(){
        boolean morning = new Random().nextBoolean();
        return (morning) ? "Hello" : "GoodBye"; //if morning is true, get hello, if false get goodbye
    }

    @GetMapping("/bad")
    public String badRequest() {
        throw new RuntimeException("Something went wrong");
    }

    @GetMapping("/teapot")
    public String teapot() {
        throw new TeapotException();
    }

    @GetMapping("/book")
    public String book( ModelMap model, @RequestParam Integer id) {
        model.put("book", bookRepository.getBook(id));
        return "book";
    }

    private void setCommonAttributes(ModelMap model, Principal principal) {
        model.put("showForm", (principal != null && principal.getName().equalsIgnoreCase("admin")));
        model.put("book", new Book("", "")); //need a book attribute to start w/cuz the JSP file is looking for "book" attribute in order to map all fields correctly from form input to Java object
        model.put("name", (principal != null) ? principal.getName() : "Kanye West");
        model.put("message", getMessage());
        model.put("books", bookRepository.getBooks());
    }


}

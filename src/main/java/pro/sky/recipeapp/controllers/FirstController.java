package pro.sky.recipeapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public String helloWorld() {
        return ("Приложение запущено");
    }

    @GetMapping("/info")
    public String page() {
        return "Info:\n" +
                "Name: heykamikaze\n"+
                "App: REcipeAppApplication\n" +
                "Date: 03/17/2023\n" +
                "Tools: Java, Spring\n";

    }
}

package gr.athtech.toDoAppWithLogin.controller;

import gr.athtech.toDoAppWithLogin.ToDoAppWithLoginApplication;
import gr.athtech.toDoAppWithLogin.service.InitiationService;
import gr.athtech.toDoAppWithLogin.service.SessionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    SessionService sessionService;
    InitiationService initiationService;


    public MainController(SessionService sessionService, InitiationService initiationService) {
        this.sessionService = sessionService;
        this.initiationService = initiationService;
    }

    @GetMapping("/login")
    public String login() {
        boolean loggedIn;

        if(ToDoAppWithLoginApplication.isFirstTime()) {
            initiationService.initiateDatabase();
            ToDoAppWithLoginApplication.setFirstTime(false);
        }

        loggedIn = sessionService.isUserLoggedIn(SecurityContextHolder.getContext().getAuthentication());
        if (loggedIn) {
            /* The user is logged in */
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @GetMapping("/")
    public String main() {
            return "main";
    }
}

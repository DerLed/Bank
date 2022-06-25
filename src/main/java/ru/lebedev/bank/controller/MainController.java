package ru.lebedev.bank.controller;

//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping()
    public String main(){
        return "main";
    }

//    @GetMapping("/account")
//    public String account(Authentication authentication, Model model){
//        UserDetails user = (UserDetails) authentication.getPrincipal();
//        model.addAttribute("name", user.getUsername());
//        return "account";
//    }
}

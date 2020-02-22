package kganesh1795.security_tutorial.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.ui.Model;

@Controller
@RequestMapping(path={"/home", "/"})
public class HomeController {
    @GetMapping
    public String getHome(Model model, @AuthenticationPrincipal(errorOnInvalidType=true) UserDetails user) {
        //System.out.println(user.getUsername());
        model.addAttribute("user", user);
        return "home";
    }
}
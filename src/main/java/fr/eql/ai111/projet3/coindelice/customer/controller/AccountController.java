package fr.eql.ai111.projet3.coindelice.customer.controller;


import fr.eql.ai111.projet3.coindelice.library.model.Customer;
import fr.eql.ai111.projet3.coindelice.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
public class AccountController {

    @Autowired
    private CustomerService customerService;




    @GetMapping("/account")
    public String accountHome(Model model , Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        model.addAttribute("customer", customer);

        return "account";
    }

    @RequestMapping(value = "/update-infor", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCustomer(
            @ModelAttribute("customer") Customer customer,
            Model model,
            RedirectAttributes redirectAttributes,
            Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Customer customerSaved = customerService.saveInfor(customer);

        redirectAttributes.addFlashAttribute("customer", customerSaved);

        return "redirect:/account";
    }
}

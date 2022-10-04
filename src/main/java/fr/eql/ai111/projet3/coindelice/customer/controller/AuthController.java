package fr.eql.ai111.projet3.coindelice.customer.controller;

import fr.eql.ai111.projet3.coindelice.library.dto.CustomerDto;
import fr.eql.ai111.projet3.coindelice.library.model.Customer;
import fr.eql.ai111.projet3.coindelice.library.service.CustomerService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "register";
    }


    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                  BindingResult result,
                                  Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", customerDto);
                return "register";
            }
            Customer customer = customerService.findByUsername(customerDto.getUsername());
            if(customer != null){
                model.addAttribute("username", "Email enregistré avec succés");
                model.addAttribute("customerDto",customerDto);
                return "register";
            }
            if(customerDto.getPassword().equals(customerDto.getRepeatPassword())){
                customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
                customerService.save(customerDto);
                model.addAttribute("success", "Succés");
                return "register";
            }else{
                model.addAttribute("password", "Les mots des passes sont différents");
                model.addAttribute("customerDto",customerDto);
                return "register";
            }
        }catch (Exception e){
            model.addAttribute("error", "Problème de serveur");
            model.addAttribute("customerDto",customerDto);
        }
        return "register";
    }

}

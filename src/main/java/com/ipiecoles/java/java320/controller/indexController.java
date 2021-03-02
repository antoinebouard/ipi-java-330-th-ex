package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.model.Employe;
import com.ipiecoles.java.java320.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @Autowired
    private EmployeService employeService;

    @GetMapping(value= "/")
    public String index(final ModelMap model) {
        model.put("nom", "ipi");
        model.put("nbemployes", employeService.countAllEmploye());
        return "accueil";
    }


}

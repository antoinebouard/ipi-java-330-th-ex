package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.model.Employe;
import com.ipiecoles.java.java320.model.Manager;
import com.ipiecoles.java.java320.repository.CommercialRepository;
import com.ipiecoles.java.java320.service.EmployeService;
import com.ipiecoles.java.java320.service.ManagerService;
import com.ipiecoles.java.java320.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employes")
public class employeController {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private TechnicienService technicienService;

    @GetMapping(value= "/{id}")
    public String getEmployeById(@PathVariable("id") Long id, final ModelMap model){
        Employe employe = employeService.findById(id);
        model.put("employe", employeService.findById(id));
        return "detail";
    }

    @GetMapping(value = "", params = "matricule")
    public String searchEmployeByMatricule(@RequestParam("matricule") String matricule, final ModelMap model) {
        model.put("employe", employeService.findMyMatricule(matricule));
        return "detail";
    }

    @GetMapping(value = "")
    public String listEmployes(final ModelMap model,
                               @RequestParam(defaultValue = "0") Integer page,
                               @RequestParam(defaultValue = "10") Integer size,
                               @RequestParam(defaultValue = "ASC") String sortDirection,
                               @RequestParam(defaultValue = "matricule") String sortProperty) {
        Page<Employe> employes = employeService.findAllEmployes(page, size, sortDirection ,sortProperty);
        model.put("employes", employes);
        model.addAttribute("start", page * size + 1);
        model.addAttribute("end", (page + 1) * size);
        model.addAttribute("pageNumber", page + 1);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("previousPage", page - 1);
        model.addAttribute("page", "employesListe");
        model.addAttribute("fragment", "employesListe");
        return "liste";
    }
}

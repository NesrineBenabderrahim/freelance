package tn.essat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tn.essat.model.Project;
import tn.essat.model.Proposition;
import tn.essat.service.ProjectService;
import tn.essat.service.PropositionService;

import java.util.List;

@Controller
public class PropositionController {

    @Autowired
    private PropositionService propositionService;
    @Autowired
    private ProjectService projServ;
    @GetMapping("/listProposition")
    public String showPropositions(Model model, @RequestParam(name = "clientId", required = false) Integer clientId) {
        List<Proposition> propositions = propositionService.findAll();
        model.addAttribute("propositions", propositions);
        model.addAttribute("clientId", clientId); 
        return "proposition-list";
    }

    @GetMapping("/addPropositionForm/{id}")
    public String showAddPropositionForm(@PathVariable("id") int id,Model m) {
    	Project proj = projServ.getProjectById(id);
    	m.addAttribute("proj", proj);
    	m.addAttribute("proposition", new Proposition());
        return "proposition"; 
    }


    @PostMapping("/addPropositionForm/{id}")
    public String createProposition(@PathVariable("id") int id,@ModelAttribute Proposition proposition, Model model) {
        try {
        	Project p = projServ.getProjectById(id);
        	proposition.setProj(p);
            propositionService.save(proposition);
            List<Proposition> propositions = propositionService.findAll();
            model.addAttribute("propositions", propositions);

            model.addAttribute("message", "Proposition submitted successfully!");

            return "redirect:/ShowAllProjects"; 
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while saving the proposition.");
            return "error";
        }
        
    }

}

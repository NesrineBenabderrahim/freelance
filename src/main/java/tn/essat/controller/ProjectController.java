package tn.essat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tn.essat.dao.IProject;
import tn.essat.dao.IUser;
import tn.essat.model.Project;
import tn.essat.model.User;
import tn.essat.service.ProjectService;

import java.util.List;

@Controller
public class ProjectController {
	
	
    @Autowired
    private IProject daoProj;

    @Autowired
    private IUser daoUser;

    @GetMapping("/projects")
    public String listProjects(Model model) {
        List<Project> projects = daoProj.findAll();
        model.addAttribute("projects", projects);
        return "listProjects"; 
    }

    @GetMapping("/projects/new")
    public String showCreateProjectForm(@RequestParam("username") String username, Model model) {
        System.out.println("Opening createProject.jsp for user: " + username);

        model.addAttribute("username", username);
        model.addAttribute("project", new Project());
        return "createProject";
    }
    @GetMapping("/projects/details")
    public String viewProjectDetails(@RequestParam("id") Integer id, Model model) {
        Project project = daoProj.findById(id).orElse(null);
        if (project == null) {
            model.addAttribute("error", "Project not found.");
            return "error"; 
        }
        model.addAttribute("project", project);
        return "projectsDetails"; 
    }

    @PostMapping("/projects/new")
    public String createProject(@ModelAttribute("project") Project project, 
                                 @RequestParam("username") String username, 
                                 Model model) {
        User client = daoUser.findByUsername(username);
        if (client == null) {
            model.addAttribute("error", "Client not found.");
            return "error"; 
        }

        project.setClient(client);
        daoProj.save(project); 
        model.addAttribute("message", "Project created successfully!");
        return "redirect:/ShowAllProjects"; 
    }
    
    @Autowired
    private ProjectService projServ;
    

@GetMapping("/projects/editStatusForm/{id}")
public String editProjectForm(
        @PathVariable("id") int projectId, Model m) {
	Project p = projServ.getProjectById(projectId);
	m.addAttribute("proj",p);
 
    return "editProject";
}
@PostMapping("/projects/confirmEdit")
public String editProject(@RequestParam("id") String projId,@ModelAttribute("proj") Project proj, Model m) {
	Project p = projServ.getProjectById(Integer.parseInt(projId));
	p.setStatut(proj.getStatut());
	projServ.createProject(p);
	return "redirect:/ShowAllProjects";
}
    
   
}

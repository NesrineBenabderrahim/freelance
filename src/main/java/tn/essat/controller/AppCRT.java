package tn.essat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import tn.essat.dao.IProfile;
import tn.essat.dao.IProject;
import tn.essat.dao.IUser;
import tn.essat.model.Profile;
import tn.essat.model.Project;
import tn.essat.model.User;

@Controller
public class AppCRT {

    @Autowired
    private IProject daoProj;
    @Autowired
    private IUser daoUser;
    @Autowired
    private IProfile daoProfile; 

    @GetMapping("/profile")
    public String showCreateProfileForm(@RequestParam("username") String username, Model model) {
        User user = daoUser.findByUsername(username);
       
        Profile profile = new Profile();
        model.addAttribute("profile", profile);
        model.addAttribute("user", user);
        return "addProfile";
    }
    @PostMapping("/profile")
    public String createOrUpdateProfile(@RequestParam("username") String username,
                                        Profile profile, Model model) {
        try {
            User user = daoUser.findByUsername(username);

            Profile existingProfile = user.getProfile();

            if (existingProfile != null) {
                existingProfile.setBiblio(profile.getBiblio());
                existingProfile.setSkills(profile.getSkills());
                existingProfile.setAnneeExperience(profile.getAnneeExperience());
                existingProfile.setDisponibilite(profile.getDisponibilite());
                existingProfile.setRating(profile.getRating());

                daoProfile.save(existingProfile);
                model.addAttribute("message", "Profile updated successfully!");
            } else {
                profile.setUser(user);
                user.setProfile(profile);

                daoUser.save(user); 
                model.addAttribute("message", "Profile created successfully!");
                model.addAttribute("profile",profile);
                model.addAttribute("username",username);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Failed to create or update profile: " + e.getMessage());
            return "addProfile"; 
        }

        return "freelancer";
    }
    
    @GetMapping("/profileFreelancer")
    public String getFreelancerProfile(@RequestParam("username") String username, Model model) {
        User user = daoUser.findByUsername(username);
        
        if (user != null) {
            Profile profile = daoProfile.findByUserId(user.getId());
            
            model.addAttribute("username", username);
            model.addAttribute("profile", profile);
            
            return "freelancer";  
        } else {
            model.addAttribute("error", "User not found");
            return "errorPage";  
        }
    }
    
    
    @GetMapping("/ShowAllProjects")
    public String getAllProjects(Model m) {
        List<Project> projs = daoProj.findAll();
        m.addAttribute("projs", projs);
        return "listProjects";
    }
    

}

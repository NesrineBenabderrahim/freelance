package tn.essat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tn.essat.model.Role;
import tn.essat.model.User;
import tn.essat.dao.IRole;
import tn.essat.dao.IUser;

@Controller
public class AuthCRT {

    @Autowired
    private IUser daoUser;
    @Autowired
    private IRole daoRole;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String preLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        String msg = null;
        if (error != null) {
            msg = "Username or Password is incorrect !!";
        }
        model.addAttribute("errorMessge", msg);
        return "login";
    }
    
    @GetMapping("/signup")
    public String showSignupPage(@RequestParam("newUser") String role, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("newUser",role);

        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignup(User user, @RequestParam("newUser") String role, Model model) {
        Role userRole = daoRole.findByRole("ROLE_"+role);
        if (userRole == null) {
            model.addAttribute("error", "Invalid role. Please try again.");
            return "signup";
        }
        User u = daoUser.findByUsername(user.getUsername());
        if (u != null) {
        	model.addAttribute("error", "Username is used by someone else.\n please use an other username.");
            return "signup";
        }

        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true); 

        daoUser.save(user);

        model.addAttribute("message", "Signup successful, please login.");
        return "login"; 
    }

    @GetMapping("/restricted")
    public String restrictedPage() {
        return "restricted";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "redirect:/login.jsp";
    }
}

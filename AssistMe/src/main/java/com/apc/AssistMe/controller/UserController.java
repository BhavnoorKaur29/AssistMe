// package com.apc.AssistMe.controller;

// import com.apc.AssistMe.model.User;
// import com.apc.AssistMe.security.JwtUtil;
// import com.apc.AssistMe.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private JwtUtil jwtUtil;

//     // ========================
//     // REGISTER USER
//     // ========================
//     @PostMapping("/register")
//     public ResponseEntity<?> registerUser(@RequestBody User user) {
//         Optional<User> existingUser = userService.getUserByEmail(user.getEmail());
//         if (existingUser.isPresent()) {
//             return ResponseEntity.badRequest().body("Email already registered");
//         }
//         User savedUser = userService.saveUser(user);
//         return ResponseEntity.ok(savedUser);
//     }

//     // ========================
//     // LOGIN USER
//     // ========================
//     @PostMapping("/login")
//     public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
//         Optional<User> userOpt = userService.getUserByEmail(loginRequest.getEmail());

//         if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginRequest.getPassword())) {
//             // Generate JWT token
//             String token = jwtUtil.generateToken(userOpt.get().getEmail());
//             return ResponseEntity.ok().body("{\"token\":\"Bearer " + token + "\"}");
//         } else {
//             return ResponseEntity.status(401).body("Invalid email or password");
//         }
//     }

//     // ========================
//     // GET ALL USERS (Protected)
//     // ========================
//     @GetMapping
//     public ResponseEntity<List<User>> getAllUsers() {
//         List<User> users = userService.getAllUsers();
//         return ResponseEntity.ok(users);
//     }

//     // ========================
//     // GET USER BY EMAIL (Protected)
//     // ========================
//     @GetMapping("/{email}")
//     public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
//         Optional<User> userOpt = userService.getUserByEmail(email);
//         return userOpt.map(ResponseEntity::ok)
//                       .orElse(ResponseEntity.notFound().build());
//     }
// }


package com.apc.AssistMe.controller;

import com.apc.AssistMe.model.User;
import com.apc.AssistMe.service.UserService;
import com.apc.AssistMe.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // ====== LOGIN PAGE ======
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "success", required = false) String success,
                            Model model) {
        if (error != null) {
            if ("user_not_found".equals(error)) {
                model.addAttribute("error", "Kindly register");
            } else if ("invalid_password".equals(error)) {
                model.addAttribute("error", "Invalid password");
            } else {
                model.addAttribute("error", "Invalid email or password");
            }
        }
        if (success != null) {
            model.addAttribute("success", "Registration successful");
        }
        return "login"; // returns login.html
    }

    // ====== REGISTER PAGE ======
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register"; // returns register.html
    }

    // ====== REGISTER USER ======
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {

        // Validate email
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return "redirect:/register?error=emptyemail";
        }

        // Check if email already exists
        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            return "redirect:/register?error=emailexists";
        }

        // Save new user
        userService.saveUser(user);

        return "redirect:/login?success";
    }

    // ====== LOGIN USER ======
    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password) {

        Optional<User> userOpt = userService.getUserByEmail(email);

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            // For now, just redirect to dashboard. Later add session or JWT.
            return "redirect:/dashboard";
        } else {
            return "redirect:/login?error";
        }
    }
}

package org.example.termtrackbackend.Controller;

import org.example.termtrackbackend.config.JwtService;
import org.example.termtrackbackend.exception.UserNotFoundException;
import org.example.termtrackbackend.model.User;
import org.example.termtrackbackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    private Integer getUserIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.extractUsername(token);
        return userRepository.findByEmail(email).orElseThrow().getId();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        Integer userId = getUserIdFromRequest(request);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id, HttpServletRequest request) {
        Integer authenticatedUserId = getUserIdFromRequest(request);
        if (!authenticatedUserId.equals(id)) {
            return ResponseEntity.status(403).body("Access denied");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User newUser, @PathVariable Integer id, HttpServletRequest request) {
        Integer authenticatedUserId = getUserIdFromRequest(request);
        if (!authenticatedUserId.equals(id)) {
            return ResponseEntity.status(403).body("Access denied");
        }

        return userRepository.findById(id)
                .map(user -> {
                    if (newUser.getEmail() != null && !newUser.getEmail().equals(user.getEmail())) {
                        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
                            return ResponseEntity.status(409).body("Email already registered");
                        }
                        user.setEmail(newUser.getEmail());
                    }
                    user.setName(newUser.getName());
                    if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
                    }
                    return ResponseEntity.ok(userRepository.save(user));
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id, HttpServletRequest request) {
        Integer authenticatedUserId = getUserIdFromRequest(request);
        if (!authenticatedUserId.equals(id)) {
            return ResponseEntity.status(403).body("Access denied");
        }
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("Account deleted");
    }
}
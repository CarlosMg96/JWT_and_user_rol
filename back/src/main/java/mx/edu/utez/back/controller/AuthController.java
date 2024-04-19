package mx.edu.utez.back.controller;

import mx.edu.utez.back.config.JwtTokenProvider;
import mx.edu.utez.back.model.LoginRequest;
import mx.edu.utez.back.model.RegisterRequest;
import mx.edu.utez.back.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PersonService personService;

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        // Autenticar al usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Generar token JWT si la autenticación es exitosa
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/api/register/client")
    public ResponseEntity<String> registerClient(@RequestBody RegisterRequest request) {
        personService.registerClient(request.getFullname(), request.getEmail(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cliente creado exitosamente.");
    }

    @PostMapping("/api/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterRequest request) {
        personService.registerAdmin(request.getFullname(), request.getEmail(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario administrador creado exitosamente.");
    }
}


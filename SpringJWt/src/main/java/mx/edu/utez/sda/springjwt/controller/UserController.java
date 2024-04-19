package mx.edu.utez.sda.springjwt.controller;

import mx.edu.utez.sda.springjwt.entity.AuthRequest;
import mx.edu.utez.sda.springjwt.entity.UserInfo;
import mx.edu.utez.sda.springjwt.service.JwtService;
import mx.edu.utez.sda.springjwt.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserInfoService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/index")
    public String index(){
        logger.trace("Un mensaje de trace");
        logger.debug("Este es un mensaje de debug");
        logger.info("Este es un mensaje de info");
        logger.warn("Este es un mensaje de waning");
        logger.error("Este es un mensaje de error");

        return "Servicio index";
    }
    @PostMapping("/save-user")
    public String register(@RequestBody UserInfo userInfo){
        return service.saveUser(userInfo);
    }
    @GetMapping("/admin/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminOnly(){
        return "This endpoint is only available for users with admin role :D";
    }

    @GetMapping("/user/test")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public String userPath(){
        return "This endpoint is available for all users";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest credentials){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
            System.out.println("Username =>"+credentials.getUsername());
            if(authentication.isAuthenticated()){
                System.out.println("Hola, entramos");
                return jwtService.generateToken(credentials.getUsername());
            }else{
                System.out.println("No autenticado");
                throw new UsernameNotFoundException("Usuario invalido");
            }
        }catch(Exception e){
            System.out.println("No autenticado");
            throw new UsernameNotFoundException("Credenciales inválidas");
        }
    }
}

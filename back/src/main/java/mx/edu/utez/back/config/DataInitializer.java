package mx.edu.utez.back.config;

import mx.edu.utez.back.model.Role;
import mx.edu.utez.back.repository.RoleRepository;
import mx.edu.utez.back.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
        initializePerson();
    }

    private void initializeRoles() {
        if (!roleRepository.existsByName("ROLE_ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }
        if (!roleRepository.existsByName("ROLE_CLIENT")) {
            Role clientRole = new Role();
            clientRole.setName("ROLE_CLIENT");
            roleRepository.save(clientRole);
        }
    }

    private void initializePerson(){
        if (!personService.existsByEmail("admin@mail.com")) {
            String adminEmail = "admin@mail.com";
            String adminPassword = "admin";
            personService.registerAdmin("Admin Kim", adminEmail, adminPassword);
        }
        if (!personService.existsByEmail("client@mail.com")) {
            String clientEmail = "client@mail.com";
            String clientPassword = "client";
            personService.registerClient("Jaimito", clientEmail, clientPassword);
        }
    }

}

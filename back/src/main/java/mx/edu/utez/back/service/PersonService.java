package mx.edu.utez.back.service;

import mx.edu.utez.back.model.Person;
import mx.edu.utez.back.model.Role;
import mx.edu.utez.back.repository.PersonRepository;
import mx.edu.utez.back.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final PersonRepository personRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }

    public Person registerClient(String fullname, String email, String password) {
        if (personRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        Role clientRole = roleRepository.findById(2L).orElseThrow(() -> new RuntimeException("El rol de cliente no existe"));

        Person person = new Person();
        person.setFullname(fullname);
        person.setEmail(email);
        person.setPassword(passwordEncoder.encode(password));
        person.setRole(clientRole);

        return personRepository.save(person);
    }

    public Person registerAdmin(String fullname, String email, String password) {
        // Verifica si el usuario ya existe
        if (personRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        Role clientRole = roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("El rol de admin no existe"));

        Person person = new Person();
        person.setFullname(fullname);
        person.setEmail(email);
        person.setPassword(passwordEncoder.encode(password));
        person.setRole(clientRole);

        return personRepository.save(person);
    }
}

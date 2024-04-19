package mx.edu.utez.back.repository;

import mx.edu.utez.back.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Object> findByEmail(String email);

    boolean existsByEmail(String email);
}

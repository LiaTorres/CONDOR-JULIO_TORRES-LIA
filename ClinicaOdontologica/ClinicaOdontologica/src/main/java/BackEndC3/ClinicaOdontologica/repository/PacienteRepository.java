package BackEndC3.ClinicaOdontologica.repository;

import BackEndC3.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Interfaz de repositorio para la entidad Paciente. Extiende JpaRepository para heredar métodos CRUD.
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Método personalizado para buscar un paciente por su email.
    // La implementación de este método es generada automáticamente por Spring Data JPA.
    Optional<Paciente> findByEmail(String email);
}

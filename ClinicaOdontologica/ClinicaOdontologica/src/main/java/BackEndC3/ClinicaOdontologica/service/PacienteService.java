package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.entity.Paciente;
import BackEndC3.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Anotación que indica que esta clase es un servicio de Spring, que contiene la lógica de negocio
@Service
public class PacienteService {

    // Inyección de dependencias del repositorio de Paciente
    @Autowired
    private PacienteRepository pacienteRepository;

    // Método para guardar un nuevo paciente o actualizar uno existente
    public Paciente guardarPaciente(Paciente paciente) {
        // Llama al método save del repositorio, que guarda el paciente en la base de datos
        return pacienteRepository.save(paciente);
    }

    // Método para buscar un paciente por su ID
    public Optional<Paciente> buscarPorID(Long id) {
        // Llama al método findById del repositorio, que busca el paciente por su ID
        return pacienteRepository.findById(id);
    }

    // Método para buscar un paciente por su email
    public Optional<Paciente> buscarPorEmail(String email) {
        // Llama al método findByEmail del repositorio, que busca el paciente por su email
        return pacienteRepository.findByEmail(email);
    }

    // Método para actualizar un paciente existente
    public void actualizarPaciente(Paciente paciente) {
        // Llama al método save del repositorio, que guarda el paciente actualizado en la base de datos
        pacienteRepository.save(paciente);
    }

    // Método para eliminar un paciente por su ID
    public void pacienteAEliminar(Long id) {
        // Llama al método deleteById del repositorio, que elimina el paciente por su ID
        pacienteRepository.deleteById(id);
    }

    // Método para buscar todos los pacientes
    public List<Paciente> buscarPacientes() {
        // Llama al método findAll del repositorio, que devuelve una lista de todos los pacientes
        return pacienteRepository.findAll();
    }
}

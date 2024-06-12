package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.entity.Paciente;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    // Inyecta el servicio PacienteService para manejar la lógica del negocio
    @Autowired
    private PacienteService pacienteService;

    // Método para obtener una lista de todos los pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>> pacienteList() {
        // Llama al método del servicio para buscar todos los pacientes
        List<Paciente> listaPacientes = pacienteService.buscarPacientes();
        // Devuelve la lista de pacientes en la respuesta HTTP con estado 200 (OK)
        return ResponseEntity.ok(listaPacientes);
    }

    // Método para buscar un paciente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorPaciente(@PathVariable Long id) {
        // Llama al método del servicio para buscar un paciente por ID
        Optional<Paciente> paciente = pacienteService.buscarPorID(id);
        // Si el paciente está presente, devuelve el paciente con estado 200 (OK)
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente);
        } else {
            // Si el paciente no se encuentra, devuelve el estado 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }

    // Método para buscar un paciente por su email
    @GetMapping("/buscar/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email) {
        // Llama al método del servicio para buscar un paciente por email
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
        // Si el paciente está presente, devuelve el paciente con estado 200 (OK)
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado);
        } else {
            // Si el paciente no se encuentra, devuelve el estado 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }

    // Método para guardar un nuevo paciente
    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        // Llama al método del servicio para guardar el paciente
        Paciente nuevoPaciente = pacienteService.guardarPaciente(paciente);
        // Devuelve el paciente guardado en la respuesta HTTP con estado 200 (OK)
        return ResponseEntity.ok(nuevoPaciente);
    }

    // Método para actualizar un paciente existente
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        // Llama al método del servicio para buscar un paciente por ID
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorID(paciente.getId());
        // Si el paciente está presente, actualiza el paciente
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            // Devuelve un mensaje de éxito con estado 200 (OK)
            return ResponseEntity.ok("Paciente actualizado con exito");
        } else {
            // Si el paciente no se encuentra, devuelve un mensaje de error con estado 400 (Bad Request)
            return ResponseEntity.badRequest().body("Paciente no encontrado");
        }
    }

    // Método para eliminar un paciente por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> pacienteAEliminar(@PathVariable Long id) {
        // Llama al método del servicio para buscar un paciente por ID
        Optional<Paciente> pacienteConsultado = pacienteService.buscarPorID(id);
        // Si el paciente está presente, elimina el paciente
        if (pacienteConsultado.isPresent()) {
            pacienteService.pacienteAEliminar(id);
            // Devuelve un mensaje de éxito con estado 200 (OK)
            return ResponseEntity.ok("Paciente eliminado exitosamente");
        } else {
            // Si el paciente no se encuentra, devuelve el estado 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
}
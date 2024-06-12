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

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> pacienteList() {
        List<Paciente> listaPacientes = pacienteService.buscarPacientes();
        return ResponseEntity.ok(listaPacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorPaciente(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.buscarPorID(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{email}")
    public ResponseEntity<Optional<Paciente>> buscarPorEmail(@PathVariable String email) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        Paciente nuevoPaciente = pacienteService.guardarPaciente(paciente);
        return ResponseEntity.ok(nuevoPaciente);
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorID(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente actualizado con exito");
        } else {
            return ResponseEntity.badRequest().body("Paciente no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> pacienteAEliminar(@PathVariable Long id) {
        Optional<Paciente> pacienteConsultado = pacienteService.buscarPorID(id);
        if (pacienteConsultado.isPresent()) {
            pacienteService.pacienteAEliminar(id);
            return ResponseEntity.ok("Paciente eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

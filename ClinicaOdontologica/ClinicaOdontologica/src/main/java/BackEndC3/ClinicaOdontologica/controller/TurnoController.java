package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import BackEndC3.ClinicaOdontologica.entity.Paciente;
import BackEndC3.ClinicaOdontologica.entity.Turno;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import BackEndC3.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos() {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorID(@PathVariable Long id) {
        Optional<Turno> turno = turnoService.buscarPorID(id);
        if (turno.isPresent()) {
            return ResponseEntity.ok(turno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorID(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorID(turno.getOdontologo().getId());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            Turno nuevoTurno = turnoService.guardarTurno(turno);
            return ResponseEntity.ok(nuevoTurno);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        Optional<Turno> turnoBuscado = turnoService.buscarPorID(turno.getId());
        if (turnoBuscado.isPresent()) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado con exito");
        } else {
            return ResponseEntity.badRequest().body("Turno no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> turnoAEliminar(@PathVariable Long id) {
        Optional<Turno> turnoConsultado = turnoService.buscarPorID(id);
        if (turnoConsultado.isPresent()) {
            turnoService.turnoAEliminar(id);
            return ResponseEntity.ok("Turno eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import BackEndC3.ClinicaOdontologica.entity.Paciente;
import BackEndC3.ClinicaOdontologica.entity.Turno;
import BackEndC3.ClinicaOdontologica.exception.ResourceNotFoundException;
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
    public ResponseEntity<List<Turno>> buscarTodos() throws ResourceNotFoundException {
        List<Turno> turnos = turnoService.buscarTodos();
        if (turnos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron turnos");
        }
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorID(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turno = turnoService.buscarPorID(id);
        if (turno.isEmpty()) {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
        return ResponseEntity.ok(turno);
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) throws ResourceNotFoundException, Exception {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorID(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorID(turno.getOdontologo().getId());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            Turno nuevoTurno = turnoService.guardarTurno(turno);
            return ResponseEntity.ok(nuevoTurno);
        } else {
            throw new ResourceNotFoundException("Paciente u odontólogo no encontrado");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoService.buscarPorID(turno.getId());
        if (turnoBuscado.isPresent()) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado con éxito");
        } else {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> turnoAEliminar(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoConsultado = turnoService.buscarPorID(id);
        if (turnoConsultado.isPresent()) {
            turnoService.turnoAEliminar(id);
            return ResponseEntity.ok("Turno eliminado exitosamente");
        } else {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }
}
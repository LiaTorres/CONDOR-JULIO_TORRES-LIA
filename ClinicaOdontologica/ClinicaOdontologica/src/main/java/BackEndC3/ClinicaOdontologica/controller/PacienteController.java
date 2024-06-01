package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @Controller

@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService = new PacienteService();
    }

    @GetMapping
    public List<Paciente> pacienteList() {
        List<Paciente> listaPacientes = pacienteService.buscarPacientes();
        return listaPacientes;
    }

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente) {

        Paciente pacienteBuscado = pacienteService.buscarPorID(paciente.getId());
        if (pacienteBuscado != null) {
            pacienteService.actualizarPaciente(paciente);
            return "paciente actualizado con exito";
        } else {
            return "paciente no encontrado";
        }

    }

    @GetMapping("/{id}")
    public Paciente buscarPorPaciente(@PathVariable Integer id) {
        return pacienteService.buscarPorID(id);
    }

    @DeleteMapping("/{id}")
    public String pacienteAEliminar(@PathVariable Integer id) {
        Paciente pacienteConsultado = pacienteService.buscarPorID(id);
        if (pacienteConsultado != null) {
            return pacienteService.pacienteAEliminar(pacienteConsultado);
        } else {
            return "Paciente no encontrado";
        }
    }
}
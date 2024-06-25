package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import BackEndC3.ClinicaOdontologica.exception.ResourceNotFoundException;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Odontologo>> odontologoList() throws ResourceNotFoundException {
        List<Odontologo> listaOdontogos = odontologoService.buscarOdontologos();
        if (listaOdontogos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron odontólogos");
        }
        return ResponseEntity.ok(listaOdontogos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorID(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo = odontologoService.buscarPorID(id);
        if (odontologo.isEmpty()) {
            throw new ResourceNotFoundException("Odontólogo no encontrado");
        }
        return ResponseEntity.ok(odontologo);
    }

    @PostMapping
    public ResponseEntity<Odontologo> crearOdontologos(@RequestBody Odontologo odontologo) throws Exception {
        try {
            Odontologo nuevoOdontologo = odontologoService.crearOdontologos(odontologo);
            return ResponseEntity.ok(nuevoOdontologo);
        } catch (Exception e) {
            throw new Exception("Error al crear el odontólogo");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoId = odontologoService.buscarPorID(odontologo.getId());
        if (odontologoId.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontólogo actualizado con éxito");
        } else {
            throw new ResourceNotFoundException("Odontólogo no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> odontologoAEliminar(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoConsultado = odontologoService.buscarPorID(id);
        if (odontologoConsultado.isPresent()) {
            odontologoService.odontologoAEliminar(id);
            return ResponseEntity.ok("Odontólogo eliminado exitosamente");
        } else {
            throw new ResourceNotFoundException("Odontólogo no encontrado");
        }
    }
}
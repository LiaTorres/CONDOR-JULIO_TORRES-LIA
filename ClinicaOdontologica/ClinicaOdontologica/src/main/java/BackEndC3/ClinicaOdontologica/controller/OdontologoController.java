package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Odontologo>> odontologoList() {
        List<Odontologo> listaOdontogos = odontologoService.buscarOdontologos();
        return ResponseEntity.ok(listaOdontogos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorID(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<Odontologo> crearOdontologos(@RequestBody Odontologo odontologo) {
        Odontologo nuevoOdontologo = odontologoService.crearOdontologos(odontologo);
        return ResponseEntity.ok(nuevoOdontologo);
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoId = odontologoService.buscarPorID(odontologo.getId());
        if (odontologoId.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo actualizado con exito");
        } else {
            return ResponseEntity.badRequest().body("Odontologo no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> odontologoAEliminar(@PathVariable Long id) {
        Optional<Odontologo> odontologoConsultado = odontologoService.buscarPorID(id);
        if (odontologoConsultado.isPresent()) {
            odontologoService.odontologoAEliminar(id);
            return ResponseEntity.ok("Odontologo eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

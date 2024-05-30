package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController() {
      odontologoService=new OdontologoService();
    }

 @GetMapping
    public List<Odontologo> odontologoList() {
     List<Odontologo> listaOdontogos = odontologoService.buscarOdontologos();
     return listaOdontogos;
 }
 @PostMapping
    public Odontologo crearOdontologos(@RequestBody Odontologo odontologo){
        return odontologoService.crearOdontologos(odontologo);
 }
    @PutMapping
    public String actualizarOdontologo(@RequestBody Odontologo odontologo){

        Odontologo odontologoId= odontologoService.buscarPorID(odontologo.getId());
        if(odontologoId!=null){
            odontologoService.actualizarOdontologo(odontologo);
            return "Odontologo actualizado con exito";
        }else{
            return "Odontologo no encontrado";
        }

    }
 @GetMapping("/{id}")
 public Odontologo buscarPorID(@PathVariable Integer id){
        return odontologoService.buscarPorID(id);
    }

}

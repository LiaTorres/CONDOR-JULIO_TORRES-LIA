package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private  OdontologoService odontologoService;

    @Test
    @Order(1)
    public void crearOdontologo(){
        Odontologo odontologo= new Odontologo(4L, "8888", "Mariana","Perez");
        Odontologo odontologoGuardado= odontologoService.crearOdontologos(odontologo);
        assertEquals(4L,odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPorId(){
        Long id= 4L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorID(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Long id= 4L;
        Odontologo odontologo= new Odontologo(4L,"5444", "Susana","Jimenez");
        odontologoService.actualizarOdontologo(odontologo);
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorID(id);
        assertEquals("Susana", odontologoBuscado.get().getNombre());
    }

    @Test
    @Order(4)
    public void listarTodos(){
        List<Odontologo> listaOdontologos= odontologoService.buscarOdontologos();
        assertEquals(4,listaOdontologos.size());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo(){
        odontologoService.odontologoAEliminar(4L);
        Optional<Odontologo> odontologoEliminado= odontologoService.buscarPorID(4L);
        assertFalse(odontologoEliminado.isPresent());
    }
}
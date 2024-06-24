package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.entity.Domicilio;
import BackEndC3.ClinicaOdontologica.entity.Paciente;
import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import BackEndC3.ClinicaOdontologica.entity.Turno;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    public void crearTurno(){
        Paciente paciente= new Paciente(4L,"Pedro","Gonzales","9999", LocalDate.of(2024,6,20),new Domicilio(4L, "calle falsa",123,"La Rioja","Argentina"),"jorge.pereyra@digitalhouse.com");
        Paciente pacienteGuardado= pacienteService.guardarPaciente(paciente);
        Odontologo odontologo = new Odontologo(4L, "1233", "Ernesto", "Rodriguez");
        Odontologo odontologoGuardado = odontologoService.crearOdontologos(odontologo);
        Turno turno = new Turno(4L, pacienteGuardado, odontologoGuardado, LocalDate.of(2024,6,20));
        Turno turnoGuardado = turnoService.guardarTurno(turno);
        assertEquals(4L,turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId(){
        Long id= 4L;
        Optional<Turno> turnoBuscado = turnoService.buscarPorID(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurno() {
        Long id = 4L;
        LocalDate nuevaFecha = LocalDate.of(2024, 12, 15);
        Optional<Turno> turnoInicial = turnoService.buscarPorID(id);
        if (turnoInicial.isPresent()){
            Turno turnoNuevaFecha = turnoInicial.get();
            turnoNuevaFecha.setFecha(nuevaFecha);
            turnoService.actualizarTurno(turnoNuevaFecha);
            Optional<Turno> turnoActualizado = turnoService.buscarPorID(id);
            assertEquals(nuevaFecha, turnoActualizado.get().getFecha());
        }
        assertEquals(nuevaFecha, turnoInicial.get().getFecha());
    }

    @Test
    @Order(4)
    public void ListarTodos(){
        List<Turno> listaTurnos = turnoService.buscarTodos();
        assertEquals(4, listaTurnos.size());
    }
    @Test
    @Order(5)
    public void eliminarTurno(){
        turnoService.turnoAEliminar(4L);
        Optional<Turno> turnoEliminado = turnoService.buscarPorID(4L);
        assertFalse(turnoEliminado.isPresent());
    }
}

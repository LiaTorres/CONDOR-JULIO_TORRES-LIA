package BackEndC3.ClinicaOdontologica.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Turno {
    private Integer id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fecha;

    public Turno(Integer id, LocalDate fecha, Odontologo odontologo, Paciente paciente) {
        this.id = id;
        this.fecha = fecha;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }


    public Turno() {
    }


    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fecha=" + fecha +
                '}';
    }
}
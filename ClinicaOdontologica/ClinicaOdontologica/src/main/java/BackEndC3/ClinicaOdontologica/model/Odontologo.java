package BackEndC3.ClinicaOdontologica.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Odontologo {
    private Integer id;
    private String matricula;
    private String nombre;
    private String apellido;

    public Odontologo(Integer id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", Matricula='" + matricula + '\'' +
                ", nombre=" + nombre +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
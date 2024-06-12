package BackEndC3.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String matricula;
    @Column
    private String nombre;
    @Column
    private String apellido;

//    public Odontologo(String apellido, String nombre, String matricula) {
//        this.apellido = apellido;
//        this.nombre = nombre;
//        this.matricula = matricula;
//    }
//
//    public Odontologo(Long id, String matricula, String nombre, String apellido) {
//        this.id = id;
//        this.matricula = matricula;
//        this.nombre = nombre;
//        this.apellido = apellido;
//    }

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
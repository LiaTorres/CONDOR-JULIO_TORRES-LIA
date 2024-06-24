package BackEndC3.ClinicaOdontologica.security;

import BackEndC3.ClinicaOdontologica.entity.*;
import BackEndC3.ClinicaOdontologica.repository.OdontologoRepository;
import BackEndC3.ClinicaOdontologica.repository.PacienteRepository;
import BackEndC3.ClinicaOdontologica.repository.TurnoRepository;
import BackEndC3.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar= "admin";
        String passCifrado=  passwordEncoder.encode(passSinCifrar);
        Usuario usuario= new Usuario("jorgito","jpereryradh","admin@admin.com",passCifrado, UsuarioRole.ROLE_ADMIN);
        Usuario usuario2= new Usuario("lia","paolia","liapaola@lia.lia",passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
        usuarioRepository.save(usuario2);

//      Odontologos
        Odontologo odontologo= new Odontologo(1L, "343434", "Rodrigo","Borja");
        odontologoRepository.save(odontologo);

        Odontologo odontologo2= new Odontologo(2L, "656576", "Gerardo","Caminos");
        odontologoRepository.save(odontologo2);

        Odontologo odontologo3= new Odontologo(3L, "345437", "Mariana","Perez");
        odontologoRepository.save(odontologo3);

//      Pacientes
        Paciente paciente= new Paciente(1L,"Jorgito","Pereira","11111", LocalDate.of(2024,9,12),new Domicilio(1L,"Frau bentos",332,"Av rivera","Argentina"),"jorge.pereyra@digitalhouse.com");
        pacienteRepository.save(paciente);

        Paciente paciente2= new Paciente(2L,"Juan","Castillo","4234242", LocalDate.of(2024,1,22),new Domicilio(2L,"Street",435,"Albuquerque","Canada"),"Juan@castillo.com");
        pacienteRepository.save(paciente2);

        Paciente paciente3= new Paciente(3L,"Pedro","Gonzales","6563453", LocalDate.of(2024,7,18),new Domicilio(3L,"Mount view",3251,"Soriano","Uruguay"),"pedro@gonzales.com");
        pacienteRepository.save(paciente3);

        Turno turno = turnoRepository.save(new Turno(1L,paciente, odontologo, LocalDate.of(2024,6,20)));
        Turno turno2 = turnoRepository.save(new Turno(2L,paciente2, odontologo2, LocalDate.of(2024,6,20)));
        Turno turno3 = turnoRepository.save(new Turno(3L,paciente3, odontologo3, LocalDate.of(2024,6,20)));




    }
}

package BackEndC3.ClinicaOdontologica;

import BackEndC3.ClinicaOdontologica.service.OdontologoServiceTest;
import BackEndC3.ClinicaOdontologica.service.PacienteServiceTest;
import BackEndC3.ClinicaOdontologica.service.TurnoServiceTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({
		OdontologoServiceTest.class,
		PacienteServiceTest.class,
		TurnoServiceTest.class,
		OdontologoIntegracionTest.class,
		PacienteIntegracionTest.class,
		TurnosIntegracionTest.class
})

@SpringBootTest
public class ClinicaOdontologicaApplicationTests {

}
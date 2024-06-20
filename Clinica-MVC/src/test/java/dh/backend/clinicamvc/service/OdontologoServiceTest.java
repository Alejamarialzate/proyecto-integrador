package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.entity.Domicilio;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OdontologoServiceTest {


    @Autowired
    OdontologoService odontologoService;
    Odontologo odontologo;

    @BeforeEach
    void setUp(){
        odontologo = new Odontologo();
        odontologo.setNombre("maria");
        odontologo.setApellido("alzate");
        odontologo.setId(4562154);
        odontologo.setNroMatricula("5155");

        Paciente paciente = new Paciente();
        paciente.setNombre("Menganito");
        paciente.setApellido("Cosme");
        paciente.setDni("464646");
        paciente.setFechaIngreso(LocalDate.of(2024,01,12));
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle falsa");
        domicilio.setNumero(123);
        domicilio.setLocalidad("San Pedro");
        domicilio.setProvincia("Jujuy");
        paciente.setDomicilio(domicilio);
        Turno turno = new Turno();
        turno.setFecha(LocalDate.of(2024, 9, 5));
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

    }
    @Test
    @DisplayName("Testear que un odontologo fue guardado")
    void testOdontologoGuardado(){
        Odontologo odontologoBD = odontologoService.agregarOdontologo(odontologo);
        assertNotNull(odontologoBD);
    }


}

package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.Dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Domicilio;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.service.impl.TurnoService;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import dh.backend.clinicamvc.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {

    @Autowired
    TurnoService turnoService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    PacienteService pacienteService;

    Odontologo odontologo;
    Paciente paciente;

    @BeforeEach
    void setUp() {
        // Crear un odontólogo para el test
        odontologo = new Odontologo();
        odontologo.setNombre("Juan");
        odontologo.setApellido("Perez");
        odontologo.setNroMatricula("12345");
        odontologo.setId(1);
        odontologoService.agregarOdontologo(odontologo);


        // Crear un paciente para el test
        paciente = new Paciente();
        paciente.setNombre("María");
        paciente.setApellido("Gomez");
        paciente.setDni("12345678");
        paciente.setId(1);
        pacienteService.registrarPaciente(paciente);
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle Falsa");
        domicilio.setNumero(123);
        domicilio.setLocalidad("Ciudad Autónoma");
        domicilio.setProvincia("Buenos Aires");
        paciente.setDomicilio(domicilio);

    }

    @Test
    @DisplayName("Registrar un nuevo turno")
    void testRegistrarTurno() {
        // Crear un nuevo turno
        TurnoRequestDto turnoRequestDto = new TurnoRequestDto();
        turnoRequestDto.setFecha(LocalDate.of(2024, 9, 1).toString());
        turnoRequestDto.setOdontologo_id(odontologo.getId());
        turnoRequestDto.setPaciente_id(paciente.getId());


        // Registrar el turno
        TurnoResponseDto turnoRegistrado = turnoService.registrar(turnoRequestDto);

        // Verificar que el turno registrado no sea nulo
        assertNotNull(turnoRegistrado.getId(), "El ID del turno registrado no debería ser nulo");

        // Opcional: Verificar otros aspectos del turno registrado si es necesario
        assertEquals(odontologo.getId(), turnoRegistrado.getOdontologo().getId(), "El odontólogo del turno registrado debe ser el mismo");
        assertEquals(paciente.getId(), turnoRegistrado.getPaciente().getId(), "El paciente del turno registrado debe ser el mismo");
    }


}
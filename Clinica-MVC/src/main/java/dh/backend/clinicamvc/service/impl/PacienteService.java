package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente) {

        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public void actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = buscarPorId(paciente.getId());
        if (pacienteOptional.isPresent()) {
            logger.info("paciente actualizado correctamente");
            pacienteRepository.save(paciente);
        } else {
            loggerPacienteNoEncontrado();
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
    }

    private void loggerPacienteNoEncontrado() {
        logger.info("paciente no encontrado");
    }

    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = buscarPorId(id);
        if (pacienteOptional.isPresent()) {
            logger.info("paciente eliminado correctamente");
            pacienteRepository.deleteById(id);
        } else {
            loggerPacienteNoEncontrado();
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
    }
}

package co.edu.usbcali.aerolinea.services.Implements;

import co.edu.usbcali.aerolinea.domain.Aeropuerto;
import co.edu.usbcali.aerolinea.domain.Avion;
import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.AvionDTO;
import co.edu.usbcali.aerolinea.mappers.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mappers.AvionMapper;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.AvionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AvionServiceImpl implements AvionService {
    private final AvionRepository avionRepository;
    private final ModelMapper modelMapper;
    public AvionServiceImpl(AvionRepository avionRepository, ModelMapper modelMapper) {
        this.avionRepository = avionRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<AvionDTO> obtenerAviones() {
        return AvionMapper.domainToDTOList(avionRepository.findAll());
    }
    @Override
    public AvionDTO obtenerAvion(Integer id) throws Exception {
        if (!avionRepository.existsById(id)) {
            throw new Exception("El id " + id + " no corresponde a ningun avion!");
        }
        return AvionMapper.domainToDTO(avionRepository.getReferenceById(id));
    }
    @Override
    public AvionDTO agregarAvion(AvionDTO avionDTO) throws Exception {
        if (avionDTO == null) {
            throw new Exception("El avion no puede ser nulo");
        }
        if (avionDTO.getIdAvion() == null) {
            throw new Exception("El id del avion es obligatorio!");
        }
        if (avionDTO.getAerolineaAvion() == null || avionDTO.getAerolineaAvion().isBlank() || avionDTO.getAerolineaAvion().trim().isEmpty()) {
            throw new Exception("El nombre del avion es invalido!");
        }
        if (avionDTO.getEstado() == null || avionDTO.getEstado().isBlank() || avionDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado es invalido!");
        }
        if(avionRepository.findById(avionDTO.getIdAvion()).isPresent()){
            throw new Exception("Ya existe el id del aeropuerto!");
        }
        Avion avion = AvionMapper.dtoToDomain(avionDTO);
        return AvionMapper.domainToDTO(avionRepository.save(avion));
    }

    @Override
    public List<AvionDTO> obtenerAvionesActivos() {
        return AvionMapper.domainToDTOList(avionRepository.findAllByEstado("A"));
    }

    @Override
    public AvionDTO updateAvion(AvionDTO avionDTO) throws Exception {
        if (avionDTO == null) {
            throw new Exception("El avion no puede ser nulo");
        }
        if (avionDTO.getAerolineaAvion() == null || avionDTO.getAerolineaAvion().isBlank() || avionDTO.getAerolineaAvion().trim().isEmpty()) {
            throw new Exception("El nombre del avion es invalido!");
        }
        if (avionDTO.getEstado() == null || avionDTO.getEstado().isBlank() || avionDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado es invalido!");
        }
        Avion avion = AvionMapper.dtoToDomain(avionDTO);
        return AvionMapper.domainToDTO(avionRepository.save(avion));
    }

    @Override
    public AvionDTO deleteAvion(Integer id) throws Exception {
        Optional<Avion> avionOptional = avionRepository.findById(id);

        if (avionOptional.isPresent()) {

            Avion avion = avionOptional.get();
            avionRepository.deleteById(id);

            return AvionMapper.domainToDTO(avion);
        } else {
            throw new Exception("No se encontró el avion con ese id!");
        }
    }
}

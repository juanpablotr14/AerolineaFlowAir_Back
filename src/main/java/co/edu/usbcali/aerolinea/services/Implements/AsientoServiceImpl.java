package co.edu.usbcali.aerolinea.services.Implements;

import co.edu.usbcali.aerolinea.domain.Aeropuerto;
import co.edu.usbcali.aerolinea.domain.Asiento;
import co.edu.usbcali.aerolinea.domain.Avion;
import co.edu.usbcali.aerolinea.domain.TipoAsiento;
import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.AsientoDTO;
import co.edu.usbcali.aerolinea.mappers.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mappers.AsientoMapper;
import co.edu.usbcali.aerolinea.repository.AsientoRepository;
import co.edu.usbcali.aerolinea.repository.AvionRepository;
import co.edu.usbcali.aerolinea.repository.TipoAsientoRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.AsientoService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AsientoServiceImpl implements AsientoService {
    private final AsientoRepository asientoRepository;
    private final TipoAsientoRepository tipoAsientoRepository;
    private final AvionRepository avionRepository;
    private final ModelMapper modelMapper;

    public AsientoServiceImpl(AsientoRepository asientoRepository, TipoAsientoRepository tipoAsientoRepository, AvionRepository avionRepository, ModelMapper modelMapper) {
        this.asientoRepository = asientoRepository;
        this.tipoAsientoRepository = tipoAsientoRepository;
        this.avionRepository = avionRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<AsientoDTO> obtenerAsientos() {
        return AsientoMapper.domainToDTOList(asientoRepository.findAll());
    }
    @Override
    public AsientoDTO obtenerAsiento(Integer id) throws Exception {
        if (!asientoRepository.existsById(id)) {
            throw new Exception("El id " + id + " no corresponde a ningun asiento!");
        }
        return AsientoMapper.domainToDTO(asientoRepository.getReferenceById(id));
    }
    @Override
    public AsientoDTO agregarAsiento(AsientoDTO asientoDTO) throws Exception {
        if (asientoDTO == null) {
            throw new Exception("El asiento es nulo!");
        }
        if (asientoDTO.getUbicacion() == null || asientoDTO.getUbicacion().isBlank() || asientoDTO.getUbicacion().trim().isEmpty()) {
            throw new Exception("La ubicación del asiento es invalido!");
        }
        if (asientoDTO.getPrecio() < 0) {
            throw new Exception("El precio del asiento debe ser negativo!");
        }
        if (asientoDTO.getEstado() == null || asientoDTO.getEstado().isBlank() || asientoDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del asiento es invalido!");
        }
        if(asientoRepository.findById(asientoDTO.getIdAsiento()).isPresent()){
            throw new Exception("Ya existe el id del asiento!");
        }

        Asiento asiento = AsientoMapper.dtoToDomain(asientoDTO);

        TipoAsiento tipoAsiento = tipoAsientoRepository.getReferenceById(asientoDTO.getIdTipoa());
        Avion avion = avionRepository.getReferenceById(asientoDTO.getIdAvion());
        asiento.setIdTipoa(tipoAsiento);
        asiento.setIdAvion(avion);

        return AsientoMapper.domainToDTO(asientoRepository.save(asiento));
    }

    @Override
    public AsientoDTO updateAsiento(AsientoDTO asientoDTO) throws Exception {
        if (asientoDTO == null) {
            throw new Exception("El asiento es nulo!");
        }
        if (asientoDTO.getUbicacion() == null || asientoDTO.getUbicacion().isBlank() || asientoDTO.getUbicacion().trim().isEmpty()) {
            throw new Exception("La ubicación del asiento es invalido!");
        }
        if (asientoDTO.getPrecio() < 0) {
            throw new Exception("El precio del asiento no debe ser negativo!");
        }
        if (asientoDTO.getEstado() == null || asientoDTO.getEstado().isBlank() || asientoDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado del asiento es invalido!");
        }

        Asiento asiento = AsientoMapper.dtoToDomain(asientoDTO);

        TipoAsiento tipoAsiento = tipoAsientoRepository.getReferenceById(asientoDTO.getIdTipoa());
        Avion avion = avionRepository.getReferenceById(asientoDTO.getIdAvion());
        asiento.setIdTipoa(tipoAsiento);
        asiento.setIdAvion(avion);

        return AsientoMapper.domainToDTO(asientoRepository.save(asiento));
    }

    @Override
    public AsientoDTO deleteAsiento(Integer id) throws Exception {
        Optional<Asiento> asientoOptional = asientoRepository.findById(id);

        if (asientoOptional.isPresent()) {

            Asiento asiento = asientoOptional.get();
            asientoRepository.deleteById(id);

            return AsientoMapper.domainToDTO(asiento);
        } else {
            throw new Exception("No se encontró el asiento con ese id!");
        }
    }

    @Override
    public List<AsientoDTO> obtenerAsientosActivos() {
        return AsientoMapper.domainToDTOList(asientoRepository.findAllByEstado("A"));
    }
}

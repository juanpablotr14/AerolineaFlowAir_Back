package co.edu.usbcali.aerolinea.services.Implements;

import co.edu.usbcali.aerolinea.controllers.VueloController;
import co.edu.usbcali.aerolinea.domain.*;
import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.ReservaDTO;
import co.edu.usbcali.aerolinea.mappers.AeropuertoMapper;
import co.edu.usbcali.aerolinea.mappers.ReservaMapper;
import co.edu.usbcali.aerolinea.repository.AsientoRepository;
import co.edu.usbcali.aerolinea.repository.ReservaRepository;
import co.edu.usbcali.aerolinea.repository.UsuarioRepository;
import co.edu.usbcali.aerolinea.repository.VueloRepository;
import co.edu.usbcali.aerolinea.services.Interfaces.ReservaService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final VueloRepository vueloRepository;
    private final AsientoRepository asientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    public ReservaServiceImpl(ReservaRepository reservaRepository, VueloRepository vueloRepository, AsientoRepository asientoRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.reservaRepository = reservaRepository;
        this.vueloRepository = vueloRepository;
        this.asientoRepository = asientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<ReservaDTO> obtenerReservas() {
        return ReservaMapper.domainToDTOList(reservaRepository.findAll());
    }
    @Override
    public ReservaDTO obtenerReserva(Integer id) throws Exception {
        if (!reservaRepository.existsById(id)) {
            throw new Exception("El id " + id + " no corresponde a ninguna reserva!");
        }
        return ReservaMapper.domainToDTO(reservaRepository.getReferenceById(id));
    }
    @Override
    public ReservaDTO agregarReserva(ReservaDTO reservaDTO) throws Exception {
        if (reservaDTO == null) {
            throw new Exception("La reserva es invalida!");
        }
        if (reservaDTO.getPrecioTotal() < 0) {
            throw new Exception("El precio de la reserva no puede ser negativo!");
        }
        if (reservaDTO.getEstadoPago() == null || reservaDTO.getEstadoPago().isBlank() || reservaDTO.getEstadoPago().trim().isEmpty()) {
            throw new Exception("El estado de pago de la reserva es invalido!");
        }
        if (reservaDTO.getFecha() == null) {
            throw new Exception("La fecha de la reserva es invalida!");
        }
        if (reservaDTO.getEstado() == null || reservaDTO.getEstado().isBlank() || reservaDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado de la reserva es invalido!");
        }
        if(reservaRepository.findById(reservaDTO.getIdReserva()).isPresent()){
            throw new Exception("Ya existe el id de la reserva!");
        }
        Reserva reserva = ReservaMapper.dtoToDomain(reservaDTO);

        Vuelo vuelo = vueloRepository.getReferenceById(reservaDTO.getIdVuelo());
        Asiento asiento = asientoRepository.getReferenceById(reservaDTO.getIdAsiento());
        Usuario usuario = usuarioRepository.getReferenceById(reservaDTO.getIdUsuario());

        reserva.setIdVuelo(vuelo);
        reserva.setIdAsiento(asiento);
        reserva.setIdUsuario(usuario);

        return ReservaMapper.domainToDTO(reservaRepository.save(reserva));
    }
    @Override
    public List<ReservaDTO> obtenerReservasActivas() {
        return ReservaMapper.domainToDTOList(reservaRepository.findAllByEstado("A"));
    }

    @Override
    public ReservaDTO updateReserva(ReservaDTO reservaDTO) throws Exception {
        if (reservaDTO == null) {
            throw new Exception("La reserva es invalida!");
        }
        if (reservaDTO.getPrecioTotal() < 0) {
            throw new Exception("El precio de la reserva no puede ser negativo!");
        }
        if (reservaDTO.getEstadoPago() == null || reservaDTO.getEstadoPago().isBlank() || reservaDTO.getEstadoPago().trim().isEmpty()) {
            throw new Exception("El estado de pago de la reserva es invalido!");
        }
        if (reservaDTO.getFecha() == null) {
            throw new Exception("La fecha de la reserva es invalida!");
        }
        if (reservaDTO.getEstado() == null || reservaDTO.getEstado().isBlank() || reservaDTO.getEstado().trim().isEmpty()) {
            throw new Exception("El estado de la reserva es invalido!");
        }

        Reserva reserva = ReservaMapper.dtoToDomain(reservaDTO);

        Vuelo vuelo = vueloRepository.getReferenceById(reservaDTO.getIdVuelo());
        Asiento asiento = asientoRepository.getReferenceById(reservaDTO.getIdAsiento());
        Usuario usuario = usuarioRepository.getReferenceById(reservaDTO.getIdUsuario());

        reserva.setIdVuelo(vuelo);
        reserva.setIdAsiento(asiento);
        reserva.setIdUsuario(usuario);

        return ReservaMapper.domainToDTO(reservaRepository.save(reserva));
    }

    @Override
    public ReservaDTO deleteReserva(Integer id) throws Exception {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (reservaOptional.isPresent()) {

            Reserva reserva = reservaOptional.get();
            reservaRepository.deleteById(id);

            return ReservaMapper.domainToDTO(reserva);
        } else {
            throw new Exception("No se encontró la reserva con ese id!");
        }
    }
}

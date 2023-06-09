package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.MensajeDTO;
import co.edu.usbcali.aerolinea.dto.ReservaDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.ReservaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT , RequestMethod.DELETE })
@Slf4j
public class ReservaController {
    private final ReservaService reservaService;
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
    @GetMapping("/obtenerReservas")
    public ResponseEntity<List<ReservaDTO>> obtenerReservas() {
        return new ResponseEntity(reservaService.obtenerReservas(), HttpStatus.OK);
    }
    @PostMapping(path = "/agregarReserva",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity agregarReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            return new ResponseEntity(reservaService.agregarReserva(reservaDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/obtenerReservasActivas")
    public ResponseEntity<List<ReservaDTO>> obtenerReservasActivas() {
        return new ResponseEntity(reservaService.obtenerReservasActivas(), HttpStatus.OK);
    }
    @GetMapping("/obtenerReserva/{idReserva}")
    public ResponseEntity<ReservaDTO> obtenerReserva(@PathVariable("idReserva") Integer idReserva) {
        try {
            return new ResponseEntity(reservaService.obtenerReserva(idReserva), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path = "/updateReserva",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            return new ResponseEntity(reservaService.updateReserva(reservaDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteReserva/{idReserva}")
    public ResponseEntity deleteReserva(@PathVariable("idReserva") Integer idReserva) {
        try {
            return new ResponseEntity(reservaService.deleteReserva(idReserva), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}

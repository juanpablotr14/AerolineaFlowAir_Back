package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.AeropuertoDTO;
import co.edu.usbcali.aerolinea.dto.MensajeDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.AeropuertoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/aeropuerto")
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Slf4j
public class AeropuertoController {
    private AeropuertoService aeropuertoService;
    public AeropuertoController(AeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }
    @GetMapping("/obtenerAeropuerto/{idAeropuerto}")
    public ResponseEntity<AeropuertoDTO> obtenerAeropuerto(@PathVariable("idAeropuerto") Integer idAeropuerto) {
        try {
            return new ResponseEntity(aeropuertoService.obtenerAeropuerto(idAeropuerto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/obtenerAeropuertos")
    public ResponseEntity<List<AeropuertoDTO>> obtenerAeropuertos()  throws Exception {
        return new ResponseEntity(aeropuertoService.obtenerAeropuertos(), HttpStatus.OK);
    }

    @GetMapping("/obtenerAeropuertosActivos")
    public ResponseEntity<List<AeropuertoDTO>> obtenerAeropuertosActivos() {
        return new ResponseEntity(aeropuertoService.obtenerAeropuertosActivos(), HttpStatus.OK);
    }

    @PostMapping(path = "/agregarAeropuerto",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity agregarAeropuerto(@RequestBody AeropuertoDTO aeropuertoDTO) {
        try {
            return new ResponseEntity(aeropuertoService.agregarAeropuerto(aeropuertoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path = "/updateAeropuerto",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAeropuerto(@RequestBody AeropuertoDTO aeropuertoDTO) {
        try {
            return new ResponseEntity(aeropuertoService.updateAeropuerto(aeropuertoDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value = "/deleteAeropuerto/{idAeropuerto}")
    public ResponseEntity deleteAeropuerto(@PathVariable("idAeropuerto") Integer idAeropuerto) {
        try {
            return new ResponseEntity(aeropuertoService.deleteAeropuerto(idAeropuerto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}


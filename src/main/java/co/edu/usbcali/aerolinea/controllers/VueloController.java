package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.MensajeDTO;
import co.edu.usbcali.aerolinea.dto.UsuarioDTO;
import co.edu.usbcali.aerolinea.dto.VueloDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.VueloService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/vuelo")
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Slf4j
@JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss.SSS")
public class VueloController{
    private VueloService vueloService;
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }
    @GetMapping("/obtenerVuelo/{idVuelo}")
    public ResponseEntity<VueloDTO> obtenerVuelo(@PathVariable("idVuelo") Integer idVuelo){
        try {
            return new ResponseEntity(vueloService.obtenerVuelo(idVuelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/obtenerVuelos")
    public ResponseEntity<List<VueloDTO>> obtenerVuelos() {
        return new ResponseEntity(vueloService.obtenerVuelos(), HttpStatus.OK);
    }
    @GetMapping("/obtenerVuelosActivos")
    public ResponseEntity<List<VueloDTO>> obtenerVuelosActivos(){
        return new ResponseEntity(vueloService.obtenerVuelosActivos(), HttpStatus.OK);
    }
    @PostMapping(path = "/guardarVuelo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarVuelo(@RequestBody VueloDTO vuelDTO) {
        try {
            return new ResponseEntity(vueloService.guardarVuelo(vuelDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(path = "/updateVuelo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateVuelo(@RequestBody VueloDTO vueloDTO) {
        try {
            return new ResponseEntity(vueloService.updateVuelo(vueloDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteVuelo/{idVuelo}")
    public ResponseEntity deleteVuelo(@PathVariable("idVuelo") Integer idVuelo) {
        try {
            return new ResponseEntity(vueloService.deleteVuelo(idVuelo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}

package co.edu.usbcali.aerolinea.controllers;

import co.edu.usbcali.aerolinea.dto.MensajeDTO;
import co.edu.usbcali.aerolinea.dto.UsuarioDTO;
import co.edu.usbcali.aerolinea.services.Interfaces.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Slf4j
public class UsuariosController {
    private UsuarioService usuarioService;
    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping("/obtenerUsuario/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable("idUsuario") Integer idUsuario) {
        try {
            return new ResponseEntity(usuarioService.obtenerUsuario(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/obtenerUsuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() {
        return new ResponseEntity(usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }
    @GetMapping("/obtenerUsuariosActivos")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosActivos() {
        return new ResponseEntity(usuarioService.obtenerUsuariosActivos(), HttpStatus.OK);
    }
    @PostMapping(path = "/agregarUsuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity agregarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return new ResponseEntity(usuarioService.agregarUsuario(usuarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping(path = "/updateUsuario",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            return new ResponseEntity(usuarioService.updateUsuario(usuarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteUsuario/{idUsuario}")
    public ResponseEntity deleteTrayecto(@PathVariable("idUsuario") Integer idUsuario) {
        try {
            return new ResponseEntity(usuarioService.deleteUsuario(idUsuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(MensajeDTO.builder().mensaje(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}

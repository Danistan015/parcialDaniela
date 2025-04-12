package com.example.parcial.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parcial.model.Usuario;
import com.example.parcial.service.UsuarioService;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {

    private final UsuarioService UsuarioService;

    public UsuarioController(UsuarioService UsuarioService) {
        this.UsuarioService = UsuarioService;
    }

    @PostMapping("/guardar-Usuario")
    public ResponseEntity<String> guardarUsuario(@RequestBody Usuario Usuario){
        try{
            UsuarioService.guardarUsuario(Usuario);
            return ResponseEntity.status(200).body("Usuario guardado exitosamente");
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/Usuario-id/{id}")
    public ResponseEntity<?> obtenerUsuarioById(@PathVariable Integer id) {
        try {
            Usuario Usuario = UsuarioService.obtenerUsuarioById(id);
            return ResponseEntity.status(200).body(Usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return UsuarioService.obtenerUsuarios();
    }

    @PutMapping("/actualizar-Usuario")
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario Usuario) {
        try {
            UsuarioService.actualizarUsuario(Usuario);
            return ResponseEntity.status(200).body("Usuario actualizado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar-Usuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id) {
        try {
            UsuarioService.eliminarUsuario(id);
            return ResponseEntity.status(200).body("Usuario eliminado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}

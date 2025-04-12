package com.example.parcial.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.parcial.model.Usuario;
import com.example.parcial.repository.UsuarioRepository;

import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService {

    private final UsuarioRepository UsuarioRepository;

    public UsuarioService(UsuarioRepository UsuarioRepository){
        this.UsuarioRepository = UsuarioRepository;
    }

    @Transactional
    public void guardarUsuario(Usuario Usuario) {
        Usuario UsuarioAux = UsuarioRepository.obtenerUsuarioById(Usuario.getId());
        if (UsuarioAux != null) {
            throw new IllegalArgumentException("Este usuario ya se encuentra registrado");
        }
        UsuarioRepository.guardarUsuario(Usuario);
    }

    public List<Usuario> obtenerUsuarios(){
        return UsuarioRepository.obtenerUsuarios();
    }
    
    public Usuario obtenerUsuarioById(Integer id) {
        Usuario Usuario = UsuarioRepository.obtenerUsuarioById(id);
        if (Usuario == null) {
            throw new IllegalArgumentException("El Usuario NO se encuentra registrado");
        }
        return Usuario;
    }

    @Transactional
    public void actualizarUsuario(Usuario Usuario) {
        Usuario UsuarioAux = UsuarioRepository.obtenerUsuarioById(Usuario.getId());
        if (UsuarioAux == null) {
            throw new IllegalArgumentException("El Usuario no se puede actualizar");
        }
        UsuarioRepository.actualizarUsuario(Usuario);
        
    }

    @Transactional
    public void eliminarUsuario(Integer id) {
        Usuario UsuarioAux = UsuarioRepository.obtenerUsuarioById(id);
        if (UsuarioAux == null) {
            throw new IllegalArgumentException("El Usuario no se puede eliminar");
        }
        UsuarioRepository.eliminarUsuario(id);
    }
    
}

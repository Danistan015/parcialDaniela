package com.example.parcial.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.parcial.model.Usuario;
import com.example.parcial.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@ActiveProfiles("test")
@SpringBootTest
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository UsuarioRepository;
    
    @Test
    @Transactional
    public void guardarUsuarioTest(){
        Usuario Usuario = new Usuario(4, "Juan", "12345", "juan@example.com", "3000000000", 20);
        UsuarioRepository.guardarUsuario(Usuario);
        assertNotNull(Usuario.getId());
        assertEquals("Juan", Usuario.getNombre());

    }

    @Test
    @Transactional
    public void obtenerUsuarioByIdTest(){
        Usuario Usuario = new Usuario(3, "Ana", "45678", "ana@example.com", "4000000000", 21);
        UsuarioRepository.guardarUsuario(Usuario);
        Usuario UsuarioObtenido = UsuarioRepository.obtenerUsuarioById(Usuario.getId());
        assertNotNull(UsuarioObtenido);
        assertEquals("Ana", UsuarioObtenido.getNombre());
    }

    @Test
    @Transactional
    public void actualizarUsuarioTest(){
        Usuario Usuario = new Usuario(1, "Isa", "3579", "isa@example.com", "6000000000", 59);
        UsuarioRepository.guardarUsuario(Usuario);
        Usuario.setNombre("Isabel");

        UsuarioRepository.actualizarUsuario(Usuario);

        Usuario UsuarioActualizado = UsuarioRepository.obtenerUsuarioById(Usuario.getId());
        assertNotNull(UsuarioActualizado);
        assertEquals("Isabel", UsuarioActualizado.getNombre());
    }

    @Test 
    @Transactional
    public void eliminarUsuarioTest(){
        Usuario Usuario = new Usuario(2, "Abi", "1654", "abi@example.com", "7000000000", 20);
        UsuarioRepository.guardarUsuario(Usuario);
        UsuarioRepository.eliminarUsuario(Usuario.getId());
        Usuario UsuarioEliminado = UsuarioRepository.obtenerUsuarioById(Usuario.getId());
        assertEquals(null, UsuarioEliminado);

    }
}

package com.example.parcial.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.parcial.model.Usuario;
import com.example.parcial.repository.UsuarioRepository;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository UsuarioRepository;
    @InjectMocks
    private UsuarioService UsuarioService;


    private Usuario Usuario1;
    private Usuario Usuario2;
    
    @BeforeEach
    void setUp() {
        Usuario1 = new Usuario(1, "Usuario #1", "11111", "pruebaUno@gmail.com", "12345", 1);
        Usuario2 = new Usuario(2, "Usuario #2", "22222", "pruebaDos@gmail.com", "6789", 2);
    }

    @Test
    void guardarUsuarioTestExitoso(){
        when(UsuarioRepository.obtenerUsuarioById(Usuario1.getId())).thenReturn(null);
        UsuarioService.guardarUsuario(Usuario1);
        verify(UsuarioRepository, times(1)).guardarUsuario(Usuario1);
    }

    @Test
    void guardarUsuarioTestIdDuplicada() {
        when(UsuarioRepository.obtenerUsuarioById(Usuario1.getId())).thenReturn(Usuario1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            UsuarioService.guardarUsuario(Usuario1);
        });

        assertEquals("Este usuario ya se encuentra registrado", exception.getMessage());
        verify(UsuarioRepository, never()).guardarUsuario(Usuario1);
    }

    @Test
    void obtenerUsuarioByIdTestExitoso() {
        when(UsuarioRepository.obtenerUsuarioById(1)).thenReturn(Usuario1);

        Usuario resultado = UsuarioService.obtenerUsuarioById(1);

        assertNotNull(resultado);
        assertEquals("Usuario #1", resultado.getNombre());
        verify(UsuarioRepository, times(1)).obtenerUsuarioById(1);
    }

    @Test
    void obtenerUsuarioByIdTestIdNoExistente(){
        when(UsuarioRepository.obtenerUsuarioById(3)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            UsuarioService.obtenerUsuarioById(3);
        });

        assertEquals("El Usuario NO se encuentra registrado", exception.getMessage());
        verify(UsuarioRepository, times(1)).obtenerUsuarioById(3);
    }

    @Test
    void obtenerUsuariosTestExitoso() {
        List<Usuario> Usuarios = Arrays.asList(Usuario1, Usuario2);
        when(UsuarioRepository.obtenerUsuarios()).thenReturn(Usuarios);

        List<Usuario> resultado = UsuarioService.obtenerUsuarios();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(UsuarioRepository, times(1)).obtenerUsuarios();
    }
    
    @Test
    void actualizarUsuarioTestExito() {
        when(UsuarioRepository.obtenerUsuarioById(Usuario1.getId())).thenReturn(Usuario1);

        UsuarioService.actualizarUsuario(Usuario1);

        verify(UsuarioRepository, times(1)).actualizarUsuario(Usuario1);
    }
    
    @Test
    void actualizarUsuarioTestUsuarioNoExistente() {
        when(UsuarioRepository.obtenerUsuarioById(Usuario1.getId())).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            UsuarioService.actualizarUsuario(Usuario1);
        });

        assertEquals("El Usuario no se puede actualizar", exception.getMessage());
        verify(UsuarioRepository, never()).actualizarUsuario(Usuario1);
    }

    @Test
    void eliminarUsuarioTestExitoso() {
        when(UsuarioRepository.obtenerUsuarioById(Usuario1.getId())).thenReturn(Usuario1);

        UsuarioService.eliminarUsuario(Usuario1.getId());

        verify(UsuarioRepository, times(1)).eliminarUsuario(Usuario1.getId());
    }

    @Test
    void eliminarUsuarioTestUsuarioNoExistente() {
        when(UsuarioRepository.obtenerUsuarioById(Usuario1.getId())).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            UsuarioService.eliminarUsuario(Usuario1.getId());
        });

        assertEquals("El Usuario no se puede eliminar", exception.getMessage());
        verify(UsuarioRepository, never()).eliminarUsuario(Usuario1.getId());
    }

}

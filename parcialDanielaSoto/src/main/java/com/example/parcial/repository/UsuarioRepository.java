package com.example.parcial.repository;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.example.parcial.model.Usuario;

import jakarta.persistence.*;


@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void guardarUsuario(Usuario usuario){
        entityManager.persist(usuario);
    }

    public Usuario obtenerUsuarioById(Integer id){
        return entityManager.find(Usuario.class, id);
    }

    public List<Usuario> obtenerUsuarios(){
        return entityManager.createQuery("Select c FROM Usuario c", Usuario.class).getResultList();
    }

    public void actualizarUsuario(Usuario usuario){
        entityManager.merge(usuario);
    }
    
    public void eliminarUsuario(Integer id){
        Usuario usuario = obtenerUsuarioById(id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }
}

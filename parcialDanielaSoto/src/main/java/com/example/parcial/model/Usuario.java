package com.example.parcial.model;
import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    private Integer id; //id de la tabla autoincrementable

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String cedula;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private int edad;

    public Usuario(){

    }

    public Usuario(Integer id, String nombre, String cedula, String correo, String telefono, int edad){
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.edad = edad;
    }

    //get & set
    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public String getNombre() { 
        return nombre; 
    }

    public void setNombre(String nombre) {
         this.nombre = nombre; 
    }

    public String getCedula() { 
        return cedula; 
    }
    public void setCedula(String cedula) { 
        this.cedula = cedula; 
    }

    public String getTelefono() { 
        return telefono; 
    }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }

    public String getCorreo() { 
        return correo; 
    }
    public void setCorreo(String correo) { 
        this.correo = correo; 
    }

    public int getEdad(){
        return edad;
    }
    public void setEdad(int edad){
        this.edad = edad;
    }
}
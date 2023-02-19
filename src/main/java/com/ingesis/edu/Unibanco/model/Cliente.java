package com.ingesis.edu.Unibanco.model;

import com.ingesis.edu.Unibanco.exceptions.ValorRequeridoException;

import java.util.Objects;

public class Cliente {

    private String nombre;
    private String apellidos;
    private String cedula;
    private String direccion;
    private String email;


    public Cliente(String nombre, String apellidos, String cedula, String direccion, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.direccion = direccion;
        this.email = email;
    }

    public static Cliente of(String nombre, String apellidos, String cedula, String direccion, String email) throws ValorRequeridoException{
        if(Objects.requireNonNull(nombre, "El nombre es requerido").isEmpty()){
            throw new ValorRequeridoException("numero de identificación");
        }
        if(Objects.requireNonNull(apellidos, "El apellido es requerido").isEmpty()){
            throw new ValorRequeridoException("apellido");
        }
        if(Objects.requireNonNull(cedula, "La cedula es requerida").isEmpty()){
            throw new ValorRequeridoException("cedula");
        }
        if(Objects.requireNonNull(direccion, "La direccion es requerida").isEmpty()){
            throw new ValorRequeridoException("direccion");
        }
        if (Objects.requireNonNull(email, "El email es requerido").isEmpty()){
            throw new ValorRequeridoException("email");
        }
        return new Cliente(nombre, apellidos, cedula, direccion, email);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

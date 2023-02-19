package com.ingesis.edu.Unibanco.utils;

import com.ingesis.edu.Unibanco.model.Cliente;

import java.util.function.Predicate;

public class ClienteUtil {

    public static Predicate<Cliente> buscarPorNombre(String nombre){
        return cliente -> cliente.getNombre().equals(nombre);
    }

    public static Predicate<Cliente> buscarPorApellido(String apellido){
        return cliente -> cliente.getApellidos().equals(apellido);
    }

    public static Predicate<Cliente> buscarPorCedula(String cedula){
        return cliente -> cliente.getCedula().equals(cedula);
    }

    public static Predicate<Cliente> buscarPorDireccion(String direccion){
        return cliente -> cliente.getDireccion().equals(direccion);
    }

    public static Predicate<Cliente> buscarPorCorreo(String correo){
        return cliente -> cliente.getEmail().equals(correo);
    }

    public static Predicate<Cliente> buscarPorTodo(String nombre, String apellido, String cedula, String direccion, String correo){
        Predicate<Cliente> predicado = cliente -> true;
        if (nombre != null && !nombre.isEmpty()){
            predicado = predicado.and(buscarPorNombre(nombre));
        }
        if (apellido != null && !cedula.isEmpty()){
            predicado = predicado.and(buscarPorApellido(apellido));
        }
        if (cedula != null && !cedula.isEmpty()){
            predicado = predicado.and(buscarPorCedula(cedula));
        }
        if (direccion != null && !direccion.isEmpty()){
            predicado = predicado.and(buscarPorDireccion(direccion));
        }
        if (correo != null && !correo.isEmpty()){
            predicado = predicado.and(buscarPorCorreo(correo));
        }
        return predicado;
    }
}

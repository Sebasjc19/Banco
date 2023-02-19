package com.ingesis.edu.Unibanco.model;

import com.ingesis.edu.Unibanco.exceptions.PersonaExisteException;
import com.ingesis.edu.Unibanco.utils.CuentaUtil;
import com.ingesis.edu.Unibanco.utils.TextUtil;
import com.ingesis.edu.Unibanco.utils.TransaccionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Banco {
    private String nombre;
    private String nit;
    private static List<Cuenta> cuentas;
    private List<Transaccion> transacciones;
    private Cuenta cuentaSeleccionada;

    public Banco(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
        cuentas = new ArrayList<>();
        transacciones = new ArrayList<>();
    }

    public void addCuenta(Cuenta cuenta) throws PersonaExisteException {
        if( buscarCuentaNumeroCuenta(cuenta.getNumeroCuenta()).isPresent()){
            throw new PersonaExisteException();
        }
        if (buscarClienteNumeroIdentificacion(cuenta.getCliente().getCedula()).isPresent()){
            throw  new PersonaExisteException();
        }
        if (buscarClienteEmail(cuenta.getCliente().getEmail()).isPresent()){
            throw new PersonaExisteException();
        }
        cuentas.add(cuenta);
    }

    public Optional<Cuenta> buscarCuentaNumeroCuenta(String numeroCuenta){
        return cuentas.stream().filter(CuentaUtil.buscarPorNumeroCuenta(numeroCuenta)).findFirst();
    }

    public Optional<Cuenta> buscarClienteEmail(String email){
        return cuentas.stream().filter(cuenta -> cuenta.getCliente().getEmail().equals(email)).findFirst();
    }
    public Optional<Cuenta> buscarClienteNumeroIdentificacion(String numeroIdentificacion) {
        return cuentas.stream()
                .filter(cuenta -> cuenta.getCliente().getCedula().equals(numeroIdentificacion))
                .findFirst();
    }


    public void addTransaccion(Cuenta cuenta, Double valor, TipoTransaccion tipoTransaccion){
        Transaccion transaccion = new Transaccion(valor, tipoTransaccion, TextUtil.obtenerFecha() ,cuenta);
        cuenta.addTransaccion(transaccion);
        transacciones.add(transaccion);
    }

    public static void removeCuenta(Cuenta cuenta) {
        cuentas.remove(cuenta);}

    public List<Cuenta> buscar (String numeroCuenta, Double saldo, TipoCuenta tipoCuenta){
        return cuentas.stream().filter(CuentaUtil.buscarPorTodo(numeroCuenta, saldo, tipoCuenta))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Transaccion> buscarTransacciones(String numeroCuenta, Estado estado, String fecha, String hora, TipoTransaccion tipo, Double valor){
        return transacciones.stream().filter(TransaccionUtil.buscarPorTodo(numeroCuenta, estado, fecha, hora, tipo, valor))
                .collect(Collectors.toUnmodifiableList());
    }

    public void modificarCliente(String nombre, String apellidos, String direccion, String correo){
        cuentaSeleccionada.getCliente().setNombre(nombre);
        cuentaSeleccionada.getCliente().setApellidos(apellidos);
        cuentaSeleccionada.getCliente().setDireccion(direccion);
        cuentaSeleccionada.getCliente().setEmail(correo);
    }

    public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
        this.cuentaSeleccionada = cuentaSeleccionada;
    }
}

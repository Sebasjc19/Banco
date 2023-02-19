package com.ingesis.edu.Unibanco.model;

import com.ingesis.edu.Unibanco.exceptions.ValorNoValidoException;
import com.ingesis.edu.Unibanco.exceptions.ValorRequeridoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cuenta {
    private String numeroCuenta;
    private Double saldo;
    private TipoCuenta tipoCuenta;
    private Cliente cliente;
    private Double cantidadPrestamo;
    private List<Transaccion> transacciones;

    public Cuenta(String numeroCuenta, Double saldo, TipoCuenta tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        cantidadPrestamo=0.0;
        transacciones = new ArrayList<>();
    }


    public static Cuenta of(String numeroCuenta, TipoCuenta tipoCuenta) throws ValorRequeridoException {
        if (Objects.requireNonNull(numeroCuenta, "Se requiere un numero de cuenta").isEmpty()){
            throw new ValorRequeridoException("numero de cuenta");
        }
        if (tipoCuenta == null){
            throw new ValorRequeridoException("tipo de cuenta");
        }
        return new Cuenta(numeroCuenta, 0.0, tipoCuenta);
    }

    public void depositar(Double valor) throws ValorNoValidoException {
        if(valor <= 0.0){
            throw new ValorNoValidoException(String.valueOf(valor));
        }
        saldo+=valor;
    }

    public void retirar(Double valor) throws ValorNoValidoException {
       if(valor>saldo){
           throw new ValorNoValidoException(String.valueOf(valor));
       }
        saldo-=valor;
    }
    public void solicitar(Double valor) throws ValorNoValidoException {
        if ((valor+saldo)>1000000){
            throw new ValorNoValidoException(String.valueOf(valor));
        }
        cantidadPrestamo+=valor;
        saldo+=valor;
    }

    public void addTransaccion(Transaccion transaccion){
        transacciones.add(transaccion);
    }
    //Getters & Setters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getCantidadPrestamo() {
        return cantidadPrestamo;
    }

    public void setCantidadPrestamo(Double cantidadPrestamo) {
        this.cantidadPrestamo = cantidadPrestamo;
    }
}


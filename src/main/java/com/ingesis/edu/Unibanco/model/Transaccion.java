package com.ingesis.edu.Unibanco.model;

import com.ingesis.edu.Unibanco.utils.TextUtil;

public class Transaccion {
    private Double valor;
    private String hora;
    private TipoTransaccion tipoTransaccion;
    private String fecha;
    private Estado estado;
    private Cuenta cuenta;
    private String numeroCuenta;

    public Transaccion(Double valor, TipoTransaccion tipoTransaccion, String fecha, Cuenta cuenta) {
        this.valor = valor;
        this.hora = TextUtil.obtenerHora();
        this.tipoTransaccion = tipoTransaccion;
        this.fecha = fecha;
        this.estado = comprobar(cuenta, valor, tipoTransaccion);
        this.cuenta = cuenta;
        numeroCuenta = cuenta.getNumeroCuenta();
    }

    public Estado comprobar(Cuenta cuenta, Double valor, TipoTransaccion tipoTransaccion) {
        Estado estado = Estado.FALLIDO;
        if (tipoTransaccion == TipoTransaccion.DEPOSITAR){
            try {
                cuenta.depositar(valor);
                estado = Estado.EXITOSO;
            } catch (Exception e) {
                estado = Estado.FALLIDO;
                return estado;
            }
        }
        if (tipoTransaccion == TipoTransaccion.RETIRAR){
            try{
                cuenta.retirar(valor);
                estado = Estado.EXITOSO;
            } catch (Exception e) {
                estado = Estado.SINFONDOS;
                return estado;
            }
        }
        if (tipoTransaccion == TipoTransaccion.SOLICITAR){
            try {
                cuenta.solicitar(valor);
                estado = Estado.EXITOSO;
            }catch (Exception e){
                estado = Estado.FALLIDO;
                return estado;
            }
        }
        return estado;
    }

    public Double getValor() {
        return valor;
    }

    public String getHora() {
        return hora;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}

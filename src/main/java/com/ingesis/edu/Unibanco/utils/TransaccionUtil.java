package com.ingesis.edu.Unibanco.utils;

import com.ingesis.edu.Unibanco.model.Estado;
import com.ingesis.edu.Unibanco.model.TipoTransaccion;
import com.ingesis.edu.Unibanco.model.Transaccion;

import java.util.function.Predicate;

public class TransaccionUtil {

    public static Predicate<Transaccion> buscarPorNumeroCuenta(String numeroCuenta){
        return transaccion -> transaccion.getCuenta().getNumeroCuenta().equals(numeroCuenta);
    }

    public static Predicate<Transaccion> buscarPorEstado(Estado estado){
        return transaccion -> transaccion.getEstado().equals(estado);
    }

    public static Predicate<Transaccion> buscarPorFecha(String fecha){
        return transaccion -> transaccion.getFecha().equals(fecha);
    }

    public static Predicate<Transaccion> buscarPorHora(String hora){
        return transaccion -> transaccion.getHora().equals(hora);
    }

    public static Predicate<Transaccion> buscarPorTipo(TipoTransaccion tipo){
        return transaccion -> transaccion.getTipoTransaccion().equals(tipo);
    }

    public static Predicate<Transaccion> buscarPorValor(Double valor){
        return transaccion -> transaccion.getValor().equals(valor);
    }

    public static Predicate<Transaccion> buscarPorTodo(String numeroCuenta, Estado estado, String fecha, String hora, TipoTransaccion tipo, Double valor){
        Predicate<Transaccion> predicado = transaccion -> true;
        if(numeroCuenta != null && !numeroCuenta.isEmpty()){
            predicado = predicado.and(buscarPorNumeroCuenta(numeroCuenta));
        }
        if (estado != null){
            predicado = predicado.and(buscarPorEstado(estado));
        }
        if (fecha != null && !fecha.isEmpty()){
            predicado = predicado.and(buscarPorFecha(fecha));
        }
        if (hora != null && !hora.isEmpty()){
            predicado = predicado.and(buscarPorHora(hora));
        }
        if (tipo != null){
            predicado = predicado.and(buscarPorTipo(tipo));
        }
        if (valor != null){
            predicado = predicado.and(buscarPorValor(valor));
        }
        return predicado;
    }
}

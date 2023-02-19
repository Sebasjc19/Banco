package com.ingesis.edu.Unibanco.utils;

import com.ingesis.edu.Unibanco.model.Cuenta;
import com.ingesis.edu.Unibanco.model.TipoCuenta;

import java.util.Objects;
import java.util.function.Predicate;

public class CuentaUtil {

    public static Predicate<Cuenta> buscarPorNumeroCuenta(String numeroCuenta){
        return cuenta -> cuenta.getNumeroCuenta().equals(numeroCuenta);
    }

    public static Predicate<Cuenta> buscarPorSaldo(Double saldo){
        return cuenta -> Objects.equals(cuenta.getSaldo(), saldo);
    }

    public static Predicate<Cuenta> buscarPorTipoCuenta(TipoCuenta tipoCuenta){
        return cuenta -> cuenta.getTipoCuenta().equals(tipoCuenta);
    }

    public static Predicate<Cuenta> buscarPorTodo(String numeroCuenta, Double saldo, TipoCuenta tipoCuenta){
        Predicate<Cuenta> predicado = cuenta -> true;
        if (numeroCuenta != null && !numeroCuenta.isEmpty()){
            predicado = predicado.and(buscarPorNumeroCuenta(numeroCuenta));
        }
        if (saldo != null){
            predicado = predicado.and(buscarPorSaldo(saldo));
        }
        if (tipoCuenta != null){
            predicado = predicado.and(buscarPorTipoCuenta(tipoCuenta));
        }
        return predicado;
    }
}

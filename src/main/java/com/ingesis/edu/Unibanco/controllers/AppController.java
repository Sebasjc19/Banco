package com.ingesis.edu.Unibanco.controllers;

import com.ingesis.edu.Unibanco.model.Banco;

public enum AppController {
    INSTANCE;

    private final Banco banco;

    AppController(){
        banco = new Banco("Unibanco","1234567890");
    }

    public Banco getBanco(){
        return banco;
    }
}

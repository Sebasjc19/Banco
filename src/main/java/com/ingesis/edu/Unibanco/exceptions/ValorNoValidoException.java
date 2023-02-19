package com.ingesis.edu.Unibanco.exceptions;

public class ValorNoValidoException extends Exception{

    public ValorNoValidoException(String valor){
            super(String.format("El %s no fue permitido", valor));
        }
}

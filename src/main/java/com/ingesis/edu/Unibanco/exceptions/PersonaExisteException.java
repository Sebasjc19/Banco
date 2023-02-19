package com.ingesis.edu.Unibanco.exceptions;

public class PersonaExisteException extends Exception{

    public PersonaExisteException(){
        super("La persona ya existe en el sistema :)");
    }
}

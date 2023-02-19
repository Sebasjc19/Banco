package com.ingesis.edu.Unibanco.exceptions;

public class PersonaNoExiste extends Exception{
    public PersonaNoExiste(){
        super("La persona ya existe en el sistema de oportunidades :)");
    }
}

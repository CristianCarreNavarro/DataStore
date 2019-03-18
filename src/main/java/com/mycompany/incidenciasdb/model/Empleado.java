/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.incidenciasdb.model;

/**
 *
 * @author CRISTIAN
 */
public class Empleado {
    
    private Long id;
    private String nombre;
    private String pass;
    private int edad;

    
    //ENTER KEYS
  public static final String NOMBRE = "nombre";
  public static final String PASS = "pass";
  public static final String EDAD = "edad";

    //FINAL KEYS
    
     public Empleado() {
    }


    public Empleado( String nombre, String pass, int edad) {
 
        this.nombre = nombre;
        this.pass = pass;
        this.edad = edad;
     
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }



    
    
    
}

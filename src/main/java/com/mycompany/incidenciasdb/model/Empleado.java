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
    
     private Integer id;
    private String nombre;
    private String pass;
    private int edad;
        private String eventos;
    
    
    
    
     public Empleado() {
    }

    public Empleado(Integer id, String nombre, String pass, int edad, String eventos) {
        this.id = id;
        this.nombre = nombre;
        this.pass = pass;
        this.edad = edad;
        this.eventos = eventos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEventos() {
        return eventos;
    }

    public void setEventos(String eventos) {
        this.eventos = eventos;
    }

    
    
    
}

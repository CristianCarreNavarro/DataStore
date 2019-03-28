/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.incidenciasdb.model;

import com.google.cloud.datastore.Key;
import java.util.Date;

/**
 *
 * @author CRISTIAN
 */
public class Incidencia implements Comparable<Incidencia>  {

    private Long id;
    private String nombreEmpleado;

    private Date fecha;
    private boolean urgencia;
    private Empleado origen;
    private Empleado destino;
//true si es urgente, false si es normal
    //ENTER KEYS
    public static final String ID = "id";
    public static final String NOMBRE_EMPLEADO = "nombreEmpleado";
    public static final String FECHA = "fecha";
    public static final String URGENCIA = "urgencia";
    public static final String ORIGEN = "origen";
    public static final String DESTINO = "destino";

    //FINAL KEYS
    public Incidencia() {
    }

    public Incidencia(String nEmpleado, Date fecha, boolean urgencia, Empleado origen, Empleado destino) {
   
        this.nombreEmpleado = nEmpleado;
        this.fecha = fecha;
        this.urgencia = urgencia;
        this.origen = origen;
        this.destino = destino;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isUrgencia() {
        return urgencia;
    }

    public void setUrgencia(boolean urgencia) {
        this.urgencia = urgencia;
    }

    public Empleado getOrigen() {
        return origen;
    }

    public void setOrigen(Empleado origen) {
        this.origen = origen;
    }

    public Empleado getDestino() {
        return destino;
    }

    public void setDestino(Empleado destino) {
        this.destino = destino;
    }

    @Override
    public int compareTo(Incidencia incidencia) {
        
                return (int) (this.getFecha().getTime() - incidencia.getFecha().getTime());

        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.incidenciasdb;

import com.google.cloud.datastore.Datastore;
import com.mycompany.incidenciasdb.DAO.DatastoreDao;
import com.mycompany.incidenciasdb.model.Empleado;
import com.mycompany.incidenciasdb.model.Incidencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * //
 *
 *
 * @author CRISTIAN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Datastore datastore;
        DatastoreDao datastoreDao = new DatastoreDao();
        int edad = 17;
        Date date = new Date();
        Long edadLong = new Long(edad);

        Empleado empleado1 = new Empleado("Manolo", "1234", edadLong);
        datastoreDao.insertEmpleado(empleado1);
        Empleado e = datastoreDao.getEmpleado(empleado1.getNombre());
 


        

        
        
    
    
                
   


        //System.out.println("\n ID EMPLEADO= "+e.getId()+"\n");
        Incidencia incidencia1 = new Incidencia("Manuel", new Date(), false, e, e);

        datastoreDao.insertIncidencia(incidencia1);
        //datastoreDao.getIncidenciaById(incidencia1.getId());
        datastoreDao.updateEdad(e, 9999);

        //datastoreDao.removeEmpleado(e);
//        System.out.println("nombre empleado="+ e.getNombre());
        // datastoreDao.entityToEmpleado(entity);
    }

    //CONSULTAR SI EXISTE ANTES EL USERNAME Y A TRAVÉS DE JAVA Y SI EXISTE NO PERMITIRLE DARLE DE ALTA
    //CONSULTA MEDIANTE QUERY CON FILTROS
}

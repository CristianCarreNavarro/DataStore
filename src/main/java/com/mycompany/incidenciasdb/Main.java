/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.incidenciasdb;

import com.google.cloud.datastore.Datastore;
import com.mycompany.incidenciasdb.DAO.DatastoreDao;
import com.mycompany.incidenciasdb.model.Empleado;



/**
 *
 * @author CRISTIAN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Datastore datastore;
        int edad = 14;
        Long edadLong = new Long(edad);
        
        Empleado empleado1 = new Empleado("Javi", "12345", edadLong);
        
        DatastoreDao datastoreDao = new DatastoreDao();
        
        
        //datastoreDao.insertEmpleado(empleado1);
        
        Empleado e = datastoreDao.getEmpleado(empleado1.getNombre());
  
        datastoreDao.updateEmpleado(e, edadLong, "234567");
       
         //datastoreDao.removeEmpleado(e);
         
         
//        System.out.println("nombre empleado="+ e.getNombre());
       // datastoreDao.entityToEmpleado(entity);
    }
    
    //CONSULTAR SI EXISTE ANTES EL USERNAME Y A TRAVÉS DE JAVA Y SI EXISTE NO PERMITIRLE DARLE DE ALTA
    //CONSULTA MEDIANTE QUERY CON FILTROS
    
}

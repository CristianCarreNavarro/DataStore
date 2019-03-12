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
        
            Empleado empleado1 = new Empleado("Pepe", "12345", 25, "evento1");
        
        DatastoreDao datastoreDao = new DatastoreDao();
        
        datastoreDao.insertEmpleado(empleado1);
        
        datastoreDao.entityToEmpleado(entity);
    }
    
}

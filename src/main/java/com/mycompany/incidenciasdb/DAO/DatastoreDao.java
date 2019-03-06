/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.incidenciasdb.DAO;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import com.mycompany.incidenciasdb.model.Empleado;
import com.mycompany.incidenciasdb.model.Evento;
import com.mycompany.incidenciasdb.model.Incidencia;
import com.mycompany.incidenciasdb.model.RankingTO;
import java.awt.print.Book;
import java.util.List;

/**
 *
 * @author CRISTIAN
 */

public class DatastoreDao implements DAOInterface {

  // [START constructor]
  private Datastore datastore;
  private KeyFactory keyFactory;

  
  
  public DatastoreDao() {
    datastore = DatastoreOptions.getDefaultInstance().getService(); // Authorized Datastore service
    keyFactory = datastore.newKeyFactory().setKind("Empleado");      // Is used for creating keys later
  
  }

  
 
  public Empleado entityToEmpleado(Entity entity) {
      
    String nombre=entity.getString(Empleado.NOMBRE);
    String pass=entity.getString(Empleado.PASS);
    int edad=Integer.parseInt(entity.getString(Empleado.EDAD));
    String eventos=entity.getString(Empleado.EVENTOS);

    Empleado empleado1 = new Empleado(edad, nombre, pass, edad, eventos);

      return  empleado1;                              
  }
  
    @Override
    public void insertEmpleado(Empleado e) {
       
       IncompleteKey key = keyFactory.newKey();          // Key will be assigned once written
       FullEntity<IncompleteKey> EmpleadoEntity = Entity.newBuilder(key)  // Create the Entity
      .set(Empleado.NOMBRE, e.getNombre())           // Add Property ("author", book.getAuthor())
      .set(Empleado.PASS, e.getPass())
      .set(Empleado.EDAD, e.getEdad())
      .set(Empleado.EVENTOS, e.getEventos())
      .build();
  Entity entity1 = datastore.add(EmpleadoEntity); // Save the Entity  
        
    }

    @Override
    public boolean loginEmpleado(String user, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEmpleado(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Incidencia getIncidenciaById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Incidencia> selectAllIncidencias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertIncidencia(Incidencia i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Incidencia> getIncidenciaByDestino(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Incidencia> getIncidenciaByOrigen(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarEvento(Evento e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Evento getUltimoInicioSesion(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RankingTO> getRankingEmpleados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

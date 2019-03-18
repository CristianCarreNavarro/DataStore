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
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;

import com.google.cloud.datastore.StructuredQuery.Filter;
import com.google.datastore.v1.RunQueryRequest;
import com.google.datastore.v1.RunQueryResponse;

import com.mycompany.incidenciasdb.model.Empleado;
import com.mycompany.incidenciasdb.model.Evento;
import com.mycompany.incidenciasdb.model.Incidencia;
import com.mycompany.incidenciasdb.model.RankingTO;

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

        //poner el id del dataStore
        keyFactory = datastore.newKeyFactory().setKind("Empleado");      // Is used for creating keys later

    }

    public Empleado entityToEmpleado(Entity entity) {

        String nombre = entity.getString(Empleado.NOMBRE);
        String pass = entity.getString(Empleado.PASS);
        int edad = Integer.parseInt(entity.getString(Empleado.EDAD));
 

        Empleado empleado1 = new Empleado(nombre, pass, edad);

        return empleado1;
    }

    @Override
    public Long insertEmpleado(Empleado e) {

        IncompleteKey key = keyFactory.newKey();          // Key will be assigned once written
        FullEntity<IncompleteKey> EmpleadoEntity = Entity.newBuilder(key) // Create the Entity
                .set(Empleado.NOMBRE, e.getNombre()) // Add Property ("author", book.getAuthor())
                .set(Empleado.PASS, e.getPass())
                .set(Empleado.EDAD, e.getEdad())
                .build();

        Entity empleado = datastore.add(EmpleadoEntity);
        return empleado.getKey().getId();
    }

    public Empleado getEmpleado(String nombre) {

//        Filter filtro = new FilterPredicate("nombre", FilterOperator.EQUAL, nombre);
//        Query q = new Query("Empleado").setFilter(filtro);
        Query<Entity> q = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, "select * from Empleado").build();
        QueryResults<Entity> results = datastore.run(q);
        while (results.hasNext()) {
            Entity entity = results.next();
            System.out.println(entity);
//           Empleado e = entityToEmpleado(entity);
//            System.out.println(e);
        }

        return null;
    }
//    
//    public Empleado getEmpleado(String nombre){
//    Entity entity1 = datastore.get(keyFactory.newKey(nombre));
//    return entityToEmpleado(entity1);
//    }

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

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
import com.google.cloud.datastore.Key;

import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;

import com.google.cloud.datastore.StructuredQuery.Filter;
import com.google.datastore.v1.RunQueryRequest;
import com.google.datastore.v1.RunQueryResponse;
import com.google.protobuf.TextFormat.ParseException;

import com.mycompany.incidenciasdb.model.Empleado;
import com.mycompany.incidenciasdb.model.Evento;
import com.mycompany.incidenciasdb.model.Incidencia;
import com.mycompany.incidenciasdb.model.RankingTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CRISTIAN
 */
public class DatastoreDao implements DAOInterface {

    // [START constructor]
    private Datastore datastore;
    private Key keyFactory;
    private Key keyFactory2;
    private int id = 0;

    public DatastoreDao() {
        datastore = DatastoreOptions.getDefaultInstance().getService(); // Authorized Datastore service

    }

    public Empleado entityToEmpleado(Entity entity) {
        String nombre = entity.getString(Empleado.NOMBRE);
        String pass = entity.getString(Empleado.PASS);
        Long edad = entity.getLong(Empleado.EDAD);
        Empleado newempleado = new Empleado(nombre, pass, edad);
        return newempleado;
    }

    //Todo probar esto, posibles cosas que no van:
    // Incidencia tiene key como id, quiza no se puede usar? si funciona deberiamos poner 
    public Incidencia entityToIncidencia(Entity entity) throws ParseException {

        String nombreEmpleado = entity.getString(Incidencia.NOMBRE_EMPLEADO);
        String date = entity.getString(Incidencia.FECHA);
        Date newDate = null;
        try {
            newDate = new SimpleDateFormat("E L dd ").parse(date);
            
        } catch (java.text.ParseException ex) {
            System.out.println("Error de parse Date");
        }
        boolean urgencia = entity.getBoolean(Incidencia.URGENCIA);

        String nombreOrigen = entity.getString(Incidencia.ORIGEN);
        Empleado EmpleadoOrigen = getEmpleado(nombreOrigen);

        String nombreDestino = entity.getString(Incidencia.DESTINO);
        Empleado EmpleadoDestino = getEmpleado(nombreDestino);

        Incidencia newIncidencia = new Incidencia(nombreEmpleado, newDate, urgencia, EmpleadoOrigen, EmpleadoDestino);
        return newIncidencia;
    }

//BORRAR Y UPDATE
    @Override
    public void insertEmpleado(Empleado empleado) {
        keyFactory = datastore.newKeyFactory().setKind("Empleado").newKey(empleado.getNombre());
        IncompleteKey key = keyFactory;          // Key will be assigned once written
        FullEntity<IncompleteKey> Entity1 = Entity.newBuilder(key) // Create the Entity
                .set(Empleado.NOMBRE, empleado.getNombre()) // Add Property ("author", book.getAuthor())
                .set(Empleado.PASS, empleado.getPass())
                .set(Empleado.EDAD, empleado.getEdad())
                .build();
        datastore.put(Entity1);
    }

    public long putId() {
        List<Incidencia> listIncidencias = new ArrayList<Incidencia>();
        listIncidencias = selectAllIncidencias();
        listIncidencias.sort(null);

        Incidencia incidencia = listIncidencias.get(0); 
        long ultimoID=incidencia.getId();
        
        long nuevoId = ultimoID+1;
        
        return nuevoId;
    }

    @Override
    public void insertIncidencia(Incidencia incidencia) {
        keyFactory2 = datastore.newKeyFactory().setKind("Incidencia").newKey(putId());

        IncompleteKey key = keyFactory2;
        FullEntity<IncompleteKey> Entity1 = Entity.newBuilder(key) // Create the Entity
                .set(Incidencia.NOMBRE_EMPLEADO, incidencia.getNombreEmpleado())
                .set(Incidencia.FECHA, incidencia.getFecha().toString())
                .set(Incidencia.URGENCIA, incidencia.isUrgencia())
                .set(Incidencia.ORIGEN, incidencia.getOrigen().toString())
                .set(Incidencia.DESTINO, incidencia.getDestino().toString())
                .build();
        datastore.add(Entity1);
    }

    public Empleado getEmpleado(String nombre) {
        Query<Entity> q = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, "select * from Empleado where nombre=@nombre")
                .setBinding("nombre", nombre)
                .build();
        QueryResults<Entity> results = datastore.run(q);
        while (results.hasNext()) {
            Entity entity = results.next();
            Empleado empleado1 = entityToEmpleado(entity);
//            System.out.println(empleado1);
            return empleado1;
        }
        return null;
    }

    @Override
    public Incidencia getIncidenciaById(Long id) {
        Query<Entity> q = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, "select * from Incidencias where id=@id")
                .setBinding("id", id)
                .build();
        QueryResults<Entity> results = datastore.run(q);
        while (results.hasNext()) {
            Entity entity = results.next();
            try {
                Incidencia incidencia = entityToIncidencia(entity);
                return incidencia;
            } catch (ParseException ex) {
                Logger.getLogger(DatastoreDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public List<Incidencia> selectAllIncidencias() {
        List<Incidencia> list = new ArrayList<Incidencia>();
        Query<Entity> q = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, "select * from Incidencia")
                .build();
        QueryResults<Entity> results = datastore.run(q);
        while (results.hasNext()) {
            try {
                Entity entity = results.next();
                Incidencia incidence = entityToIncidencia(entity);
                list.add(incidence);
            } catch (ParseException ex) {
                Logger.getLogger(DatastoreDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public void updateEdad(Empleado e, int newAge) {
        Long age = new Long(newAge);
        removeEmpleado(e);
        Empleado updatedEmpleado = new Empleado(e.getNombre(), e.getPass(), age);
        insertEmpleado(updatedEmpleado);
    }

    @Override
    public void updatePassword(Empleado e, String newPwd) {
        removeEmpleado(e);
        Empleado updatedEmpleado = new Empleado(e.getNombre(), newPwd, e.getEdad());
        insertEmpleado(updatedEmpleado);
    }

    @Override
    public boolean loginEmpleado(String user, String pass) {
        Query<Entity> q = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, "select * from Empleado where nombre='" + user + "' and pass='" + pass + "'").build();
        QueryResults<Entity> results = datastore.run(q);
        while (results != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmpleado(Empleado e) {
        Query<Entity> q = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, "select * from Empleado where nombre=@nombre and pass=@pass")
                .setBinding("nombre", e.getNombre())
                .setBinding("pass", e.getPass())
                .build();
        QueryResults<Entity> results = datastore.run(q);

        if (results != null) {
            Entity entidadUser = results.next();
            datastore.delete(entidadUser.getKey());
            return true;
        } else {
            return false;
        }

    }

    //selecciona por nombre, si se cambia key empleado debera seleccionar por id
    @Override
    public List<Incidencia> getIncidenciaByDestino(Empleado e) {
        List<Incidencia> list = new ArrayList<Incidencia>();
        Query<Entity> q = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, "select * from Incidencias where destino=@destino")
                .setBinding("@destino", e.getNombre())
                .build();
        QueryResults<Entity> results = datastore.run(q);
        while (results.hasNext()) {
            try {
                Entity entity = results.next();
                Incidencia incidence = entityToIncidencia(entity);
                list.add(incidence);
            } catch (ParseException ex) {
                Logger.getLogger(DatastoreDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public List<Incidencia> getIncidenciaByOrigen(Empleado e) {
        List<Incidencia> list = new ArrayList<Incidencia>();
        Query<Entity> q = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, "select * from Incidencias where origen=@origen")
                .setBinding("@origen", e.getNombre())
                .build();
        QueryResults<Entity> results = datastore.run(q);
        while (results.hasNext()) {
            try {
                Entity entity = results.next();
                Incidencia incidence = entityToIncidencia(entity);
                list.add(incidence);
            } catch (ParseException ex) {
                Logger.getLogger(DatastoreDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
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

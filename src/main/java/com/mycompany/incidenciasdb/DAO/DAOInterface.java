/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.incidenciasdb.DAO;


import com.google.cloud.datastore.Key;
import java.util.List;
import com.mycompany.incidenciasdb.model.Empleado;
import com.mycompany.incidenciasdb.model.Evento;
import com.mycompany.incidenciasdb.model.Incidencia;
import com.mycompany.incidenciasdb.model.RankingTO;


public  interface DAOInterface {


    // Método para insertar un nuevo empleado.
    public void insertEmpleado(Empleado e);

    // Método para validar el login de un empleado.
    public boolean loginEmpleado(String user, String pass);

    // Método para modificar la edad de un empleado.
    public void updateEdad(Empleado e, int newAge);
    
    // Método para modificar el password de un empleado.
    public void updatePassword(Empleado e, String newPwd);

    // Método para eliminar un empleado.
     public boolean removeEmpleado(Empleado e);

    // Obtener una Incidencia a partir de su Id.
    public Incidencia getIncidenciaById(Long id);

    // Obtener una lista de todas las incidencias
    public List<Incidencia> selectAllIncidencias();

    // Insertar una incidencia a partir de un objeto incidencia
    public void insertIncidencia(Incidencia i);

    // Obtener la lista de incidencias con destino un determinado
    // empleado, a partir de un objeto empleado.
    public List<Incidencia> getIncidenciaByDestino(Empleado e);

    // Obtener la lista de incidencias con origen un determinado
    // empleado, a partir de un objeto empleado.
    public List<Incidencia> getIncidenciaByOrigen(Empleado e);

    // Método para insertar un evento en la tabla historial.
    // Pasaremos como parámetro un objeto tipo evento, y no devolverá nada.
    // Llamaremos a este método desde los métodos
    // que producen los eventos, que son 3:
    // 1) Cuando un usuario hace login 
    // 2) Cuando un usuario crea una incidencia de tipo urgente 
    // 3) Cuando se consultan las incidencias destinadas a un usuario 
    public void insertarEvento(Evento e);

    // Obtener la fecha-hora del último inicio de sesión para un empleado.
    public Evento getUltimoInicioSesion(Empleado e);

    // Obtener el ranking de los empleados por cantidad de incidencias
    // urgentes creadas (más incidencias urgentes primero).
    public List<RankingTO> getRankingEmpleados();

}



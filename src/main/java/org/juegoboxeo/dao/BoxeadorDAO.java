package org.juegoboxeo.dao;

import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.Boxeador;
import org.juegoboxeo.model.Golpe;
import org.juegoboxeo.model.InformacionGolpe;

import java.util.Map;

public interface BoxeadorDAO {
    
    // Create
    int insertarBoxeador(Boxeador b);
    
    // Read
    Boxeador cargarBoxeador(int idBoxeador, int idPartida, Map <Golpe, InformacionGolpe> golpeInformacionGolpeMap) throws NoSeEncuentranRegistrosException;
    
    String nombreBoxeador(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    // Update
    void actualizarStatsBoxeador(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    //Delete
    void eliminarBoxeador(int idBoxeador);
    
}
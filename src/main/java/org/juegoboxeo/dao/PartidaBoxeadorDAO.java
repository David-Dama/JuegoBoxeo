package org.juegoboxeo.dao;

import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.PartidaBoxeador;

public interface PartidaBoxeadorDAO {
    
    //Create
    void insertarPartidaBoxeador(PartidaBoxeador pb);
    
    //Read
    PartidaBoxeador cargarPartidaBoxeador(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    boolean existeJugador(int idPartida);
    
    Integer idBoxeadorDePartida(int idPartida);
    
    int obtenerVictorias(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    int obtenerDerrotas(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    //Update
    void sumarVictorias(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    void sumarDerrotas(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    void setVictorias(int valor, int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    void setDerrotas(int valor, int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException;
    
    //Delete
    void eliminarJugador(int idPartida, int idBoxeador);
}
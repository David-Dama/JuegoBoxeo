package org.juegoboxeo.service;

import org.juegoboxeo.dao.BoxeadorDAO;
import org.juegoboxeo.dao.InformacionGolpeDAO;
import org.juegoboxeo.dao.PartidaBoxeadorDAO;
import org.juegoboxeo.dao.impl.BoxeadorDAOImpl;
import org.juegoboxeo.dao.impl.InformacionGolpeDAOImpl;
import org.juegoboxeo.dao.impl.PartidaBoxeadorDAOImpl;
import org.juegoboxeo.dto.ResultadoTurnoDTO;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.Boxeador;
import org.juegoboxeo.model.Golpe;
import org.juegoboxeo.model.InformacionGolpe;

import java.util.Random;

public class PelearService {
    
    private final BoxeadorDAO boxeadorDAO = new BoxeadorDAOImpl();
    private final InformacionGolpeDAO informacionGolpeDAO = new InformacionGolpeDAOImpl();
    private final PartidaBoxeadorDAO partidaBoxeadorDAO = new PartidaBoxeadorDAOImpl();
    
    private final Random random = new Random();
    
    private Boxeador jugador;
    private Boxeador contrincante;
    private int idPartida;
    
    public void iniciarPelea(int idJugador, int idContrincante, int idPartida) throws NoSeEncuentranRegistrosException {
        
        this.idPartida = idPartida;
        
        jugador = boxeadorDAO.cargarBoxeador(idJugador, this.idPartida, informacionGolpeDAO.obtenerGolpesPorBoxeador(idJugador, idPartida));
        
        contrincante = boxeadorDAO.cargarBoxeador(idContrincante, this.idPartida, informacionGolpeDAO.obtenerGolpesPorBoxeador(idContrincante, idPartida));
    }
    
    public ResultadoTurnoDTO turnoJugador(Golpe golpeJugador) {
        
        String resultadoJugador = usarGolpe(jugador, contrincante, golpeJugador);
        
        String resultadoIA = "";
        
        if (contrincante.estaVivo()) {
            Golpe golpeIA = elegirGolpeIA();
            resultadoIA = usarGolpe(contrincante, jugador, golpeIA);
        }
        
        return new ResultadoTurnoDTO(resultadoJugador, resultadoIA);
    }
    
    private String formatearTurno(Golpe golpe, String resultado, int dano, boolean critico, int staminaUsada) {
        return golpe + " | " + resultado + " | " + dano + " DAÑO | " + (critico ?
            "CRÍTICO" :
            "-") + " | " + "-" + staminaUsada + " STAMINA";
    }
    
    private String usarGolpe(Boxeador atacante, Boxeador defensor, Golpe golpe) {
        
        InformacionGolpe info = atacante.getGolpes().get(golpe);
        
        // SIN STAMINA
        if (atacante.getStaminaActual() < info.getCosteStamina()) {
            atacante.recuperarStaminaMitad();
            return golpe + " | TURNO PERDIDO | 0 DAÑO | - | +50% STAMINA";
        }
        
        int stamina = info.getCosteStamina();
        
        int r = random.nextInt(100);
        boolean acierto = r < info.getPrecision();
        
        if (!acierto) {
            atacante.reducirStamina(stamina);
            return formatearTurno(golpe, "FALLÓ", 0, false, stamina);
        }
        
        int danyo = info.getDanyo();
        
        boolean critico = random.nextInt(100) < info.getProbabilidadCritico();
        if (critico) {
            danyo *= 2;
        }
        
        defensor.recibirDanyo(danyo);
        atacante.reducirStamina(stamina);
        
        return formatearTurno(golpe, "ACIERTO", danyo, critico, stamina);
    }
    
    private Golpe elegirGolpeIA() {
        
        Golpe[] golpes = Golpe.values();
        
        int indice = random.nextInt(golpes.length);
        
        return golpes[indice];
    }
    
    public Boxeador getJugador() {
        return jugador;
    }
    
    public Boxeador getContrincante() {
        return contrincante;
    }
    
    public void finalizarPelea() throws NoSeEncuentranRegistrosException {
        
        if (!jugador.estaVivo()) {
            
            partidaBoxeadorDAO.sumarDerrotas(jugador.getId(), idPartida);
            partidaBoxeadorDAO.sumarVictorias(contrincante.getId(), idPartida);
            
        }
        
        if (!contrincante.estaVivo()) {
            
            partidaBoxeadorDAO.sumarVictorias(jugador.getId(), idPartida);
            partidaBoxeadorDAO.sumarDerrotas(contrincante.getId(), idPartida);
        }
    }
}
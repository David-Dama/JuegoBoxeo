package org.juegoboxeo.model;

/**
 * Representa la relación entre una partida y un boxeador.
 * Contiene información del rol del boxeador dentro de la partida
 * y sus estadísticas de combate.
 */
public class PartidaBoxeador {
    
    private int idPartida;
    private int idBoxeador;
    private Rol rol;
    private int victorias;
    private int derrotas;
    
    /**
     * Constructor vacío.
     */
    public PartidaBoxeador() {
    }
    
    /**
     * Constructor completo de PartidaBoxeador.
     *
     * @param idPartida  identificador de la partida
     * @param idBoxeador identificador del boxeador
     * @param rol        rol del boxeador en la partida (JUGADOR o IA)
     * @param victorias  número de victorias en la partida
     * @param derrotas   número de derrotas en la partida
     */
    public PartidaBoxeador(int idPartida, int idBoxeador, Rol rol, int victorias, int derrotas) {
        this.idPartida = idPartida;
        this.idBoxeador = idBoxeador;
        this.rol = rol;
        this.victorias = victorias;
        this.derrotas = derrotas;
    }
    
    /** @return id de la partida */
    public int getIdPartida() {
        return idPartida;
    }
    
    /**
     * @param idPartida establece el id de la partida
     */
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }
    
    /** @return id del boxeador */
    public int getIdBoxeador() {
        return idBoxeador;
    }
    
    /**
     * @param idBoxeador establece el id del boxeador
     */
    public void setIdBoxeador(int idBoxeador) {
        this.idBoxeador = idBoxeador;
    }
    
    /** @return rol del boxeador en la partida */
    public Rol getRol() {
        return rol;
    }
    
    /**
     * @param rol establece el rol del boxeador
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    /** @return número de victorias */
    public int getVictorias() {
        return victorias;
    }
    
    /**
     * @param victorias establece las victorias
     */
    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }
    
    /** @return número de derrotas */
    public int getDerrotas() {
        return derrotas;
    }
    
    /**
     * @param derrotas establece las derrotas
     */
    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
    
    /**
     * Representación en texto del objeto PartidaBoxeador.
     *
     * @return información completa del objeto en formato legible
     */
    @Override
    public String toString() {
        return String.format("""
            Información PartidaBoxeador:
            Id Partida: %d
            Id Boxeador: %d
            Rol: %s
            Victorias: %d
            Derrotas: %d
            """, this.idPartida, this.idBoxeador, this.rol, this.victorias, this.derrotas);
    }
}
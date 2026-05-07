package org.juegoboxeo.model;

public class InformacionGolpe {
    private int danyo;
    private int precision;
    private int probabilidadCritico;
    private int costeStamina;
    
    /**
     Constructor de un golpe con todos sus atributos.
     * @param danyo daño del golpe.
     * @param precision probabilidad de acertar el golpe.
     * @param probabilidadCritico probabilidad de hacer el doble de daño con un golpe.
     * @param costeStamina estamina que gastas por golpe.
     */
    public InformacionGolpe(int danyo, int precision, int probabilidadCritico, int costeStamina) {
        this.danyo = danyo;
        this.precision = precision;
        this.probabilidadCritico = probabilidadCritico;
        this.costeStamina = costeStamina;
    }
    
    /**
     * Obtiene el daño del golpe.
     * @return daño del golpe
     */
    public int getDanyo() {
        return danyo;
    }
    
    /**
     * Establece el daño del golpe.
     * @param danyo nuevo valor de daño
     */
    public void setDanyo(int danyo) {
        this.danyo = danyo;
    }
    
    /**
     * Obtiene la precisión del golpe.
     * @return precisión del golpe
     */
    public int getPrecision() {
        return precision;
    }
    
    /**
     * Establece la precisión del golpe.
     * @param precision nuevo valor de precisión
     */
    public void setPrecision(int precision) {
        this.precision = precision;
    }
    
    /**
     * Obtiene la probabilidad de crítico.
     * @return probabilidad de golpe crítico
     */
    public int getProbabilidadCritico() {
        return probabilidadCritico;
    }
    
    /**
     * Establece la probabilidad de crítico.
     * @param probabilidadCritico nuevo valor de probabilidad de crítico
     */
    public void setProbabilidadCritico(int probabilidadCritico) {
        this.probabilidadCritico = probabilidadCritico;
    }
    
    /**
     * Obtiene el coste de stamina del golpe.
     * @return coste de stamina
     */
    public int getCosteStamina() {
        return costeStamina;
    }
    
    /**
     * Establece el coste de stamina del golpe.
     * @param costeStamina nuevo coste de stamina
     */
    public void setCosteStamina(int costeStamina) {
        this.costeStamina = costeStamina;
    }
    
    /**
     * Devuelve una representación en texto del objeto StatsGolpe.
     * @return información formateada del golpe
     */
    @Override
    public String toString() {
        return String.format("""
            Información del golpe:
            Daño: %d
            Precisión: %d
            Probabilidad crítico: %d
            Coste de stamina: %d
            """, this.danyo, this.precision, this.probabilidadCritico, this.costeStamina);
    }
}

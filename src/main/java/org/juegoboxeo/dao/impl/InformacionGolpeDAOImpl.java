package org.juegoboxeo.dao.impl;

import org.juegoboxeo.config.DatabaseConnection;
import org.juegoboxeo.dao.InformacionGolpeDAO;
import org.juegoboxeo.model.Golpe;
import org.juegoboxeo.model.InformacionGolpe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InformacionGolpeDAOImpl implements InformacionGolpeDAO {
    
    //Read
    @Override
    public Map <Golpe, InformacionGolpe> obtenerGolpesPorBoxeador(int idBoxeador, int idPartida) {
        //Recoge la informacion de las estadisticas de los golpes de un boxeador de una partida específica
        String consulta = "SELECT bg.golpe, bg.danyo, bg.precision_golpe, bg.probabilidad_critico, bg.coste_stamina FROM informacion_golpe bg INNER JOIN partida_boxeador pb ON bg.boxeador_id = pb.id_boxeador WHERE bg.boxeador_id = ? AND pb.id_partida = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(consulta)) {
            
            //Creamos el mapa
            Map <Golpe, InformacionGolpe> informacionGolpeMap = new HashMap <>();
            
            //Inyectamos información a la query
            pstm.setInt(1, idBoxeador);
            pstm.setInt(2, idPartida);
            
            //Creamos el ResulSet con la query completa
            try (ResultSet res = pstm.executeQuery()) {
                
                while (res.next()) {
                    Golpe golpe = Golpe.valueOf(res.getString("golpe"));
                    int danyo = res.getInt("danyo");
                    int precision = res.getInt("precision_golpe");
                    int probabilidadCritico = res.getInt("probabilidad_critico");
                    int costeStamina = res.getInt("coste_stamina");
                    
                    InformacionGolpe informacionGolpe = new InformacionGolpe(danyo, precision, probabilidadCritico, costeStamina);
                    
                    informacionGolpeMap.put(golpe, informacionGolpe);
                }
            }
            
            return informacionGolpeMap;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return Collections.emptyMap();
    }
}

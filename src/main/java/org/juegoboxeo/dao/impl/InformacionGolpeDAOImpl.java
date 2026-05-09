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
    
    //Create
    @Override
    public void insertarGolpesBase(int boxeadorId) {
        String sql = "INSERT INTO informacion_golpe (boxeador_id, golpe, danyo, precision_golpe, probabilidad_critico, coste_stamina) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            
            insertarGolpe(conn, sql, boxeadorId, "JAB", 10, 80, 25, 15);
            insertarGolpe(conn, sql, boxeadorId, "CROSS", 25, 40, 30, 20);
            insertarGolpe(conn, sql, boxeadorId, "HOOK", 25, 60, 25, 25);
            insertarGolpe(conn, sql, boxeadorId, "UPPER", 30, 50, 15, 35);
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar golpes base", e);
        }
    }
    
    private void insertarGolpe(Connection conn, String sql, int boxeadorId, String golpe, int danyo, int precision, int critico, int stamina) throws SQLException {
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, boxeadorId);
            ps.setString(2, golpe);
            ps.setInt(3, danyo);
            ps.setInt(4, precision);
            ps.setInt(5, critico);
            ps.setInt(6, stamina);
            
            ps.executeUpdate();
        }
    }
    
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
    
    //Delete
    @Override
    public void eliminarPorBoxeador(int boxeadorId) {
        String sql = "DELETE FROM informacion_golpe WHERE boxeador_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, boxeadorId);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

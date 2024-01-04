package lk.ijse.rms.dao.custom;


import lk.ijse.rms.dto.TrouserDto;

import java.sql.SQLException;

public interface TrouserDAO {
    String generateNextId() throws SQLException ;
    String splitrouserId(String currentTrouser) ;
    boolean saveTroserMeasurements(TrouserDto trouserDto) throws SQLException ;
    TrouserDto searchMeasurements(String trouserM) throws SQLException ;
    boolean updateTrouser(TrouserDto trouserDto) throws SQLException ;
}

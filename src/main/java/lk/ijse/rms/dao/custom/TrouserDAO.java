package lk.ijse.rms.dao.custom;


import lk.ijse.rms.dto.TrouserDto;
import lk.ijse.rms.entity.Trouser;

import java.sql.SQLException;

public interface TrouserDAO extends CrudDAO<Trouser>{
   // String generateNextId() throws SQLException, ClassNotFoundException;
    String splitrouserId(String currentTrouser) ;
    //boolean saveTroserMeasurements(TrouserDto trouserDto) throws SQLException, ClassNotFoundException;
    //TrouserDto searchMeasurements(String trouserM) throws SQLException, ClassNotFoundException;
    //boolean updateTrouser(TrouserDto trouserDto) throws SQLException, ClassNotFoundException;
}

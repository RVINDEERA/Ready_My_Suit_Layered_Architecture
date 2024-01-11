package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dto.TrouserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TrouserBo extends SupperBO {
    public String generateNextID() throws SQLException, ClassNotFoundException ;
    public String splitrouserId(String currentTrouser) ;
    public boolean save(TrouserDto trouserDto) throws SQLException, ClassNotFoundException ;
    public TrouserDto search(String trouserM) throws SQLException, ClassNotFoundException ;
    public boolean update(TrouserDto trouserDto) throws SQLException, ClassNotFoundException;
}

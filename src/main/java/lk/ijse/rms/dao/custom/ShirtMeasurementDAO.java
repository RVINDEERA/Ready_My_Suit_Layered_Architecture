package lk.ijse.rms.dao.custom;


import lk.ijse.rms.dto.ShirtDto;


import java.sql.SQLException;

public interface ShirtMeasurementDAO {
    boolean shirtMeasurementSave(ShirtDto shirtDto) throws SQLException ;
    String generateNextShirtId() throws SQLException ;
    String splitShirtID(String currentShirtID);
    ShirtDto searchMeasurements(String shirtM) throws SQLException ;
    boolean updateShirt(ShirtDto shirtDto) throws SQLException ;
}

package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.CoatMeasurementsDto;

import java.sql.SQLException;

public interface CoatMeasurementDAO {
    String generateNextCoatId() throws SQLException;
    String splitCoatId(String string);
    boolean coatMeasurementSave(CoatMeasurementsDto coatMeasurementsDto) throws SQLException;
    CoatMeasurementsDto searchMeasurements(String coatM) throws SQLException;
    boolean updateCoat(CoatMeasurementsDto coatMeasurementsDto) throws SQLException;


    }

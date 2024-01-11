package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.CoatMeasurementsDto;

import java.sql.SQLException;

public interface CoatMeasurementDAO extends CrudDAO<CoatMeasurementsDto>{
    String splitCoatId(String string);
    }

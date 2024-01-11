package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.CoatMeasurementsDto;
import lk.ijse.rms.entity.CoatMeasurements;

import java.sql.SQLException;

public interface CoatMeasurementDAO extends CrudDAO<CoatMeasurements>{
    String splitCoatId(String string);
    }

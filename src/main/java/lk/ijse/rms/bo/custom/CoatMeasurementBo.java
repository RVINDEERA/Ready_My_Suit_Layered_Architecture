package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dto.CoatMeasurementsDto;

import java.sql.SQLException;

public interface CoatMeasurementBo extends SupperBO {
        public String generateNextID() throws SQLException, ClassNotFoundException ;
        public CoatMeasurementsDto search(String coatM) throws SQLException, ClassNotFoundException;
        public boolean save(CoatMeasurementsDto coatMeasurementsDto) throws SQLException, ClassNotFoundException;
        public boolean update(CoatMeasurementsDto coatMeasurementsDto) throws SQLException, ClassNotFoundException;
}

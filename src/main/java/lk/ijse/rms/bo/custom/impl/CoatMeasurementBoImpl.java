package lk.ijse.rms.bo.custom.impl;

import lk.ijse.rms.bo.custom.CoatMeasurementBo;
import lk.ijse.rms.dao.DAOFactory;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.CoatDAO;
import lk.ijse.rms.dao.custom.CoatMeasurementDAO;
import lk.ijse.rms.dto.CoatMeasurementsDto;
import lk.ijse.rms.entity.CoatMeasurements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CoatMeasurementBoImpl implements CoatMeasurementBo {

    CoatMeasurementDAO coatMeasurementDAO = (CoatMeasurementDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COATMEASUREMENT);
    public String generateNextID() throws SQLException, ClassNotFoundException {
       return coatMeasurementDAO.generateNextID();
    }

    public boolean save(CoatMeasurementsDto coatMeasurementsDto) throws SQLException, ClassNotFoundException {

        return coatMeasurementDAO.save(new CoatMeasurements(coatMeasurementsDto.getCmId(),coatMeasurementsDto.getCustomerId(),coatMeasurementsDto.getDate(),coatMeasurementsDto.getLength(),coatMeasurementsDto.getChest(),coatMeasurementsDto.getShoulder(),coatMeasurementsDto.getSleeveLength(),coatMeasurementsDto.getCollar(),coatMeasurementsDto.getWaist(),coatMeasurementsDto.getNeck(),coatMeasurementsDto.getElbow()));
    }

    public CoatMeasurementsDto search(String coatM) throws SQLException, ClassNotFoundException {
        CoatMeasurements coatMeasurements = coatMeasurementDAO.search(coatM);
        if (coatMeasurements != null) {
            return new CoatMeasurementsDto(coatMeasurements.getCmId(),coatMeasurements.getCustomerId(),coatMeasurements.getDate(),coatMeasurements.getLength(),coatMeasurements.getChest(),coatMeasurements.getShoulder(),coatMeasurements.getSleeveLength(),coatMeasurements.getCollar(),coatMeasurements.getWaist(),coatMeasurements.getNeck(),coatMeasurements.getElbow());
        } else {
            return null;
        }
    }

    public boolean update(CoatMeasurementsDto coatMeasurementsDto) throws SQLException, ClassNotFoundException {
        return coatMeasurementDAO.update(new CoatMeasurements(coatMeasurementsDto.getCmId(),coatMeasurementsDto.getCustomerId(),coatMeasurementsDto.getDate(),coatMeasurementsDto.getLength(),coatMeasurementsDto.getChest(),coatMeasurementsDto.getShoulder(),coatMeasurementsDto.getSleeveLength(),coatMeasurementsDto.getCollar(),coatMeasurementsDto.getWaist(),coatMeasurementsDto.getNeck(),coatMeasurementsDto.getElbow()));
    }

}
